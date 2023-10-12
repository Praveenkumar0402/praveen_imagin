package mockito;

import mockito.entity.Person;
import mockito.repository.PersonRepository;
import mockito.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class JunitMockitoApplicationTests {

    @InjectMocks
    PersonService personService;
    @Mock
    PersonRepository personRepository;
    private Person person;

    @BeforeEach
    void setup() {
        person = new Person(1, "Praveen", 25, "Male");
    }

    @Test
    public void save() {
        Mockito.when(personRepository.save(person)).thenReturn(person);
        Person person1 = personService.add(person);
        assertAll(
                () -> assertEquals("Praveen", person1.getName()),
                () -> assertNotNull(person1)
        );
    }


	@Test
	public void fetch() {
		List<Person> person1=List.of(new Person(1,"Vishnu",26,"Male"),
                new Person(2,"Chaitanya",25,"Male"),
                new Person(3,"DK",25,"Female"));
		Mockito.when(personRepository.findAll()).thenReturn(person1);
        List<Person> list=personRepository.findAll();
        assertAll(()->assertEquals(3,personRepository.findAll().size()));
	}


    @Test
    public void update() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.of(person));
        Mockito.when(personRepository.save(person)).thenReturn(person);
        person.setName("Praveen");
        Person person1=personService.update(1,person);
        assertAll(()->assertNotNull(person1),
                ()->assertEquals("Praveen",person1.getName()),
                ()->assertEquals(25,person1.getAge()));
    }


	@Test
	public void delete() {
		personService.delete(1);
		Mockito.verify(personRepository, times(1)).deleteById(1);
	}

}
