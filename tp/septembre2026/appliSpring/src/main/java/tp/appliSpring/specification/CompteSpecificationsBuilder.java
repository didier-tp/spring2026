package tp.appliSpring.specification;

import org.springframework.data.jpa.domain.Specification;
import tp.appliSpring.criteria.SearchCriteria;

public class CompteSpecificationsBuilder  extends GenericSpecificationsBuilder {

    public CompteSpecificationsBuilder() {
        super();
    }
    public Specification newSpecification(SearchCriteria searchCriteria) {
        return new CompteEntitySpecification(searchCriteria);
    }

}
