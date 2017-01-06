package com.jakenelson1999.lemonutil.util;

import com.jakenelson1999.lemonutil.LemonUtil;

import scala.Console;

public class ArmourGetValues extends Thread{  
  public void run(){  
	  // This script updates the armour variable every 3 seconds.
    System.out.println("Armour values has started");  
    while(true){
    LemonUtil.setArmourMessage(LemonUtil.getArmourStatus());
    
    try {Thread.sleep(3000);} catch (InterruptedException e) {Console.out().println("ArmourGetValues> Not In-Game.");}}
 }  
}