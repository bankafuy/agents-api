package com.prudential.demo.model;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "agent")
public class Agent {
    @Id
    private String id;
    private String agentNumber;
    private Calendar transactionDate;
    private Long api;

    public String getId() {
        return id;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public Calendar getTransactionDate() {
        return transactionDate;
    }

    public Long getApi() {
        return api;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }

    public void setTransactionDate(Calendar transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setApi(Long api) {
        this.api = api;
    }
}
