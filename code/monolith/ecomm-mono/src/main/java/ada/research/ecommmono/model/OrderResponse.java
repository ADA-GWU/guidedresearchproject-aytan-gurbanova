package ada.research.ecommmono.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String email;
    private List<OrderProductResponse> orderProduct;
    private float totalAmount;
    private Date createdAt;
    private Date updatedAt;
}
