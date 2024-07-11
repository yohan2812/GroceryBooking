package com.grocery;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@ComponentScan(basePackages = {"com.grocery.*"})
public class GroceryBookingApplication {

	@Value("${aws.secretName}")
	private String secretName;

	public static void main(String[] args) {
		SpringApplication.run(GroceryBookingApplication.class, args);
	}

	@Bean
	public String dbPassword() {
		String secretJson = getSecret();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> secretMap = objectMapper.readValue(secretJson, Map.class);
            return secretMap.get("password");
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse secret JSON", e);
        }
	}
	
	@Bean
	public String dbUserName() {
		String secretJson = getSecret();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, String> secretMap = objectMapper.readValue(secretJson, Map.class);
            return secretMap.get("userName");
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse secret JSON", e);
        }
	}

	public String getSecret() {
		AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().build();
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		String secretString = getSecretValueResult.getSecretString();
		return secretString;
	}

}
