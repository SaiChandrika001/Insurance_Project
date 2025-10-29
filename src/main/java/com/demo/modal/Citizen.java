package com.demo.modal;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Citizen")
public class Citizen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
	
	@Column(name="FullName")
	private String fullName;
	
	@Column(name="MobilNumber")
	private Long mobileNumber;
	
	@Column(name="	Email")
	private String email;
	
	@Column(name="Gender")
	private String gender;
	
	@Column(name="SSN" ,length=9, unique = true)
	private Long ssn;
	
	@Column(name="DateOfBirth")
	private LocalDate dateofBirth;
	
	@Column(name="StateName")
	private String stateName;
	
	@Column(name="Created_By")
    private String createdBy;
	
	@Column(name="Updated_By")
    private String updatedBy;
	
	@PrePersist
	public void onCreate() {
	    this.createdDate = LocalDate.now();
	    this.createdBy = "System"; // or logged-in user
	}

	@PreUpdate
	public void onUpdate() {
	    this.updatedDate = LocalDate.now();
	    this.updatedBy = "System";
	}
	
	@Column(name = "InsertedTime", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	@Column(name = "UpdateTime", insertable = false)
	private LocalDate updatedDate;

	
	
}
