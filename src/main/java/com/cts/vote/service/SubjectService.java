package com.cts.vote.service;

import java.util.List;

import com.cts.vote.domain.Subject;

public interface SubjectService {
	
	Subject findByName(String name);
    
    List<Subject> findSubjectList();
}
