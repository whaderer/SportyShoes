package com.sportyshoes.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Lombok - A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, and @Setter on all non-final fields, and @RequiredArgsConstructor
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
public class Admin { 

	@Id
	// define the identifier generation strategy
	@GeneratedValue(strategy = GenerationType.AUTO)
	// not "necessary", unless you want to override default column naming, or default datastore column type etc.
	@Column(name = "ID")
	private Long ID;

	@Column(name = "admin_id")
	private String adminId;
	
	@Column(name = "admin_pwd")
	private String adminPwd;

}
