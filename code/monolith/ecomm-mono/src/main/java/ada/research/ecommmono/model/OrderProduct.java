package ada.research.ecommmono.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_products")
public class OrderProduct {
    @SequenceGenerator(name = "order_pr_sequence",
            sequenceName = "order_pr_sequence",
            allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_pr_sequence")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;
}
