package tp.appliSpring.specification;

import org.springframework.data.jpa.domain.Specification;
import tp.appliSpring.criteria.SearchCriteriaEx;

public class CompteSpecificationsBuilder  extends GenericSpecificationsBuilder {

    public CompteSpecificationsBuilder() {
        super();
    }
    public Specification newSpecification(SearchCriteriaEx searchCriteriaEx) {
        return new CompteEntitySpecification(searchCriteriaEx);
    }

}
