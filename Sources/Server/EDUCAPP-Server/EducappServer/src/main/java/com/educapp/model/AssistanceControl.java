package com.educapp.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.educapp.utilities.CustomDateDeserializerSerializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class AssistanceControl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private boolean isPresent;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Enrollment enrollmentReference;
	
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Calendar date;
	
	
	public AssistanceControl(){}
	
	/**
	 * 
	 * Constructor with mandatory fields.
	 */
	public AssistanceControl(Enrollment enrollment, Calendar date, boolean isPresent){
		this.enrollmentReference = enrollment;
		this.date = date;
		this.isPresent = isPresent;
	}
	
	/**
	 * Constructor of object without active status.
	 * 
	 * @param enrollment
	 * @param date
	 */
	public AssistanceControl(Enrollment enrollment, Calendar date){
		this.enrollmentReference = enrollment;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Enrollment getEnrollmentReference() {
		return enrollmentReference;
	}

	public void setEnrollmentReference(Enrollment enrollmentReference) {
		this.enrollmentReference = enrollmentReference;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public static boolean isValid(AssistanceControl ac){
		return (ac.getDate().get(Calendar.HOUR_OF_DAY) == 0
				&& ac.getDate().get(Calendar.MINUTE) == 0
				&& ac.getDate().get(Calendar.SECOND) == 0
				&& ac.getDate().get(Calendar.MILLISECOND) == 0);
	}
}
