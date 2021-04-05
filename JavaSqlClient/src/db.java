  import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import com.mysql.jdbc.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
/**
  *
  * Datenbank Klasse
  *
  * @version 1.0 vom 16.11.2019
  * @author Firat Can Sülünkü
  */

public class db {
  
  // Anfang Attribute
   // JDBC driver name and database URL
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  public String DB_URL = "";

 //  Database credentials
  public String USER = "";
  public String PASS = "";

 // Databse things 

  public Connection conn = null;
  public Statement stmt = null;
  public ResultSet rs = null;
  
  public JTextArea taFehler = null;
  // Ende Attribute
  
  // Anfang Methoden
  public db(JTextArea taFehler){
    this.taFehler = taFehler;
    }
  public ResultSet Query(String sql) {
    try {
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      System.out.println("SQL Query: '" + sql + "'");
      rs = stmt.executeQuery(sql);
      
      return rs;
    } catch (SQLException se) {
      //Handle errors for JDBC
      taFehler.setText(se.getMessage());
      taFehler.setVisible(true);
      se.printStackTrace();
    } catch (Exception e) {
      //Handle errors for Class.forName
      taFehler.setText(e.getMessage());
      taFehler.setVisible(true);
      e.printStackTrace();
      
    }
    return null;
  }       
  public int Update(String sql) {
    try {
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = conn.createStatement();
      System.out.println("SQL Query: '" + sql + "'");
      return stmt.executeUpdate(sql);
      
    } catch (SQLException se) {
      //Handle errors for JDBC
      taFehler.setText(se.getMessage());
      taFehler.setVisible(true);
      se.printStackTrace();
    } catch (Exception e) {
      //Handle errors for Class.forName
      taFehler.setText(e.getMessage());
      taFehler.setVisible(true);
      e.printStackTrace();
      
    }
    return 0;
  }
  public void Disconnect() {
    //STEP 6: Clean-up environment
    try {
      if (stmt != null)
        stmt.close();
    } catch (SQLException se2) {} // nothing we can do
    try {
      if (conn != null)
        conn.close();
    } catch (SQLException se) {
      taFehler.setText(se.getMessage());
      taFehler.setVisible(true);
      se.printStackTrace();
    } //end finally try
  }
  // Ende Methoden
} // end of functions
