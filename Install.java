/* Sameer Hussain - IB Computer Science 1 - Match 13, 2018 - This program installs the Java database*/
//package hussainiaproject;

/**
 *
 * @author sameerhussain
 */
public class Install 
{
    //main
    public static void main(String [] args)
    {
        
        //Inventory Table
        String inventoryTable;
        JavaDb objDb = new JavaDb();
        objDb.createDb("IaDb");
        inventoryTable = "CREATE TABLE Inventory ( " +
                "itemName varchar(20), " +
                "itemCount int " +
            ")";
        
        System.out.println(inventoryTable);
        objDb.createTable(inventoryTable, "IaDb");
       
        
       //Lab Table
       String labList;
       labList = "CREATE TABLE LabList ( " +
               "labName varchar(20) " +
               ")";
        System.out.println(labList);
        objDb.createTable(labList, "IaDb");
        
        String labNeeds;
       labNeeds = "CREATE TABLE LabNeeds ( " +
               "name varchar(20), " +
               "count int " +
               ")";
        System.out.println(labNeeds);
        objDb.createTable(labNeeds, "IaDb");
    }
}
