package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.Product;
import ada.research.ecommmono.model.ProductResponse;
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
    public List<ProductResponse> filterProducts(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Float priceMin,
            @RequestParam(required = false) Float priceMax
    ){
        return service.filterProducts(brand, productName, categoryName, priceMin, priceMax);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProduct(
            @RequestParam String keyword
    ){
        return service.searchProduct(keyword);
    }
}
