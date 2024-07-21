package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.service.ReportService;

@Component
public class ScheduledTasks {

	 @Autowired
	 private ReportService reportService;

	    @Scheduled(cron = "0 0 0 * * ?") // Example: every day at midnight
	    public void scheduleReportGeneration() {
	        // Add logic to determine the file paths or fetch from a configured source
	        String feedFilePath = "path/to/feed.csv";
	        String referenceFilePath = "path/to/reference.csv";
	        reportService.generateReport(feedFilePath, referenceFilePath);
	    }
}
