package com.cts.vote.service;

import java.util.List;

import com.cts.vote.domain.User;
import com.cts.vote.domain.VoteBean;
import com.cts.vote.domain.Votes;

public interface VoteService {

    void save (Votes vote);
    
    Votes saveVote (Votes vote); 
    
    public Votes convertToVoteObject(User user, VoteBean vBean);
    
    Votes findByVoteruserid(Long userId);
    
    public boolean validateVotesByUserIdAndSubId(User user, VoteBean vBean);
    
    public List<VoteBean> getVotesSummary();
}
