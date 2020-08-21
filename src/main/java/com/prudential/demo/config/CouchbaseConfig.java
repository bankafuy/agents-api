package com.prudential.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.host}")
    private String host;
    @Value("${couchbase.username}")
    private String username;
    @Value("${couchbase.password}")
    private String password;
    @Value("${couchbase.bucket}")
    private String bucketName;


    @Override
    public String getConnectionString() {
        return String.format("couchbase://%s", host);
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getBucketName() {
        return this.bucketName;
    }
}
