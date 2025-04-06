package com.demo.dto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @JsonProperty("id")
    private Long id;	
    
    @JsonProperty("names")
	private String names;
    
    @JsonProperty("lastNames")
	private String lastNames;
    
    @JsonProperty("fullName")
	private String fullName;
    
    @JsonProperty("age")
	private int age;
    
    @JsonProperty("email")
	private String email;
    
    @JsonProperty("telephone")
	private String telephone;
    
    @JsonProperty("publications")
    private List<PublicationDTO> publications = new ArrayList<>();
       
	 public PersonDTO(Long id, String names, String lastNames, int age, String email, String telephone) {
		this.id = id;
		this.names = names;
		this.lastNames = lastNames;
		this.fullName = names + " " + lastNames;
		this.age = age;
		this.email = email;
		this.telephone = telephone;
	}
	 	 
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the names
	 */
	public String getNames() {
		return names;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(String names) {
		this.names = names;
	}

	/**
	 * @return the lastNames
	 */
	public String getLastNames() {
		return lastNames;
	}

	/**
	 * @param lastNames the lastNames to set
	 */
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}


	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the publications
	 */
	public List<PublicationDTO> getPublications() {
		return publications;
	}

	/**
	 * @param publications the publications to set
	 */
	public void setPublications(List<PublicationDTO> publications) {
		this.publications = publications;
	}

	@Override
    public String toString() {
        return "PersonDTO{" +
                ", nombres='" + names + '\'' +
                ", apellidos='" + lastNames + '\'' +
                ", edad=" + age +
                ", email='" + email + '\'' +
                ", telefono='" + telephone + '\'' +
                '}';
    }
}