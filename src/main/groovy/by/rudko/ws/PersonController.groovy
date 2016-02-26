package by.rudko.ws

import by.rudko.dao.PersonDao
import by.rudko.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.*
import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

/**
 * Created by Dmitriy_Rudko on 2/25/2016.
 */
@RestController
@RequestMapping(path = "persons/", produces = APPLICATION_JSON_VALUE)
class PersonController {

    @Autowired
    PersonDao dao

    @RequestMapping(path = 'init', method = GET)
    def init() {
        dao.save([
            new Person(1, "A", "A"),
            new Person(2, "B", "B"),
            new Person(3, "C", "C")
        ])
    }

    @RequestMapping(path = '/', method = GET)
    def List<Person> persons() {
        dao.findAll()
    }

    @RequestMapping(path = 'add', method = POST)
    def addPerson(@RequestBody Person p) {
        dao.save(p)
    }

}
