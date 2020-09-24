package pl.kazet.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kazet.stockmarket.dto.stockprice.StockPrice__Stock_NamePriceDTO;
import pl.kazet.stockmarket.dto.userstock.UserStockGroupedByStockDTO;
import pl.kazet.stockmarket.dto.userstock.UserStock_NamePriceAmountDateDTO;
import pl.kazet.stockmarket.entity.tables.User;
import pl.kazet.stockmarket.entity.tables.UserStock;
import pl.kazet.stockmarket.repository.StockPriceRepository;
import pl.kazet.stockmarket.repository.StockRepository;
import pl.kazet.stockmarket.repository.UserStockRepository;
import pl.kazet.stockmarket.utils.NetProfitMarginCalculator;
import pl.kazet.stockmarket.utils.UserUtils;
import pl.kazet.stockmarket.viewmodel.stock.UserStockViewModel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/userstock")
public class UserStockController {

    UserStockRepository userStockRepository;
    StockPriceRepository stockPriceRepository;
    StockRepository stockRepository;

    @Autowired
    public UserStockController(UserStockRepository userStockRepository, StockPriceRepository stockPriceRepository, StockRepository stockRepository) {
        this.userStockRepository = userStockRepository;
        this.stockPriceRepository = stockPriceRepository;
        this.stockRepository = stockRepository;
    }

    @GetMapping(value = "/summary")
    public ResponseEntity<UserStockViewModel> getSummaryForUser() {
        Map<String, UserStockGroupedByStockDTO> resultUserStocks = new HashMap<>();

        User currentUser = UserUtils.getCurrentUser();
        List<UserStock> allStocksByUser = userStockRepository.findAllByUserIdUser(currentUser.getIdUser());
        Map<String, List<UserStock_NamePriceAmountDateDTO>> groupedStocksByStockName = groupUserStockByStockName(allStocksByUser);

        for (Map.Entry<String, List<UserStock_NamePriceAmountDateDTO>> entry : groupedStocksByStockName.entrySet()) {
            String stockName = entry.getKey();
            List<UserStock_NamePriceAmountDateDTO> groupedUserStock = entry.getValue();

            StockPrice__Stock_NamePriceDTO currentStockPrice = new StockPrice__Stock_NamePriceDTO(stockRepository.findStockByName(stockName).get(), stockPriceRepository.findFirstByStockNameOrderByDateDesc(stockName));
            NetProfitMarginCalculator netProfitMarginCalculator = new NetProfitMarginCalculator(groupedUserStock, currentStockPrice);

            for (int i = 0; i < groupedUserStock.size(); i++) {
                groupedUserStock.get(i).setNetProfitMarginValue(netProfitMarginCalculator.calculateNetProfitMarginValueForOneStock(i));
                groupedUserStock.get(i).setNetProfitMarginPercentageValue(netProfitMarginCalculator.calculateNetProfitPercentageValueForOneStock(i));
            }
            resultUserStocks.put(stockName, new UserStockGroupedByStockDTO(currentStockPrice, groupedUserStock, netProfitMarginCalculator.calculateNetProfitMarginValueForGroup(), netProfitMarginCalculator.calculateNetProfitPercentageValueForGroup()));
        }

        return new ResponseEntity<>(new UserStockViewModel(resultUserStocks), HttpStatus.OK);
    }

    private Map<String, List<UserStock_NamePriceAmountDateDTO>> groupUserStockByStockName(List<UserStock> allStocksByUser) {
        return allStocksByUser.stream().map(userStock -> new UserStock_NamePriceAmountDateDTO(userStock)).collect(Collectors.groupingBy(UserStock_NamePriceAmountDateDTO::getStockName));
    }

}
