package com.prudential.demo.repository;

import com.prudential.demo.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AgentRepository extends JpaRepository<Agent, String>, QuerydslPredicateExecutor<Agent> {

}
