package tp.appliRest.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Customer  {
    private String id;
    private String name;
    private String companyName;
}


/*
old version without wrapper:
 public class Customer extends RepresentationModel<tp.appliRest.model.Customer>   {
   {
    private String id;
    private String name;
    private String companyName;
}
 */