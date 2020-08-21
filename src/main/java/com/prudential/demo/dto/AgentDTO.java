package com.prudential.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private Calendar fromDate;
    private Calendar toDate;
    private Long id;
    private String agentNumber;
    private Calendar transactionDate;
    private int api;
    private String email;
}
