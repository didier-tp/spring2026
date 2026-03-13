package tp.appliSpring.bank.web.api.exchange;

import lombok.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;


@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Book {

      //@NonNull
      @Nullable
      private Long id;

      private String title;
}

/*
NB: @NonNull (ou @NotNull ) et @Nullable existent en de multiples versions (lombok, ...)
S'il est possible de choisir la version org.jspecify.annotations c'est idéal
car c'est la version qui est en voie de standardisation et qui est  officiellement adoptée par Spring7
 */
