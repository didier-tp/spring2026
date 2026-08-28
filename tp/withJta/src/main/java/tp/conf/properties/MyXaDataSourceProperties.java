package tp.conf.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MyXaDataSourceProperties {
    private String uniqueResourceName;
    private String xaDataSourceClassName;
    private int poolSize;
    private java.util.Properties xaProperties;
}
