package by.rudko.dao

import by.rudko.configuration.DaoTestConfiguration
import by.rudko.model.Person
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static org.junit.Assert.*
import static org.hamcrest.Matchers.*


/**
 * Created by Dmitriy_Rudko on 2/29/2016.
 */
@ActiveProfiles("itest")
@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(DaoTestConfiguration.class)
class PersonDaoTest {

    @Autowired PersonDao dao

    @Before void dbSetup() {
        dao.save([
            new Person(1, "A", "A"),
            new Person(2, "B", "B"),
            new Person(3, "C", "C")
        ])
    }

    @Test void "findAll() method should return all 3 init Persons"() {
        def persons = dao.findAll()
        assertThat persons, hasSize(3)
        assertThat persons, contains([
            is(new Person(1, "A", "A")),
            is(new Person(2, "B",  "B")),
            is(new Person(3, "C",  "C"))
        ])
    }

    @Test void "findOne method should return the Person by ID"() {
        def expected = new Person(1, "A", "A")
        def actual = dao.findOne(1L)
        assertEquals expected, actual
    }
}
