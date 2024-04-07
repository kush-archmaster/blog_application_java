package com.blog.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "roles")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	@Id
	@Column(name = "role_id")
	private Long roleId;
	private String name;
}
