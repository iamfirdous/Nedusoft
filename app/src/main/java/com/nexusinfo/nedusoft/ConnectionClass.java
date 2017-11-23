package com.nexusinfo.nedusoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by lukhman on 11/23/2017.
 */

public class ConnectionClass {
    public static final String IP_ADDRESS = "198.204.247.107";
    public static final String MASTER_DB = "master";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "3776x5YN";

    //Table
    public static final String NEDUSOFT_TABLE = "Nedusoft";
    //Columns
    public static final int ID = 0;
    public static final int UniqueID = 1;
    public static final int DomainName = 2;
    public static final int DatabaseName = 3;
    public static final int SchoolName = 4;
    public static final int cmpid = 5;
    public static final int brcode = 6;

    public static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection conn = null;
        try{
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + IP_ADDRESS +"/" + MASTER_DB + ";user=" + USERNAME + ";password=" + PASSWORD);
        }
        catch (Exception e){
            throw e;
        }

        return conn;
    }
}
