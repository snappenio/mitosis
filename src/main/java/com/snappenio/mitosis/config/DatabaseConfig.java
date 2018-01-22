package com.snappenio.mitosis.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoAuditing
@EnableMongoRepositories(basePackages = "com.snappenio.mitosis")
@PropertySource({"classpath:mongodb.properties"})
public class DatabaseConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment environment;

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("mongodb.name");
    }

    @Override
    public Mongo mongo() {
        ServerAddress serverAddress = new ServerAddress(environment.getRequiredProperty("mongodb.host"));
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(MongoCredential.createScramSha1Credential(
                environment.getRequiredProperty("mongodb.username"),
                environment.getRequiredProperty("mongodb.name"),
                environment.getRequiredProperty("mongodb.password").toCharArray()
        ));
        MongoClientOptions options = new MongoClientOptions.Builder().build();
        return new MongoClient(serverAddress, credentials, options);
    }

}
