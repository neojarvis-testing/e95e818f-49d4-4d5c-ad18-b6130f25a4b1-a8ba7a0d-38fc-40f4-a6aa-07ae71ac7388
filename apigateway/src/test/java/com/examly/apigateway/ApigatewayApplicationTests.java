package com.examly.apigateway;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApigatewayApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    public String investorToken;
    private String fundManagerToken;
    private String administratorToken;
    private int customerUserId;

    private ObjectMapper objectMapper = new ObjectMapper(); 

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Test
    @Order(1)
    void backend_testRegisterInvestor() throws Exception {
        int userId = 1; // Example userId for test
        
        String requestBody = "{"
            + "\"userId\": " + userId + ","
            + "\"email\": \"customer@gmail.com\","
            + "\"password\": \"customer@1234\","
            + "\"username\": \"customer\","
            + "\"userRole\": \"Investor\","
            + "\"mobileNumber\": \"1234567890\""
            + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Assert status is Created
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        int registeredUserId = responseBody.get("userId").asInt();
        Assertions.assertEquals(userId, registeredUserId, "UserId should match the provided one");

        // Store userId for further use if needed
        customerUserId = registeredUserId;
        System.out.println("Registered Investor UserId: " + customerUserId);
    }

    @Test
    @Order(2)
    void backend_testRegisterFundManager() throws Exception {
        int userId = 2; // Example userId for test
        
        String requestBody = "{"
            + "\"userId\": " + userId + ","
            + "\"email\": \"fundmanager@gmail.com\","
            + "\"password\": \"fundmanager@1234\","
            + "\"username\": \"fundmanager\","
            + "\"userRole\": \"FundManager\","
            + "\"mobileNumber\": \"1234567890\""
            + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Assert status is Created
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        int registeredUserId = responseBody.get("userId").asInt();
        Assertions.assertEquals(userId, registeredUserId, "UserId should match the provided one");

        // Store token for further use if needed
        int loanManagerUserId = registeredUserId;
        System.out.println("Registered Financial Consultant UserId: " + registeredUserId);
    }

    @Test
    @Order(3)
    void backend_testRegisterAdministrator() throws Exception {
        int userId = 3; // Example userId for test
        
        String requestBody = "{"
            + "\"userId\": " + userId + ","
            + "\"email\": \"administrator@gmail.com\","
            + "\"password\": \"administrator@1234\","
            + "\"username\": \"administrator\","
            + "\"userRole\": \"Administrator\","
            + "\"mobileNumber\": \"1234567890\""
            + "}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Assert status is Created
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        int registeredUserId = responseBody.get("userId").asInt();
        Assertions.assertEquals(userId, registeredUserId, "UserId should match the provided one");

        // Store token for further use if needed
        int branchManagerUserId = registeredUserId;
        System.out.println("Registered Regional Manager UserId: " + registeredUserId);
    }

    @Test
    @Order(4)
    void backend_testLoginInvestor() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"customer@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        // Parse the response body
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        investorToken = token;
        customerUserId = responseBody.get("userId").asInt();

        // Assert status is OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(customerUserId, "UserId should not be null");
    }

    @Test
    @Order(5)
    void backend_testLoginFundManager() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"fundmanager@gmail.com\", \"password\": \"fundmanager@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        fundManagerToken = token;

        // Assert status is OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }

    @Test
    @Order(6)
    void backend_testLoginAdministrator() throws Exception, JsonProcessingException {
        String requestBody = "{\"email\": \"administrator@gmail.com\", \"password\": \"administrator@1234\"}";

        ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
                new HttpEntity<>(requestBody, createHeaders()), String.class);

        JsonNode responseBody = objectMapper.readTree(response.getBody());
        String token = responseBody.get("token").asText();
        administratorToken = token;

        // Assert status is OK
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(token);
    }





	@Test
	@Order(7)
	void backend_testAddMutualFund_AsFundManager() throws Exception {
		Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");
	
		String requestBody = "{"
			+ "\"fundManagerId\": 1,"  // Including fundManagerId in the request body
			+ "\"fundName\": \"Growth Fund\","
			+ "\"fundManager\": \"John Doe\","
			+ "\"currentNAV\": 120.5,"
			+ "\"expenseRatio\": 1.2,"
			+ "\"prospectus\": \"Growth Fund prospectus\","
			+ "\"dateCreated\": \"2023-09-01T10:00:00\","
			+ "\"lastUpdated\": \"2023-09-01T10:00:00\""
			+ "}";
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + fundManagerToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
	
		ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.POST, requestEntity, String.class);
	
		// Assert status is CREATED (201)
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	
		// Optionally log the response
		System.out.println("Response status: " + response.getStatusCode());
		System.out.println("Response body: " + response.getBody());
	}
	


@Test
@Order(8)
void backend_testAddMutualFund_InvalidToken() throws Exception {
    String requestBody = "{"
        + "\"fundManagerId\": 1,"
        + "\"fundName\": \"Growth Fund\","
        + "\"fundManager\": \"John Doe\","
        + "\"currentNAV\": 120.5,"
        + "\"expenseRatio\": 1.2,"
        + "\"prospectus\": \"Growth Fund prospectus\","
        + "\"dateCreated\": \"2023-09-01T10:00:00\","
        + "\"lastUpdated\": \"2023-09-01T10:00:00\""
        + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer invalidtoken");
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.POST, requestEntity, String.class);

    // Assert status is UNAUTHORIZED (401)
    Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}



@Test
@Order(9)
void backend_testAddMutualFund_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    String requestBody = "{"
        + "\"fundManagerId\": 1,"
        + "\"fundName\": \"Growth Fund\","
        + "\"fundManager\": \"John Doe\","
        + "\"currentNAV\": 120.5,"
        + "\"expenseRatio\": 1.2,"
        + "\"prospectus\": \"Growth Fund prospectus\","
        + "\"dateCreated\": \"2023-09-01T10:00:00\","
        + "\"lastUpdated\": \"2023-09-01T10:00:00\""
        + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.POST, requestEntity, String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}


@Test
@Order(10)
void backend_testAddMutualFund_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    String requestBody = "{"
        + "\"fundManagerId\": 1,"
        + "\"fundName\": \"Growth Fund\","
        + "\"fundManager\": \"John Doe\","
        + "\"currentNAV\": 120.5,"
        + "\"expenseRatio\": 1.2,"
        + "\"prospectus\": \"Growth Fund prospectus\","
        + "\"dateCreated\": \"2023-09-01T10:00:00\","
        + "\"lastUpdated\": \"2023-09-01T10:00:00\""
        + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.POST, requestEntity, String.class);

    // Assert status is UNAUTHORIZED (401)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}



@Test
@Order(11)
void backend_testGetMutualFundById_AsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    int mutualFundId = 1; // Replace with an actual mutual fund ID if needed

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund/{id}", HttpMethod.GET, requestEntity, String.class, mutualFundId);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}


@Test
@Order(12)
void backend_testGetMutualFundById_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    int mutualFundId = 1; // Replace with an actual mutual fund ID if needed

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund/{id}", HttpMethod.GET, requestEntity, String.class, mutualFundId);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status (Administrator): " + response.getStatusCode());
    System.out.println("Response body (Administrator): " + response.getBody());
}


@Test
@Order(13)
void backend_testGetMutualFundById_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    int mutualFundId = 1; // Replace with an actual mutual fund ID if needed

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund/{id}", HttpMethod.GET, requestEntity, String.class, mutualFundId);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status (Investor): " + response.getStatusCode());
    System.out.println("Response body (Investor): " + response.getBody());
}


@Test
@Order(14)
void backend_testGetAllMutualFunds_AsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.GET, requestEntity, String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status (Fund Manager): " + response.getStatusCode());
    System.out.println("Response body (Fund Manager): " + response.getBody());
}


@Test
@Order(15)
void backend_testGetAllMutualFunds_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.GET, requestEntity, String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status (Administrator): " + response.getStatusCode());
    System.out.println("Response body (Administrator): " + response.getBody());
}

@Test
@Order(16)
void backend_testGetAllMutualFunds_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund", HttpMethod.GET, requestEntity, String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status (Investor): " + response.getStatusCode());
    System.out.println("Response body (Investor): " + response.getBody());
}


@Test
@Order(17)
void backend_testUpdateMutualFund_AsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    int mutualFundId = 1; // Replace with an actual mutual fund ID if needed

    String requestBody = "{"
        + "\"fundName\": \"Updated Growth Fund\","
        + "\"fundManager\": \"John Doe\","
        + "\"currentNAV\": 130.5,"
        + "\"expenseRatio\": 1.1,"
        + "\"prospectus\": \"Updated Growth Fund prospectus\","
        + "\"dateCreated\": \"2023-09-01T10:00:00\","
        + "\"lastUpdated\": \"2023-09-10T10:00:00\""
        + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund/{id}", HttpMethod.PUT, requestEntity, String.class, mutualFundId);

    // Assert status is OK (200) or No Content (204) based on your API implementation
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); // or HttpStatus.NO_CONTENT

    // Optionally log the response
    System.out.println("Response status (Fund Manager): " + response.getStatusCode());
    System.out.println("Response body (Fund Manager): " + response.getBody());
}

@Test
@Order(18)
void backend_testUpdateMutualFund_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    int mutualFundId = 1; // Replace with an actual mutual fund ID if needed

    String requestBody = "{"
        + "\"fundName\": \"Updated Growth Fund\","
        + "\"fundManager\": \"John Doe\","
        + "\"currentNAV\": 130.5,"
        + "\"expenseRatio\": 1.1,"
        + "\"prospectus\": \"Updated Growth Fund prospectus\","
        + "\"dateCreated\": \"2023-09-01T10:00:00\","
        + "\"lastUpdated\": \"2023-09-10T10:00:00\""
        + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund/{id}", HttpMethod.PUT, requestEntity, String.class, mutualFundId);

    // Assert status is UNAUTHORIZED (401) or FORBIDDEN (403)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode()); // or HttpStatus.UNAUTHORIZED

    // Optionally log the response
    System.out.println("Response status (Administrator): " + response.getStatusCode());
    System.out.println("Response body (Administrator): " + response.getBody());
}


@Test
@Order(19)
void backend_testUpdateMutualFund_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    int mutualFundId = 1; // Replace with an actual mutual fund ID if needed

    String requestBody = "{"
        + "\"fundName\": \"Updated Growth Fund\","
        + "\"fundManager\": \"John Doe\","
        + "\"currentNAV\": 130.5,"
        + "\"expenseRatio\": 1.1,"
        + "\"prospectus\": \"Updated Growth Fund prospectus\","
        + "\"dateCreated\": \"2023-09-01T10:00:00\","
        + "\"lastUpdated\": \"2023-09-10T10:00:00\""
        + "}";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/mutualFund/{id}", HttpMethod.PUT, requestEntity, String.class, mutualFundId);

    // Assert status is UNAUTHORIZED (401) or FORBIDDEN (403)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode()); // or HttpStatus.UNAUTHORIZED

    // Optionally log the response
    System.out.println("Response status (Investor): " + response.getStatusCode());
    System.out.println("Response body (Investor): " + response.getBody());
}

@Test
@Order(20)
void backend_testAddRiskAssessment_AsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

	String requestBody = "{"
    + "\"mutualFund\": {"
    + "\"mutualFundId\": 1"
    + "},"
    + "\"riskLevel\": \"Low\","
    + "\"analysisDetails\": \"Administrator risk analysis\","
    + "\"assessmentDate\": \"2023-09-15T12:00:00\""
    + "}";


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment", HttpMethod.POST, requestEntity, String.class);

    // Assert status is CREATED (201)
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}




@Test
@Order(21)
void backend_testAddRiskAssessment_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    String requestBody = "{"
    + "\"mutualFund\": {"
    + "\"mutualFundId\": 1"
    + "},"
    + "\"riskLevel\": \"Low\","
    + "\"analysisDetails\": \"Administrator risk analysis\","
    + "\"assessmentDate\": \"2023-09-15T12:00:00\""
    + "}";


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment", HttpMethod.POST, requestEntity, String.class);

    // Assert status is CREATED (201)
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}





@Test
@Order(22)
void backend_testAddRiskAssessment_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");
	
	String requestBody = "{"
    + "\"mutualFund\": {"
    + "\"mutualFundId\": 1"
    + "},"
    + "\"riskLevel\": \"Low\","
    + "\"analysisDetails\": \"Administrator risk analysis\","
    + "\"assessmentDate\": \"2023-09-15T12:00:00\""
    + "}";
   

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment", HttpMethod.POST, requestEntity, String.class);

    // Assert status is FORBIDDEN (403)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}





@Test
@Order(23)
void backend_testGetRiskAssessmentById_AsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    int riskAssessmentId = 1; // Replace with an actual risk assessment ID if needed

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment/{id}", HttpMethod.GET, requestEntity, String.class, riskAssessmentId);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}



@Test
@Order(24)
void backend_testGetRiskAssessmentById_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    int riskAssessmentId = 1; // Replace with an actual risk assessment ID if needed

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment/{id}", HttpMethod.GET, requestEntity, String.class, riskAssessmentId);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}



@Test
@Order(25)
void backend_testGetRiskAssessmentById_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    int riskAssessmentId = 1; // Replace with an actual risk assessment ID if needed

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment/{id}", HttpMethod.GET, requestEntity, String.class, riskAssessmentId);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}




@Test
@Order(26)
void backend_testGetAllRiskAssessments_AsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment", HttpMethod.GET, requestEntity, String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}


@Test
@Order(27)
void backend_testGetAllRiskAssessments_AsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment", HttpMethod.GET, requestEntity, String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}

@Test
@Order(28)
void backend_testGetAllRiskAssessments_AsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/riskassessment", HttpMethod.GET, requestEntity, String.class);

    // Assert status is OK (200)
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    // Optionally log the response
    System.out.println("Response status: " + response.getStatusCode());
    System.out.println("Response body: " + response.getBody());
}


@Test
@Order(29) // Ensure the order is unique and appropriate
void backend_testAddFeedbackByInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    String requestBody = "{"
            + "\"feedbackId\": 1,"
            + "\"feedbackText\": \"Great application, really user-friendly!\","
            + "\"date\": \"2024-09-15\","
            + "\"user\": {"
            + "\"userId\": " + 1
            + "}"
            + "}";

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + investorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}

@Test
@Order(30) // Ensure the order is unique and appropriate
void backend_testAddFeedbackByAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    String requestBody = "{"
            + "\"feedbackId\": 1,"
            + "\"feedbackText\": \"Great application, really user-friendly!\","
            + "\"date\": \"2024-09-15\","
            + "\"user\": {"
            + "\"userId\": " + 1
            + "}"
            + "}";

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}


@Test
@Order(31) // Ensure the order is unique and appropriate
void backend_testAddFeedbackByFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    String requestBody = "{"
            + "\"feedbackId\": 1,"
            + "\"feedbackText\": \"Great application, really user-friendly!\","
            + "\"date\": \"2024-09-15\","
            + "\"user\": {"
            + "\"userId\": " + 1
            + "}"
            + "}";

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
            String.class);

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
}

@Test
@Order(32)
void backend_testGetAllFeedbackByFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(33)
void backend_testGetAllFeedbackByAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(34)
void backend_testGetAllFeedbackByInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + investorToken);

    ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(35)
void backend_testGetFeedbackByUserIdAsInvestor() throws Exception {
    Assertions.assertNotNull(investorToken, "Investor token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + investorToken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(36)
void backend_testGetFeedbackByUserIdAsFundManager() throws Exception {
    Assertions.assertNotNull(fundManagerToken, "FundManager token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + fundManagerToken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(37)
void backend_testGetFeedbackByUserIdAsAdministrator() throws Exception {
    Assertions.assertNotNull(administratorToken, "Administrator token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + administratorToken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
            HttpMethod.GET,
            new HttpEntity<>(headers),
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}



@Test
@Order(38)
void backend_testInvalidCredentialsLoginInvestor() throws Exception {
    // Use incorrect credentials
    String requestBody = "{\"email\": \"customer@gmail.com\", \"password\": \"wrongpassword\"}";

    ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
            new HttpEntity<>(requestBody, createHeaders()), String.class);

    // Assert status is FORBIDDEN (403)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
}


@Test
@Order(39)
void backend_testInvalidCredentialsLoginAdministrator() throws Exception {
    // Use incorrect credentials
    String requestBody = "{\"email\": \"administrator@gmail.com\", \"password\": \"wrongpassword\"}";

    ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
            new HttpEntity<>(requestBody, createHeaders()), String.class);

    // Assert status is FORBIDDEN (403)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
}



@Test
@Order(40)
void backend_testInvalidCredentialsLoginFundManager() throws Exception {
    // Use incorrect credentials
    String requestBody = "{\"email\": \"fundmanager@gmail.com\", \"password\": \"wrongpassword\"}";

    ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
            new HttpEntity<>(requestBody, createHeaders()), String.class);

    // Assert status is FORBIDDEN (403)
    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
}

}
