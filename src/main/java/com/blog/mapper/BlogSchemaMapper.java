package com.blog.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.dtos.UserDto;
import com.blog.entities.User;

@Mapper(componentModel = "spring")
public interface BlogSchemaMapper {

	@Mapping(target = "createdAt", expression = "java(getCurrentTime())")
	User toUser(UserDto userDto);
	
	default Timestamp getCurrentTime() {
		return Timestamp.valueOf(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	}
	
	default String convertTimestampToString(Timestamp timestamp) {
		if(timestamp!=null) {
			String time = timestamp.toString();
			return time.substring(0, time.indexOf("."));
		}
		return null;
	}

	@Mapping(target = "createdAt", expression = "java(convertTimestampToString(user.getCreatedAt()))")
	UserDto toUserDto(User user);;
}
