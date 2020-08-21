package com.prudential.demo.repository;

import com.prudential.demo.model.AgentNew;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface AgentRepository extends CrudRepository<AgentNew, Long> {

    Iterable<AgentNew> findAllByTransactionDateGreaterThanAndTransactionDateLessThanEqualOrderById(Date greaterDate, Date lessDate);

}
