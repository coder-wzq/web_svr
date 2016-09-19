package cc.blueview.dao.sqlproviders;

/**
 * 
 * @author WZQ date on 2016年5月18日
 */
public class TestSqlProvider {

	public String getUserList(String userName) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from bvc_user where user_name like '"+userName+"'");
		return sql.toString();
	}
}
