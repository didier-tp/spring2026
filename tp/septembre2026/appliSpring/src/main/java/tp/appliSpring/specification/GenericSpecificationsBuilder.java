package tp.appliSpring.specification;

import org.springframework.data.jpa.domain.Specification;
import tp.appliSpring.criteria.SearchCriteriaEx;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSpecificationsBuilder {

    protected final List<SearchCriteriaEx> params;

    public static final String ZERO_OR_MORE_REGEX = "*";

    public static final String OR_OPERATOR = "OR";
    public static final String AND_OPERATOR = "AND";

    public static final String LEFT_PARANTHESIS = "(";
    public static final String RIGHT_PARANTHESIS = ")";

    public abstract Specification newSpecification(SearchCriteriaEx searchCriteriaEx);

    public GenericSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final GenericSpecificationsBuilder with(String key, String operation, Object value) {
        return with(null, key, operation, value, null, null);
    }

    public final GenericSpecificationsBuilder with(String key, String operation, Object value,
                                                   String prefix, String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final GenericSpecificationsBuilder with(String orPredicate, String key, String operation,
                                                       Object value, String prefix, String suffix) {
        boolean validBasicOperation = SearchCriteriaEx.isValidBasicOperation(operation);
        if (!validBasicOperation) {
            //.... //next version/iteration
            }
            params.add(new SearchCriteriaEx( key, operation, value/*,orPredicate*/));  //default AND
        return this;
    }

    public Specification build() {
        if (params.size() == 0)
            return null;

        Specification result = newSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(newSpecification(params.get(i)))
                    : Specification.where(result).and(newSpecification(params.get(i)));
        }

        return result;
    }
}
