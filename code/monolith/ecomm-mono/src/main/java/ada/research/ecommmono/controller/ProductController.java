package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.Product;
import ada.research.ecommmono.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mono/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String product_name,
            @RequestParam(required = false) String category_name,
            @RequestParam(required = false) Float price_min,
            @RequestParam(required = false) Float price_max
    ){
        return service.filterProducts(brand, product_name, category_name, price_min, price_max);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(
            @RequestParam String keyword
    ){
        return service.searchProduct(keyword);
    }
}
