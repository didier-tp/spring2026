package tp.appliSpring.specification;

import org.springframework.data.jpa.domain.Specification;
import tp.appliSpring.criteria.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericSpecificationsBuilder {

    protected final List<SearchCriteria> params;
    public abstract Specification newSpecification(SearchCriteria searchCriteria);

    public GenericSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final GenericSpecificationsBuilder with(String key, String operation,  Object value) {
        params.add(new SearchCriteria( key, operation, value));
        return this;
    }

    public Specification build() {
        if (params.size() == 0)
            return null;

        Specification result = newSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result =Specification.where(result).and(newSpecification(params.get(i)));
        }

        return result;
    }
}
