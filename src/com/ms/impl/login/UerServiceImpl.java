package com.ms.impl.login;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ms.dao.login.UserDao;
import com.ms.service.login.IUserService;
@Service("userService")
public class UerServiceImpl implements IUserService {

	@Resource
	private UserDao userDao;
//	public User login(Map<String,String> map) {
//		return this.userDao.selectByPrimaryKey(map); 
//	}
	
	public List login(Map<String,String> map) {
		return this.userDao.selectByPrimaryKey(map);  
	}
}
