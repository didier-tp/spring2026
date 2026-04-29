package tp.appliRest.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String code;
    private String name;
    private Double change;
}
