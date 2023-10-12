package mockito.controller;

import mockito.entity.Person;
import mockito.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/all")
    public List<Person> fetch() {
        return personService.getAll();
    }

    @PostMapping("/save")
    public Person add(@RequestBody Person person){
        return personService.add(person);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        return personService.delete(id);
    }

    @PutMapping("/update/{id}")
    public Person update(@PathVariable("id") int id,@RequestBody Person person) {
        return personService.update(id,person);
    }
}
