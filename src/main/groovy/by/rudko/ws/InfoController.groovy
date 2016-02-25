package by.rudko.ws

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET

/**
 * Created by Dmitriy_Rudko on 2/25/2016.
 */
@RestController
@RequestMapping("info/")
class InfoController {

    @Value('${application.name}')
    String name;
    @Value('${application.version}')
    String version;
    @Value('${application.git}')
    String git;

    @RequestMapping(path = '/', method = GET)
    def Map info() {
        [
                'application': name,
                'version'    : version,
                'git'        : git
        ]
    }
}
