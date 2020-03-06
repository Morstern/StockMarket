package pl.kazet.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kazet.stockmarket.entity.tables.StockSubscription;
import pl.kazet.stockmarket.entity.tables.StockSubscriptionCompositeKey;
import pl.kazet.stockmarket.projections.stocksubscription.StockSubscriptionWithoutUserProjection;

import java.util.List;

public interface StockSubscriptionRepository extends JpaRepository<StockSubscription, StockSubscriptionCompositeKey> {
    List<StockSubscriptionWithoutUserProjection> findAllByUserIdUser(Long idUser);
}
