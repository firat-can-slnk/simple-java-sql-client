import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
/**
  *
  * Funktionen Klasse
  *
  * @version 1.0 vom 16.11.2019
  * @author Firat Can Sülünkü
  */

public class functions {
  
  // Anfang Attribute
  // Ende Attribute
  
  // Anfang Methoden
  public static void setTable(JTable table, ResultSet resultSet, int width){
    try{
      DefaultTableModel model = (DefaultTableModel) table.getModel(); 
      
      ResultSetMetaData rsmd = resultSet.getMetaData();
      
      
      model.setRowCount(0);
      model.setColumnCount(0);
      model.setColumnCount(rsmd.getColumnCount());
      System.out.println(model.getColumnCount());
      for (int i = 1; i <= model.getColumnCount() ; i++ ) {
      
        JTableHeader header= table.getTableHeader();
        TableColumn tabCol = header.getColumnModel().getColumn(i-1);
        tabCol.setMinWidth(width);
        System.out.println(i);
        tabCol.setHeaderValue(rsmd.getColumnName(i));
        
        
        header.repaint();
      } // end of for
      
      while (resultSet.next()) {
        String[] data = new String[rsmd.getColumnCount()];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
          data[i-1] = resultSet.getString(i);
        }
        model.addRow(data);
      }
      
    }catch(SQLException e){
      e.printStackTrace();
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  // Ende Methoden
} // end of functions
