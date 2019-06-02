package com.cts.vote.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cts.vote.domain.Subject;

public interface SubjectDao extends CrudRepository<Subject, Long> {
    
	Subject findByName(String name);
	
    List<Subject> findAll();
    
    @Query(value ="SELECT * FROM SUBJECT WHERE CURDATE() between start_date and end_date", nativeQuery = true)
    List<Subject> findBetweenrange();
    
}
