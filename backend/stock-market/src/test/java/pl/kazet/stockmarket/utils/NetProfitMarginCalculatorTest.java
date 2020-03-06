package pl.kazet.stockmarket.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pl.kazet.stockmarket.dto.stockprice.StockPrice__Stock_NamePriceDTO;
import pl.kazet.stockmarket.dto.userstock.UserStock_NamePriceAmountDateDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class NetProfitMarginCalculatorTest {
    static final int FIRST_ELEMENT = 0;
    static final int SECOND_ELEMENT = 1;
    static final String STOCK_NAME = "TEST";

    @Test
    public void calculateNetProfitMarginForGroup_OneStock() {
        //given
        List<UserStock_NamePriceAmountDateDTO> userStock_priceAmountDateDTOList = Arrays.asList(
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME,BigDecimal.valueOf(1000), Long.valueOf(10), null)
        );
        StockPrice__Stock_NamePriceDTO stockPrice_priceDTO = new StockPrice__Stock_NamePriceDTO(STOCK_NAME, BigDecimal.valueOf(1500), BigDecimal.valueOf(1200));
        NetProfitMarginCalculator netProfitMarginCalculator = new NetProfitMarginCalculator(userStock_priceAmountDateDTOList,stockPrice_priceDTO);
        //when
        BigDecimal netProfitMargin = netProfitMarginCalculator.calculateNetProfitMarginValueForGroup();
        //then
        Assertions.assertEquals(BigDecimal.valueOf(5000.0), netProfitMargin);
    }

    @Test
    public void calculateNetProfitMarginForGroup_MultipleStocks() {
        //given
        List<UserStock_NamePriceAmountDateDTO> userStock_priceAmountDateDTOList = Arrays.asList(
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME, BigDecimal.valueOf(449.5), Long.valueOf(10), null),
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME, BigDecimal.valueOf(448), Long.valueOf(25), null)
        );
        StockPrice__Stock_NamePriceDTO stockPrice_priceDTO = new StockPrice__Stock_NamePriceDTO( STOCK_NAME,BigDecimal.valueOf(460), BigDecimal.valueOf(1200));
        NetProfitMarginCalculator netProfitMarginCalculator = new NetProfitMarginCalculator(userStock_priceAmountDateDTOList,stockPrice_priceDTO);
        //when
        BigDecimal netProfitMargin = netProfitMarginCalculator.calculateNetProfitMarginValueForGroup();
        //then
        Assertions.assertEquals(BigDecimal.valueOf(405.0), netProfitMargin);
    }

    @Test
    public void calculateNetProfitPercentageForGroup(){
        // given
        List<UserStock_NamePriceAmountDateDTO> userStock_priceAmountDateDTOList = Arrays.asList(
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME, BigDecimal.valueOf(1000), Long.valueOf(10), null)
        );
        StockPrice__Stock_NamePriceDTO stockPrice_priceDTO = new StockPrice__Stock_NamePriceDTO(STOCK_NAME, BigDecimal.valueOf(1500), BigDecimal.valueOf(1100));
        NetProfitMarginCalculator netProfitMarginCalculator = new NetProfitMarginCalculator(userStock_priceAmountDateDTOList,stockPrice_priceDTO);
        // when
        BigDecimal netProfitPercentage = netProfitMarginCalculator.calculateNetProfitPercentageValueForGroup();
        // then
        Assertions.assertEquals(BigDecimal.valueOf(0.33333), netProfitPercentage);
    }


    @Test
    void calculateNetProfitMarginForOneStock() {
        //given

        List<UserStock_NamePriceAmountDateDTO> userStock_priceAmountDateDTOList = Arrays.asList(
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME,BigDecimal.valueOf(1000), Long.valueOf(10), null),
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME,BigDecimal.valueOf(1200), Long.valueOf(10), null)
        );
        StockPrice__Stock_NamePriceDTO stockPrice_priceDTO = new StockPrice__Stock_NamePriceDTO(STOCK_NAME, BigDecimal.valueOf(1500), BigDecimal.valueOf(1200));
        NetProfitMarginCalculator netProfitMarginCalculator = new NetProfitMarginCalculator(userStock_priceAmountDateDTOList,stockPrice_priceDTO);
        //when
        BigDecimal netProfitMarginForFirst = netProfitMarginCalculator.calculateNetProfitMarginValueForOneStock(FIRST_ELEMENT);
        BigDecimal netProfitMarginForSecond = netProfitMarginCalculator.calculateNetProfitMarginValueForOneStock(SECOND_ELEMENT);
        //then
        Assertions.assertEquals(BigDecimal.valueOf(5000.0), netProfitMarginForFirst);
        Assertions.assertEquals(BigDecimal.valueOf(3000.0), netProfitMarginForSecond);
    }

    @Test
    void calculateNetProfitPercentageForOneStock() {
        // given
        List<UserStock_NamePriceAmountDateDTO> userStock_priceAmountDateDTOList = Arrays.asList(
                new UserStock_NamePriceAmountDateDTO(STOCK_NAME, BigDecimal.valueOf(1000), Long.valueOf(10), null)
        );
        StockPrice__Stock_NamePriceDTO stockPrice_priceDTO = new StockPrice__Stock_NamePriceDTO(STOCK_NAME, BigDecimal.valueOf(1500), BigDecimal.valueOf(1100));
        NetProfitMarginCalculator netProfitMarginCalculator = new NetProfitMarginCalculator(userStock_priceAmountDateDTOList,stockPrice_priceDTO);
        // when
        BigDecimal netProfitPercentage = netProfitMarginCalculator.calculateNetProfitPercentageValueForOneStock(FIRST_ELEMENT);
        // then
        Assertions.assertEquals(BigDecimal.valueOf(0.33333), netProfitPercentage);
    }
}