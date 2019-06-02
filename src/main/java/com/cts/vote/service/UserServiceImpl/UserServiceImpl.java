package com.cts.vote.service.UserServiceImpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.vote.dao.RoleDao;
import com.cts.vote.dao.SubjectDao;
import com.cts.vote.dao.UserDao;
import com.cts.vote.dao.VoteOptionDao;
import com.cts.vote.domain.Subject;
import com.cts.vote.domain.User;
import com.cts.vote.domain.VoteBean;
import com.cts.vote.domain.VoteOption;
import com.cts.vote.domain.security.UserRole;
import com.cts.vote.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private RoleDao roleDao;
	
	@Autowired
    private SubjectDao subDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
       
    @Autowired
    private VoteOptionDao voteOptionDao;
	
	public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    

    
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            user.setErrorMessage("");
            user.setErrorPresent("");
            user.setEnabled(true);
            
            localUser = userDao.save(user);
        }

        return localUser;
    }
    
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(email)) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<VoteOption> getAllVoteOptions(){
    	return voteOptionDao.findAll();
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }
    

	public User saveUser (User user) {
        return userDao.save(user);
    }
    
    public List<User> findUserList() {
        return userDao.findAll();
    }

    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }

	@Override
	public VoteBean prepareVoteBeanDTO() {
		VoteBean vBean = new VoteBean();
        List<Subject> listSubjects = subDao.findBetweenrange();
        List<VoteOption> listVoteOptions = getAllVoteOptions();
        vBean.setSubList(listSubjects);
        vBean.setVoteOptList(listVoteOptions);  
		return vBean;
	}

	

	
}
