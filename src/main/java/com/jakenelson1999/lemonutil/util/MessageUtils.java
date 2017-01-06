package com.jakenelson1999.lemonutil.util;

import com.jakenelson1999.lemonutil.LemonUtil;

public class MessageUtils {
	public boolean isPlayerMentioned(String s){
		if(s.contains("]")){
			if(s.toLowerCase().split("]")[1].contains(LemonUtil.playername)){
				return true;
			}
		} else {
			if(s.toLowerCase().contains(LemonUtil.playername)){
				return true;
			}
		}
		return false;
		
	}
}
