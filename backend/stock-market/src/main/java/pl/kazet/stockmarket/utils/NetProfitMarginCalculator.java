package pl.kazet.stockmarket.utils;

import pl.kazet.stockmarket.dto.stockprice.StockPrice__Stock_NamePriceDTO;
import pl.kazet.stockmarket.dto.userstock.UserStock_NamePriceAmountDateDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

// REFACTORED:
// IT USED TO BE STATIC CLASS NetProfitMarginCalculator.calculateNetProfitMarginForOneStock(groupedUserStocks, currentStockPrice)
// SINCE MY FRIEND SAID IT'S A BAD PRACTICE TO CREATE FULLY STATIC CLASSES I REFACTORED IT INTO NORMAL CLASS

public class NetProfitMarginCalculator {
    private static final Integer SCALE = 5;

    private List<UserStock_NamePriceAmountDateDTO> groupedUserStocks;
    private StockPrice__Stock_NamePriceDTO currentStockPrice;
    private Long amountOfBoughtStocks;


    public NetProfitMarginCalculator(List<UserStock_NamePriceAmountDateDTO> groupedUserStocks, StockPrice__Stock_NamePriceDTO currentStockPrice) {
        this.groupedUserStocks = groupedUserStocks;
        this.currentStockPrice = currentStockPrice;
        this.amountOfBoughtStocks = getAmountOfBoughtStocks();
    }

    public BigDecimal calculateNetProfitMarginValueForGroup() {
        BigDecimal totalCurrentSelloutValue = getTotalCurrentSelloutValue();
        BigDecimal totalValueOfBoughtStocks = getSumProductOfBoughtStocks();
        return totalCurrentSelloutValue.subtract(totalValueOfBoughtStocks);
    }

    public BigDecimal calculateNetProfitMarginValueForOneStock(Integer i) {
        UserStock_NamePriceAmountDateDTO userStock = groupedUserStocks.get(i);
        BigDecimal totalCurrentSelloutValue = BigDecimal.valueOf(userStock.getBoughtAmount() * currentStockPrice.getCurrentSelloutPrice().doubleValue());
        BigDecimal totalValueOfBoughtStocks = BigDecimal.valueOf(userStock.getBoughtAmount()*userStock.getBoughtPrice().doubleValue());
        return totalCurrentSelloutValue.subtract(totalValueOfBoughtStocks);
    }

    public BigDecimal calculateNetProfitPercentageValueForGroup() {
        BigDecimal totalCurrentSelloutValue = getTotalCurrentSelloutValue();
        BigDecimal totalValueOfBoughtStocks = getSumProductOfBoughtStocks();
        return totalCurrentSelloutValue.subtract(totalValueOfBoughtStocks).divide(totalCurrentSelloutValue, SCALE, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateNetProfitPercentageValueForOneStock(Integer i) {
        UserStock_NamePriceAmountDateDTO userStock = groupedUserStocks.get(i);
        BigDecimal totalCurrentSelloutValue = BigDecimal.valueOf(userStock.getBoughtAmount() * currentStockPrice.getCurrentSelloutPrice().doubleValue());
        BigDecimal totalValueOfBoughtStocks = BigDecimal.valueOf(userStock.getBoughtAmount()*userStock.getBoughtPrice().doubleValue());
        return totalCurrentSelloutValue.subtract(totalValueOfBoughtStocks).divide(totalCurrentSelloutValue, SCALE, RoundingMode.HALF_UP);
    }

    private Long getAmountOfBoughtStocks(){
        return groupedUserStocks.stream().mapToLong(stock ->stock.getBoughtAmount()).sum();
    }

    private  BigDecimal getTotalCurrentSelloutValue(){
        return BigDecimal.valueOf(amountOfBoughtStocks * currentStockPrice.getCurrentSelloutPrice().doubleValue());
    }

    private BigDecimal getSumProductOfBoughtStocks(){
        return BigDecimal.valueOf(groupedUserStocks.stream().mapToDouble(stock -> stock.getBoughtAmount() * stock.getBoughtPrice().doubleValue()).sum());
    }

}
