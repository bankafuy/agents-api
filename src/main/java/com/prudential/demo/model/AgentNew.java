package com.prudential.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.Date;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentNew {
    @Id
    @Field
    private Long id;
    @Field
    private String agentNumber;
    @Field
    private Date transactionDate;
    @Field
    private int api;
}
