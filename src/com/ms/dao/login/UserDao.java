package com.ms.dao.login;

import java.util.List;
import java.util.Map;


public interface UserDao {
	
//	User selectByPrimaryKey(Map<String,String> map);
	
	List selectByPrimaryKey(Map<String,String> map);

}
