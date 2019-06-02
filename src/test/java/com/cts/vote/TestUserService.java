package com.cts.vote;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.vote.domain.VoteBean;
import com.cts.vote.domain.VoteOption;
import com.cts.vote.service.UserService;
import com.cts.vote.service.UserServiceImpl.UserServiceImpl;

/**
 * Junit test case class for UserService
 * 
 * @author Aniruddha Anikhindi
 *
 */

@SpringBootTest(classes = { UserService.class})
@EnableConfigurationProperties
public class TestUserService {

    private UserService userService = new UserServiceImpl();

	@Test
	public void testVoteOptionsNotNull() {
		List<VoteOption> list = userService.getAllVoteOptions();
		assertNotNull(list);
	}

	@Test
	public void testVoteBeanNotNull() {
		VoteBean bean = userService.prepareVoteBeanDTO();
		assertNotNull(bean);
	}

}
