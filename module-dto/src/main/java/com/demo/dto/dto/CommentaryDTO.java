package com.demo.dto.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class CommentaryDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("publication_id")
	private Long publication_id;
	
	@JsonProperty("person_id")
	private Long person_id;
	
	@JsonProperty("dateCommentary")
	private LocalDate dateCommentary;
	
	@JsonProperty("content")
	private String content;
	
	public CommentaryDTO(Long id, Long publication_id, Long person_id, LocalDate dateCommentary, String content) {
		super();
		this.id = id;
		this.publication_id = publication_id;
		this.person_id = person_id;
		this.dateCommentary = dateCommentary;
		this.content = content;
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
	 * @return the publication_id
	 */
	public Long getPublication_id() {
		return publication_id;
	}

	/**
	 * @param publication_id the publication_id to set
	 */
	public void setPublication_id(Long publication_id) {
		this.publication_id = publication_id;
	}

	/**
	 * @return the person_id
	 */
	public Long getPerson_id() {
		return person_id;
	}

	/**
	 * @param person_id the person_id to set
	 */
	public void setPerson_id(Long person_id) {
		this.person_id = person_id;
	}

	/**
	 * @return the dateCommentary
	 */
	public LocalDate getDateCommentary() {
		return dateCommentary;
	}

	/**
	 * @param dateCommentary the dateCommentary to set
	 */
	public void setDateCommentary(LocalDate dateCommentary) {
		this.dateCommentary = dateCommentary;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
