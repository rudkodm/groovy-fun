package by.rudko.model

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Created by Dmitriy_Rudko on 2/25/2016.
 */
@Entity
@Canonical()
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id
    String firstName
    String secondName
}
