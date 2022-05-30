package ph.apper;

import lombok.Data;

@Data
public class GetAnimalResponse {
    private Integer id;
    private String name;
    private Boolean isMammal;
}
