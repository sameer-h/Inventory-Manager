/* Sameer Hussain - IB Computer Science 1 - Match 13, 2018 - This program has the database methods */

//package hussainiaproject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;



public class JavaDb {

    private String dbName;
    private Object[][] data;
    private Connection dbConn;
    
    public JavaDb(String dbName)
    {
        this.dbName = dbName;
        this.data = null;
        setDbConn();
    }
    
    //constructor
    
    public JavaDb()
    {
        this.dbName="";
        this.data = null;
        this.dbConn=null;
    }
    
    //Gets data from the table

    public Object[][] getData(String tableName, String[] tableHeaders) 
    {
        int columnCount = tableHeaders.length;
        ResultSet rs = null;
        Statement s = null;
        String dbQuery = "SELECT * FROM " + tableName;
        ArrayList<ArrayList<String>> dataList = new ArrayList<>(); //ArrayList for the data from rows and columns
        
        try
        {
            s = this.dbConn.createStatement(); //JDBC Statements
            rs = s.executeQuery(dbQuery);
            while(rs.next())
            {
                ArrayList<String> row = new ArrayList<>();
                for(int i=0; i<columnCount; i++)
                {
                    row.add(rs.getString(tableHeaders[i])); // Loop to get the data from columns and rows
                }
                dataList.add(row);
            }
            this.data = new Object[dataList.size()][columnCount];
            for (int i=0; i<dataList.size(); i++)
            {
                ArrayList<String> row = new ArrayList<>();
                row = dataList.get(i);
                for (int j=0; j<columnCount; j++)
                {
                    this.data[i][j] = row.get(j);
                }
            }  
        }
        catch(Exception e)
        {
            System.exit(0);
        }
        return data;
    }

    //Accessors and mutators for data 
    
    public void setData(Object[][] data) {
        this.data = data;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Connection getDbConn() {
        return dbConn;
    }
    
    //Creating connection

    public void setDbConn() {
        String connectionURL = "jdbc:derby:" + this.dbName;
        this.dbConn = null;
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            this.dbConn = DriverManager.getConnection(connectionURL);  
        }
        catch (SQLException err)
        {
            ErrorFrame objEF = new ErrorFrame();
            err.printStackTrace();
        }
        catch(ClassNotFoundException ex)
        {
            ErrorFrame objEF = new ErrorFrame();
            ex.printStackTrace();
        }
    }

    //Closing connection
    
    public void closeDbConn()
    {
        try
        {
            this.dbConn.close();
        }
        catch(Exception err)
        {
            ErrorFrame objEF = new ErrorFrame();
            err.printStackTrace();
        }
    }
    
    //Creating the database with connection and setting the new name
    
    public void createDb(String newDbName)
    {
       this.dbName = newDbName;
       String connectionURL = "jdbc:derby:" + this.dbName + ";create=true";
       try
       {
           Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           this.dbConn = DriverManager.getConnection(connectionURL);
           System.out.println("new database created");
           this.dbConn.close();
       }
       catch (Exception err)
       {
            ErrorFrame objEF = new ErrorFrame();  
            err.printStackTrace();
       }
    }
    
    //Creating each table - used in Install.java
    
    public void createTable(String newTable, String dbName)
    {
        Statement s;
        setDbName(dbName);
        setDbConn();
        try
        {
            s = this.dbConn.createStatement();
            s.execute(newTable);
            System.out.println("New table created");
            this.dbConn.close();
        }
        catch (SQLException err)
        {
            ErrorFrame objEF = new ErrorFrame();
            err.printStackTrace();
        }
    }
    
    //Method for returning the amount of items in a column
    
    public int returnCount(String itemName, String dbName)
    {
        //statement = smth like "SELECT count  FROM Inventory WHERE name='beaker';
        Statement s;
        ResultSet rs;
        String statement = "SELECT itemCount FROM Inventory WHERE itemName='" + itemName + "'";
        setDbName(dbName);
        setDbConn();
        int out = -1;
        try
        {
            s = this.dbConn.createStatement();
            rs = s.executeQuery(statement);
            System.out.println("Who cares");
            if(rs.next())
            {
            out = rs.getInt("itemCount");
            }
            this.dbConn.close();
        }
        catch(SQLException e)
        {
            ErrorFrame objEF = new ErrorFrame();
            e.printStackTrace();
        }
        return out; 
    }
    
    //Getting a list of String from a table to display later
    
    public String[] getLabList() throws ClassNotFoundException
    {
        String[] dbList;
        Connection connection = dbConn;
         ArrayList<String> dbNames = new ArrayList<>();
         
       
             String driverName = "com.mysql.jdbc.Driver";
             
             String serverName = "localhost";
             String schema = "IaDb";

            //String url = "jdbc:mysql://" + serverName +  "/" + schema;
            
            setDbConn();

            System.out.println("Successfully Connected to the database!");
            
        

   //metaData methods to get the table names
        
    try {
    System.out.println(connection);
    DatabaseMetaData metadata = connection.getMetaData();
    String[] types = {"TABLE"};
    ResultSet resultSet = metadata.getTables(null, null, "%", types);
    
    while (resultSet.next()) {
    String tableName = resultSet.getString(3);
    //String tableCatalog = resultSet.getString(1);
    //String tableSchema = resultSet.getString(2);
    //System.out.println("Table : " + tableName + "nCatalog : " + tableCatalog + "nSchema : " + tableSchema);
        
    String [] ignoreList = {"INVENTORY", "LABLIST", "LABNEEDS"};
    
    if(!Arrays.asList(ignoreList).contains(tableName))
    {
    dbNames.add(tableName);
    }          
   }
 }  
    catch (SQLException e) {
  System.out.println("Could not get database metadata " + e.getMessage());
}
    
    return dbNames.toArray(new String[dbNames.size()]);
            
    }

//getting the supplies needed as an Object from the LabsTable
    
    public Object[][] suppliesNeeded(String labName)
   
            
    {
        try
        
        {
            
        
        Object [][] out;
        Statement stmt = dbConn.createStatement();
        ResultSet rs;
        ArrayList<String> Names = new ArrayList<>();
        ArrayList<String> Counts = new ArrayList<>();
        
        String  sql = "SELECT * FROM "+labName;
        rs = stmt.executeQuery(sql);
        while(rs.next())
        {   
            Names.add(rs.getString("itemName"));
            Counts.add(Integer.toString(rs.getInt("itemCount")));
        }
        out = new Object[Names.size()][2];
         
        
        for(int i=0; i<Names.size(); i++)
        {
           out[i][0] = Names.get(i);
           out[i][1] = Counts.get(i);
        }
        return out;
        }
        catch(SQLException e)
        {
            return  null;
        }
    }


    
    //toString  method
    public String toString()
    {
        return "The database is called " + getDbName();
    }
}   