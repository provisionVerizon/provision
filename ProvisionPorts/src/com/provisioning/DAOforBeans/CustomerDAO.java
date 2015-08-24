package com.provisioning.DAOforBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.provisioning.connectionpool.DataSource;
import com.provisioning.javabeans.Customer;
import com.provisioning.javabeans.DataCard;
import com.provisioning.javabeans.ONT;
import com.provisioning.javabeans.PonPort;
import com.provisioning.javabeans.VideoCard;
import com.provisioning.javabeans.VoicePort;

public class CustomerDAO {
	Customer c;
	ONT ont;
	VoicePort vp;
	VideoCard vc;
	DataCard dc;
	PonPort pp;
	Connection con;
	public CustomerDAO ()
	{
		c=new Customer();
		ont=new ONT();
		vp=new VoicePort();
		vc=new VideoCard();
		dc=new DataCard();
		pp=new PonPort();
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
	//implement the wireless and ves module according to the customer type
	public Customer getCustomerDetails(String customerid)
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("select CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_TYPE,STREETNAME,ZIPCODE,CITY,STATE,COUNTRY from customer where CUSTOMER_ID=?");
			pst.setString(1, customerid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				c.setCUSTOMER_ID(rs.getString("CUSTOMER_ID"));
				c.setCUSTOMER_NAME(rs.getString("CUSTOMER_NAME"));
				c.setCUSTOMER_TYPE(rs.getString("CUSTOMER_TYPE"));
				c.setSTREETNAME(rs.getString("STREETNAME"));
				c.setZIPCODE(Integer.parseInt(rs.getString("ZIPCODE")));
				c.setCITY(rs.getString("CITY"));
				c.setSTATE(rs.getString("STATE"));
				c.setCOUNTRY(rs.getString("COUNTRY"));
			}
			
			pp=getRequiredPonPort(customerid);
			vp=getRequiredVoicePort(pp);
			vc=getRequiredVideoCard(pp);
			dc=getRequiredDataCard(pp);
			ont=getRequiredOnt(pp);
			c.setPp(pp);
			c.setVc(vc);
			c.setOnt(ont);
			c.setDc(dc);
			c.setVp(vp);
			
		}
		catch(SQLException e)
		{
			
		}
		return c;
	}
	
	private PonPort getRequiredPonPort(String customerid)
	{
		 try
		 {
			 PreparedStatement pst=con.prepareStatement("select PON_PORT_ID from ONT where CUSTOMER_ID=?");
			 pst.setString(1, customerid);
			 ResultSet rs=pst.executeQuery();
			 if(rs.next())
			 {
				 PreparedStatement pst1=con.prepareStatement("select STATUS from PON_PORT where PON_PORT_ID=?");
				 pst.setString(1, rs.getString("PON_PORT_ID"));
				 ResultSet rs1=pst.executeQuery();
				 pp.setPON_PORT_ID(rs.getString("PON_PORT_ID"));
				 pp.setSTATUS(rs1.getString("STATUS"));
			 }
			 else
			 {
				 System.out.println("customer is a new user");
			 }
		 }
		 catch(SQLException e)
		 {
			 
		 }
		return pp;
	}
	
	// if more than one voice port then we have to use a voice port array to implement the functionality
	private VoicePort getRequiredVoicePort(PonPort p)
	{
		String ponportid=p.getPON_PORT_ID();
		try
		{
			PreparedStatement pst=con.prepareStatement("select VOICE_PORT_ID,STATUS from voice_port where PON_PORT_ID=?");
			pst.setString(1, ponportid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				vp.setVOICE_PORT_ID(rs.getString("VOICE_PORT_ID"));
				vp.setSTATUS(rs.getString("STATUS"));
			}
		}
		catch(SQLException e)
		{
			
		}
		vp.setPp(p);
		return vp;
	}
	 private ONT getRequiredOnt(PonPort p)
	 {
		 String ponportid=p.getPON_PORT_ID();
		 try
		 {
			 PreparedStatement pst=con.prepareStatement("select ONT_ID,STATUS from ONT where PON_PORT_ID=?");
			 pst.setString(1, ponportid);
			 ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					ont.setONT_ID(rs.getString("ONT_ID"));
					ont.setSTATUS(rs.getString("STATUS"));
				}
		 }
		 catch(SQLException e)
		 {
			 
		 }
		 ont.setPp(p);
		 return ont;
	 }
	 
	 private VideoCard getRequiredVideoCard(PonPort p)
	 {
		 String ponportid=p.getPON_PORT_ID();
		 try
		 {
			 PreparedStatement pst=con.prepareStatement("select VIDEO_PORT_ID,STATUS from VIDEO_CARD where PON_PORT_ID=?");
			 pst.setString(1, ponportid);
			 ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					vc.setVIDEO_CARD_ID(rs.getString("VIDEO_PORT_ID"));
					vc.setSTATUS(rs.getString("STATUS"));
				}
		 }
		 catch(SQLException e)
		 {
			 
		 }
		 vc.setPp(p);
		 return vc;
	 }
	 
	 private DataCard getRequiredDataCard(PonPort p)
	 {
		 String ponportid=p.getPON_PORT_ID();
		 try
		 {
			 PreparedStatement pst=con.prepareStatement("select DATACARD_ID,CAPACITY,STATUS from DATA_CARD where PON_PORT_ID=?");
			 pst.setString(1, ponportid);
			 ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
					dc.setDATACARD_ID(rs.getString("VIDEO_PORT_ID"));
					dc.setCAPACITY_ALLOCATED(rs.getInt("CAPACITY"));
					dc.setSTATUS(rs.getString("STATUS"));
				}
		 }
		 catch(SQLException e)
		 {
			 
		 }
		 dc.setPp(p);
		 return dc;
	 }
}
