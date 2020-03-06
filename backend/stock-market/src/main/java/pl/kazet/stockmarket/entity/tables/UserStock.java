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
@Table(name = "userstock")
public class UserStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduserstock")
    private Long idStockUser;

    @ManyToOne
    @JoinColumn(name = "ISIN")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    private LocalDateTime date;
}
