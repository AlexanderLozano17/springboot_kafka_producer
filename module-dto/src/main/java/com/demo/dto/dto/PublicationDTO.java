package com.demo.dto.dto;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class PublicationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("datePublication")
	private LocalDate datePublication;
	
	@JsonProperty("commentaries")
	private List<CommentaryDTO> commentaries = new ArrayList<>();
		
    public PublicationDTO(Long id, String title, String content, LocalDate datePublication) {
        this.id = id;
    	this.title = title;
        this.content = content;
        this.datePublication = datePublication;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the datePublication
	 */
	public LocalDate getDatePublication() {
		return datePublication;
	}

	/**
	 * @param datePublication the datePublication to set
	 */
	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}

	/**
	 * @return the commentaries
	 */
	public List<CommentaryDTO> getCommentaries() {
		return commentaries;
	}

	/**
	 * @param commentaries the commentaries to set
	 */
	public void setCommentaries(List<CommentaryDTO> commentaries) {
		this.commentaries = commentaries;
	}
}

