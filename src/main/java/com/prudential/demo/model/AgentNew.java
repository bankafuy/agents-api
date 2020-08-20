package com.prudential.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "api_agent")
public class AgentNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String agentNumber;
    private Date transactionDate;
    private int api;

    public Long getId() {
        return id;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getApi() {
        return api;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setApi(int api) {
        this.api = api;
    }
}
