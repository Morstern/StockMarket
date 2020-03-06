package pl.kazet.stockmarket.entity.tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name = "ISIN")
    private String ISIN;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Stock{" +
                "ISIN='" + ISIN + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
