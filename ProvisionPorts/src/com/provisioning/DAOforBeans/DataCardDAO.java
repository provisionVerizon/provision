package com.provisioning.DAOforBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.provisioning.javabeans.DataCard;

// modification in the data card table is required data card has to be a buffer
public class DataCardDAO {

	DataCard dc;
	public DataCardDAO()
	{
		
	}
	
	public DataCard createDataCard(Connection con,String dslamid)
	{
		try
		{
			PreparedStatement pst=con.prepareStatement("SELECT DATACARD_ID,CAPACITY,STATUS FROM DATACARD WHERE DSLAM_ID=?");
			pst.setString(1, dslamid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				dc.setDATACARD_ID(rs.getString("DATACARD_ID"));
				dc.setCAPACITY_ALLOCATED(rs.getInt("CAPACITY"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Problem with data card dao " + e.getMessage());
		}
		return dc;
	}

}
