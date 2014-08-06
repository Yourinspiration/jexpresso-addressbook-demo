package de.yourinspiration.jexpresso.addressbook;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.mongodb.MongoClient;

import de.yourinspiration.jexpresso.JExpresso;

@Configuration
@EnableScheduling
@EnableMongoRepositories(basePackageClasses = SpringConfig.class)
@ComponentScan(basePackageClasses = SpringConfig.class)
public class SpringConfig {

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        final String host = System.getenv("MONGO_HOST") != null ? System.getenv("MONGO_HOST") : "localhost";
        final String database = System.getenv("MONGO_DB") != null ? System.getenv("MONGO_DB") : "addressbook";
        final String username = System.getenv("MONGO_USERNAME") != null ? System.getenv("MONGO_USERNAME") : "";
        final String password = System.getenv("MONGO_PASSWORD") != null ? System.getenv("MONGO_PASSWORD") : "";
        return new MongoTemplate(new MongoClient(host), database, new UserCredentials(username, password));
    }

    @Bean
    public JExpresso jexpresso() {
        return new JExpresso();
    }

}
