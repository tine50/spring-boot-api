package com.first.web.application.second.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.first.web.application.second.api.CustomProperties;
import com.first.web.application.second.api.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {

	@Autowired
	public CustomProperties properties;

	public Iterable<Employee> getEmployees() {
		String baseApiUrl = properties.getApiUrl();
		String getEmployeesUrl = baseApiUrl + "/employees";

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(getEmployeesUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Employee>>() {
				});
		log.debug("Get employees call", response.getStatusCode().toString());

		return response.getBody();
	}

	public Employee creatEmployee(Employee e) {
		String baseApiUrl = properties.getApiUrl();
		String createEmployessUrl = baseApiUrl + "/employees";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(e);
		ResponseEntity<Employee> response = restTemplate.exchange(createEmployessUrl, HttpMethod.POST, request,
				Employee.class);
		log.debug("Create employee call", response.getStatusCode().toString());

		return response.getBody();
	}

	public Employee getEmployee(int id) {
		String baseApiUrl = properties.getApiUrl();
		String getEmployeeUrl = baseApiUrl + "/employee/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Employee> response = restTemplate.exchange(getEmployeeUrl, HttpMethod.GET, null, Employee.class);
		log.debug("Get employee call " + response.getStatusCode().toString());
		return response.getBody();
	}

	public Employee updateEmployee(Employee e) {
		String baseApiUrl = properties.getApiUrl();
		String updateEmployeeUrl = baseApiUrl + "/employee/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Employee> request = new HttpEntity<Employee>(e);
		ResponseEntity<Employee> response = restTemplate.exchange(updateEmployeeUrl, HttpMethod.PUT, request,
				Employee.class);

		log.debug("Update employee call " + response.getStatusCode().toString());
		return response.getBody();
	}

	public void deleteEmployee(int id) {
		String baseApiUrl = properties.getApiUrl();
		String deleteEmployeeUrl = baseApiUrl + "/employee/" + id;

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Void> response = restTemplate.exchange(deleteEmployeeUrl, HttpMethod.DELETE, null, Void.class);
		log.debug("Delete employee call " + response.getStatusCode().toString());
	}
}
