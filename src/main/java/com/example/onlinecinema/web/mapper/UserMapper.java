package com.example.onlinecinema.web.mapper;

import com.example.onlinecinema.domain.user.UserEntity;
import com.example.onlinecinema.web.DTO.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends Mappable<UserEntity, UserDTO> {
}
