package by.rudko.util

import spock.lang.Specification

/**
 * Created by Dmitriy_Rudko on 2/26/2016.
 */
class ListSpec extends Specification {

    def "get(0) method of List should return first element"() {
        given:
            List list = []
        when:
            list.add(1)
            list.add(2)
        then:
           list.get(0) == 1
    }

    def "List with 1 element should have size 1"() {
        expect:
            [1].size == 1
    }

    def "When wrong element index is used then exception should be thrown"() {
        given:
            List list = []
        when:
            list[-1]
        then:
            thrown(IndexOutOfBoundsException)
    }

    def "Stub should print phrase in sout"() {
        given:
            ByteArrayOutputStream out = new ByteArrayOutputStream()
            System.setOut(new PrintStream(out))
            List list = Stub(){
                get(0) >> { print "phrase"}
            }
        when:
            list.get(0)
        then:
            out.toString() == "phrase"
    }

    def "I should be able to get the same element that I had put before"() {
        given:
            List list = []
        when:
            list[0] = elementToPut
        then:
            elementToCheck == list[0]
        where:
            elementToPut | elementToCheck
            1            | 1
            2            | 2
            99           | 99
    }
}
