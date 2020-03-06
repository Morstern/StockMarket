package pl.kazet.stockmarket.projections.stockprice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface StockPrice_PricesDatesProjection {
    BigDecimal getBuyout();

    BigDecimal getSellout();

    LocalDateTime getDate();
}
