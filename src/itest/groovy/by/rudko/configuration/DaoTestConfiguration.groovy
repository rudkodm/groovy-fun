package by.rudko.configuration

import by.rudko.dao.PersonDao
import by.rudko.model.Person
import org.hibernate.dialect.H2Dialect
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.hibernate5.SpringSessionContext
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean

/**
 * Created by rudkodm on 2/29/16.
 */
@Profile('itest')
@Configuration
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration])
@EnableJpaRepositories(basePackageClasses = [PersonDao])
class DaoTestConfiguration {


    @Bean(destroyMethod = 'shutdown') EmbeddedDatabase dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }

    @Bean LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        builder
            .dataSource(dataSource())
            .packages(Person)
            .properties([
                'hibernate.current_session_context_class'   : SpringSessionContext.class.getName(),
                'hibernate.dialect'                         : H2Dialect.class.getName(),
                'hibernate.hbm2ddl.auto'                    : 'create-drop'
            ])
            .build()
    }

}
