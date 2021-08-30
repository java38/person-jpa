package telran.java38.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@Entity(name = "Adon")
@Table(name = "person")
public class Person {
	
	@Id
	Integer id;
	String name;
	LocalDate birthDate;
	//@EmbeddedId
	Address address;
}
