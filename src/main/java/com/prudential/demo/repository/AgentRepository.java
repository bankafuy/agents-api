package com.prudential.demo.repository;

import com.prudential.demo.model.AgentNew;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<AgentNew, String> {

}
