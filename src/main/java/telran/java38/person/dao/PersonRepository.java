package telran.java38.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java38.person.dto.CityPopulationDto;
import telran.java38.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	@Query("select p from Adon p where p.name=?1")
	Stream<Person> findByName(String name);
	
	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	
	@Query("select p from Adon p where p.address.city=:city")
	Stream<Person> findByAddressCity(@Param("city") String city);
	
	@Query("select new telran.java38.person.dto.CityPopulationDto(p.address.city, count(p)) from Adon p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCityPopulation();
}
