package pl.kazet.stockmarket.projections.stockprice;

import pl.kazet.stockmarket.projections.stock.StockNameProjection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StockPriceDefaultProjection {
    StockNameProjection getStock();

    BigDecimal getBuyout();

    BigDecimal getSellout();

    LocalDateTime getDate();
}
