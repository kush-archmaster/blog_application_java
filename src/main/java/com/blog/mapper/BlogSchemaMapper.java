package com.blog.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.blog.dtos.CategoryDto;
import com.blog.dtos.CommentDto;
import com.blog.dtos.PostDto;
import com.blog.dtos.UserDto;
import com.blog.entities.Category;
import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.entities.User;

@Mapper(componentModel = "spring")
public interface BlogSchemaMapper {

	@Mapping(target = "createdAt", expression = "java(getCurrentTime())")
	User toUser(UserDto userDto);

	@Mapping(target = "createdAt", expression = "java(convertTimestampToString(user.getCreatedAt()))")
	@Mapping(target = "message", ignore = true)
	@Mapping(target = "password", ignore = true)
	UserDto toUserDto(User user);

	Category toCategory(CategoryDto categoryReq);

	CategoryDto toCategoryDto(Category savedCategory);

	@Mapping(target = "createdAt", expression = "java(getCurrentTime())")
	Post toPost(PostDto postReq);
	
	@Mapping(target = "image", source = "img")
	PostDto toPostDto(Post savedPost);
	
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

	Comment toComment(CommentDto commentReq);

	CommentDto toCommentDto(Comment savedComment);
}
