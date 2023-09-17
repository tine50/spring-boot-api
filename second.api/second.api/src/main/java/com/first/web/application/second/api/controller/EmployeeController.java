package com.first.web.application.second.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.first.web.application.second.api.model.Employee;
import com.first.web.application.second.api.repository.EmployeeProxy;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeProxy service = new EmployeeProxy();

	@GetMapping("/")
	public String home(Model model) {
		Iterable<Employee> listeEmployee = service.getEmployees();
		model.addAttribute("employees", listeEmployee);

		return "home";
	}

	@GetMapping("deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
		service.deleteEmployee(id);
		return new ModelAndView("redirect:/");
	}

	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		service.saveEmployee(employee);
		return new ModelAndView("redirect:/");
	}

}
