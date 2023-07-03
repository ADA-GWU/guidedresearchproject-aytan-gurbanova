package ada.research.ecommmono.service;

import ada.research.ecommmono.model.Cart;
import ada.research.ecommmono.model.Product;
import ada.research.ecommmono.model.User;
import ada.research.ecommmono.repository.CartRepository;
import ada.research.ecommmono.repository.ProductRepository;
import ada.research.ecommmono.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class CartService {
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Cart addToCart(long userId, long productId, int quantity) {
        logger.info("addToCart method started");
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            User user = optionalUser.get();
            Product product = optionalProduct.get();

            Optional<Cart> optionalCart = cartRepository.findByProductIdAndUserId(userId, productId);
            Cart cart;
            if (optionalCart.isPresent()) {
                // if cart exists for the user and product, update the quantity
                cart = optionalCart.get();
                cart.setQuantity(cart.getQuantity() + 1);
                cart.setUpdatedAt(new Date());
                logger.info("Existing cart updated");
            } else {
                // if cart does not exist, create a new cart entry
                cart = Cart.builder()
                        .user(user)
                        .product(product)
                        .quantity(quantity)
                        .createdAt(new Date())
                        .updatedAt(new Date())
                        .build();
                logger.info("New cart created");
            }
            logger.info("addToCart method ended");
            return cartRepository.save(cart);
        } else {
            // TODO: handle error later
            return null;
        }
    }
}
