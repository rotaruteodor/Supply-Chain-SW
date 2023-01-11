package bGroup.SupplyChainSWbackend.mappers;

import bGroup.SupplyChainSWbackend.dtos.UserDto;
import bGroup.SupplyChainSWbackend.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);
}
