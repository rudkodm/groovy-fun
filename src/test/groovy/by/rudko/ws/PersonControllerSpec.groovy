package by.rudko.ws

import by.rudko.dao.PersonDao
import by.rudko.model.Person

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.Matchers.*
import static org.springframework.http.MediaType.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by Dmitriy_Rudko on 2/26/2016.
 */
class PersonControllerSpec extends Specification {
    def dao = Mock(PersonDao)
    def controller = new PersonController(dao: dao)
    def mvc = MockMvcBuilders.standaloneSetup(controller).build()

    def 'Init method should add 3 persons to storage'() {
        when:
            def rs = mvc.perform(get('/persons/init'))
        then:
            1 * dao.save({ List persons ->
                persons.size() == 3
                new Person(1, "A", "A") in persons
                new Person(2, "B", "B") in persons
                new Person(3, "C", "C") in persons
            })
    }

    def 'Find-all should return Person JSON representation that have appropriate format'() {
        when:
            def rs = mvc.perform(get('/persons/'))
        then:
            1 * dao.findAll() >> [new Person(1, "name", "surname")]
            rs
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath('$', hasSize(1)))
                .andExpect(jsonPath('$[0].id', is(1)))
                .andExpect(jsonPath('$[0].firstName', is('name')))
                .andExpect(jsonPath('$[0].secondName', is('surname')))
    }

    def "Method Add should Add 1 Person to the storage"() {
        when:
            def rs = mvc.perform(post('/persons/add')
                .contentType(APPLICATION_JSON)
                .content('{"id": 1, "firstName": "name", "secondName": "surname"}'))
        then:
            1 * dao.save({ Person p ->
                p.id == 1
                p.firstName == 'name'
                p.secondName == 'surname'
            })
    }
}
