package com.graphql.example.resolver;

import com.graphql.example.dtos.DepartmentDto;
import com.graphql.example.model.Department;
import com.graphql.example.model.Organization;
import com.graphql.example.repository.DepartmentRepository;
import com.graphql.example.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMutableResolver implements GraphQLMutationResolver {

  DepartmentRepository departmentRepository;
  OrganizationRepository organizationRepository;

  DepartmentMutableResolver(DepartmentRepository departmentRepository, OrganizationRepository organizationRepository) {
    this.departmentRepository = departmentRepository;
    this.organizationRepository = organizationRepository;
  }

  public Department newDepartment(DepartmentDto departmentDto) {
    Organization organization = organizationRepository.findById(departmentDto.getOrganizationId()).get();
    return departmentRepository.save(new Department(null, departmentDto.getName(), null, organization));
  }


}
