package tp.appliSpring.criteria;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SearchCriteria {
    private static final String EQUALS_OP = ":"; //not "=" because "=" is used in URL query param
    private static final String LESS_OP = "<";
    private static final String GREATER_OP = ">";
    private static final String LIKE_OP = "~";

    private String key; //field name;
    private String operation; // > or < or : or ...
    private Object value; //value (for compare)
}
//exemples :
//new SearchCriteria("age", ">", "24")
//new SearchCriteria("lastName", ":", "Bon")