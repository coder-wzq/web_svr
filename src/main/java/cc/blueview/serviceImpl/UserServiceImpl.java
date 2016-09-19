package cc.blueview.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cc.blueview.authentication.CurrentUser;
import cc.blueview.dao.TestMybatisDao;
import cc.blueview.dao.UserDao;
import cc.blueview.domain.User;
import cc.blueview.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	TestMybatisDao testMybatisDao;

	@Override
	public UserDetails getUser(String name, String email, String phone, String pwd) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			List<User> users = testMybatisDao.getUserList(name);
			if (users.size()!=1||users==null) {
				return null;
			}
			User user = users.get(0);
			if (true) {
				authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
			}
			return new CurrentUser(user, authorities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
