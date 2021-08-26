package telran.java38.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java38.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
