import java.sql.*;

public class ViewDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:users.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connected to SQLite database: users.db");
            System.out.println("=".repeat(50));

            // Show all tables
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });

            System.out.println("TABLES IN DATABASE:");
            System.out.println("-".repeat(30));
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("- " + tableName);
            }

            System.out.println("\n" + "=".repeat(50));

            // Show Users table data
            System.out.println("USERS TABLE CONTENTS:");
            System.out.println("-".repeat(30));
            String sql = "SELECT * FROM users";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {

                // Print headers
                System.out.printf("%-5s %-15s %-15s%n", "ID", "USERNAME", "PASSWORD");
                System.out.println("-".repeat(40));

                // Print data
                while (rs.next()) {
                    System.out.printf("%-5d %-15s %-15s%n",
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"));
                }
            } catch (SQLException e) {
                System.out.println("Users table might be empty or not exist yet: " + e.getMessage());
            }

            System.out.println("\n" + "=".repeat(50));

            // Show Recipes table data
            System.out.println("RECIPES TABLE CONTENTS:");
            System.out.println("-".repeat(30));
            String recipeSql = "SELECT * FROM recipes";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(recipeSql)) {

                // Print headers
                System.out.printf("%-5s %-20s %-30s%n", "ID", "TITLE", "DESCRIPTION");
                System.out.println("-".repeat(60));

                // Print data
                while (rs.next()) {
                    System.out.printf("%-5d %-20s %-30s%n",
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"));
                }
            } catch (SQLException e) {
                System.out.println("Recipes table might be empty or not exist yet: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}