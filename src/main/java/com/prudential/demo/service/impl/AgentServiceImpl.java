package com.prudential.demo.service.impl;

import com.prudential.demo.dto.AgentDTO;
import com.prudential.demo.model.Agent;
import com.prudential.demo.model.AgentNew;
import com.prudential.demo.model.QAgent;
import com.prudential.demo.repository.AgentNewRepository;
import com.prudential.demo.repository.AgentRepository;
import com.prudential.demo.service.AgentService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentNewRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AgentDTO> getDataList(AgentDTO request) {
        List<AgentDTO> results = new LinkedList<>();

//        Sort sort = Sort.by(Sort.Order.desc("transactionDate"));
        BooleanExpression predicate = QAgent.agent.id.isNotNull();

        if(request != null) {
            if(request.getFromDate() != null) {
                predicate = predicate.and(QAgent.agent.transactionDate.goe(request.getFromDate()));
            }

            if(request.getToDate() != null) {
                predicate = predicate.and(QAgent.agent.transactionDate.loe(request.getToDate()));
            }
        }

        final Iterable<AgentNew> dataList = repo.findAll(predicate);

        for (final AgentNew agent : dataList) {
            final AgentDTO result = modelMapper.map(agent, AgentDTO.class);
            results.add(result);
        }

        return results;
    }
}
