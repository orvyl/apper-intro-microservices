package con.tbs.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginResponse {
    private LocalDateTime lastLogin;
}
