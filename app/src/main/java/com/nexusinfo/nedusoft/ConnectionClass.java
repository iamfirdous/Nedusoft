package com.nexusinfo.nedusoft;

import android.os.StrictMode;
import android.util.Log;

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
    public static final String ID = "ID";
    public static final String UNIQUE_ID = "UniqueID";
    public static final String DOMAIN_NAME = "DomainName";
    public static final String DATABASE_NAME = "DatabaseName";
    public static final String SCHOOL_NAME = "SchoolName";
    public static final String COMPANY_ID = "cmpid";
    public static final String BARCODE = "brcode";

    public static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

    public static Connection getConnection() {
        Connection conn = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(DRIVER); //.newInstance();
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + IP_ADDRESS + ";" + "databaseName=" + MASTER_DB + ";user=" +   USERNAME + ";password=" + PASSWORD + ";");
        }
        catch (SQLException e){
            Log.e("Exception", e.toString());
        }
        catch (ClassNotFoundException e){
            Log.e("Exception", e.toString());
        }
        catch (Exception e){
            Log.e("Exception", e.toString());
        }

        return conn;
    }
}
