package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.ReportService;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private ReportService reportService;

	    @Test
	    public void testGenerateReportEndpoint() throws Exception {
	        this.mockMvc.perform(post("/api/reports/generate")
	                .param("feedFilePath", "src/test/resources/testFeed.csv")
	                .param("referenceFilePath", "src/test/resources/testReference.csv")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
	    }

}
