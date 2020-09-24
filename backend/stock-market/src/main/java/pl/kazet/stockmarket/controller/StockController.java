package pl.kazet.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kazet.stockmarket.entity.tables.Stock;
import pl.kazet.stockmarket.repository.StockRepository;
import pl.kazet.stockmarket.utils.UserUtils;

import java.util.Optional;

@RestController
@RequestMapping("/stock")
public class StockController {

    private StockRepository stockRepository;

    @Autowired
    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping(value = "/{isin}")
    public ResponseEntity<?> getStockByISIN(@PathVariable("isin") String ISIN) {
        Optional<Stock> stock = stockRepository.findById(ISIN);
        if (stock.isPresent()) {
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> getStockByName(@PathVariable("name") String name) {
        Optional<Stock> stock = stockRepository.findStockByName(name);
        if (stock.isPresent()) {
            return new ResponseEntity<>(stock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createStock(@RequestBody Stock stock) {
        if (UserUtils.hasCurrentLoggedUserAdminRole()) {
            try {
                Stock createdStock = stockRepository.save(stock);
                return new ResponseEntity<>(createdStock, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/{isin}")
    public ResponseEntity<?> deleteStock(@PathVariable String isin) {
        if (UserUtils.hasCurrentLoggedUserAdminRole()) {
            try {
                Optional<Stock> foundStock = stockRepository.findById(isin);
                if (foundStock.isPresent()) {
                    stockRepository.delete(foundStock.get());
                    return new ResponseEntity<>(HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(value = "/")
    public ResponseEntity<?> updateStock(@RequestBody Stock stock){
        if (UserUtils.hasCurrentLoggedUserAdminRole()) {
            try {
                Optional<Stock> foundStock = stockRepository.findById(stock.getISIN());
                if(foundStock.isPresent()){
                    Stock updated = stockRepository.save(stock);
                    return new ResponseEntity<>(updated,HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
