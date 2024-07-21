package com.example.demo.service;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReportService {
	
	  public void generateReport(String feedFilePath, String referenceFilePath) {
	        log.info("Starting report generation for feed: {} and reference: {}", feedFilePath, referenceFilePath);

	        try {
	            List<String[]> feedData = readCsvFile(feedFilePath);
	            Map<String, String[]> referenceData = readReferenceData(referenceFilePath);

	            List<String[]> outputData = transformData(feedData, referenceData);

	            writeCsvFile("output.csv", outputData);

	            log.info("Report generation completed successfully!");
	        } catch (IOException e) {
	            log.error("Error during report generation: {}", e.getMessage());
	        }
	    }

	    public List<String[]> readCsvFile(String filePath) throws IOException {
	        List<String[]> data = new ArrayList<>();
	        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                data.add(line.split(","));
	            }
	        }
	        return data;
	    }

	    public Map<String, String[]> readReferenceData(String filePath) throws IOException {
	        Map<String, String[]> referenceData = new HashMap<>();
	        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] fields = line.split(",");
	                referenceData.put(fields[0] + fields[2], fields);
	            }
	        }
	        return referenceData;
	    }

	    public List<String[]> transformData(List<String[]> feedData, Map<String, String[]> referenceData) {
	        List<String[]> outputData = new ArrayList<>();
	        for (String[] feedRow : feedData) {
	            String refKey = feedRow[5] + feedRow[6];
	            String[] refRow = referenceData.get(refKey);

	            String[] outputRow = new String[5];
	            outputRow[0] = feedRow[0] + feedRow[1];
	            outputRow[1] = refRow[1];
	            outputRow[2] = refRow[2] + refRow[3];
	            outputRow[3] = String.valueOf(Double.parseDouble(feedRow[2]) * Math.max(Double.parseDouble(feedRow[4]), Double.parseDouble(refRow[5])));
	            outputRow[4] = String.valueOf(Math.max(Double.parseDouble(feedRow[4]), Double.parseDouble(refRow[5])));

	            outputData.add(outputRow);
	        }
	        return outputData;
	    }

	    public void writeCsvFile(String filePath, List<String[]> data) throws IOException {
	        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
	            for (String[] row : data) {
	                bw.write(String.join(",", row));
	                bw.newLine();
	            }
	        }
	    }
}
