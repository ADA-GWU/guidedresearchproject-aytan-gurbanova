package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.Cart;
import ada.research.ecommmono.model.CartResponse;
import ada.research.ecommmono.service.CartService;
import ada.research.ecommmono.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mono/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final JwtService jwtService;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(
            @RequestParam long productId,
            @RequestParam int quantity
    )
    {
        Long userId = jwtService.extractUserId();
        Cart cart = cartService.addToCart(userId, productId, quantity);
        CartResponse cartResponse = new CartResponse(cart.getUser().getEmail(),
                                                     cart.getProduct().getName(),
                                                     cart.getQuantity(),
                                                     cart.getCreatedAt(),
                                                     cart.getUpdatedAt());
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping("/view")
    public List<CartResponse> viewCart(){
        Long userId = jwtService.extractUserId();
        List<Cart> cartList = cartService.viewCart(userId);
        return cartService.convertToCartResponse(cartList);
    }

    @DeleteMapping("/remove/product/{productId}")
    public List<CartResponse> removeProduct(@PathVariable Long productId){
        Long userId = jwtService.extractUserId();
        List<Cart> cartList = cartService.removeProduct(userId, productId);
        return cartService.convertToCartResponse(cartList);
    }
}
