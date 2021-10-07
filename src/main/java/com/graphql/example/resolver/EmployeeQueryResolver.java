package com.graphql.example.resolver;

import com.graphql.example.model.Department;
import com.graphql.example.model.Employee;
import com.graphql.example.repository.EmployeeRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class EmployeeQueryResolver implements GraphQLQueryResolver {

	private EmployeeRepository repository;

	EmployeeQueryResolver(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Iterable<Employee> employees(DataFetchingEnvironment environment) {
		DataFetchingFieldSelectionSet s = environment.getSelectionSet();
		if (s.contains("department"))
			return repository.findAll(fetchDepartment());
		return repository.findAll();
	}

	private Specification<Employee> fetchDepartment() {
		return (Specification<Employee>) (root, query, builder) -> {
			Fetch<Employee, Department> f = root.fetch("department", JoinType.LEFT);
			Join<Employee, Department> join = (Join<Employee, Department>) f;
			return join.getOn();
		};
	}

	public Employee employee(Integer id) {
		return repository.findById(id).get();
	}

	/*public Iterable<Employee> employeesWithFilter(EmployeeFilter filter) {
		Specification<Employee> spec = null;
		if (filter.getSalary() != null)
			spec = bySalary(filter.getSalary());
		if (filter.getAge() != null)
			spec = (spec == null ? byAge(filter.getAge()) : spec.and(byAge(filter.getAge())));
		if (filter.getPosition() != null)
			spec = (spec == null ? byPosition(filter.getPosition()) :
					spec.and(byPosition(filter.getPosition())));
		if (spec != null)
			return repository.findAll(spec);
		else
			return repository.findAll();
	}*/

	/*private Specification<Employee> bySalary(FilterField filterField) {
		return (Specification<Employee>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("salary"));
	}

	private Specification<Employee> byAge(FilterField filterField) {
		return (Specification<Employee>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("age"));
	}

	private Specification<Employee> byPosition(FilterField filterField) {
		return (Specification<Employee>) (root, query, builder) -> filterField.generateCriteria(builder, root.get("position"));
	}*/
}
