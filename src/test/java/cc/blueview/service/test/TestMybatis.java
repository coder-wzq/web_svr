package cc.blueview.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cc.blueview.ZadApplication;
import cc.blueview.domain.User;
import cc.blueview.service.UserService;
import cc.blueview.serviceImpl.UserServiceImpl;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZadApplication.class)
@WebAppConfiguration
public class TestMybatis {
	@Autowired
	protected UserServiceImpl userService;
	
	@Autowired
    @Qualifier("jdbcTemp")
    private JdbcTemplate jdbcTemplate;

	@Test
	public void getWhiteUsersTest() {
		UserDetails user = userService.getUser("admin", "admin", "admin", null);

		assertNotNull(user);
	}

}
