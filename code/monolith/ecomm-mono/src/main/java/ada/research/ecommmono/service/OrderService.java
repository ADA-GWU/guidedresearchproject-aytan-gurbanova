package ada.research.ecommmono.service;

import ada.research.ecommmono.model.*;
import ada.research.ecommmono.repository.OrderProductRepository;
import ada.research.ecommmono.repository.OrderRepository;
import ada.research.ecommmono.repository.ProductRepository;
import ada.research.ecommmono.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class OrderService {
    private static final Logger logger = Logger.getLogger(AuthenticationService.class.getName());
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderResponse placeOrder(long userId, List<OrderRequest> orderRequestList){
        logger.info("placeOrder method started");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Order order = new Order();
            order.setUser(user);
            order.setCreatedAt(new Date());
            order.setUpdatedAt(new Date());

            float totalAmount = 0;

            List<OrderProductResponse> orderProductResponseList = new ArrayList<>();
            List<OrderProduct> orderProductList = new ArrayList<>();
            for (OrderRequest orderRequest : orderRequestList) {
                Optional<Product> optionalProduct = productRepository.findById(orderRequest.getProductId());
                if (optionalProduct.isPresent()) {
                    Product product = optionalProduct.get();

                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    orderProduct.setProduct(product);
                    orderProduct.setQuantity(orderRequest.getQuantity());

                    OrderProductResponse orderProductResponse = new OrderProductResponse(
                            orderProduct.getProduct().getName(),
                            orderRequest.getQuantity()
                    );

                    orderProductResponseList.add(orderProductResponse);
                    orderProductList.add(orderProduct);

                    totalAmount += product.getPrice() * orderRequest.getQuantity();
                }
            }

            order.setTotalAmount(totalAmount);

            order = orderRepository.save(order);
            orderProductRepository.saveAll(orderProductList);

            OrderResponse orderResponse = new OrderResponse(
                    order.getUser().getEmail(),
                    orderProductResponseList,
                    totalAmount,
                    order.getCreatedAt(),
                    order.getUpdatedAt()
            );

            logger.info("placeOrder method ended");
            return orderResponse;
        } else {
            // TODO: Handle user not found error
            logger.info("placeOrder method ended");
            return null;
        }
    }

    public List<OrderResponse> viewOrders(long userId) {
        logger.info("viewOrders method started");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<OrderResponse> orderResponseList = new ArrayList<>();
            List<Order> orderList = orderRepository.findByUserId(userId);

            for (Order order : orderList) {
                long orderId = order.getId();
                List<OrderProduct> orderProductList = orderProductRepository.findByOrderId(orderId);

                List<OrderProductResponse> orderProductResponseList = new ArrayList<>();
                for (OrderProduct orderProduct : orderProductList) {
                    OrderProductResponse orderProductResponse = new OrderProductResponse(
                            orderProduct.getProduct().getName(),
                            orderProduct.getQuantity()
                    );
                    orderProductResponseList.add(orderProductResponse);
                }

                OrderResponse orderResponse = new OrderResponse(
                        order.getUser().getEmail(),
                        orderProductResponseList,
                        order.getTotalAmount(),
                        order.getCreatedAt(),
                        order.getUpdatedAt()
                );
                orderResponseList.add(orderResponse);
            }

            logger.info("viewOrders method ended");
            return orderResponseList;
        } else {
            // TODO: Handle user not found error
            logger.info("User not found");
            return null;
        }
    }
}
