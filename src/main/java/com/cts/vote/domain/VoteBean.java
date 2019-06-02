package com.cts.vote.domain;

import java.io.Serializable;
import java.util.List;

public class VoteBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long selUserId;
	
	private List<Subject> subList;
	
	private List<VoteOption> voteOptList;
	
	private String voter_fullname;
	
	private String vote_subject;
	
	private String vote_option;

	public Long getSelUserId() {
		return selUserId;
	}

	public void setSelUserId(Long selUserId) {
		this.selUserId = selUserId;
	}

	
	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}

	public List<VoteOption> getVoteOptList() {
		return voteOptList;
	}

	public void setVoteOptList(List<VoteOption> voteOptList) {
		this.voteOptList = voteOptList;
	}

	public String getVoter_fullname() {
		return voter_fullname;
	}

	public void setVoter_fullname(String voter_fullname) {
		this.voter_fullname = voter_fullname;
	}

	public String getVote_subject() {
		return vote_subject;
	}

	public void setVote_subject(String vote_subject) {
		this.vote_subject = vote_subject;
	}

	public String getVote_option() {
		return vote_option;
	}

	public void setVote_option(String vote_option) {
		this.vote_option = vote_option;
	}

	
	
}
