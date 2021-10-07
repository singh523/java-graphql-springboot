package com.graphql.example.resolver;

import com.graphql.example.model.Organization;
import com.graphql.example.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class OrganizationQueryResolver implements GraphQLQueryResolver {

	private OrganizationRepository repository;

	OrganizationQueryResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Iterable<Organization> organizations() {
		return repository.findAll();
	}

	public Organization organization(Integer id) {
		return repository.findById(id).orElseThrow(RuntimeException::new);
	}
}
