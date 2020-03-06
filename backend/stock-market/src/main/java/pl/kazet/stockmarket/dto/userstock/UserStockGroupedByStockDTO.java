package pl.kazet.stockmarket.dto.userstock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kazet.stockmarket.dto.stockprice.StockPrice__Stock_NamePriceDTO;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserStockGroupedByStockDTO {
    private StockPrice__Stock_NamePriceDTO stockData;
    private List<UserStock_NamePriceAmountDateDTO> allStocksPossessedByUser;
    private BigDecimal totalNetMarginProfitValue;
    private BigDecimal totalNetMarginProfitPercentage;
}
