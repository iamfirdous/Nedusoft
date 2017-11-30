package com.nexusinfo.nedusoft.connection;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.nexusinfo.nedusoft.LocalDBHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by firdous on 11/29/2017.
 */

public class DatabaseConnection extends BaseConnection {

    //Tables
    public static final String TABLE_MSTUDENT = "MStudent";

    //Columns for MSTUDENT
    public static final String COL_ROLL_NO = "RollNo";
    public static final String COL_PASSWORD = "Password";

    public DatabaseConnection(Context context) {
        DB = new LocalDBHelper(context).getDatabaseName();
    }

    public DatabaseConnection(String databaseName) {
        DB = databaseName;
    }

    @Override
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

    @Override
    public String toString() {
        return super.toString() + " DB Name: " + DB;
    }
}
