package com.test.coursemanagementspring.inbounds.httpcontrollers.person;

import com.test.coursemanagementspring.core.person.adapters.PersonServiceAdapter;
import com.test.coursemanagementspring.core.person.entities.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private final PersonServiceAdapter personService;

    PersonController(PersonServiceAdapter personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return this.personService.getPerson(id);
    }
}
