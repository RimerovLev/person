package telran.java51.person.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class PersonDto {
    Integer id;
    @Setter// Lombok сгенерирует getId()
    String name;         // Lombok сгенерирует getName()
    LocalDate birthDate;
    @Setter// Lombok сгенерирует getBirthDate()
    AddressDto address;  // Lombok сгенерирует getAddress()


}

