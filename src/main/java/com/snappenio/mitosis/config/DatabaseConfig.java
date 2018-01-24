package com.snappenio.mitosis.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.snappenio.mitosis")
@PropertySource({"classpath:mongodb.properties"})
public class DatabaseConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    protected String getDatabaseName() {
        return mongoClientURI().getDatabase();
    }

    @Override
    public Mongo mongo() {
        return new MongoClient(mongoClientURI());
    }

    @Bean
    public MongoClientURI mongoClientURI() {
        return new MongoClientURI(environment.getRequiredProperty("mongodb.uri"));
    }

}
