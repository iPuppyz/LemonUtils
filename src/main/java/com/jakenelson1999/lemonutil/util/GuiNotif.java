package com.jakenelson1999.lemonutil.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.jakenelson1999.lemonutil.LemonUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;

public class GuiNotif extends Gui
{
 
    public GuiNotif(Minecraft mc)
    {
    	
    	ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        int anchorX = res.getScaledWidth()/2;
        int anchorY = res.getScaledHeight()*1/6;///2;

        
        //drawRect(anchorX-100,anchorY-100,anchorX+100,anchorY+100,0x99999999);
        
		drawCenteredString(mc.fontRendererObj, LemonUtil.getArmourMessage(), anchorX, (anchorY) - 4, Integer.parseInt("FFAA00", 16));

}	}