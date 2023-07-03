package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.Cart;
import ada.research.ecommmono.model.CartResponse;
import ada.research.ecommmono.model.User;
import ada.research.ecommmono.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mono/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService service;

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(
            @RequestParam long productId,
            @RequestParam int quantity
    )
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Long userId = user.getId();
        Cart cart = service.addToCart(userId, productId, quantity);
        CartResponse cartResponse = new CartResponse(cart.getUser().getEmail(),
                                                     cart.getProduct().getName(),
                                                     cart.getQuantity(),
                                                     cart.getCreatedAt(),
                                                     cart.getUpdatedAt());
        return ResponseEntity.ok(cartResponse);
    }
}
