# report-generator
Report Generator using feed data and reference data.

# Report Generator Service
This project is a Spring Boot application that ingests CSV files, applies transformation rules using reference data, and generates transformed reports. The application supports scheduling and can be triggered via a REST API.

# Features
Ingest CSV files and apply transformation rules.
Schedule report generation.
Trigger report generation via REST API.
Support for large files (up to 1GB).
Easily extensible to support other file formats (e.g., Excel, JSON).
Configurable transformation rules and output formats.
Requirements
Java 11
Maven

# Usage
REST API
The application provides a REST API to trigger report generation.

Endpoint: /api/reports/generate
Method: POST
Parameters:
feedFilePath: Path to the input feed file (testFeed.csv). 
referenceFilePath: Path to the reference file (testReference.csv).
ouputFilePath: output of the operations performed on Feed and Reference data (i.e., output.csv).

Example using curl
curl -X POST "http://localhost:8080/api/reports/generate?feedFilePath=src/test/resources/testFeed.csv&referenceFilePath=src/test/resources/testReference.csv"

# Testing
Unit tests are located in the src/test/java/com/example/reportgenerator/service directory.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
