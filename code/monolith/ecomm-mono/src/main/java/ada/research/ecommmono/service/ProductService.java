package ada.research.ecommmono.service;

import ada.research.ecommmono.model.Product;
import ada.research.ecommmono.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> filterProducts(String brand,
                                        String product_name,
                                        String category_name,
                                        Float price_min,
                                        Float price_max){
        return repository.filterProducts(brand, product_name, category_name, price_min, price_max);
    }

    public List<Product> searchProduct(String keyword){
        return repository.searchProduct(keyword);
    }
}
