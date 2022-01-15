package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    final private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    private void execute(String sql, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(connection, tableName));
        }
    }

    private void execute(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
       String createTable = "create table if not exists %s (id serial primary key)";
       execute(String.format(createTable, tableName), tableName);
    }

    public void dropTable(String tableName) throws Exception {
        String dropTable = "drop table if exists ";
        execute(String.format(dropTable + tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String addColumn = "alter table %s add column %s %s";
        execute(String.format(addColumn, tableName, columnName, type), tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String dropColumn = "alter table %s drop column %s";
        execute(String.format(dropColumn, tableName, columnName), tableName);
    }

    public void renameColumn(String tableName,
                             String columnName,
                             String newColumnName) throws Exception {
        String renameColumn = "alter table %s rename column %s to %s";
        execute(String.format(renameColumn, tableName, columnName, newColumnName), tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties property = new Properties();
        String path = "./src/main/java/ru/job4j/jdbc/app.properties";
        try (FileInputStream in = new FileInputStream(path)) {
            property.load(in);
            TableEditor tableEditor = new TableEditor(property);
            tableEditor.createTable("demo_table");
            tableEditor.addColumn("demo_table", "age", "int");
            tableEditor.renameColumn("demo_table", "age", "century");
            tableEditor.dropColumn("demo_table", "century");
            tableEditor.dropTable("demo_table");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}