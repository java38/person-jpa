package telran.java38.person.service;

import telran.java38.person.dto.PersonDto;

public interface PersonService {
	boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	PersonDto editPerson(Integer id, String name);

	PersonDto removePerson(Integer id);

	Iterable<PersonDto> findPersonsByName(String name);

	// Level Up!
	Iterable<PersonDto> findPersonsByAges(int minAge, int maxAge);
}
