package com.nexusinfo.nedusoft.connection;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by firdous on 11/23/2017.
 */

public class SchoolCodeConnection extends BaseConnection {

    //Tables
    public static final String TABLE_NEDUSOFT = "Nedusoft";

    //Columns for TABLE_NEDUSOFT
    public static final String COL_ID = "ID";
    public static final String COL_UNIQUE_ID = "UniqueID";
    public static final String COL_DOMAIN_NAME = "DomainName";
    public static final String COL_DATABASE_NAME = "DatabaseName";
    public static final String COL_SCHOOL_NAME = "SchoolName";
    public static final String COL_COMPANY_ID = "cmpid";
    public static final String COL_BARCODE = "brcode";

    public SchoolCodeConnection() {
        DB = "master";
    }

    public Connection getConnection() {
        Connection conn = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(DRIVER); //.newInstance();
            conn = DriverManager.getConnection(CONN_STRINGS[0] + IP_ADDRESS + CONN_STRINGS[1] + DB + CONN_STRINGS[2] + DB_USERNAME + CONN_STRINGS[3] + DB_PASSWORD + CONN_STRINGS[4]);
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
