package ada.research.ecommmono.service;

import ada.research.ecommmono.config.ApplicationConfig;
import ada.research.ecommmono.model.Product;
import ada.research.ecommmono.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private static final Logger logger = Logger.getLogger(ApplicationConfig.class.getName());
    public List<Product> filterProducts(String brand,
                                        String productName,
                                        String categoryName,
                                        Float priceMin,
                                        Float priceMax){
        logger.log(Level.INFO, "filterProducts method started");
        List<Product> products = repository.filterProducts(brand, productName, categoryName, priceMin, priceMax);
        logger.log(Level.INFO, "filterProducts method ended");
        return products;
    }

    public List<Product> searchProduct(String keyword){
        logger.log(Level.INFO, "searchProduct method started");
        List<Product> products = repository.searchProduct(keyword);
        logger.log(Level.INFO, "searchProduct method ended");
        return products;
    }
}
