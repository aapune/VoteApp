package com.cts.vote.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.vote.dao.VotesDao;
import com.cts.vote.domain.User;
import com.cts.vote.domain.VoteBean;
import com.cts.vote.domain.Votes;
import com.cts.vote.service.VoteService;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {
	
	private static final Logger LOG = LoggerFactory.getLogger(VoteService.class);
   
    @Autowired
    private VotesDao voteDao;
	    


	@Override
	public void save(Votes vote) {
		voteDao.save(vote);
		
	}

	@Override
	public Votes saveVote(Votes vote) {
		return voteDao.save(vote);
	}

	@Override
	public Votes convertToVoteObject(User user, VoteBean vBean) {
		Votes vote = new Votes();
		vote.setVote_option(vBean.getVoteOptList().get(0).getDescription());
		vote.setVote_subject(vBean.getSubList().get(0).getDescription());
		vote.setVoteruserid(user.getUserId());
		vote.setVoter_user_fullname(user.getFirstName()+" "+user.getLastName());
		vote.setVotessubid(vBean.getSubList().get(0).getId());
		vote.setActive("Y");
		return vote;		
	}

	@Override
	public Votes findByVoteruserid(Long userId) {
		return voteDao.findByVoteruserid(userId);
	}

	@Override
	public boolean validateVotesByUserIdAndSubId(User user, VoteBean vBean) {
		List<Votes> existingVotes = voteDao.getVotesByUserIdAndSubId(user.getUserId(), vBean.getSubList().get(0).getId());
		if(existingVotes != null) {
			if(existingVotes.size() <=1) {
				
				if(existingVotes.size() >0 && existingVotes.get(0).getVote_option().equalsIgnoreCase(vBean.getVoteOptList().get(0).getDescription())) {
					LOG.info("validation failed");
					return false;
				}else {
					return true;
				}			
				
			}else {
				return false;
			}
		}else {
			return true;
		}
	}

	@Override
	public List<VoteBean> getVotesSummary() {
		List<Votes> voteList = voteDao.getVoteSummary();
		List<VoteBean> listVoteBean = new ArrayList<>();
		if(voteList != null) {
			for(Votes vote: voteList) {
				VoteBean bean  = new VoteBean();
				bean.setVoter_fullname(vote.getVoter_user_fullname());
				bean.setVote_subject(vote.getVote_subject());
				bean.setVote_option(vote.getVote_option());
				listVoteBean.add(bean);
			}
		}
		return listVoteBean;
	}

	

	
    
   
	
}
