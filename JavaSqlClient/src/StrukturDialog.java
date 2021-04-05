import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
/**
  *
  * Code für den Struktur Dialog
  *
  * @version 1.0 vom 16.11.2019
  * @author Firat Can Sülünkü
  */

  // Anfang Attribute
  
  // Ende Attribute
  public class StrukturDialog extends JDialog {
  private JTable table = new JTable(0, 8);
    private DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    private JScrollPane tableScrollPane = new JScrollPane(table);
    
  public StrukturDialog(JFrame frame, ResultSet rs, JList listDatabases, JList listTables) {
    super(frame,"SQL Struktur von "+listTables.getSelectedValue(),true);
        
    int frameWidth = 763; 
    int frameHeight = 284;
    this.setSize(frameWidth, frameHeight);
    this.setPreferredSize(new Dimension(frameWidth, frameHeight));
    
    // Anfang Komponenten
    tableScrollPane.setBounds(0, 0, 748, 246);
    table.setRowHeight(20);
    table.getColumnModel().getColumn(0).setHeaderValue("Name");
    table.getColumnModel().getColumn(1).setHeaderValue("Typ");
    table.getColumnModel().getColumn(2).setHeaderValue("Kollation");
    table.getColumnModel().getColumn(3).setHeaderValue("Attribute");
    table.getColumnModel().getColumn(4).setHeaderValue("Null");
    table.getColumnModel().getColumn(5).setHeaderValue("Standard");
    table.getColumnModel().getColumn(6).setHeaderValue("Kommentare");
    table.getColumnModel().getColumn(7).setHeaderValue("Extra");
    tableScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    tableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    table.getTableHeader().setReorderingAllowed(false);
    table.setRowHeight(22);
    table.getColumnModel().getColumn(0).setHeaderValue("Tabelleninhalt");
    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
    table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(100);
    table.setEnabled(false);
    
    functions.setTable(table, rs, 120);
    
    Container c=getContentPane();
    c.setLayout(null);
    c.add(tableScrollPane);
    this.setResizable(false); 
    this.pack();
    this.show();
    // Ende Komponenten
  // Anfang Methoden
  // Ende Methoden
    }
    }