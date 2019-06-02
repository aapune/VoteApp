package com.cts.vote.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Votes {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "voteid", nullable = false, updatable = false)
    private Long voteid;
    private Long voteruserid;
    private String voter_user_fullname;
    private String vote_option;  
    private String vote_subject;
    private String active;
    private Long votessubid;
  

    
	public String getVote_subject() {
		return vote_subject;
	}


	public void setVote_subject(String vote_subject) {
		this.vote_subject = vote_subject;
	}


	


	public Long getVoteid() {
		return voteid;
	}


	public void setVoteid(Long voteid) {
		this.voteid = voteid;
	}


	public Long getVoteruserid() {
		return voteruserid;
	}


	public void setVoteruserid(Long voteruserid) {
		this.voteruserid = voteruserid;
	}


	public String getVoter_user_fullname() {
		return voter_user_fullname;
	}


	public void setVoter_user_fullname(String voter_user_fullname) {
		this.voter_user_fullname = voter_user_fullname;
	}


	public String getVote_option() {
		return vote_option;
	}


	public void setVote_option(String vote_option) {
		this.vote_option = vote_option;
	}

	

	public String getActive() {
		return active;
	}


	public void setActive(String active) {
		this.active = active;
	}

	
	

	public Long getVotessubid() {
		return votessubid;
	}


	public void setVotessubid(Long votessubid) {
		this.votessubid = votessubid;
	}


	@Override
    public String toString() {
        return "User{" +
                "Id=" + voteid +
                ", userid='" + voteruserid + '\'' +
                ", fullname='" + voter_user_fullname + '\'' +
                '}';
    }

  
  

}
