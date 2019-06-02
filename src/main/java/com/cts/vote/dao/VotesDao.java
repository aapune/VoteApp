package com.cts.vote.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cts.vote.domain.Votes;

public interface VotesDao extends CrudRepository<Votes, Long> {
   
	
    List<Votes> findAll();
    
    Votes findByVoteruserid(Long userId);
    
    /*@Query("SELECT at.accDesc FROM  AccountType at")
    List <String> getAccountTypes();*/
    
    @Query(value = "SELECT * FROM VOTES WHERE VOTERUSERID = ?1 AND VOTESSUBID = ?2 ", nativeQuery = true)
    List<Votes> getVotesByUserIdAndSubId(Long userId, Long subId);
    
    @Modifying
    @Query(value = "UPDATE VOTES v SET v.active = ?1 WHERE v.voteruserid = ?2 AND v.votessubid = ?3 ", nativeQuery = true)
    void inactivateOldVote(String inactiveFlag, Long userId, Long subId);
    
    
    @Query(value = "SELECT * FROM VIEW_VOTES", nativeQuery = true)
    List<Votes> getVoteSummary();
}
