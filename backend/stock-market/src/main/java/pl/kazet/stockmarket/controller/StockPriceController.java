package pl.kazet.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kazet.stockmarket.dto.databaseview.Top10RecordsEachISIN_BuyoutSelloutDateDTO;
import pl.kazet.stockmarket.projections.stockprice.StockPrice_PricesDatesProjection;
import pl.kazet.stockmarket.repository.StockPriceRepository;
import pl.kazet.stockmarket.repository.Top10RecordsEachISINViewRepository;
import pl.kazet.stockmarket.utils.Validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stockprice")
public class StockPriceController {
    private StockPriceRepository stockPriceRepository;
    private Top10RecordsEachISINViewRepository top10RecordsEachISINViewRepository;

    @Autowired
    public StockPriceController(StockPriceRepository stockPriceRepository, Top10RecordsEachISINViewRepository top10RecordsEachISINViewRepository) {
        this.stockPriceRepository = stockPriceRepository;
        this.top10RecordsEachISINViewRepository = top10RecordsEachISINViewRepository;
    }

    @GetMapping(value = "/{isin}")
    public ResponseEntity<List<StockPrice_PricesDatesProjection>> getStockByISIN(@PathVariable("isin") String ISIN, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (Validator.containParameters(page, size) == true) {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(stockPriceRepository.findAllByStockISINOrderByDateDesc(ISIN, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(stockPriceRepository.findAllByStockISINOrderByDateDesc(ISIN, null), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<List<StockPrice_PricesDatesProjection>> getStockByName(@PathVariable("name") String name, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (Validator.containParameters(page, size) == true) {
            Pageable pageable = PageRequest.of(page, size);
            return new ResponseEntity<>(stockPriceRepository.findAllByStockNameOrderByDateDesc(name, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(stockPriceRepository.findAllByStockNameOrderByDateDesc(name, null), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<Map<String, List<Top10RecordsEachISIN_BuyoutSelloutDateDTO>>> getSummaryView(){
        return new ResponseEntity<>(top10RecordsEachISINViewRepository.findAll().stream().map(top10RecordsEachISIN -> new Top10RecordsEachISIN_BuyoutSelloutDateDTO(top10RecordsEachISIN)).collect(Collectors.groupingBy(Top10RecordsEachISIN_BuyoutSelloutDateDTO::getStockName)), HttpStatus.OK);
    }

}
