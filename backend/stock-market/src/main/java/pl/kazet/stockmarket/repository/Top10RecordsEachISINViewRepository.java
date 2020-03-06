package pl.kazet.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kazet.stockmarket.entity.views.Top10RecordsEachISIN;

public interface Top10RecordsEachISINViewRepository extends JpaRepository<Top10RecordsEachISIN, Long> {
}
