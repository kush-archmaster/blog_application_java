package com.blog.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(generator="user_seq")
	@SequenceGenerator(name="user_seq",sequenceName="MY_SEQ", allocationSize=1)
	private Long id;
	@Column(name = "user_name", nullable = false, length = 100)
	private String name;
	@Column(name = "user_email")
	private String email;
	private String password;
	private String about;
	private Timestamp createdAt;
}
