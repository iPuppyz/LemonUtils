package com.jakenelson1999.lemonutil.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.jakenelson1999.lemonutil.LemonUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class UtilCommand implements ICommand {

	@Override
	public int compareTo(ICommand o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkPermission(MinecraftServer arg0, ICommandSender arg1) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		LemonUtil.printToChat(LemonUtil.playername);
		if(args.length == 1 || args.length == 2){
			if(args[0].toLowerCase().contains("armour") || args[0].toLowerCase().contains("armor")){
				LemonUtil.printToChat(LemonUtil.getArmourStatus());
			}
			if(args[0].toLowerCase().contains("autotpaccept") && args[1].toLowerCase().contains("all")){
				LemonUtil.printToChat("> Accepting TP Requests from "+TextFormatting.WHITE+""+TextFormatting.BOLD+"All");
				LemonUtil.setAcceptRequests(2);
			}
			if(args[0].toLowerCase().contains("autotpaccept") && args[1].toLowerCase().contains("friends")){
				LemonUtil.printToChat("> Accepting TP Requests from "+TextFormatting.WHITE+""+TextFormatting.BOLD+"Friends");
				LemonUtil.setAcceptRequests(1);
			}
			
			if(args[0].toLowerCase().contains("autotpaccept") && args[1].toLowerCase().contains("off")){
				LemonUtil.printToChat("> Accepting TP Requests from "+TextFormatting.WHITE+""+TextFormatting.BOLD+"No-one");
				LemonUtil.setAcceptRequests(1);
			}
			if(args[0].toLowerCase().contains("whoisonline")){
			    Minecraft.getMinecraft().thePlayer.sendChatMessage("whoisonline");
			   } 

			
		} else{
			LemonUtil.showHelp();
		}
        
	}

	@Override
	public List<String> getCommandAliases() {
		return LemonUtil.cmdalias;
	}

	@Override
	public String getCommandName() {
		return "lemonutil";
	}

	@Override
	public String getCommandUsage(ICommandSender arg0) {
		return null;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer arg0, ICommandSender arg1, String[] arg2,
			BlockPos arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
