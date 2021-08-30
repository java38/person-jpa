package telran.java38.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.java38.person.dao.PersonRepository;
import telran.java38.person.dto.CityPopulationDto;
import telran.java38.person.dto.PersonDto;
import telran.java38.person.dto.exceptions.PersonNotFoundException;
import telran.java38.person.dto.exceptions.UnknownPersonTypeException;
import telran.java38.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	private static final String PATH_MODEL = "telran.java38.person.model.";

	private static final String PATH_DTO = "telran.java38.person.dto.";
	
	private static final String DTO_SUFFIX = "Dto";

	PersonRepository personRepository;
	ModelMapper modelMapper;

	@Autowired
	public PersonServiceImpl(PersonRepository personRepository, ModelMapper modelMapper) {
		this.personRepository = personRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = modelMapper.map(personDto, getModelClass(personDto));
		personRepository.save(person);
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		return modelMapper.map(person, getDtoClass(person));
	}

	@Override
	@Transactional
	public PersonDto editPerson(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setName(name);
		return modelMapper.map(person, getDtoClass(person));
	}

	@Override
	@Transactional
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.delete(person);
		return modelMapper.map(person, getDtoClass(person));
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonsByName(String name) {
		return personRepository.findByName(name)
				.map(p -> modelMapper.map(p, getDtoClass(p)))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonsByAges(int minAge, int maxAge) {
		LocalDate from = LocalDate.now().minusYears(maxAge);
		LocalDate to = LocalDate.now().minusYears(minAge);
		return personRepository.findByBirthDateBetween(from, to)
				.map(p -> modelMapper.map(p, getDtoClass(p)))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonsByCity(String city) {
		return personRepository.findByAddressCity(city)
				.map(p -> modelMapper.map(p, getDtoClass(p)))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personRepository.getCityPopulation();
	}
	
	@SuppressWarnings("unchecked")
	private Class<Person> getModelClass(PersonDto personDto) {
		String modelClassName = personDto.getClass().getSimpleName();
		try {
			return (Class<Person>) Class.forName(PATH_MODEL + modelClassName.substring(0, modelClassName.length()-3));
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnknownPersonTypeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Class<PersonDto> getDtoClass(Person person) {
		String dtoClassName = person.getClass().getSimpleName();
		try {
			return (Class<PersonDto>) Class.forName(PATH_DTO + dtoClassName  + DTO_SUFFIX);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnknownPersonTypeException();
		}
	}

}
