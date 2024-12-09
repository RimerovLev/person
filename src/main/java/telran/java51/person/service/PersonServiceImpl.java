package telran.java51.person.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.PersonDto;
import org.modelmapper.ModelMapper;
import telran.java51.person.dto.exceptions.PersonNotFoundExcp;
import telran.java51.person.model.Address;
import telran.java51.person.model.Person;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundExcp::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonByCity(String city) {
        return  personRepository.findPersonByAddressCityIgnoreCase(city)
                .map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }

//    @Override
//    public List<PersonDto> findPersonByCity(String city) {
//        return StreamSupport.stream(personRepository.findAll().spliterator(),false)
//                .filter(p -> p.getAddress().getCity().equalsIgnoreCase(city))
//                .map(person -> modelMapper.map(p, PersonDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<PersonDto> findPersonByAge(Integer minAge, Integer maxAge) {
//        LocalDate from = LocalDate.now().minusYears(maxAge);
//        LocalDate to = LocalDate.now().plusYears(minAge);
//        return StreamSupport.stream(personRepository.findAll().spliterator(),false)
//                .filter(person -> person.getBirthDate().isAfter(from) && person.getBirthDate().isBefore(to))
//                .map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
//    }
//
    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonByAge(Integer minAge, Integer maxAge) {
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().plusYears(minAge);
        return personRepository.findPersonByBirthDateBetween(from, to)
                .map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }



    @Override
    @Transactional
    public PersonDto updateName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundExcp::new);
        person.setName(name);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> findPersonByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(person -> modelMapper.map(person, PersonDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundExcp::new);
        person.setAddress(modelMapper.map(addressDto, Address.class));
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto deletePersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundExcp::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personRepository.getCityPopulation();
    }

}
