package digital.metro.pricing.calculator.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Data
@Entity
@Table(name="basket")
@NoArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "basket_uuid")
    private String basketId;

    @Column//(nullable = false)
    private String customerId;

    @OneToMany(mappedBy = "articleId")
    private Set<BasketEntry> entries;
}
