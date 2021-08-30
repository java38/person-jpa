package telran.java38.person.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.java38.person.model.Address;

@NoArgsConstructor
@Getter
@Setter
public class ChildDto extends PersonDto {
	String kindergarten;

	public ChildDto(Integer id, String name, LocalDate birthDate, Address address, String kindergarten) {
		super(id, name, birthDate, address);
		this.kindergarten = kindergarten;
	}
	
	
}
