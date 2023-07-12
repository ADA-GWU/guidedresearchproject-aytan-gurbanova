package ada.research.ecommmono.repository;

import ada.research.ecommmono.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:brand IS NULL OR p.brand = :brand) " +
            "AND (:productName IS NULL OR p.name = :productName) " +
            "AND (:categoryName IS NULL OR p.category.name = :categoryName) " +
            "AND (:priceMin IS NULL OR p.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR p.price <= :priceMax) " +
            "ORDER BY p.id")
    List<Product> filterProducts(
                String brand,
                String productName,
                String categoryName,
                Float priceMin,
                Float priceMax);

    @Query("SELECT p FROM Product p WHERE " +
                "LOWER(p.brand) LIKE CONCAT('%', LOWER(:keyword), '%') OR " +
                "LOWER(p.name) LIKE CONCAT('%', LOWER(:keyword), '%') OR " +
                "LOWER(p.description) LIKE CONCAT('%', LOWER(:keyword), '%') OR " +
                "LOWER(p.category.name) LIKE CONCAT('%', LOWER(:keyword), '%')")
    List<Product> searchProduct(String keyword);
}
