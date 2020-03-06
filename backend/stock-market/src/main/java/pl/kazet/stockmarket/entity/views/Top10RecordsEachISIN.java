package pl.kazet.stockmarket.entity.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name="top_10_records_each_isin")
public class Top10RecordsEachISIN {
    @Id
    @Column(name = "idstockprice")
    private Long idStockPrice;

    @Column (name = "name")
    private String stockName;

    @Column (name = "buyout")
    private BigDecimal buyout;

    @Column (name= "sellout")
    private BigDecimal sellout;

    @Column (name = "date")
    private LocalDateTime date;
}
