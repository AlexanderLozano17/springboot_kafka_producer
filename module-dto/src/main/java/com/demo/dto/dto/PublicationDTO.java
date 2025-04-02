package com.demo.dto.dto;


import java.io.Serializable;
import java.time.LocalDate;

public class PublicationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;	
	private String title;
	private String content;
	private LocalDate datePublication;
		
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
}

