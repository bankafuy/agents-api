package com.prudential.demo.dto;

import java.util.Calendar;

public class AgentDTO {
    private Calendar fromDate;
    private Calendar toDate;
    private Long id;
    private String agentNumber;
    private Calendar transactionDate;
    private Long api;
    private String email;

    public AgentDTO() {

    }

    public AgentDTO(Long id, String agentNumber, Calendar transactionDate, Long api) {
        this.id = id;
        this.agentNumber = agentNumber;
        this.transactionDate = transactionDate;
        this.api = api;
    }

    public Calendar getFromDate() {
        return fromDate;
    }

    public Calendar getToDate() {
        return toDate;
    }

    public Long getId() {
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

    public String getEmail() {
        return email;
    }

    public void setFromDate(Calendar fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Calendar toDate) {
        this.toDate = toDate;
    }

    public void setId(Long id) {
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

    public void setEmail(String email) {
        this.email = email;
    }
}
