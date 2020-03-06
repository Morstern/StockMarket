package pl.kazet.stockmarket.entity.tables;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class StockSubscriptionCompositeKey implements Serializable {
    private String stock;
    private Long user;
}
