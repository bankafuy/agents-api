package com.prudential.demo.service;

import com.prudential.demo.dto.AgentDTO;

import java.util.List;

public interface AgentService {
    public List<AgentDTO> getDataList(AgentDTO request);
}
