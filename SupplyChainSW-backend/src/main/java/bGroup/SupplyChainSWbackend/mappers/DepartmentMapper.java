package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.DepartmentDto;
import bGroup.SupplyChainSWbackend.entities.Department;
import org.mapstruct.Mapper;

@Mapper
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
}
