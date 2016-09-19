package cc.blueview.dao.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cc.blueview.ZadApplication;
import cc.blueview.authentication.CurrentUser;
import cc.blueview.dao.TestDao;
import cc.blueview.dao.UserDao;
import cc.blueview.domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ZadApplication.class)
@WebAppConfiguration
public class DaoTest {

	@Autowired
	@Qualifier("jdbcTemp")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TestDao testDao;
	
	
	@Test
	public void testDAOFactory() {
		String user = testDao.getTestResult();
		System.out.println(user);
	}

}
