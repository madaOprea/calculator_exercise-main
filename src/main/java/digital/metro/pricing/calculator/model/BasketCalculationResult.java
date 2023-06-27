package digital.metro.pricing.calculator.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BasketCalculationResult {

    private String customerId;
    private Map<String, BigDecimal> pricedBasketEntries;
    private BigDecimal totalAmount;
}
