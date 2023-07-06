package ada.research.ecommmono.repository;

import ada.research.ecommmono.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
    @Query("SELECT o FROM OrderProduct o WHERE o.order.id = :orderId ORDER BY o.order.createdAt DESC")
    List<OrderProduct> findByOrderId(long orderId);
}
