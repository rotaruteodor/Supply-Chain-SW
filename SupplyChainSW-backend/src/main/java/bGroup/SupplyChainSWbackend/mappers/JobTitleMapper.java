package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.JobTitleDto;
import bGroup.SupplyChainSWbackend.entities.JobTitle;
import org.mapstruct.Mapper;

@Mapper
public interface JobTitleMapper {
    JobTitleDto toDto(JobTitle jobTitle);
}
