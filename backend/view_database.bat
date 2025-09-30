@echo off
echo ==========================================
echo       SQLite Database Contents
echo ==========================================
echo.
echo Connecting to users.db...
echo.

java -cp "target\classes;%USERPROFILE%\.m2\repository\org\xerial\sqlite-jdbc\3.34.0\sqlite-jdbc-3.34.0.jar" -c "
import java.sql.*;
public class ViewDB {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(\"jdbc:sqlite:users.db\");
        
        System.out.println(\"USERS TABLE:\");
        System.out.println(\"============\");
        ResultSet rs = conn.createStatement().executeQuery(\"SELECT * FROM users\");
        while(rs.next()) {
            System.out.println(\"ID: \" + rs.getInt(\"id\") + \", Username: \" + rs.getString(\"username\") + \", Password: \" + rs.getString(\"password\"));
        }
        
        System.out.println(\"\nRECIPES TABLE:\");
        System.out.println(\"==============\");
        rs = conn.createStatement().executeQuery(\"SELECT * FROM recipes\");
        while(rs.next()) {
            System.out.println(\"ID: \" + rs.getInt(\"id\") + \", Title: \" + rs.getString(\"title\") + \", Description: \" + rs.getString(\"description\"));
        }
        
        conn.close();
    }
}
"

pause