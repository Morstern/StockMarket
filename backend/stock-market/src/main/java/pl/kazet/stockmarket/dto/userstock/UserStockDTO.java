package pl.kazet.stockmarket.dto.userstock;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.entity.tables.User;
import pl.kazet.stockmarket.entity.tables.UserStock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class UserStockDTO {
    private Long idStockUser;
    private Stock stock;
    private User user;
    private Long amount;
    private BigDecimal price;
    private LocalDateTime date;

    public UserStockDTO(UserStock userStock) {
        this.idStockUser = userStock.getIdStockUser();
        this.stock = userStock.getStock();
        this.user = userStock.getUser();
        this.amount = userStock.getAmount();
        this.price = userStock.getPrice();
        this.date = userStock.getDate();
    }

    public UserStock toEntity() {
        return new UserStock(idStockUser, stock, user, amount, price, date);
    }
}
