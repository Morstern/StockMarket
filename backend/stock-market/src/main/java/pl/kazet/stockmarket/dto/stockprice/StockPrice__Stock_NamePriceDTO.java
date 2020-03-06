package pl.kazet.stockmarket.dto.stockprice;

import lombok.Getter;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.entity.tables.StockPrice;

import java.math.BigDecimal;

@Getter
public class StockPrice__Stock_NamePriceDTO {
    private String stockISIN;
    private BigDecimal currentSelloutPrice;
    private BigDecimal currentBuyoutPrice;


    public StockPrice__Stock_NamePriceDTO(Stock stock, StockPrice stockPrice) {
        this.stockISIN = stock.getISIN();
        this.currentSelloutPrice = stockPrice.getSellout();
        this.currentBuyoutPrice = stockPrice.getBuyout();
    }

    public StockPrice__Stock_NamePriceDTO(String name, BigDecimal currentSelloutPrice, BigDecimal currentBuyoutPrice) {
        this.stockISIN = name;
        this.currentSelloutPrice = currentSelloutPrice;
        this.currentBuyoutPrice = currentBuyoutPrice;

    }
}
