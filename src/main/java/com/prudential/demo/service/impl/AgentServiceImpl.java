package com.prudential.demo.service.impl;

import com.prudential.demo.dto.AgentDTO;
import com.prudential.demo.model.AgentNew;
import com.prudential.demo.repository.AgentRepository;
import com.prudential.demo.service.AgentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AgentDTO> getDataList(AgentDTO request) {
        List<AgentDTO> results = new LinkedList<>();

//        Sort sort = Sort.by(Sort.Order.desc("transactionDate"));
//        BooleanExpression predicate = QAgentNew.agentNew.id.isNotNull();
//
//        if(request != null) {
//            if(request.getFromDate() != null) {
//                predicate = predicate.and(QAgentNew.agentNew.transactionDate.goe(request.getFromDate().getTime()));
//            }
//
//            if(request.getToDate() != null) {
//                predicate = predicate.and(QAgentNew.agentNew.transactionDate.loe(request.getToDate().getTime()));
//            }
//        }

//        final Iterable<AgentNew> dataList = repo.findAll(predicate);
        final Iterable<AgentNew> dataList = repo.findAll();

        for (final AgentNew agent : dataList) {
            final AgentDTO result = modelMapper.map(agent, AgentDTO.class);
            results.add(result);
        }

        return results;
    }
}
