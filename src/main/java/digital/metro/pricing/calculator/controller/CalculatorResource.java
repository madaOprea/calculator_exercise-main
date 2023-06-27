package digital.metro.pricing.calculator.controller;

import digital.metro.pricing.calculator.model.*;
import digital.metro.pricing.calculator.service.BasketCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculator")
public class CalculatorResource {

    private BasketCalculatorService basketCalculatorService;

    @Autowired
    public CalculatorResource(BasketCalculatorService basketCalculatorService) {
        this.basketCalculatorService = basketCalculatorService;
    }

    @PostMapping("/calculate-basket")
    public ResponseEntity<BasketCalculationResult> calculateBasket(@RequestBody Basket basket) {
        return ResponseEntity.status(HttpStatus.OK).body(basketCalculatorService.calculateBasket(basket));
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<BigDecimal> getArticlePrice(@PathVariable("articleId") String articleId) {
        return ResponseEntity.status(HttpStatus.OK).body(basketCalculatorService.calculateArticle(new BasketEntry(articleId, BigDecimal.ONE), null));
    }

    @GetMapping("/getarticlepriceforcustomer")
    public ResponseEntity<BigDecimal> getArticlePriceForCustomer(@RequestParam String articleId, @RequestParam String customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(basketCalculatorService.calculateArticle(new BasketEntry(articleId, BigDecimal.ONE), customerId));
    }
}
