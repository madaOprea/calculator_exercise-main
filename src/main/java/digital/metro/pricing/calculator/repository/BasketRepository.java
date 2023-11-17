package digital.metro.pricing.calculator.repository;

import digital.metro.pricing.calculator.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, String> {


}
