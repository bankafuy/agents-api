package com.prudential.demo.repository;

import com.prudential.demo.model.AgentNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AgentNewRepository  extends JpaRepository<AgentNew, Long>, QuerydslPredicateExecutor<AgentNew> {
}
