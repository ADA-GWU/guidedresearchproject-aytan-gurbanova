package ada.research.ecommmono.repository;

import ada.research.ecommmono.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId AND c.product.id = :productId")
    Optional<Cart> findByProductIdAndUserId(long userId, long productId);

    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId ORDER BY c.updatedAt DESC")
    List<Cart> findByUserId(long userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Cart c SET c.quantity = c.quantity - 1 WHERE c.user.id = :userId AND " +
            "c.product.id = :productId AND " +
            "c.quantity > 0")
    void removeProduct(long userId, long productId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.user.id = :userId AND c.product.id = :productId")
    void removeByProductIdAndUserId(long userId, long productId);
}
