package com.prudential.demo.controller;

import com.prudential.demo.ExcelUtil;
import com.prudential.demo.ExcelUtilImpl;
import com.prudential.demo.dto.AgentDTO;
import com.prudential.demo.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;

    @Autowired
    private ExcelUtilImpl util;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity getAgentList(@RequestBody AgentDTO request) {
        final List<AgentDTO> dataList = agentService.getDataList(request);
        return ResponseEntity.ok(dataList);
    }

    @RequestMapping(value = "/sample", method = RequestMethod.GET)
    public ResponseEntity getSample() {
        List<AgentDTO> dataList = new LinkedList<>();
        dataList.add(new AgentDTO("id-1", "AGENT-9999", Calendar.getInstance(), 90000L));
        dataList.add(new AgentDTO("id-2", "AGENT-9999", Calendar.getInstance(), 70000L));
        dataList.add(new AgentDTO("id-3", "AGENT-9999", Calendar.getInstance(), 80000L));
        dataList.add(new AgentDTO("id-4", "AGENT-9999", Calendar.getInstance(), 20000L));
        dataList.add(new AgentDTO("id-5", "AGENT-9999", Calendar.getInstance(), 30000L));
        dataList.add(new AgentDTO("id-6", "AGENT-9999", Calendar.getInstance(), 40000L));
        dataList.add(new AgentDTO("id-7", "AGENT-9999", Calendar.getInstance(), 50000L));
        return ResponseEntity.ok(dataList);
    }

    @RequestMapping(value = "/send-email", method = RequestMethod.GET)
    public Object sendEmail() throws IOException {
        List<AgentDTO> dataList = new LinkedList<>();
        dataList.add(new AgentDTO("id-1", "AGENT-9999", Calendar.getInstance(), 90000L));
        dataList.add(new AgentDTO("id-2", "AGENT-9999", Calendar.getInstance(), 70000L));
        dataList.add(new AgentDTO("id-3", "AGENT-9999", Calendar.getInstance(), 80000L));
        dataList.add(new AgentDTO("id-4", "AGENT-9999", Calendar.getInstance(), 20000L));
        dataList.add(new AgentDTO("id-5", "AGENT-9999", Calendar.getInstance(), 30000L));
        dataList.add(new AgentDTO("id-6", "AGENT-9999", Calendar.getInstance(), 40000L));
        dataList.add(new AgentDTO("id-7", "AGENT-9999", Calendar.getInstance(), 50000L));

        return ResponseEntity.ok(util.createXlsx(dataList));
    }
}
