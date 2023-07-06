package ada.research.ecommmono.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResponse {
    private String productName;
    private int quantity;
}
