package com.gl.employeesapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@EqualsAndHashCode(exclude = "roles")
@ToString(exclude = "roles")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	
	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	@Column(name="ROLES")
	private Set<Role> roles;
	
	public void addRole(Role role) {
		if(this.roles == null) {
			this.roles = new HashSet<Role>();
		}
		
		this.roles.add(role);
		role.getUsers().add(this);
	}
}
