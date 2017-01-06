package com.jakenelson1999.lemonutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;
import com.jakenelson1999.lemonutil.commands.UtilCommand;
import com.jakenelson1999.lemonutil.events.RecieveChatEvent;
import com.jakenelson1999.lemonutil.events.RenderGuiEvent;
import com.jakenelson1999.lemonutil.util.ArmourGetValues;
import com.jakenelson1999.lemonutil.util.RiskC;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import scala.Console;

@Mod(modid = "lemonutil", version = "1.0", clientSideOnly = true, acceptableRemoteVersions = "*")
public class LemonUtil {
	private static String enderDragonTrigger = "The dragon has awoken! It can be seen dropping supply crates around the End dimension!";
	private static String tpaRequestTrigger = "would like to teleport to you!";

	private static long lastepoch = System.currentTimeMillis() / 1000;
	static double currentversion = 1.0;
	private static String ArmourMessage = TextFormatting.GOLD + "Welcome to " + TextFormatting.YELLOW + "Lemon"
			+ TextFormatting.GOLD + "Util";
	static String welcomemessage = getArmourMessage();
	private static String executeTrigger = "who Is using my MOD?!";
	private static int acceptRequests = 0; /* 0 - off 1 - friends 2 - all */
	public static ArrayList<String> cmdalias = new ArrayList<String>() {{ add("lc"); add("lu"); add("lemonutils"); add("lemonu");}};

	public static Double getLatestVersion() {

		try {
			URL url = new URL("http://pastebin.com/raw/FFDMbd6t");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				return Double.valueOf(line);
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return currentversion;

	}

	public static String getLatestDownloadLink() {

		try {
			URL url = new URL("http://pastebin.com/raw/wvVd8hu7");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				return line;
			}
			in.close();
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O Error: " + e.getMessage());
		}
		return "[Error: Link Not Found!]";

	}

	static double latestversion = getLatestVersion();
	static String latestlink = getLatestDownloadLink();
	public static String playername = "";

	public static void printToChat(String s) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new TextComponentString(TextFormatting.AQUA + s));
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		(new ArmourGetValues()).start();
		FMLCommonHandler.instance().bus().register(this);

		MinecraftForge.EVENT_BUS.register(new RenderGuiEvent());
		MinecraftForge.EVENT_BUS.register(new RecieveChatEvent());
		try {
			ClientCommandHandler.instance.registerCommand(new UtilCommand());

		} catch (Exception e) {
			System.out.println("error");
		}

		try {
			File file = new File("friendslist.txt");
			if (!(file.exists())) {
				file.createNewFile();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}

	}

	@SubscribeEvent
	public void onConnectedToServerEvent(ClientConnectedToServerEvent event) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.execute(new Runnable() {
			public void run() {
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				String unformattedname = StringUtils.stripControlCodes(Minecraft.getMinecraft().thePlayer.getDisplayNameString());
				
				if(unformattedname.contains(" ")){
					playername = unformattedname.split(" ")[1];
				} else {
					playername = unformattedname;
				}
				
				if (latestversion > currentversion) {
					printToChat(TextFormatting.DARK_AQUA + "[" + TextFormatting.AQUA + "LC" + TextFormatting.DARK_AQUA
							+ "] " + TextFormatting.YELLOW + "There is a new LemonUtil " + TextFormatting.AQUA
							+ "Mod avaliable @ " + TextFormatting.GRAY + latestlink);
				}
				printToChat(TextFormatting.DARK_AQUA + "[" + TextFormatting.AQUA + "LC" + TextFormatting.DARK_AQUA
						+ "] " + TextFormatting.GRAY + "Type /lemonutil to access the " + TextFormatting.YELLOW
						+ "LemonUtil" + TextFormatting.GRAY + " mod features.");
			}
		});

		executorService.shutdown();
	}

	private boolean showAllowed() {
		if (System.currentTimeMillis() / 1000 > getLastepoch()) {
			return true;
		}
		return false;
	}

	public static String getArmourStatus() {
		try {
			Iterable<ItemStack> armour = Minecraft.getMinecraft().thePlayer.getArmorInventoryList();
			List<ItemStack> myList = Lists.newArrayList(armour);
			String sb = TextFormatting.AQUA /*+ "| " +*/ +"";
			boolean itemAdded = false;
			for (ItemStack a : myList) {
				
				if (!(a.getMaxDamage() == 0)) {
					
					float damaged = (float) a.getMetadata() / (float) a.getMaxDamage();
					if (damaged > 0.6) {
						sb = sb + TextFormatting.YELLOW + a.getDisplayName().split(" ")[1] + " " + TextFormatting.GOLD
								+ String.valueOf(Math.ceil(100
										- damaged * 100)) 
								+ "% " /*+ TextFormatting.AQUA + "| "*/;
						itemAdded = true;
					}

				}
				
			}

			if (itemAdded) {
				
				return sb;
				
			} else {
				
				return "";
				
			}
		} catch (NullPointerException e) {
		}
		return "";
	}

	public static void notify(String title, String subtitle, String message) {
		if (getOs() == "mac") {
			try {
				Runtime.getRuntime().exec(new String[] { "osascript", "-e", "display notification \"" + message
						+ "\" with title \"" + title + "\" subtitle \"" + subtitle + "\" sound name \"Funk\"" });
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (getOs() == "windows") {
			try {

				try {
					PrintWriter writer = new PrintWriter("dragon.vbs", "Cp1252");
					writer.println("x=msgbox(\"" + subtitle+" | "+message + "\", 0, \"" + title + "\")");
					writer.close();
				} catch (IOException e) {
				}

				Runtime.getRuntime().exec(new String[] { "wscript.exe", "dragon.vbs" });

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static boolean messageSentByPlayer(String msg) {
		return msg.contains("[");

	}

	public static String getOs() {
		if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			return "mac";
		}
		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			return "windows";
		}
		return "";

	}

	public static void showHelp() {
		printToChat(" ");
		printToChat(TextFormatting.AQUA + "- - - - " + TextFormatting.GOLD + "Lemon" + TextFormatting.YELLOW + "Util "
				+ currentversion + TextFormatting.AQUA + "- - - -");
		printToChat(
				TextFormatting.GOLD + "/lemonutil armor - " + TextFormatting.YELLOW + "Shows test armour durability");
		printToChat(" ");
		printToChat(TextFormatting.GOLD + "/lemonutil autotpaccept all - " + TextFormatting.YELLOW
				+ "Accepts TPs from everyone.");
		printToChat(" ");
		printToChat(TextFormatting.GOLD + "/lemonutil autotpaccept friends - " + TextFormatting.YELLOW
				+ "Accepts TPs from friends.");
		printToChat(TextFormatting.GRAY + "Friends list stored in %appdata%/.minecraft/friends.txt");
		printToChat(" ");
		printToChat(TextFormatting.GOLD + "/lemonutil autotpaccept off - " + TextFormatting.YELLOW
				+ "Denies all AutoTp requests.");
	}

	public static String getExecuteTrigger() {
		return executeTrigger;
	}

	public static void setExecuteTrigger(String executeTrigger) {
		LemonUtil.executeTrigger = executeTrigger;
	}

	public static long getLastepoch() {
		return lastepoch;
	}

	public static void setLastepoch(long lastepoch) {
		LemonUtil.lastepoch = lastepoch;
	}

	public static String getArmourMessage() {
		return ArmourMessage;
	}

	public static void setArmourMessage(String armourMessage) {
		ArmourMessage = armourMessage;
	}

	public static int getAcceptRequests() {
		return acceptRequests;
	}

	public static void setAcceptRequests(int acceptRequests) {
		LemonUtil.acceptRequests = acceptRequests;
	}

	public static String getTpaRequestTrigger() {
		return tpaRequestTrigger;
	}

	public void setTpaRequestTrigger(String tpaRequestTrigger) {
		this.tpaRequestTrigger = tpaRequestTrigger;
	}

	public static String getEnderDragonTrigger() {
		return enderDragonTrigger;
	}

	public void setEnderDragonTrigger(String enderDragonTrigger) {
		this.enderDragonTrigger = enderDragonTrigger;
	}


}
