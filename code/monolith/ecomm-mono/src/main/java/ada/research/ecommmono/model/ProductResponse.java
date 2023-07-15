package ada.research.ecommmono.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    private String brand;
    private String category;
    private Date createdAt;
    private Date updatedAt;
}
