package telran.java51.person.controller;

import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.service.PersonService;

import java.util.List;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;



    @PostMapping
    public Boolean addPesron(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }


    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }


    @GetMapping("/city/{city}")
    public List<PersonDto> findPersonByCity(@PathVariable String city) {
        return personService.findPersonByCity(city);
    }


    @GetMapping("/ages/{ageFrom}/{ageTo}")
    public List<PersonDto> findPersonByAge(@PathVariable Integer ageFrom, @PathVariable Integer ageTo) {
        return personService.findPersonByAge(ageFrom, ageTo);
    }


    @PutMapping("/{id}/name/{name}")
    public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updateName(id,name);
    }


    @GetMapping("/name/{name}")
    public List<PersonDto> findPersonByName(@PathVariable String name) {
        return personService.findPersonByName(name);
    }


    @PutMapping("/{id}/{address}")
    public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto address) {
        return personService.updatePersonAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public PersonDto deletePersonById(@PathVariable Integer id) {
        return personService.deletePersonById(id);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCityPopulation() {
        return personService.getCityPopulation();
    }


}

