package pl.kazet.stockmarket.dto.stockprice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.entity.tables.StockPrice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class StockPriceDTO {

    private Integer idStockPrice;
    private Stock stock;
    private BigDecimal buyout;
    private BigDecimal sellout;
    private LocalDateTime date;


    public StockPriceDTO(StockPrice stockPrice) {
        this.idStockPrice = stockPrice.getIdStockPrice();
        this.stock = stockPrice.getStock();
        this.buyout = stockPrice.getBuyout();
        this.sellout = stockPrice.getSellout();
        this.date = stockPrice.getDate();
    }

    public StockPriceDTO(Stock stock, BigDecimal buyout, BigDecimal sellout) {
        this.stock = stock;
        this.buyout = buyout;
        this.sellout = sellout;
        this.date = LocalDateTime.now();
    }

    public StockPrice toEntity() {
        return new StockPrice(idStockPrice, stock, buyout, sellout, date);
    }
}
