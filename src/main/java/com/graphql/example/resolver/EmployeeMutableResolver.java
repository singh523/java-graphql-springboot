package com.graphql.example.resolver;

import com.graphql.example.dtos.EmployeeDto;
import com.graphql.example.model.Department;
import com.graphql.example.model.Employee;
import com.graphql.example.model.Organization;
import com.graphql.example.repository.DepartmentRepository;
import com.graphql.example.repository.EmployeeRepository;
import com.graphql.example.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMutableResolver implements GraphQLMutationResolver {

	DepartmentRepository departmentRepository;
	EmployeeRepository employeeRepository;
	OrganizationRepository organizationRepository;

	EmployeeMutableResolver(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
		this.organizationRepository = organizationRepository;
	}

	public Employee newEmployee(EmployeeDto employeeInput) {
		Department department = departmentRepository.findById(employeeInput.getDepartmentId()).get();
		Organization organization = organizationRepository.findById(employeeInput.getOrganizationId()).get();
		return employeeRepository.save(new Employee(null, employeeInput.getFirstName(), employeeInput.getLastName(),
				employeeInput.getPosition(), employeeInput.getAge(), employeeInput.getSalary(),
				department, organization));
	}

}
