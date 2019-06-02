package com.cts.vote.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.vote.domain.VoteOption;

public interface VoteOptionDao extends CrudRepository<VoteOption, Long> {
    
    List<VoteOption> findAll();

}
