package com.cts.vote.service.UserServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.vote.dao.SubjectDao;
import com.cts.vote.domain.Subject;
import com.cts.vote.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SubjectService.class);
	
	@Autowired
	private SubjectDao subDao;

	@Override
    public List<Subject> findSubjectList() {
        return subDao.findAll();
    }

	@Override
	public Subject findByName(String name) {
		LOG.info("findby name : "+name);
		return subDao.findByName(name);
	}


}
