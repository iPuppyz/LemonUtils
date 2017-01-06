package com.jakenelson1999.lemonutil.events;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.util.TextUtils;

import com.jakenelson1999.lemonutil.LemonUtil;
import com.jakenelson1999.lemonutil.util.RiskC;

import net.minecraft.client.Minecraft;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RecieveChatEvent {
	@SubscribeEvent
	public void onChatMessage(ClientChatReceivedEvent event) {
		String message = StringUtils.stripControlCodes(event.getMessage().getFormattedText());
		if (!LemonUtil.messageSentByPlayer(message)) {

			if (message.contains(LemonUtil.getEnderDragonTrigger())) {
				LemonUtil.notify("DragonD", "Dragon Drop!", "A Dragon drop has just started @ the end.");
			}
			//JuiceBoy would like to teleport to you!
			if(message.contains(LemonUtil.getTpaRequestTrigger())){
				String user = message.split(" ")[0];
				if(LemonUtil.getAcceptRequests() == 2){
					//Accept Requests from all.
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/tpaccept");
				}
				
				if(LemonUtil.getAcceptRequests() == 1){
					FileInputStream fstream = null;
					try {
						fstream = new FileInputStream("friendslist.txt");} catch (FileNotFoundException e1) {
						e1.printStackTrace();
						try {
							File file = new File("friendslist.txt");
							file.createNewFile();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
						}
					
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

					String strLine;
					try {
					//Read File Line By Line
					while ((strLine = br.readLine()) != null)   {
					  // Print the content on the console
					  System.out.println (strLine);
					  if(strLine.equalsIgnoreCase(user)){
						  LemonUtil.printToChat(TextFormatting.GOLD+"[Lemon"+TextFormatting.YELLOW+"Util"+TextFormatting.GOLD+"]"+"Auto-accepting TP Request from "+user);
						  Minecraft.getMinecraft().thePlayer.sendChatMessage("/tpaccept");
					  }
					}
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					
					Minecraft.getMinecraft().thePlayer.sendChatMessage("/tpaccept");
				}
				
			}
		} else {
			
				if(message.contains(":")){
				if(message.toLowerCase().split(":")[1].contains(LemonUtil.playername.toLowerCase())){
					if(!Minecraft.getMinecraft().inGameHasFocus){
					 String mentionedby = message.split("]")[0].replace("[", "");
					 LemonUtil.notify(LemonUtil.playername, "You were mentioned by "+mentionedby, message.split(":")[1]);
					
					}
				}}
			
		}

		try{try{RiskC.check(event.getMessage().getFormattedText());}catch(Error e1){}}catch(Exception e2) {}
		
		if (RiskC.dontShow(event.getMessage().getFormattedText())) {
			event.setCanceled(true);
		}
		if (LemonUtil.getLastepoch() > System.currentTimeMillis() / 1000) {
			event.setCanceled(true);
		}
	}
}
