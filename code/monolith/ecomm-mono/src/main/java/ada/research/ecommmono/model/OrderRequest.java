package ada.research.ecommmono.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private long productId;
    private int quantity;
}
