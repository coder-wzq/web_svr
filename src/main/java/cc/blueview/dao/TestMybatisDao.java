package cc.blueview.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;


import cc.blueview.config.TargetDataSource;
import cc.blueview.dao.sqlproviders.TestSqlProvider;
import cc.blueview.domain.User;

@TargetDataSource
public interface TestMybatisDao {

	
//	@Select("select param_3 from tab_tickets where ticket_id = #{ticketId}")
//	public String queryTicketFile(@Param("ticketId") Integer ticketId);
	
	@Results({@Result(id = true, column = "user_id", property = "userId"), @Result(column = "user_name", property = "userName")})
	@SelectProvider(type = TestSqlProvider.class, method = "getUserList")
	public List<User> getUserList(String userName);
}
