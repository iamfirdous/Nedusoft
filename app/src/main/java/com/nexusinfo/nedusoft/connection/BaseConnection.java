package com.nexusinfo.nedusoft.connection;

import java.sql.Connection;

/**
 * Created by lukhman on 11/29/2017.
 */

public abstract class BaseConnection {
    protected static final String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

    protected static final String[] CONN_STRINGS = { "jdbc:jtds:sqlserver://" , ";databaseName=" , ";user=" , ";password=" , ";" };

    protected static final String IP_ADDRESS = "198.204.247.107";
    protected static final String DB_USERNAME = "sa";
    protected static final String DB_PASSWORD = "3776x5YN";

    protected String DB;

    public abstract Connection getConnection();
}
