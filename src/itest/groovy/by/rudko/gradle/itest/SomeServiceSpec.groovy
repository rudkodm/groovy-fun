package by.rudko.gradle.itest

import spock.lang.Specification

class SomeServiceSpec extends Specification {
    static String methodForTest(int a, int b) { String.valueOf(a + b) }

    def "Spock test probe"() {
        expect:
        methodForTest(a, b) == c

        where:
        a << [1, 4, 5]
        b << [2, 5, 5]
        c << ["3", "9", "10"]
    }
}
