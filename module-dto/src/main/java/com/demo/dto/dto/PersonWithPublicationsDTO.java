package com.demo.dto.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class PersonWithPublicationsDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@JsonProperty("id")
    private Long id;	
    
    @JsonProperty("names")
	private String names;
    
    @JsonProperty("lastNames")
	private String lastNames;
    
    @JsonProperty("age")
	private int age;
    
    @JsonProperty("email")
	private String email;
    
    @JsonProperty("telephone")
	private String telephone;
    
    @JsonProperty("publications")
    List<PublicationDTO> publications;
    
	public PersonWithPublicationsDTO(Long id, String names, String lastNames, int age, String email,
			String telephone, List<PublicationDTO> publications) {
		super();
		this.id = id;
		this.names = names;
		this.lastNames = lastNames;
		this.age = age;
		this.email = email;
		this.telephone = telephone;
		this.publications = publications;
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
}
