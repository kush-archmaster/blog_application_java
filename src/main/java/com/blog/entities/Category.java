package com.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "categories")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

	@Id
	@GeneratedValue(generator="category_seq")
	@SequenceGenerator(name="category_seq",sequenceName="MY1_SEQ", allocationSize=1)
	private Long categoryId;
	
	@Column(length = 100, nullable = false)
	private String categoryName;
	
	private String description;
}
