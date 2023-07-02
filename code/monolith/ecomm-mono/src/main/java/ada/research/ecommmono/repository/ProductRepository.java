package ada.research.ecommmono.repository;

import ada.research.ecommmono.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
        @Query("SELECT p FROM Product p WHERE (:brand IS NULL OR p.brand = :brand) " +
                "AND (:product_name IS NULL OR p.name = :product_name) " +
                "AND (:category_name IS NULL OR p.category.name = :category_name) " +
                "AND (:price_min IS NULL OR p.price >= :price_min) " +
                "AND (:price_max IS NULL OR p.price <= :price_max)")
        List<Product> filterProducts(
                String brand,
                String product_name,
                String category_name,
                Float price_min,
                Float price_max
    );
        @Query("SELECT p FROM Product p WHERE " +
                "LOWER(p.brand) LIKE CONCAT('%', LOWER(:keyword), '%') OR " +
                "LOWER(p.name) LIKE CONCAT('%', LOWER(:keyword), '%') OR " +
                "LOWER(p.description) LIKE CONCAT('%', LOWER(:keyword), '%') OR " +
                "LOWER(p.category.name) LIKE CONCAT('%', LOWER(:keyword), '%')")
        List<Product> searchProduct(String keyword);
}
