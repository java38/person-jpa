package telran.java38.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.java38.person.dto.CityPopulationDto;
import telran.java38.person.dto.PersonDto;
import telran.java38.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@PutMapping("/{id}/name/{name}")
	public PersonDto editPerson(@PathVariable Integer id, @PathVariable String name) {
		return personService.editPerson(id, name);
	}

	@DeleteMapping("/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}

	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}

	@GetMapping("/minAge/{minAge}/maxAge/{maxAge}")
	public Iterable<PersonDto> findPersonsByAges(@PathVariable int minAge, @PathVariable int maxAge) {
		return personService.findPersonsByAges(minAge, maxAge);
	}

	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@GetMapping("/population/city")
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}

}
