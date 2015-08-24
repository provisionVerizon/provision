package com.provisioning.connectionpool;

public class Configuration {
	
	public String DB_USERNAME;
	public String DB_PASSWORD;
	public String DB_URL;
	public int DB_MAX_CONNECTIONS;
	public String DB_DRIVER;
	
	public Configuration()
	{
		init();
	}
	
	private static Configuration configuration=new Configuration();
	
	public static Configuration getInsatance()
	{
		return configuration;
	}
	
	private void  init()
	{
		DB_DRIVER="oracle.jdbc.driver.OracleDriver";
		DB_URL="jdbc:oracle:thin:@localhost:1521:xe";
		DB_USERNAME="HR";
		DB_PASSWORD="tiger";
		DB_MAX_CONNECTIONS=10;
	}
	
}
