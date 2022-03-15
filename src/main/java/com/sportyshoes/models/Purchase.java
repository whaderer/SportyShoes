package com.sportyshoes.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
public class Purchase { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "gross_total")
	private BigDecimal total;
}
