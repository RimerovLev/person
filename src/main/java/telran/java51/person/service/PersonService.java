package telran.java51.person.service;
import org.springframework.stereotype.Service;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.PersonDto;

import java.util.List;

@Service
public interface PersonService {
    Boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    List<PersonDto> findPersonByCity(String city);

    List<PersonDto> findPersonByAge(Integer ageFrom, Integer ageTo);

    PersonDto updateName(Integer id, String name);

    List<PersonDto> findPersonByName(String name);

    PersonDto updatePersonAddress(Integer id, AddressDto address);

    PersonDto deletePersonById(Integer id);

    Iterable<CityPopulationDto> getCityPopulation();

}
