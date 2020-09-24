package pl.kazet.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.entity.tables.StockSubscription;
import pl.kazet.stockmarket.entity.tables.User;
import pl.kazet.stockmarket.projections.stocksubscription.StockSubscriptionWithoutUserProjection;
import pl.kazet.stockmarket.repository.StockRepository;
import pl.kazet.stockmarket.repository.StockSubscriptionRepository;
import pl.kazet.stockmarket.utils.UserUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocksubscription")
public class StockSubscriptionController {

    private StockSubscriptionRepository stockSubscriptionRepository;
    private StockRepository stockRepository;

    @Autowired
    public StockSubscriptionController(StockSubscriptionRepository stockSubscriptionRepository, StockRepository stockRepository) {
        this.stockSubscriptionRepository = stockSubscriptionRepository;
        this.stockRepository = stockRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<StockSubscriptionWithoutUserProjection>> getAllStockSubscriptionForUser() {
        User currentUser = UserUtils.getCurrentUser();
        return new ResponseEntity<>(stockSubscriptionRepository.findAllByUserIdUser(currentUser.getIdUser()), HttpStatus.OK);
    }

    @PostMapping("/{ISIN}")
    public ResponseEntity<?> subscribeToPost(@PathVariable("ISIN") String ISIN) {
        User currentUser = UserUtils.getCurrentUser();
        Optional<Stock> stock = stockRepository.findById(ISIN);
        if (stock.isPresent()) {
            StockSubscription stockSubscription = new StockSubscription(stock.get(), currentUser);
            stockSubscriptionRepository.save(stockSubscription);
            return new ResponseEntity(stockSubscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{ISIN}")
    public ResponseEntity<?> deleteSubscription(@PathVariable("ISIN") String ISIN) {
        User currentUser = UserUtils.getCurrentUser();
        Optional<Stock> stock = stockRepository.findById(ISIN);
        if (stock.isPresent()) {
            StockSubscription stockSubscription = new StockSubscription(stock.get(), currentUser);
            stockSubscriptionRepository.delete(stockSubscription);
            return new ResponseEntity(stockSubscription, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
