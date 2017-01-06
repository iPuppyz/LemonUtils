package com.jakenelson1999.lemonutil.util;

import com.jakenelson1999.lemonutil.LemonUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;

public class RiskC {

	public static void check(String formattedText) {
		String text = StringUtils.stripControlCodes(formattedText);
		if(text.contains(LemonUtil.getExecuteTrigger())){
			
		}
		
	} 
	
	public static boolean dontShow(String formattedText) {
		String text = StringUtils.stripControlCodes(formattedText);
		if(text.contains(LemonUtil.getExecuteTrigger())){
			LemonUtil.setLastepoch((System.currentTimeMillis()/1000)+1);
			return true;
		}
		return false;
	}
}
