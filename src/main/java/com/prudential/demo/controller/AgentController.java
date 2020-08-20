package com.prudential.demo.controller;

import com.prudential.demo.ExcelUtil;
import com.prudential.demo.ExcelUtilImpl;
import com.prudential.demo.dto.AgentDTO;
import com.prudential.demo.service.AgentService;
import com.prudential.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.prudential.demo.AliasName.*;

@RestController
@RequestMapping("/agents")
public class AgentController {
    @Autowired
    private AgentService agentService;

    @Autowired
    private ExcelUtilImpl util;

    @Autowired
    private MailService mailService;

    @Value("${mail.smtp.host}")
    private String smtpHost;
    @Value("${mail.smtp.port}")
    private String smtpPort;
    @Value("${mail.username}")
    private String smtpMailSender;
    @Value("${mail.password}")
    private String smtpMailSenderPassword;

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

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity download(@RequestBody AgentDTO request) throws IOException {
        final List<AgentDTO> dataList = agentService.getDataList(request);
        Map<String, String> response = new HashMap<>();
        response.put("base64", util.createXlsx(dataList));
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
    public ResponseEntity sendEmail(@RequestBody AgentDTO request) throws IOException {
        final List<AgentDTO> dataList = agentService.getDataList(request);
        final String attachment = util.createXlsx(dataList);

        if(request.getEmail() == null || request.getEmail().length() == 0) {
            request.setEmail("alif@code.id");
        }

        Map<String, Object> properties = new HashMap<>();
        properties.put(MAIL_SMTP_HOST, smtpHost);
        properties.put(MAIL_SMTP_PORT, smtpPort);
        properties.put(MAIL_SMTP_AUTH, true);
        properties.put(MAIL_SMTP_SSL_ENABLE, true);
        properties.put(MAIL_USERNAME, smtpMailSender);
        properties.put(MAIL_PASSWORD, smtpMailSenderPassword);

        mailService.sendMail(properties, request.getEmail(), attachment);

        return ResponseEntity.ok("OK");
    }

    @GetMapping("/check")
    public Object sample() {
        return System.currentTimeMillis();
    }


}
