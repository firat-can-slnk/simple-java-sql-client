import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
/**
 *
 * Main Klasse
 *
 * @version 1.0 vom 10.11.2019
 * @author Firat Can Sülünkü
 */

public class main extends JFrame {
 // Anfang Attribute



  private JTextField tfServer = new JTextField();
  private JButton bVerbinden = new JButton();
  private JTextField tfBenutzername = new JTextField();
  private JPasswordField pfPasswort = new JPasswordField();
  private JLabel lServer = new JLabel();
  private JLabel lBenutzername = new JLabel();
  private JLabel lPasswort = new JLabel();
  private JButton bHACK = new JButton();
  static JList<String> listDatabases = new JList<String>();
  private DefaultListModel<String> listDatabasesModel = new DefaultListModel<String>();
  private JScrollPane listDatabasesScrollPane = new JScrollPane(listDatabases);
  private JLabel lDatenbanken = new JLabel();
  static JList<String> listTables = new JList<String>();
  private DefaultListModel<String> listTablesModel = new DefaultListModel<String>();
  private JScrollPane listTablesScrollPane = new JScrollPane(listTables);
  private JLabel lTabellen = new JLabel();
  private JTextArea taFehler = new JTextArea("");
  private JScrollPane taFehlerScrollPane = new JScrollPane(taFehler);
  private JTable jTable1 = new JTable(0, 1) {
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
};
  private DefaultTableModel jTable1Model = (DefaultTableModel) jTable1.getModel();
  
  private JScrollPane jTable1ScrollPane = new JScrollPane(jTable1);
  private JButton bAktualisieren = new JButton();
  private JButton bSQLBefehl = new JButton();
  private JButton bStruktur = new JButton();
  private JButton bEinfugen = new JButton();
  private JButton bLoschen = new JButton();
  private JLabel lPort = new JLabel();
  private JTextField tfPort = new JTextField();
  private JLabel lDatenbank = new JLabel();
  private JTextField tfDatabase = new JTextField();
  private db database = new db(taFehler);
 // Ende Attribute
 

  public main() {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 1110; 
    int frameHeight = 690;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("SQL Client");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
//    this.addComponentListener(new ComponentAdapter() {
//    public void componentResized(ComponentEvent componentEvent) {
//        //System.out.println(String.format("width: %d - height %d", getSize().width, getSize().height));
//    
//        int width = getSize().width;
//        int height = getSize().height;
//        
//        taFehler.setSize(670 - width , taFehler.getHeight());
//        jTable1.setSize(166 - width , 104 - height);
//        listTables.setSize(listTables.getWidth() , 306 - height);
//        
//        
//    }
//    });
    // Anfang Komponenten
    
    tfServer.setBounds(112, 8, 142, 19);
    tfServer.setText("");
    cp.add(tfServer);
    
    bVerbinden.setBounds(381, 56, 90, 25);
    bVerbinden.setText("Verbinden");
    bVerbinden.setEnabled(false);
    bVerbinden.setMargin(new Insets(2, 2, 2, 2));
    bVerbinden.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    bVerbinden_ActionPerformed(evt);
    }
    });
    bVerbinden.setVerticalAlignment(SwingConstants.CENTER);
    cp.add(bVerbinden);
    tfBenutzername.setBounds(112, 32, 142, 20);
    tfBenutzername.setText("");
    cp.add(tfBenutzername);
    pfPasswort.setBounds(330, 32, 142, 20);
    pfPasswort.setText("");
    cp.add(pfPasswort);
    lServer.setBounds(8, 8, 102, 19);
    lServer.setText("Server");
    lServer.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(lServer);
    lServer.setText("Datenbank URL");
    cp.add(lServer);
    lBenutzername.setBounds(8, 32, 102, 20);
    lBenutzername.setText("Benutzername");
    lBenutzername.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(lBenutzername);
    lPasswort.setBounds(264, 32, 65, 20);
    lPasswort.setText("Passwort");
    lPasswort.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(lPasswort);
    
    tfServer.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if(!tfServer.getText().isEmpty() && !tfBenutzername.getText().isEmpty()) {
				bVerbinden.setEnabled(true);
			}else {
				bVerbinden.setEnabled(false);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    });
    tfBenutzername.addKeyListener(new KeyListener() {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			if(!tfServer.getText().isEmpty() && !tfBenutzername.getText().isEmpty()) {
				bVerbinden.setEnabled(true);
			}else {
				bVerbinden.setEnabled(false);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    });
    
    
    bHACK.setBounds(272, 56, 99, 25);
    bHACK.setText("HACK");
    bHACK.setMargin(new Insets(2, 2, 2, 2));
    bHACK.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
    bHACK_ActionPerformed(evt);
    }
    });
    cp.add(bHACK);
    listDatabases.setModel(listDatabasesModel);
    listDatabasesScrollPane.setBounds(8, 102, 166, 174);
    listDatabases.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    cp.add(listDatabasesScrollPane);
    lDatenbanken.setBounds(8, 80, 166, 20);
    lDatenbanken.setText("Datenbanken");
    cp.add(lDatenbanken);
    listTablesScrollPane.setBounds(8, 304, 166, 348);
    lTabellen.setBounds(8, 280, 166, 20);
    listTables.setModel(listTablesModel);
    cp.add(listTablesScrollPane);
    lTabellen.setText("Tabellen");
    cp.add(lTabellen);
    taFehlerScrollPane.setBounds(672/*+30*/, 8, 420/*-30*/, 90);
    taFehler.setEditable(false);
    taFehler.setEnabled(true);
    taFehler.setVisible(false);
    taFehler.setLineWrap(true);
    taFehlerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    taFehler.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(taFehlerScrollPane);
    jTable1ScrollPane.setBounds(180, 102, 912, 550);
    jTable1ScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    jTable1ScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.setRowHeight(22);
    jTable1.getColumnModel().getColumn(0).setHeaderValue("Tabelleninhalt");
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
    jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(150);
    
    cp.add(jTable1ScrollPane);
    bAktualisieren.setBounds(480, 8, 91/*+20*/, 25);
    bAktualisieren.setText("Aktualisieren");
    bAktualisieren.setMargin(new Insets(2, 2, 2, 2));
    bAktualisieren.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bAktualisieren_ActionPerformed(evt);
    }
    });
    cp.add(bAktualisieren);
    bSQLBefehl.setBounds(480, 72, 187/*+30*/, 25);
    bSQLBefehl.setText("SQL Befehl");
    bSQLBefehl.setMargin(new Insets(2, 2, 2, 2));
    bSQLBefehl.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bSQLBefehl_ActionPerformed(evt);
    }
    });
    cp.add(bSQLBefehl);
    bStruktur.setBounds(576/*+10*/, 8, 91/*+20*/, 25);
    bStruktur.setText("Struktur");
    bStruktur.setMargin(new Insets(2, 2, 2, 2));
    bStruktur.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bStruktur_ActionPerformed(evt);
    }
    });
    cp.add(bStruktur);
    bEinfugen.setBounds(480, 40, 91/*+20*/, 25);
    bEinfugen.setText("Einfügen");
    bEinfugen.setMargin(new Insets(2, 2, 2, 2));
    bEinfugen.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bEinfugen_ActionPerformed(evt);
    }
    });
    cp.add(bEinfugen);
    bLoschen.setBounds(576/*+10*/, 40, 91/*+20*/, 25);
    bLoschen.setText("Löschen");
    bLoschen.setMargin(new Insets(2, 2, 2, 2));
    bLoschen.addActionListener(new ActionListener() { 
    public void actionPerformed(ActionEvent evt) { 
    bLoschen_ActionPerformed(evt);
    }
    });
    cp.add(bLoschen);
    lPort.setBounds(264, 8, 62, 20);
    lPort.setText("Port");
    lPort.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(lPort);
    tfPort.setBounds(330, 8, 142, 20);
    cp.add(tfPort);
    lDatenbank.setBounds(8, 56, 102, 20);
    lDatenbank.setText("Datenbank");
    lDatenbank.setFont(new Font("Dialog", Font.BOLD, 12));
    cp.add(lDatenbank);
    tfDatabase.setBounds(112, 56, 142, 20);
    bAktualisieren.setEnabled(false);
    bStruktur.setEnabled(false);
    bEinfugen.setEnabled(false);
    bLoschen.setEnabled(false);
    bSQLBefehl.setEnabled(false);
    
    
    listDatabases.addListSelectionListener(new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent evt) {
    if (!evt.getValueIsAdjusting()) {
    if (listDatabases.getSelectedValue() != null) {
    
    listTablesModel.removeAllElements();
    System.out.println(listDatabases.getSelectedValue());
    database.rs = database.Query("show tables from " + listDatabases.getSelectedValue() + ";");
    String[] Databases = null;
    try {
    if (database.rs != null) {
    //STEP 5: Extract data from result set 
    while (database.rs.next()) {
    //Retrieve by column name
    String table = database.rs.getString(1);
    
    //Display values
    System.out.print(" " + table);
    listTablesModel.addElement(table);
    
    }
    database.Disconnect();
    
    taFehler.setText("");
    taFehler.setVisible(false);
    listDatabases.setEnabled(false);
    
    
    
    } else {} // end of if
    } catch (SQLException se) {
    //Handle errors for JDBC
    taFehler.setText(se.getMessage());
    taFehler.setVisible(true);
    se.printStackTrace();
    } catch (Exception e) {
    //Handle errors for Class.forName
    e.printStackTrace();
    taFehler.setText(e.getMessage());
    taFehler.setVisible(true);
    }
    database.Disconnect();
    }   }
    }
    });
    
    
    listTables.addListSelectionListener(new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent evt) {
    if (!evt.getValueIsAdjusting()) {
    if (listDatabases.getSelectedValue() != null && listTables.getSelectedValue() != null ) {
    System.out.println(listTables.getSelectedValue());
    database.rs = database.Query("select * from " + listDatabases.getSelectedValue() + "." + listTables.getSelectedValue() + ";");
    String[] Databases = null;
    
    functions.setTable(jTable1, database.rs, 150);
    
    
    database.Disconnect();
    bStruktur.setEnabled(true);
    bEinfugen.setEnabled(true);
    bLoschen.setEnabled(true);
    }
    
      
    } 
    }
    });
    
    jTable1.addMouseListener(new MouseAdapter() {
    public void mousePressed(MouseEvent mouseEvent) {
        Point point = mouseEvent.getPoint();
        int row = jTable1.rowAtPoint(point);
        if (mouseEvent.getClickCount() == 2 && jTable1.getSelectedRow() != -1) {
            System.out.println(jTable1.getSelectedRow());
         
        //ResultSet res = database.Query("SELECT * FROM "+ listDatabases.getSelectedValue() + "." + listTables.getSelectedValue() + " WHERE );                                                     
        new UpdateEinfugenDialog(jTable1, jTable1.getSelectedRow(), listDatabases, listTables, database, true);
        //bAktualisieren.doClick();
      }
    }
    });
    
    cp.add(tfDatabase);
    // Ende Komponenten
    bHACK.setVisible(false);
    setVisible(true);
  } // end of public main

 // Anfang Methoden

  public static void main(String[] args) {
    new main();
  } // end of main

  public void bVerbinden_ActionPerformed(ActionEvent evt) {
    if (bVerbinden.getText() == "Verbinden") {
      // TODO hier Quelltext einfügen
      bVerbinden.setEnabled(false);
      tfServer.setEnabled(false);
      tfBenutzername.setEnabled(false);
      pfPasswort.setEnabled(false);
      tfPort.setEnabled(false);
      tfDatabase.setEnabled(false);
      
    bAktualisieren.setEnabled(true);
      //bStruktur.setEnabled(true);
      //bEinfugen.setEnabled(true);
      //bLoschen.setEnabled(true);
    bSQLBefehl.setEnabled(true);
      
      database.USER = tfBenutzername.getText();
      database.PASS = String.valueOf(pfPasswort.getPassword());
      
      String connectionURL = tfServer.getText();
      
      if (!tfPort.getText().isEmpty()) {
        connectionURL += ":" + tfPort.getText();
      } // end of if
      if (!tfDatabase.getText().isEmpty()) {
        connectionURL += "/" + tfDatabase.getText();
      } // end of if
      
      database.DB_URL = "jdbc:mysql://" + connectionURL + "?serverTimezone=UTC";
      System.out.println(database.DB_URL);
      database.rs = database.Query("show databases");
      String[] Databases = null;
      int dbIndex = 0;
      try {
        if (database.rs != null) {
          //STEP 5: Extract data from result set
          int i = 0; 
          while (database.rs.next()) {
            //Retrieve by column name
            String db = database.rs.getString("Database");
            
            if (db.toString().equals(tfDatabase.getText())) {
              dbIndex = i;
              System.out.println(db.toString() + "==" + tfDatabase.getText() + " FOUND IT");
            }else{
                System.out.println(db.toString() + "!=" + tfDatabase.getText());
              }
            
            //Display values
            listDatabasesModel.addElement(db);
            i++;
          }
          bVerbinden.setText("Trennen");
          
          if (!tfDatabase.getText().isEmpty()) {
            listDatabases.setSelectedIndex(dbIndex);
            System.out.println("i: " + i + " dbIndex: " + dbIndex);
          } // end of if  
          taFehler.setVisible(false);
          
          database.Disconnect();
        } else {
          database.Disconnect();
          tfServer.setEnabled(true);
          tfBenutzername.setEnabled(true);
          pfPasswort.setEnabled(true);
        tfPort.setEnabled(true);
        tfDatabase.setEnabled(true);
        
        bAktualisieren.setEnabled(false);
    bStruktur.setEnabled(false);
    bEinfugen.setEnabled(false);
    bLoschen.setEnabled(false);
      bSQLBefehl.setEnabled(false);
        } // end of if
      } catch (SQLException se) {
        //Handle errors for JDBC
        tfServer.setEnabled(true);
        tfBenutzername.setEnabled(true);
        pfPasswort.setEnabled(true);
        tfPort.setEnabled(true);
        tfDatabase.setEnabled(true);
        taFehler.setText(se.getMessage());
        taFehler.setVisible(true);
        
        bAktualisieren.setEnabled(false);
      bStruktur.setEnabled(false);
      bEinfugen.setEnabled(false);
      bLoschen.setEnabled(false);
      bSQLBefehl.setEnabled(false);
        se.printStackTrace();
      } catch (Exception e) {
        //Handle errors for Class.forName
        e.printStackTrace();
        taFehler.setText(e.getMessage());
        taFehler.setVisible(true);
        tfServer.setEnabled(true);
        tfPort.setEnabled(true);
        tfDatabase.setEnabled(true);
        tfBenutzername.setEnabled(true);
        pfPasswort.setEnabled(true);
        
        bAktualisieren.setEnabled(false);
      bStruktur.setEnabled(false);
      bEinfugen.setEnabled(false);
      bLoschen.setEnabled(false);
      bSQLBefehl.setEnabled(false);
      }
      database.Disconnect();
      bVerbinden.setEnabled(true);
      
      
    } else {
      database.Disconnect();
      bVerbinden.setEnabled(false);
              listDatabases.clearSelection();
              listTables.clearSelection();
      listDatabasesModel.removeAllElements();
      listTablesModel.removeAllElements();
      jTable1Model.setRowCount(0);
      jTable1Model.setColumnCount(1);
      jTable1.getColumnModel().getColumn(0).setHeaderValue("Tabelleninhalt");
      jTable1.getTableHeader().getColumnModel().getColumn(0).setMinWidth(150);
      bVerbinden.setText("Verbinden");
      
      tfServer.setEnabled(true);
      tfBenutzername.setEnabled(true);
      pfPasswort.setEnabled(true);
      tfPort.setEnabled(true);
      tfDatabase.setEnabled(true);
      bVerbinden.setEnabled(true);
      listDatabases.setEnabled(true);
      
      bAktualisieren.setEnabled(false);
      bStruktur.setEnabled(false);
      bEinfugen.setEnabled(false);
      bLoschen.setEnabled(false);
      bSQLBefehl.setEnabled(false);
    }
  } // end of bVerbinden_ActionPerformed
  
  public void bHACK_ActionPerformed(ActionEvent evt) {
  } // end of bHACK_ActionPerformed
  
  public void bAktualisieren_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    if (listDatabases.isEnabled()) { // keine db ausgewählt
      bVerbinden.doClick();bVerbinden.doClick();
    } // end of if
    if (!listDatabases.isEnabled() && listTables.isSelectionEmpty()) { // keine tabelle ausgewählt
      Object temp = listDatabases.getSelectedValue();
      bVerbinden.doClick();bVerbinden.doClick();
      listDatabases.setSelectedValue(temp, true);
    } // end of if
    if (!listDatabases.isEnabled() && !listTables.isSelectionEmpty()) { //tabelle ausgewählt
      Object tempDB = listDatabases.getSelectedValue();
      Object tempTable = listTables.getSelectedValue();
      bVerbinden.doClick();bVerbinden.doClick();
      listDatabases.setSelectedValue(tempDB, true);
      listTables.setSelectedValue(tempTable, true);
    } // end of if
    
  } // end of bAktualisieren_ActionPerformed

  public void bSQLBefehl_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    //new SQLBefehlDialog(this);
    String eingabe = JOptionPane.showInputDialog(null,"SQL Befehl",
                                                             "SQL Befehl",
                                                             JOptionPane.PLAIN_MESSAGE);
    
       try{
       if (!eingabe.isEmpty()) {
         System.out.println(database.Query(eingabe));
         }
         }catch(java.lang.NullPointerException n){
         
           
    } // end of if
  } // end of bSQLBefehl_ActionPerformed
  
  
  public void bStruktur_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    String sql = "describe "+ listDatabases.getSelectedValue() + "." + listTables.getSelectedValue();
    database.rs = database.Query(sql);
    new StrukturDialog(this, database.rs, listDatabases, listTables);
    
    
  } // end of bStruktur_ActionPerformed

    
  public void bEinfugen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    new UpdateEinfugenDialog(jTable1, jTable1.getSelectedRow(), listDatabases, listTables, database, false);
  } // end of bEinfugen_ActionPerformed

  public void bLoschen_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    System.out.println(jTable1.getSelectedRow());
    if (jTable1.getSelectedRow() != -1) {
      int eingabe = JOptionPane.showConfirmDialog(null,"Wollen Sie die ausgewählte Zeile wirklich löschen?",
                                                             "Zeile löschen",
                                                             JOptionPane.OK_CANCEL_OPTION);
     
      System.out.println(eingabe);
      if (eingabe == 0) {
      // Get primary key info from table
      String sql = "show index from "+ listDatabases.getSelectedValue() + "." + listTables.getSelectedValue() + " WHERE Key_name = 'PRIMARY';";
      database.rs = database.Query(sql);
      String pKey = null;
      String pKeyValue = null;
      
      // Get primary key header name
      try{
        while (database.rs.next()) {
        pKey = database.rs.getString("Column_name");
      }}catch(SQLException se){
          taFehler.setText(se.getMessage());
          taFehler.setVisible(true);
          se.printStackTrace();    
        }
      
      // Get value of primary key
      for(int i = 0 ; i < jTable1Model.getColumnCount() ; i++){
       if(jTable1.getColumnModel().getColumn(i).getHeaderValue().equals(pKey)){
          pKeyValue = jTable1.getValueAt(jTable1.getSelectedRow(), i).toString(); 
         } 
      } // end of while
      
    
      
        sql = "DELETE FROM " + listDatabases.getSelectedValue().toString() + "."+ listTables.getSelectedValue().toString() + " WHERE `"+pKey+"` = '"+pKeyValue + "';";
        System.out.println("Rows affected: " + database.Update(sql));
        
        System.out.println(sql);
      } // end of if
    } // end of if
  } // end of bLoschen_ActionPerformed

 // Ende Methoden
} // end of class main