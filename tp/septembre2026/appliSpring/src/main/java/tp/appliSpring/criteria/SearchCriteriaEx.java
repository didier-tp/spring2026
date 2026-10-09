package tp.appliSpring.criteria;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class SearchCriteriaEx extends SearchCriteria{
    private boolean orPredicate=false; //by default combine as "AND" , if true combine as "OR"

    public SearchCriteriaEx(String key, String operation, Object value) {
        super(key, operation, value);
    }

    public SearchCriteriaEx(String key, String operation, Object value,boolean orPredicate) {
        super(key, operation, value);
        this.orPredicate=orPredicate;
    }

    public SearchCriteriaEx() {
    }

    public static boolean isValidBasicOperation(String op){
        switch (op){
            case ":" :
            case ">":
            case "<" :
                return true;
            default :
                return false;
        }
    }
}
