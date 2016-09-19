package cc.blueview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cc.blueview.domain.User;
import cc.blueview.util.StringUtil;

@Repository
public class UserDao {

	@Autowired
	@Qualifier("jdbcTemp")
	private JdbcTemplate jdbcTemplate;

	public User getUser(String name, String email, String phone, String pwd) throws Exception {
		StringBuffer sql = new StringBuffer("select * from bvc_user where (login_name = ? or email =? or phone =?) ");
		Object[] orgs = new Object[] { name, email, phone };
		if (StringUtil.isNotNull(pwd)) {
			sql.append(" and password =? ");
			orgs = new Object[] { name, email, phone, pwd };
		}
		List<User> users = null;
		// List<CurrentUser> users = jdbcTemplate.query(sql.toString(), orgs,
		// new BeanPropertyRowMapper<CurrentUser>(CurrentUser.class));
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

}
