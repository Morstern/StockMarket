package pl.kazet.stockmarket.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.kazet.stockmarket.dto.stockprice.StockPriceDTO;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.repository.StockPriceRepository;
import pl.kazet.stockmarket.repository.StockRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockFetcher {

    private StockRepository stockRepository;
    private StockPriceRepository stockPriceRepository;

    @Autowired
    public StockFetcher(StockRepository stockRepository, StockPriceRepository stockPriceRepository) {
        this.stockRepository = stockRepository;
        this.stockPriceRepository = stockPriceRepository;
    }


    @Scheduled(cron = "0 0 10,11,12,13,14,15 * * MON-FRI")
    public void fetchStockData() {
        List<Stock> stockList = stockRepository.findAll();
        String concatISIN = getConcatISIN(stockList);

        Process p;
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "script.py", concatISIN);
            pb.directory(getCurrentDirectory());
            p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String text = in.readLine();
            String preprocessedText = preprocessData(text);
            List<String> splitKeyValuesList = getAllSplitKeyValues(preprocessedText);

            StockPriceDTO stockPriceDTO;
            for (String splitKeyValue : splitKeyValuesList) {
                List<String> ISINBuyoutSellout = getISINBuyoutSellout(splitKeyValue);

                Stock stock = getStockEntity(ISINBuyoutSellout.get(0));
                BigDecimal buyout = getBuyoutValue((ISINBuyoutSellout.get(1)));
                BigDecimal sellout = getSelloutValue((ISINBuyoutSellout.get(2)));

                stockPriceDTO = new StockPriceDTO(stock, buyout, sellout);
                stockPriceRepository.save(stockPriceDTO.toEntity());
            }
            p.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getConcatISIN(List<Stock> stockList) {
        return stockList.stream().map(stock -> stock.getISIN()).collect(Collectors.joining(","));
    }

    private File getCurrentDirectory() throws IOException {
        return new File(new File(".").getCanonicalPath() + "\\src\\main\\java\\pl\\kazet\\stockmarket\\datafetcher");
    }

    private String preprocessData(String data) {
        return data.replaceAll("[{} ',]", "");
    }

    private List<String> getAllSplitKeyValues(String text) {
        return Arrays.asList(text.split(";"));
    }

    private List<String> getISINBuyoutSellout(String keyValue) {
        List<String> splitKeyValue = Arrays.asList(keyValue.split(":"));
        List<String> ISINBuyoutSelloutList = new ArrayList<>(Arrays.asList(splitKeyValue.get(0), splitKeyValue.get(1), splitKeyValue.get(2)));
        return ISINBuyoutSelloutList;
    }

    private Stock getStockEntity(String ISIN) {
        return stockRepository.findById(ISIN).get();
    }

    private BigDecimal getBuyoutValue(String buyout) {
        return new BigDecimal(Double.parseDouble(buyout));
    }

    private BigDecimal getSelloutValue(String sellout) {
        return new BigDecimal(Double.parseDouble(sellout));
    }


}
