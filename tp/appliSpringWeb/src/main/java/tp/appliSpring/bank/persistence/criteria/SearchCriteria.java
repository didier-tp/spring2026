package tp.appliSpring.bank.persistence.criteria;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SearchCriteria {
    private String key; //field name
    private String operation; // > or < or : or ...
    private Object value; //value (for compare)
}
