package ph.apper;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAnimalRequest {

    @NotBlank
    private String name;

    @NotNull
    private Boolean isMammal;
}
