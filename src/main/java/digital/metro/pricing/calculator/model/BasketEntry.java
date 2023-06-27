package digital.metro.pricing.calculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name="basketentry")
public class BasketEntry {

    @Id
    @Column(name="article_uuid")
    @GeneratedValue(strategy= GenerationType.UUID)
    private String articleId;

    @Column
    private BigDecimal quantity;
}
