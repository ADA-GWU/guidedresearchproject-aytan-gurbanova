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
public class UserUpdateResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;
}
