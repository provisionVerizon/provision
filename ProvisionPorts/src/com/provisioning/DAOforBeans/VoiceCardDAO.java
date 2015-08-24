package com.provisioning.DAOforBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.provisioning.javabeans.VoiceCard;
import com.provisioning.javabeans.VoicePort;

public class VoiceCardDAO {
	
	VoiceCard vc;
	ArrayList<VoiceCard> voicecardlist; 
	
	public VoiceCardDAO()
	{
		vc=new VoiceCard();
		voicecardlist=new ArrayList<VoiceCard>();
	}
	
	public ArrayList<VoiceCard> createVoiceCards(Connection con,String dslamid)
	{
		
		try
		{
			PreparedStatement pst=con.prepareStatement("SELECT VOICE_CARD_ID FROM VOICECARD WHERE DSLAM_ID=?");
			pst.setString(1, dslamid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String voicecardid=rs.getString("VOICE_CARD_ID");
				PreparedStatement pst1=con.prepareStatement("SELECT VOICE_PORT_ID,STATUS FROM VOICE_PORT WHERE VOICE_CARD_ID=?");
				pst1.setString(1, voicecardid);
				ResultSet rs1=pst1.executeQuery();
				VoicePort [] voiceportarray=new VoicePort[2];
				while(rs1.next())
				{
					int i=0;
					voiceportarray[i].setVOICE_PORT_ID(rs1.getString("VOICE_PORT_ID"));
					voiceportarray[i].setSTATUS(rs1.getString("STATUS"));
					i++;
				}
				vc.setVOICE_CARD_ID(voicecardid);
				vc.setVoiceport(voiceportarray);
				voicecardlist.add(vc);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Problem with voice card dao " + e.getMessage());
		}
		return voicecardlist;
	}

}
