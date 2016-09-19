package cc.blueview.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cc.blueview.domain.User;

@Repository
public class TestDao {

	@Autowired
	@Qualifier("jdbcTemp")
	private JdbcTemplate jdbcTemplate;

	public String getTestResult() {
//		User user = jdbcTemplate.queryForObject("select * from bvc_user limit 1", new RowMapper<User>() {
//			@Override
//			public User mapRow(ResultSet resultSet, int i) throws SQLException {
//				User user = new User();
//
//				user.setUserId(Integer.valueOf(resultSet.getString("user_id")));
//				user.setUserName(resultSet.getString("user_name"));
//
//				return user;
//			}
//		});
		return jdbcTemplate.queryForObject("select user_name from bvc_user limit 1", String.class);
	}
	

}
