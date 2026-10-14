package tp.appliSpring.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import tp.appliSpring.criteria.SearchCriteria;
import tp.appliSpring.entity.CompteEntity;

@Getter @Setter
public class CompteEntitySpecification implements Specification<CompteEntity> {
    private SearchCriteria criteria;

public CompteEntitySpecification(SearchCriteria criteria) { this.criteria = criteria; }
    @Override
    public Predicate toPredicate
    (Root<CompteEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        else if (criteria.getOperation().equalsIgnoreCase("~")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            }
        }
        return null;
    }
}
