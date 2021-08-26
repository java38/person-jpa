package telran.java38.person.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.java38.person.dao.PersonRepository;
import telran.java38.person.dto.PersonDto;
import telran.java38.person.dto.exceptions.PersonNotFoundException;
import telran.java38.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	PersonRepository personRepository;
	ModelMapper modelMapper;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
		this.personRepository = personRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = modelMapper.map(personDto, Person.class);
		personRepository.save(person);
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto editPerson(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public Iterable<PersonDto> findPersonsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonDto> findPersonsByAges(int minAge, int maxAge) {
		// TODO Auto-generated method stub
		return null;
	}

}
