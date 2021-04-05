import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;


/**
  *
  * Code für den Update und Einfügen Dialog
  *
  * @version 1.0 vom 16.11.2019
  * @author Firat Can Sülünkü
  */

public class UpdateEinfugenDialog extends JDialog {
  // Anfang Attribute
  private JButton bOK = new JButton();
  private JButton bAbbrechen = new JButton();
  private JTable jTable1 = new JTable(1, 1);
  private DefaultTableModel jTable1Model = (DefaultTableModel) jTable1.getModel();
  private JScrollPane jTable1ScrollPane = new JScrollPane(jTable1);
  public String SQLBefehl = null;
  private JList listDatabases = null;
  private JList listTables = null;
  private db database = null;
  private JTable jTable2 = null;
  private boolean update = true;
  // Ende Attribute

  // Anfang Methoden
  public UpdateEinfugenDialog(JTable jTable2, int selectedRow, JList listDatabases, JList listTables, db database, boolean update) {
    
	 super(new JFrame(),update ? "Daten in "+listTables.getSelectedValue() : "Datensatz in "+listTables.getSelectedValue()+" einfügen",true);
    
    int frameWidth = 644; 
    int frameHeight = 144;
    this.setSize(frameWidth, frameHeight);
    this.setPreferredSize(new Dimension(frameWidth, frameHeight));
    this.update = update;
    
    this.listDatabases = listDatabases;
    this.listTables = listTables;
    this.jTable2 = jTable2;
    this.database = database;
    Container cp=getContentPane();
    cp.setLayout(null);
    this.setResizable(false);
    
    bOK.setBounds(456, 72, 75, 25);
    bOK.setText("OK");
    bOK.setMargin(new Insets(2, 2, 2, 2));
    bOK.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bOK_ActionPerformed(evt);
    }
    });
    cp.add(bOK);
    bAbbrechen.setBounds(544, 72, 75, 25);
    bAbbrechen.setText("Abbrechen");
    bAbbrechen.setMargin(new Insets(2, 2, 2, 2));
    bAbbrechen.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bAbbrechen_ActionPerformed(evt);
    }
    });
    cp.add(bAbbrechen);
    jTable1ScrollPane.setBounds(0, 0, 628, 62);
    
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.setRowHeight(22);
    jTable1.getColumnModel().getColumn(0).setHeaderValue("Tabelleninhalt");
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
    jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(100);
    jTable1.setEnabled(true);
    
    cp.add(jTable1ScrollPane);
    
    jTable1Model.setColumnCount(jTable2.getModel().getColumnCount());
    for (int i = 0; i < jTable2.getColumnCount() ; i++ ) {
      jTable1.getColumnModel().getColumn(i).setHeaderValue(jTable2.getColumnModel().getColumn(i).getHeaderValue());
      if (this.update) {
        jTable1.setValueAt(jTable2.getValueAt(selectedRow, i), 0, i);
      } // end of if
      jTable1.getTableHeader().getColumnModel().getColumn(i).setMinWidth(150);
    } // end of for
    
    //functions.setTable(jTable1, rs, 120);
    
    
    this.pack();
    this.show();
  }
    // Anfang Komponenten
    
    // Ende Komponenten
  public void bOK_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
    
    // Get primary key info from table
    String sql = null;
    String pKey = null;
    String pKeyValue = null;
    
    if(update){
    sql = "show index from "+ listDatabases.getSelectedValue() + "." + listTables.getSelectedValue() + " WHERE Key_name = 'PRIMARY';";
    database.rs = database.Query(sql);  
    // Get primary key header name
    try{
      while (database.rs.next()) {
        pKey = database.rs.getString("Column_name");
      }}catch(SQLException se){
      database.taFehler.setText(se.getMessage());
      database.taFehler.setVisible(true);
      se.printStackTrace();    
    }
    
    // Get value of primary key
    for(int i = 0 ; i < jTable1Model.getColumnCount() ; i++){
      if(jTable1.getColumnModel().getColumn(i).getHeaderValue().equals(pKey)){
        pKeyValue = jTable1.getValueAt(jTable1.getSelectedRow(), i).toString(); 
      } 
    } // end of while
    }
    
    if (update) {
      sql = "UPDATE " + listDatabases.getSelectedValue().toString() + "."+ listTables.getSelectedValue().toString() + " SET ";
    }else{
      sql = "INSERT INTO " + listDatabases.getSelectedValue().toString() + "."+ listTables.getSelectedValue().toString() + " VALUES (";
    }
    
    for (int i = 0; i < jTable1Model.getColumnCount(); i++ ) {
      String value = "NULL";
        if (jTable1.getValueAt(0, i) != null) {
        value = "'" + jTable1.getValueAt(0, i) + "'";  
        } // end of if
      
      if (update) {
         sql += "`" + jTable1.getColumnModel().getColumn(i).getHeaderValue() + "` = " + value + ", ";
      }else{
        sql += value + ", ";
      }
    } // end of for
    if (!update) {
      sql += ");";
    }else{
    sql += " WHERE `"+pKey+"` = '"+pKeyValue + "';";
    }
    sql = sql.replace(", )", " )");
    sql = sql.replace(",  WHERE", " WHERE");
    
    System.out.println("Rows affected: " + database.Update(sql));
    
    System.out.println(sql);
    this.dispose();
        
  } // end of bOK_ActionPerformed
    
    public void bAbbrechen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
  SQLBefehl = null;
    this.dispose();
  } // end of bAbbrechen_ActionPerformed

  // Ende Methoden
    }
