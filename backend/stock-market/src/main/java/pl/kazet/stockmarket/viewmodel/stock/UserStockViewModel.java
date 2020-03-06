package pl.kazet.stockmarket.viewmodel.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.kazet.stockmarket.dto.userstock.UserStockGroupedByStockDTO;

import java.util.Map;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserStockViewModel {
    private Map<String, UserStockGroupedByStockDTO> userStocks;
}
