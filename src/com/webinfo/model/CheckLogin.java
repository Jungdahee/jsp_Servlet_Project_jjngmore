package com.webinfo.model;

import java.util.HashMap;

public class CheckLogin {
	public boolean check(String userId, String userPw, HashMap<String, String> result) {
		boolean permission = false;
		
		if(userId.equals(result.get("userId")) && userPw.equals(result.get("userPw"))) {
			permission = true;
		}
		
		else if(userPw.equals(result.get("userPw")) == false) {
			permission = false;
		}
		
		return permission;
	}
}