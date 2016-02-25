package by.rudko.dao

import by.rudko.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Dmitriy_Rudko on 2/25/2016.
 */

@Repository
interface PersonDao extends JpaRepository<Person, Long> {

}
