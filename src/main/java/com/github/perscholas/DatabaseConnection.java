package com.github.perscholas;

import com.github.perscholas.utils.ConnectionBuilder;
import com.github.perscholas.utils.IOConsole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by leon on 2/18/2020.
 */
public enum DatabaseConnection implements DatabaseConnectionInterface {
    PRODUCTION_DATABASE(new ConnectionBuilder()
            .setUser("root")
            .setPassword("")
            .setPort(3306)
            .setDatabaseVendor("mariadb")
            .setHost("127.0.0.1")),

    TESTING_DATABASE(new ConnectionBuilder()
            .setUser("root")
            .setPassword("")
            .setPort(3306)
            .setDatabaseVendor("mariadb")
            .setHost("127.0.0.1"));

    private static final IOConsole console = new IOConsole(IOConsole.AnsiColor.CYAN);
    private volatile ConnectionBuilder connectionBuilder;

    DatabaseConnection(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public String getDatabaseName() {
        return name().toLowerCase();
    }

    @Override
    public Connection getDatabaseConnection() {
        return new ConnectionBuilder(connectionBuilder)
                .setDatabaseName(name().toLowerCase())
                .build();
    }

    @Override
    public Connection getDatabaseEngineConnection() {
        return connectionBuilder.build();
    }

    @Override
    public void drop() {
        try {
            String sqlStatement = "DROP DATABASE IF EXISTS " + getDatabaseName() + ";";
            String successMessage = String.format("Unsuccessfully executed statement \n\t`%s`", sqlStatement);
            // TODO - Complete method implementation
            throw new SQLException(new UnsupportedOperationException("Method not yet implemented"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create() {
        String sqlStatement = "CREATE DATABASE IF NOT EXISTS " + name().toLowerCase() + ";";
        try {
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            // TODO - Complete method implementation
            throw new SQLException(new UnsupportedOperationException("Method not yet implemented"));
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to execute statement\n\t`%s`", sqlStatement);
            throw new RuntimeException(errorMessage, e);
        }
    }

    @Override
    public void use() {
        try {
            String sqlStatement = "USE " + name().toLowerCase() + ";";
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            // TODO - Complete method implementation
            throw new SQLException(new UnsupportedOperationException("Method not yet implemented"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void executeStatement(String sqlStatement) {
        try {
            sqlStatement = sqlStatement.trim();
            String successMessage = String.format("Successfully executed statement \n\t`%s`", sqlStatement);
            // TODO - Complete method implementation
            throw new SQLException(new UnsupportedOperationException("Method not yet implemented"));
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to execute statement \n\t`%s`", sqlStatement);
            throw new RuntimeException(errorMessage, e);
        }
    }

    @Override
    public ResultSet executeQuery(String sqlQuery) {
        try {
            String successMessage = String.format("Successfully executed query \n\t`%s`", sqlQuery);
            // TODO - Complete method implementation
            throw new SQLException(new UnsupportedOperationException("Method not yet implemented"));
        } catch (SQLException e) {
            String errorMessage = String.format("Failed to execute query \n\t`%s`", sqlQuery);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private Statement getScrollableStatement() {
        int resultSetType = ResultSet.TYPE_SCROLL_INSENSITIVE;
        int resultSetConcurrency = ResultSet.CONCUR_READ_ONLY;
        try {
            return getDatabaseConnection().createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
