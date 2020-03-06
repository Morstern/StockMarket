package pl.kazet.stockmarket.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazet.stockmarket.entity.tables.Stock;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class StockDTO {
    private String ISIN;
    private String name;

    public StockDTO(Stock stock) {
        this.ISIN = stock.getISIN();
        this.name = stock.getName();
    }

    public Stock toEntity() {
        return new Stock(ISIN, name);
    }
}
