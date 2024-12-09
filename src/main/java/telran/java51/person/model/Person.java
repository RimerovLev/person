package telran.java51.person.model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity

public class Person implements Serializable {
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;

    //@Embedded
    @Setter
    Address address;
}
