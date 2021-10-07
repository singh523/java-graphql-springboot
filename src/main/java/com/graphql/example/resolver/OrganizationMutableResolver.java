package com.graphql.example.resolver;

import com.graphql.example.dtos.OrganizationDto;
import com.graphql.example.model.Organization;
import com.graphql.example.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMutableResolver implements GraphQLMutationResolver {

	OrganizationRepository repository;

	OrganizationMutableResolver(OrganizationRepository repository) {
		this.repository = repository;
	}

	public Organization newOrganization(OrganizationDto organizationDto) {
		return repository.save(new Organization(null, organizationDto.getName(), null, null));
	}

}
