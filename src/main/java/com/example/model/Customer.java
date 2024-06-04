package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min=0, max=15)
	private String name;
	@NotNull
	@Column(unique=true)
	@Size(min=0, max=15)
	private String userName;
	@NotNull
	@Size(min=0, max=15)
	private String password;
	@NotNull
	@Min(value=10)
	@Max(value=90)
	private int age;
	@NotNull
	@Column(unique=true)
	@Size(min=0, max=15)
	private String accNum;
	@NotNull
	private String address;
	@NotNull
	@Size(min=0, max=25)
	private String email;
	@NotNull
	@Size(min=0, max=10)
	private String phone;
	@NotNull
	@Min(value=2500)
	private double balance;
}
