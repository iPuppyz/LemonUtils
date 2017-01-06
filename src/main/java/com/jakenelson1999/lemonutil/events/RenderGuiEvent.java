package com.jakenelson1999.lemonutil.events;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;

import com.jakenelson1999.lemonutil.util.GuiNotif;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderGuiEvent
{
    

	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {
    	if (event.getType() != ElementType.EXPERIENCE) return;
    	new GuiNotif(Minecraft.getMinecraft());
    }
    
	@SubscribeEvent
	public void renderWorldLastEvent(RenderWorldLastEvent evt)
	{/*
		Minecraft mc = Minecraft.getMinecraft();
		
		double doubleX = mc.thePlayer.posX - 0.5;
	double doubleY = mc.thePlayer.posY + 0.1;
	double doubleZ = mc.thePlayer.posZ - 0.5;
	
	
	
	double radius = 10.0;
	GL11.glPushMatrix();
	GL11.glTranslated(-doubleX, -doubleY, -doubleZ);
	GL11.glColor3ub((byte)255,(byte)0,(byte)0);
	float mx = 9;
	float my = 9;
	float mz = 9;
	GL11.glBegin(GL11.GL_LINES);
	GL11.glVertex3f((float)(mx+radius),my,(float)(mz+radius));
	GL11.glVertex3f((float)(mx-radius),my,(float)(mz-radius));
	GL11.glVertex3f((float)(mx+radius),my,(float)(mz-radius));
	GL11.glVertex3f((float)(mx-radius),my,(float)(mz+radius));
	GL11.glEnd();*/
	

	
	
	}
}