package pl.kazet.stockmarket.projections.stocksubscription;

import pl.kazet.stockmarket.entity.tables.Stock;

public interface StockSubscriptionWithoutUserProjection {
    Stock getStock();
}
