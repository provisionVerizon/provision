package com.provisioning.DAOforBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.provisioning.javabeans.VideoCard;


public class VideoCardDAO {

	VideoCard vc;
	ArrayList<VideoCard> videocardlist;
	
	public VideoCardDAO() {
		vc=new VideoCard();
		videocardlist=new ArrayList<VideoCard>();
	}
	
	public ArrayList<VideoCard> createVideoeCards(Connection con,String dslamid)
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("SELECT VIDEO_PORT_ID,STATUS FROM VIDEOCARD WHERE DSLAM_ID=?");
			pst.setString(1, dslamid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String videoportid=rs.getString("VIDEO_PORT_ID");
			    vc.setVIDEO_CARD_ID(videoportid);
			    vc.setSTATUS(rs.getString("STATUS"));
				videocardlist.add(vc);
			}
		}
		catch(SQLException e)
		{
			System.out.println("Problem with video card dao " + e.getMessage());
		}
		return videocardlist;
	}
}
