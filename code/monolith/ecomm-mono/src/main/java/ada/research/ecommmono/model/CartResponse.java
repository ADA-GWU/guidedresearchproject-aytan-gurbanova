package ada.research.ecommmono.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private String email;
    private String productName;
    private int quantity;
    private Date createdAt;
    private Date updatedAt;
}
