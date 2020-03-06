package pl.kazet.stockmarket.entity.tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "stockprice")
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idstockprice")
    private Integer idStockPrice;

    @ManyToOne
    @JoinColumn(name = "ISIN")
    private Stock stock;

    @Column(name = "buyout")
    private BigDecimal buyout;

    @Column(name = "sellout")
    private BigDecimal sellout;

    @Column(name = "date")
    private LocalDateTime date;
}
