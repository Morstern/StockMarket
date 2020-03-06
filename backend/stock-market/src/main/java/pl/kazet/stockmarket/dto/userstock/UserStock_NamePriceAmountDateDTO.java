package pl.kazet.stockmarket.dto.userstock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazet.stockmarket.entity.tables.UserStock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserStock_NamePriceAmountDateDTO {
    private String stockName;
    private BigDecimal boughtPrice;
    private Long boughtAmount;
    private LocalDateTime boughtDate;
    @Setter
    private BigDecimal netProfitMarginValue;
    @Setter
    private BigDecimal netProfitMarginPercentageValue;

    public UserStock_NamePriceAmountDateDTO(UserStock userStock) {
        this.stockName = userStock.getStock().getName();
        this.boughtPrice = userStock.getPrice();
        this.boughtAmount = userStock.getAmount();
        this.boughtDate = userStock.getDate();
        this.netProfitMarginValue = null;
    }

    public UserStock_NamePriceAmountDateDTO(String stockName, BigDecimal boughtPrice, Long boughtAmount, LocalDateTime boughtDate) {
        this.stockName = stockName;
        this.boughtPrice = boughtPrice;
        this.boughtAmount = boughtAmount;
        this.boughtDate = boughtDate;
        this.netProfitMarginValue = null;
        this.netProfitMarginPercentageValue = null;
    }
}
