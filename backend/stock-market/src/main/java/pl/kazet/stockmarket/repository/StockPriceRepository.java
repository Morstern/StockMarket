package pl.kazet.stockmarket.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kazet.stockmarket.entity.tables.StockPrice;
import pl.kazet.stockmarket.projections.stockprice.StockPrice_PricesDatesProjection;

import java.util.List;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Integer> {
    List<StockPrice_PricesDatesProjection> findAllByStockISINOrderByDateDesc(String ISIN, Pageable pageable);
    List<StockPrice_PricesDatesProjection> findAllByStockNameOrderByDateDesc(String Name, Pageable pageable);
    StockPrice findFirstByStockNameOrderByDateDesc(String ISIN);
}
