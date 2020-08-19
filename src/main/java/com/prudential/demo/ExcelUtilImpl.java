package com.prudential.demo;

import com.prudential.demo.dto.AgentDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class ExcelUtilImpl implements ExcelUtil<AgentDTO> {

    @Override
    public String createXlsx(List<AgentDTO> dataList) throws IOException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            // Create Header
            int rowNumber = 0;

            final XSSFRow rowHeader = sheet.createRow(rowNumber);
            final XSSFCell cellHeaderId = rowHeader.createCell(0);
            cellHeaderId.setCellValue("ID");
            final XSSFCell cellHeaderAgentNumber = rowHeader.createCell(1);
            cellHeaderAgentNumber.setCellValue("Agent Number");
            final XSSFCell cellHeaderTransactionDate = rowHeader.createCell(2);
            cellHeaderTransactionDate.setCellValue("Transaction Date");
            final XSSFCell cellHeaderApi = rowHeader.createCell(3);
            cellHeaderApi.setCellValue("API");

            // Content
            for (final AgentDTO agent : dataList) {
                rowNumber++;
                final XSSFRow row = sheet.createRow(rowNumber);
                final XSSFCell cellId = row.createCell(0);
                final XSSFCell cellAgentNumber = row.createCell(1);
                final XSSFCell cellTransactionDate = row.createCell(2);
                final XSSFCell cellApi = row.createCell(3);

                cellId.setCellValue(agent.getId());
                cellAgentNumber.setCellValue(agent.getAgentNumber());
                cellTransactionDate.setCellValue(agent.getTransactionDate());
                cellApi.setCellValue(agent.getApi());
            }

            ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            final String resultAsBase64 = Base64.getEncoder().encodeToString(fileOutputStream.toByteArray());
            System.out.println("Create File Successfully.");
            System.out.println("Base64: " +  resultAsBase64);

            return resultAsBase64;
        } catch (Exception e) {
            throw e;
        }
    }
}
