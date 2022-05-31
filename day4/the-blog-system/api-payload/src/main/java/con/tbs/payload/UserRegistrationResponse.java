package con.tbs.payload;

import lombok.Data;

@Data
public class UserRegistrationResponse {
    private String userId;

    public UserRegistrationResponse(String userId) {
        this.userId = userId;
    }
}
