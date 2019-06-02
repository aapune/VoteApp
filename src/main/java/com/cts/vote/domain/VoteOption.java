package com.cts.vote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vote_option")
public class VoteOption {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String description;  


    
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	public String getDescription() {
		return description;
	}


	public void setDesc(String description) {
		this.description = description;
	}


	

	@Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", desc='" + description + '\'' +
                '}';
    }

  
  

}
