package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByCategory(Category category);

	List<Post> findByUser(User user);

	List<Post> findByTitleContaining(String keyword);

}
