package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

	 @Autowired
	 private ReportService reportService;

	    @PostMapping("/generate")
	    public String generateReport(@RequestParam String feedFilePath, @RequestParam String referenceFilePath) {
	        reportService.generateReport(feedFilePath, referenceFilePath);
	        return "Report generation initiated!";
	    }
}
