package com.provisioning.DAOforBeans;

import java.util.HashMap;
import java.sql.*;


import com.provisioning.connectionpool.DataSource;
import com.provisioning.javabeans.DSLAM;

public class DSLAMDAO {

		Boolean shelfStatus[]=new Boolean[6];
		Connection con;
		
		public DSLAMDAO()
		{
			for(int i=0;i<6;i++)
				shelfStatus[i]=false;
			initializeConnection();
		}
		private void initializeConnection()
		{
			try{
			con=DataSource.getConnection();
			}
			catch(ClassNotFoundException e)
			{
				
			}
			catch (SQLException e) {
				
			}
		}
		public DSLAM createRequiredDslam(String dslamid)
		{
			DSLAM dslambean=new DSLAM();
			PonCardDAO poncard=new PonCardDAO();
			DataCardDAO datacard=new DataCardDAO();
			VoiceCardDAO voicecard=new VoiceCardDAO();
			VideoCardDAO videocard=new VideoCardDAO();
			dslambean.setDSLAM_ID(dslamid);
			dslambean.setVoice(voicecard.createVoiceCards(con, dslamid));
			dslambean.setVideo(videocard.createVideoeCards(con, dslamid));
			dslambean.setDc(datacard.createDataCard(con, dslamid));
			dslambean.setPoncard(poncard.createPonCards(con, dslamid));
			return dslambean;
		}
		
		public DSLAM createRequiredDslam(HashMap<String,String> mydslam)
		{
			DSLAM dslambean=new DSLAM();
			PonCardDAO poncard=new PonCardDAO();
			DataCardDAO datacard=new DataCardDAO();
			VoiceCardDAO voicecard=new VoiceCardDAO();
			VideoCardDAO videocard=new VideoCardDAO();
			String dslamid=getRequiredDslam(mydslam);
			dslambean.setDSLAM_ID(dslamid);
			dslambean.setVoice(voicecard.createVoiceCards(con, dslamid));
			dslambean.setVideo(videocard.createVideoeCards(con, dslamid));
			dslambean.setDc(datacard.createDataCard(con, dslamid));
			dslambean.setPoncard(poncard.createPonCards(con, dslamid));
			dslambean.setShelfStatus(getDslamShelfStatus(mydslam));
			return dslambean;
		}
		public String getRequiredDslam(HashMap<String,String> mpdslam)
		{
			int zipcode=Integer.parseInt(mpdslam.get("zipcode"));
			String dslamid=null;
			try
			{
				PreparedStatement pst=con.prepareStatement("SELECT DSLAM_ID FROM DSLAM WHERE ZIP_START<=? AND ZIP_END>=?");
				pst.setInt(1, zipcode);
				pst.setInt(2,zipcode);
				ResultSet rs=pst.executeQuery();
				dslamid=(String)rs.getString("DSLAM_ID");
				rs.close();
				pst.close();
			}
			catch(SQLException e)
			{
				
			}
			return dslamid;
		}
		public Boolean[] getDslamShelfStatus(HashMap<String,String> mapdslam)
		{
			if(mapdslam.get("LOB").equals("WIRELESS"))
				shelfStatus[0]=true;
			else if(mapdslam.get("LOB").equals("VES"))
				shelfStatus[1]=true;
			else
			{
				String servicename=mapdslam.get("servicename");
				shelfStatus[5]=true;
				switch(servicename)
				{
					case "voice": 
						shelfStatus[2]=true;
						break;
					case "video": shelfStatus[3]=true;break;
					case "data":  shelfStatus[4]=true;break;
					case "voice and video": shelfStatus[2]=true;shelfStatus[3]=true;break;
					case "voice and data": shelfStatus[2]=true;shelfStatus[4]=true;break;
					case "video and data": shelfStatus[3]=true;shelfStatus[4]=true;break;
					case "voice and video and data": shelfStatus[2]=true;shelfStatus[3]=true;shelfStatus[4]=true;break;
				}
			}
			return shelfStatus;
		}
		
		public static void main(String [] args)
		{
			HashMap<String , String> myTestMap=new HashMap<String,String>();
			myTestMap.put("LOB","CMB");
			myTestMap.put("customer_id","1234567");
			myTestMap.put("servicename", "voice");
			myTestMap.put("zipcode", "600015");
			DSLAMDAO dd=new DSLAMDAO();
			Boolean test[]=dd.getDslamShelfStatus(myTestMap);
			for(Boolean i:test)
				System.out.println(i);
		}
}
