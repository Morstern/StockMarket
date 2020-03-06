package pl.kazet.stockmarket.entity.tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "stocksubscription")
@IdClass(StockSubscriptionCompositeKey.class)
public class StockSubscription implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "ISIN")
    private Stock stock;

    @Id
    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;
}
