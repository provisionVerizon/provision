package com.provisioning.DAOforBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.provisioning.javabeans.DataCard;

public class DataCardDAO {
	DataCard dc;
	public DataCardDAO()
	{
		dc=new DataCard();
	}
	
	public DataCard createDataCard(Connection con,String dslamid)
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("SELECT DATA_CARD_ID,CAPACITY,STATUS FROM DATACARD WHERE DSLAM_ID=?");
			pst.setString(1, dslamid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				dc.setCAPACITY_ALLOCATED(rs.getInt("CAPACITY"));
				dc.setDATACARD_ID(rs.getString("DATA_CARD_ID"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Problem with data card dao " + e.getMessage());
		}
		return dc;
	}

}
