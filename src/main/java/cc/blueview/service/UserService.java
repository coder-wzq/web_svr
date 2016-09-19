package cc.blueview.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

	public UserDetails getUser(String name,String email,String phone,String pwd);

}
