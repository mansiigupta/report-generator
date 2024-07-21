package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReportServiceTest {

	@InjectMocks
	private ReportService reportService;

	public ReportServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGenerateReport() throws IOException {
		// Mock input and reference file data
		List<String[]> feedData = new ArrayList<>();
		feedData.add(new String[] { "a", "b", "1", "c", "5.0", "key1", "key2" });

		Map<String, String[]> referenceData = new HashMap<>();
		referenceData.put("key1key2", new String[] { "key1", "ref1", "key2", "ref2", "ref3", "10.0" });

		// Mock methods to return mocked data
		doReturn(feedData).when(reportService).readCsvFile("testFeed.csv");
		doReturn(referenceData).when(reportService).readReferenceData("testReference.csv");

		// Invoke the generateReport method
		reportService.generateReport("testFeed.csv", "testReference.csv");

		// Verify that the data was transformed and written to the output file
		List<String[]> expectedOutputData = new ArrayList<>();
		expectedOutputData.add(new String[] { "ab", "ref1", "ref2ref3", "5.0", "10.0" });

		verify(reportService, times(1)).writeCsvFile("output.csv", expectedOutputData);
	}

	@Test
	public void testReadCsvFile() throws IOException {
		List<String[]> data = reportService.readCsvFile("src/test/resources/testFeed.csv");
		assertNotNull(data);
		assertFalse(data.isEmpty());
		assertEquals(1, data.size());
		assertArrayEquals(new String[] { "a", "b", "1", "c", "5.0", "key1", "key2" }, data.get(0));
	}

	@Test
	public void testReadReferenceData() throws IOException {
		Map<String, String[]> referenceData = reportService.readReferenceData("src/test/resources/testReference.csv");
		assertNotNull(referenceData);
		assertFalse(referenceData.isEmpty());
		assertEquals(1, referenceData.size());
		assertArrayEquals(new String[] { "key1", "ref1", "key2", "ref2", "ref3", "10.0" },
				referenceData.get("key1key2"));
	}

	@Test
	public void testTransformData() {
		List<String[]> feedData = new ArrayList<>();
		feedData.add(new String[] { "a", "b", "1", "c", "5.0", "key1", "key2" });

		Map<String, String[]> referenceData = new HashMap<>();
		referenceData.put("key1key2", new String[] { "key1", "ref1", "key2", "ref2", "ref3", "10.0" });

		List<String[]> outputData = reportService.transformData(feedData, referenceData);
		assertNotNull(outputData);
		assertFalse(outputData.isEmpty());
		assertArrayEquals(new String[] { "ab", "ref1", "ref2ref3", "5.0", "10.0" }, outputData.get(0));
	}
}
