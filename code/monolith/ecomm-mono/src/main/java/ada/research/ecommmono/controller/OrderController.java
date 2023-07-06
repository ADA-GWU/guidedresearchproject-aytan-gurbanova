package ada.research.ecommmono.controller;

import ada.research.ecommmono.model.OrderRequest;
import ada.research.ecommmono.model.OrderResponse;
import ada.research.ecommmono.service.JwtService;
import ada.research.ecommmono.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mono/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final JwtService jwtService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(
            @RequestBody List<OrderRequest> orderRequestList) {

        Long userId = jwtService.extractUserId();
        OrderResponse orderResponse = orderService.placeOrder(userId, orderRequestList);

        if (orderResponse != null) {
            return ResponseEntity.ok(orderResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view")
    public ResponseEntity<List<OrderResponse>> viewOrders() {
        Long userId = jwtService.extractUserId();
        List<OrderResponse> orderResponseList = orderService.viewOrders(userId);
        return ResponseEntity.ok(orderResponseList);
    }
}
