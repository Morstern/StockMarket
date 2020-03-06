package pl.kazet.stockmarket.dto.databaseview;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kazet.stockmarket.entity.views.Top10RecordsEachISIN;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Top10RecordsEachISIN_BuyoutSelloutDateDTO {
    private String stockName;
    private BigDecimal buyout;
    private BigDecimal sellout;
    private LocalDateTime date;

    public Top10RecordsEachISIN_BuyoutSelloutDateDTO(Top10RecordsEachISIN top10RecordsEachISINView) {
        this.stockName = top10RecordsEachISINView.getStockName();
        this.buyout = top10RecordsEachISINView.getBuyout();
        this.sellout = top10RecordsEachISINView.getSellout();
        this.date = top10RecordsEachISINView.getDate();
    }
}
