package mockito.service;

import mockito.entity.Person;
import mockito.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person add(Person person){
        return personRepository.save(person);
    }

    public String delete(int id) {
        personRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public Person update(int id,Person person) {
        Person person1=personRepository.findById(id).get();
        person1.setName(person.getName());
        return personRepository.save(person1);
    }
}
