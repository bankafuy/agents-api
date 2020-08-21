package com.prudential.demo.controller;

import com.prudential.demo.ExcelUtil;
import com.prudential.demo.ExcelUtilImpl;
import com.prudential.demo.dto.AgentDTO;
import com.prudential.demo.model.AgentNew;
import com.prudential.demo.repository.AgentRepository;
import com.prudential.demo.service.AgentService;
import com.prudential.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.prudential.demo.AliasName.*;

@RestController
@RequestMapping("/agents")
public class AgentController {
    private static Logger LOGGER = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    private AgentService agentService;

    @Autowired
    private ExcelUtilImpl util;

    @Autowired
    private MailService mailService;

    @Autowired
    private AgentRepository repo;

    @Value("${mail.smtp.host}")
    private String smtpHost;
    @Value("${mail.smtp.port}")
    private String smtpPort;
    @Value("${mail.username}")
    private String smtpMailSender;
    @Value("${mail.password}")
    private String smtpMailSenderPassword;

    @Autowired
    private TaskExecutor taskExecutor;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity getAgentList(@RequestBody AgentDTO request) {
        final List<AgentDTO> dataList = agentService.getDataList(request);
        return ResponseEntity.ok(dataList.size());
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

        final MyRunnable myRunnable = new MyRunnable(request);
        taskExecutor.execute(myRunnable);

        return ResponseEntity.ok(String.format("Data will be sent to %s", request.getEmail()));
    }

    @GetMapping("/check")
    public Object sample() {
        return System.currentTimeMillis();
    }

    @GetMapping("/create-data")
    public Object createSampleData() {

        Calendar calendar = Calendar.getInstance();

        calendar.set(1999, Calendar.FEBRUARY, 1, 0,0,0);

        calendar.setTimeZone(TimeZone.getDefault());

        List<AgentNew> dataList = new LinkedList<>();
        Random ran = new Random();

        for (int i = 0; i < 100001; i++) {
            AgentNew agentNew = new AgentNew();
            agentNew.setId((long)i);
            agentNew.setAgentNumber("1234567");
            agentNew.setTransactionDate(calendar.getTime());

            int x = ran.nextInt(6) + 5;

            agentNew.setApi(x * 100000);
            calendar.add(Calendar.DATE, 1);
            dataList.add(agentNew);

            if(calendar.get(Calendar.YEAR) == 2020) {
                calendar.add(Calendar.YEAR, -20);
            }
        }

        repo.saveAll(dataList);
        return "OK";
    }

    private class MyRunnable implements Runnable {

        private AgentDTO request;

        MyRunnable(AgentDTO request) {
            this.request = request;
        }

        @Override
        public void run() {
            try {
                final List<AgentDTO> dataList = agentService.getDataList(request);
                final String attachment = util.createXlsx(dataList);

                if (request.getEmail() == null || request.getEmail().length() == 0) {
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
            } catch (Exception e) {
                LOGGER.error(e.toString());
            }
        }
    }

}
