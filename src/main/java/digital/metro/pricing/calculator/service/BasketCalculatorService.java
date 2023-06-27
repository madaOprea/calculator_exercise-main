package digital.metro.pricing.calculator.service;

import digital.metro.pricing.calculator.model.*;
import digital.metro.pricing.calculator.repository.PriceRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Log4j2
@Service
public class BasketCalculatorService {

    private final PriceRepository priceRepository;

    @Autowired
    public BasketCalculatorService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public BasketCalculationResult calculateBasket(Basket basket) {
        log.info("***"  + this.getClass() + " calculateBasket ");

        Map<String, BigDecimal> pricedArticles = basket.getEntries().stream()
                .collect(Collectors.toMap(
                        BasketEntry::getArticleId,
                        entry -> calculateArticle(entry, basket.getCustomerId())));

        BigDecimal totalAmount = pricedArticles.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new BasketCalculationResult(basket.getCustomerId(), pricedArticles, totalAmount);
    }

    public BigDecimal calculateArticle(BasketEntry be, String customerId) {
        log.info("***"  + this.getClass() + " calculateArticle ");

        String articleId = null;
        try {
            articleId = be.getArticleId();
            priceRepository.getPriceByArticleIdAndCustomerId(articleId, customerId);
        } catch (NullPointerException exception) {
            log.error(this.getClass() + " Parameter was null! customer is: " + customerId + ", articleId is: " + articleId);
            return BigDecimal.ZERO;
        } catch (Exception e) {
            log.error(this.getClass() + " Something wrong happened!");
            return BigDecimal.ZERO;
        }

        return priceRepository.getPriceByArticleId(articleId);
    }
}
