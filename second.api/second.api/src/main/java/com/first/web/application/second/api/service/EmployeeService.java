package com.first.web.application.second.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.web.application.second.api.model.Employee;
import com.first.web.application.second.api.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {

	@Autowired
	private EmployeeProxy employeeProxy;

	public Iterable<Employee> getEmployees() {
		return employeeProxy.getEmployees();
	}

	public Employee getEmployee(final int id) {
		return employeeProxy.getEmployee(id);
	}

	public Employee saveEmployee(Employee e) {
		Employee saveEmployee;
		e.setLastName(e.getLastName().toUpperCase());
		if (e.getId() == null) {
			saveEmployee = employeeProxy.creatEmployee(e);
		} else {
			saveEmployee = employeeProxy.updateEmployee(e);
		}
		return saveEmployee;
	}

	public void deleteEmployee(final int id) {
		employeeProxy.deleteEmployee(id);
	}
}
