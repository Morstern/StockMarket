package pl.kazet.stockmarket.dto.stocksubscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.entity.tables.StockSubscription;
import pl.kazet.stockmarket.entity.tables.User;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class StockSubscriptionDTO {
    private Stock stock;
    private User user;

    public StockSubscriptionDTO(StockSubscription stockSubscription) {
        this.stock = stockSubscription.getStock();
        this.user = stockSubscription.getUser();
    }

    public StockSubscription toEntity() {
        return new StockSubscription(stock, user);
    }
}