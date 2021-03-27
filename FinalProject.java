package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinalProject {
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbconnect;
    private ResultSet results;
    private String itemType; //Type of item user wishes to buy
    private String itemTable; //contains the table of the item which the user wishes to buy
    
    public FinalProject(String DBURL, String USERNAME, String PASSWORD) {
        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }
    public String getDburl() {              //getter for DBurl
        return DBURL;
    }

    public String getUsername() {           //getter for username
        return USERNAME;
    }

    public String getPassword() {           //getter for password
        return PASSWORD;
    }
    
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public String getItemTable() {
        return itemTable;
    }

    public void setItemTable(String itemTable) {
        this.itemTable = itemTable;
    }
    
    public void initializeConnection(){         //this creates a connection between the java files and the database
        try{
            dbconnect = DriverManager.getConnection(getDburl(),getUsername(),getPassword());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            results.close();
            dbconnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean checkValidItem(String itemName)
    {
        String table ;    // table name for that item in the database
        String itemType; //type of the item

        itemName = itemName.toLowerCase();

        if(!itemName.equals("swing arm lamp"))
        {
            itemType = itemName.substring(0,itemName.indexOf(" "));
            table = itemName.substring(itemName.indexOf(" ")+1);
        }
        else
        {
            itemType="swing arm";
            table="lamp";
        }


        int count=0;
        try {
            Statement myStmt = connection.createStatement();

            results = myStmt.executeQuery("SELECT * FROM "+table+" WHERE type = '"+ itemType + "'");
            while (results.next())
            {
                count++;
            }
            if(count>0)
            {
                setItemTable(table);
                setItemType(itemType);
                return;
                //return true;
            }

            userInput();
            //return false;

        }
        catch (SQLException ex)
        {
            System.out.println();
            System.out.println("Item does not exists please enter a valid item"); //remove later
            System.out.println();
            userInput();
            //return false;
        }
    }


    public void userInput()
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the item you would like to purchase : ");
        String item = myScanner.nextLine();
        System.out.println("Enter the number of items to purchase : ");
        String numItems = myScanner.nextLine();

        System.out.println("Item : "+item);
        System.out.println("Quantity : " + numItems);

        checkValidItem(item);
    }
    
    public String selecttable(String tablename) {
        StringBuffer all = new StringBuffer();
        try {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + tablename);
            all.append(tablename);
            all.append("\n");
            if (tablename.equals("lamp")) {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Base") + " // " + results.getString("Bulb") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("manufacturer")) {
                while (results.next()) {
                    all.append("(" + results.getString("ManuID") + " // " + results.getString("Name") + " // " + results.getString("Phone") + " // " + results.getString("Province") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("filing")) {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Rails") + " // " + results.getString("Drawers") + " // " + results.getString("Cabinet") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("desk")) {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Legs") + " // " + results.getString("Top") + " // " + results.getString("Drawer") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("chair")) {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Legs") + " // " + results.getString("Arms") + " // " + results.getString("Seat") + " // " + results.getString("Cushion") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            myStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return all.toString();
    }
    public void filingselect(String type, int count){
        String[][] filing2d = new String[count][7];
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM filing WHERE Type ='"+type+"'");
                int i=0;
                while(results.next())
                {
                    filing2d[i][0] = results.getString("ID");
                    filing2d[i][1] = results.getString("Type");
                    filing2d[i][2] = results.getString("Rails");
                    filing2d[i][3] = results.getString("Drawers");
                    filing2d[i][4] = results.getString("Cabinet");
                    filing2d[i][5] = String.valueOf(results.getInt("Price"));
                    filing2d[i][6] = results.getString("ManuID");
                    i=i+1;
                }
            for(int j=0;j<count;j++)
            {
                for(int k=0;k<7;k++)
                {
                    System.out.print(filing2d[j][k]+" ");
                }
                System.out.println(" ");
            }
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
    }
    public void lampselect(String type, int count){
        String[][] lamp2d = new String[count][6];
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM lamp WHERE Type ='"+type+"'");
            int i=0;
            while(results.next())
            {
                lamp2d[i][0] = results.getString("ID");
                lamp2d[i][1] = results.getString("Type");
                lamp2d[i][2] = results.getString("Base");
                lamp2d[i][3] = results.getString("Bulb");
                lamp2d[i][4] = String.valueOf(results.getInt("Price"));
                lamp2d[i][5] = results.getString("ManuID");
                i++;
            }
            for(int j=0;j<count;j++)
            {
                for(int k=0;k<6;k++)
                {
                    System.out.print(lamp2d[j][k]+" ");
                }
                System.out.println(" ");
            }
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
    }
    public void deskselect(String type, int count){
        String[][] desk2d = new String[count][7];
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM desk WHERE Type ='"+type+"'");
            int i=0;
            while(results.next())
            {
                desk2d[i][0] = results.getString("ID");
                desk2d[i][1] = results.getString("Type");
                desk2d[i][2] = results.getString("Legs");
                desk2d[i][3] = results.getString("Top");
                desk2d[i][4] = results.getString("Drawer");
                desk2d[i][5] = String.valueOf(results.getInt("Price"));
                desk2d[i][6] = results.getString("ManuID");
                i++;
            }
            for(int j=0;j<count;j++)
            {
                for(int k=0;k<7;k++)
                {
                    System.out.print(desk2d[j][k]+" ");
                }
                System.out.println(" ");
            }
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }}
    public void chairselect(String type, int count){
        String[][] chair2d = new String[count][8];
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM chair WHERE Type = '"+type+"'");
            int i=0;
            while(results.next())
            {
                chair2d[i][0] = results.getString("ID");
                chair2d[i][1] = results.getString("Type");
                chair2d[i][2] = results.getString("Legs");
                chair2d[i][3] = results.getString("Arms");
                chair2d[i][4] = results.getString("Seat");
                chair2d[i][5] = results.getString("Cushion");
                chair2d[i][6] = String.valueOf(results.getInt("Price"));
                chair2d[i][7] = results.getString("ManuID");
                i++;
            }
            for(int j=0;j<count;j++)
            {
                for(int k=0;k<8;k++)
                {
                    System.out.print(chair2d[j][k]+" ");
                }
                System.out.println(" ");
            }
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
    }
    public void selectFurnitureType(String type, String tableName)
    {
        int count=0;
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT COUNT(ID) FROM "+tableName+" WHERE Type='"+type+"'");
            results = myStmt.executeQuery("SELECT * FROM "+tableName+" WHERE Type='"+type+"'");
            while (results.next()){
                count++;
            }
            System.out.println(count);
        if(tableName.equals("chair"))
        {
            chairselect(type,count);
        }
        if(tableName.equals("desk"))
        {
            deskselect(type,count);
        }
        if(tableName.equals("filing"))
        {
            filingselect(type,count);
        }
        if(tableName.equals("lamp"))
        {
            lampselect(type,count);
        }

    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args)
    {
        FinalProject myJDBC = new FinalProject("jdbc:mysql://localhost/inventory","NUMAN","TIGER");
        myJDBC.initializeConnection();
        //myJDBC.selectFurnitureType("Mesh","chair")

        myJDBC.userInput();
        
        myJDBC.selectFurnitureType(getItemType(),getItemTable());
        
        /*System.out.println(myJDBC.selecttable("chair"));
        System.out.println(myJDBC.selecttable("desk"));
        System.out.println(myJDBC.selecttable("filing"));
        System.out.println(myJDBC.selecttable("lamp"));
        System.out.println(myJDBC.selecttable("manufacturer"));*/
    }
}
