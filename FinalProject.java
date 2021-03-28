
package edu.ucalgary.ensf409;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
import java.io.*;
import java.util.*;

public class FinalProject {
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbconnect;
    private ResultSet results;
    private String itemType; //Type of item user wishes to buy
    private String itemTable; //contains the table of the item which the user wishes to buy
    private String numItems;

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
    public String getNumItems()
    {
        return this.numItems;
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

    public void checkValidItem(String itemName)
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
            Statement myStmt = dbconnect.createStatement();

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
        this.numItems = myScanner.nextLine();

        System.out.println("Item : "+item);
        System.out.println("Quantity : " + numItems);

        checkValidItem(item);
    }

    public String selectTable(String tablename) {
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
    public String[][] filingSelect(String type, int count){
        String[][] filing2d = new String[count][7];
        String[][] filing = new String[count][5];
        int c =count;
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM filing WHERE Type ='"+type+"'");
            int i=0;
            while(results.next())
            {
                filing2d[i][0] = results.getString("ID");
                filing[i][4] = results.getString("ID");
                filing2d[i][1] = results.getString("Type");
                filing2d[i][2] = results.getString("Rails");
                filing[i][0] = results.getString("Rails");
                filing2d[i][3] = results.getString("Drawers");
                filing[i][1] = results.getString("Drawers");
                filing2d[i][4] = results.getString("Cabinet");
                filing[i][2] = results.getString("Cabinet");
                filing2d[i][5] = String.valueOf(results.getInt("Price"));
                filing[i][3] = String.valueOf(results.getInt("Price"));
                filing2d[i][6] = results.getString("ManuID");
                i=i+1;
            }
            /*for(int j=0;j<count;j++)
            {
                for(int k=0;k<7;k++)
                {
                    System.out.print(filing2d[j][k]+" ");
                }
                System.out.println(" ");
            }*/
            checkFiling(filing,c);
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
        return filing2d;
    }
    public String[][] checkFiling(String[][] filing, int count)
    {
        int c1=0,c2=0,c3=0;
        boolean check = false;
        for(int i=0;i<count;i++)
        {
            if(filing[i][0].equals("Y"))
            {
                c1++;
            }
            if(filing[i][1].equals("Y"))
            {
                c2++;
            }
            if(filing[i][2].equals("Y"))
            {
                c3++;
            }
        }
        if((c1>0)&&(c2>0)&&(c3>0))
        {
            check = true;
            System.out.println(check);
            calculateLowestPrice(filing);
            return filing;
        }
        else
        {
            System.out.println(check);
            return null;
        }
    }
    public String[][] lampSelect(String type, int count){
        String[][] lamp2d = new String[count][6];
        String[][] lamp = new String[count][4];
        int c =count;
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM lamp WHERE Type ='"+type+"'");
            int i=0;
            while(results.next())
            {
                lamp2d[i][0] = results.getString("ID");
                lamp[i][3] = results.getString("ID");
                lamp2d[i][1] = results.getString("Type");
                lamp2d[i][2] = results.getString("Base");
                lamp[i][0] = results.getString("Base");
                lamp2d[i][3] = results.getString("Bulb");
                lamp[i][1] = results.getString("Bulb");
                lamp2d[i][4] = String.valueOf(results.getInt("Price"));
                lamp[i][2] = String.valueOf(results.getInt("Price"));
                lamp2d[i][5] = results.getString("ManuID");
                i++;
            }
            /*for(int j=0;j<count;j++)
            {
                for(int k=0;k<6;k++)
                {
                    System.out.print(lamp2d[j][k]+" ");
                }
                System.out.println(" ");
            }*/
            checkLamp(lamp,c);
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
        return lamp2d;
    }
    public String[][] checkLamp(String[][] lamp, int count)
    {   int c1=0,c2=0;
        boolean check = false;
        for(int i=0;i<count;i++)
        {
            if(lamp[i][0].equals("Y"))
            {
                c1++;
            }
            if(lamp[i][1].equals("Y"))
            {
                c2++;
            }
        }
        if((c1>0)&&(c2>0))
        {
            check = true;
            System.out.println(check);
            calculateLowestPrice(lamp);
            return lamp;
        }
        else
        {
            System.out.println(check);
            return null;
        }
    }
    public String[][] deskSelect(String type, int count){
        String[][] desk2d = new String[count][7];
        String[][] desk = new String[count][5];
        int c =count;
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM desk WHERE Type ='"+type+"'");
            int i=0;
            while(results.next())
            {
                desk2d[i][0] = results.getString("ID");
                desk[i][4] = results.getString("ID");
                desk2d[i][1] = results.getString("Type");
                desk2d[i][2] = results.getString("Legs");
                desk[i][0] = results.getString("Legs");
                desk2d[i][3] = results.getString("Top");
                desk[i][1] = results.getString("Top");
                desk2d[i][4] = results.getString("Drawer");
                desk[i][2] = results.getString("Drawer");
                desk2d[i][5] = String.valueOf(results.getInt("Price"));
                desk[i][3] = String.valueOf(results.getInt("Price"));
                desk2d[i][6] = results.getString("ManuID");
                i++;
            }
            /*for(int j=0;j<count;j++)
            {
                for(int k=0;k<7;k++)
                {
                    System.out.print(desk2d[j][k]+" ");
                }
                System.out.println(" ");
            }*/
            checkDesk(desk,c);
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
        return desk2d;
    }
    public String[][] checkDesk(String[][] desk, int count)
    {   int c1=0,c2=0,c3=0;
        boolean check = false;
        for(int i=0;i<count;i++)
        {
            if(desk[i][0].equals("Y"))
            {
                c1++;
            }
            if(desk[i][1].equals("Y"))
            {
                c2++;
            }
            if(desk[i][2].equals("Y"))
            {
                c3++;
            }
        }
        if((c1>0)&&(c2>0)&&(c3>0))
        {
            check = true;
            System.out.println(check);
            calculateLowestPrice(desk);
            return desk;
        }
        else
        {
            System.out.println(check);
            return null;
        }
    }
    public String[][] chairSelect(String type, int count){
        String[][] chair2d = new String[count][8];
        String[][] chair = new String[count][6];
        int c =count;
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM chair WHERE Type = '"+type+"'");
            int i=0;
            while(results.next())
            {
                chair2d[i][0] = results.getString("ID");
                chair[i][5] = results.getString("ID");
                chair2d[i][1] = results.getString("Type");
                chair2d[i][2] = results.getString("Legs");
                chair[i][0] = results.getString("Legs");
                chair2d[i][3] = results.getString("Arms");
                chair[i][1] = results.getString("Arms");
                chair2d[i][4] = results.getString("Seat");
                chair[i][2] = results.getString("Seat");
                chair2d[i][5] = results.getString("Cushion");
                chair[i][3] = results.getString("Cushion");
                chair2d[i][6] = String.valueOf(results.getInt("Price"));
                chair[i][4] = String.valueOf(results.getInt("Price"));
                chair2d[i][7] = results.getString("ManuID");
                i++;
            }
            /*int z=0;
            for(int j=0;j<count;j++)
            {
                for(int k=2;k<7;k++)
                {
                    chair[j][z++]=chair2d[j][k];
                }
                System.out.println(" ");
            }*/
            myStmt.close();
            checkChair(chair,c);
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
        return chair2d;
    }
    public String[][] checkChair(String[][] chair, int count)
    {   int c1=0,c2=0,c3=0,c4=0;
        boolean check = false;
        for(int i=0;i<count;i++)
        {
            if(chair[i][0].equals("Y"))
            {
                c1++;
            }
            if(chair[i][1].equals("Y"))
            {
                c2++;
            }
            if(chair[i][2].equals("Y"))
            {
                c3++;
            }
            if(chair[i][3].equals("Y"))
            {
                c4++;
            }
        }
        if((c1>0)&&(c2>0)&&(c3>0)&&(c4>0))
        {
            check = true;
            System.out.println(check);
            calculateLowestPrice(chair);
            return chair;
        }
        else
        {
            System.out.println(check);
            return null;
        }
    }
    public String[][] selectFurnitureType(String type, String tableName)
    {
        int count=0;

        try
        {
            Statement myStmt = dbconnect.createStatement();
            //results = myStmt.executeQuery("SELECT COUNT(ID) FROM "+tableName+" WHERE Type='"+type+"'");
            results = myStmt.executeQuery("SELECT * FROM "+tableName+" WHERE Type='"+type+"'");
            while (results.next()){
                count++;
            }
            if(tableName.equals("chair"))
            {
                return chairSelect(type,count);
            }
            if(tableName.equals("desk"))
            {
                return deskSelect(type,count);
            }
            if(tableName.equals("filing"))
            {
                return filingSelect(type,count);
            }
            if(tableName.equals("lamp"))
            {
                return lampSelect(type,count);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void writeManufacturers(String table) throws IOException
    {
        //import java.io to use this function
        File outFile = new File("orderform.txt");
        FileWriter myWriter = new FileWriter(outFile);

        myWriter.write("Order cannot be fulfilled based on current inventory.\n\n");
        myWriter.write("Suggested manufacturers are: " + getManufacturers(table));
        myWriter.close();
    }
    //method called in writeManufacturers
    private String getManufacturers(String table)
    {
        StringBuilder manufacturers = new StringBuilder();
        Set<String> manu_ID = new HashSet<String>();
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + table);

            while( results.next() )
            {
                manu_ID.add( results.getString("ManuID") );
            }

            results = myStmt.executeQuery("SELECT * FROM manufacturer");
            while( results.next() )
            {
                if(manu_ID.contains(results.getString("ManuID")))
                {
                    manufacturers.append( results.getString("Name") );
                    manufacturers.append(", ");
                }
            }
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }

        return manufacturers.toString();
    }

    public void deleteFromTable(String table, String objectID)
    {
        try
        {
            String query = "DELETE FROM "+ table +" WHERE ID = ?";
            PreparedStatement myStmt = dbconnect.prepareStatement(query);
            myStmt.setString(1,objectID);
            myStmt.executeUpdate();
            myStmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int calculateLowestPrice(String[][] tableData)
    {
        String id=new String();
        ArrayList<String> idTracker = new ArrayList<>();
        int count = 0; //counting the number of Y's
        ArrayList<Integer> rowCount = new ArrayList<>(); //count of Y in each row

        for(int i=0;i<tableData.length;i++)
        {
            for(int j=0;j<tableData[0].length-2;j++)
            {
                if(tableData[i][j].contains("Y"))
                {
                    count++;    //Count Y in that row i
                }
            }
            rowCount.add(count);  // add the count of each row as an element
            count=0;
        }


        int highestNumY = Collections.max(rowCount);    //maximum number of Y's
        ArrayList<Integer> rowsWithHighestNumY = new ArrayList<>();
        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<Integer> collectionOfLowestPrices = new ArrayList<>(); // contains lowest price of each greatest Y

        System.out.println(highestNumY);

        for(int i=0;i<tableData.length;i++)
        {
            if(rowCount.get(i)==highestNumY)
            {
                rowsWithHighestNumY.add(i);  // contains all rows with highest number of Y
            }
        }

        //int rowWithHighestNumY= rowCount.indexOf(Collections.max(rowCount));  // row with greatest number of Y's
        //System.out.println("Row with highest num Y "+rowWithHighestNumY);

        int columnWithValN=20; //contains the column in row with high value of Y which has N, if it remains 20 after loop we already solved

        for(int k=0;k<rowsWithHighestNumY.size();k++) {

            for (int i = 0; i < tableData[0].length-2; i++) {
                if (tableData[rowsWithHighestNumY.get(k)][i].contains("N")) {
                    columnWithValN = i;
                    break;
                }
            }


            if (columnWithValN == 20)  //if it remain 20 problem was already solved
            {
                id = tableData[rowsWithHighestNumY.get(k)][tableData[0].length-1];  //id of the record to delete
                deleteFromTable(getItemTable(),id);  //delete the specified row
                System.out.println("id : "+id);
                System.out.println("Lowest : "+tableData[rowsWithHighestNumY.get(k)][tableData[0].length - 2]);

                if(Integer.parseInt(getNumItems())>1)
                {
                    setNumItems(Character.toString(Integer.parseInt(getNumItems())-1));
                    selectFurnitureType(getItemType(),getItemTable());
                }

                return Integer.parseInt(tableData[rowsWithHighestNumY.get(k)][tableData[0].length - 2]);
            }

            System.out.println("Column containing the N for the highest row " + columnWithValN);

            ArrayList<Integer> trackIndexPrices = new ArrayList<>();  //tracks which rows from 2d array are selected prices

            for (int i = 0; i < tableData.length; i++) {
                if (tableData[i][columnWithValN].contains("Y")) {
                    prices.add(Integer.parseInt(tableData[i][tableData[0].length - 2]));  //adds price of that column
                    trackIndexPrices.add(i);
                }
            }

            // if (prices.size()==0) not possible to calculate

            System.out.println(prices);


            int lowestPriceY = prices.get(0);

            for (int i = 0; i < prices.size(); i++) {
                if (lowestPriceY > prices.get(i)) {
                    lowestPriceY = prices.get(i);
                }
            }

            idTracker.add(trackIndexPrices.get(prices.indexOf(lowestPriceY))+" "+rowsWithHighestNumY.get(k)); // keeps track of all indexes considered

            System.out.println(idTracker);

            System.out.println("lowest price of that col  " + lowestPriceY);


            lowestPriceY+=Integer.parseInt(tableData[rowsWithHighestNumY.get(k)][tableData[0].length - 2]);  //will add the price of row with most Y with the remaining rows cheapest Y
            collectionOfLowestPrices.add(lowestPriceY);  //contains lowest price of each highest Y row
            prices = new ArrayList<>(); //set prices to default
        }

        int actualLowest = collectionOfLowestPrices.get(0);

        for (int i = 0; i < collectionOfLowestPrices.size(); i++) {
            if (actualLowest > collectionOfLowestPrices.get(i))
            {
                actualLowest = collectionOfLowestPrices.get(i);
            }
        }

        String tempId = idTracker.get(collectionOfLowestPrices.indexOf(actualLowest));
        System.out.println(tempId);
        String idRow1=tableData[Integer.parseInt(tempId.substring(0,tempId.indexOf(" ")))][tableData[0].length-1];
        String idRow2=tableData[Integer.parseInt(tempId.substring(tempId.indexOf(" ")+1))][tableData[0].length-1];
        System.out.println("id1 : "+idRow1);
        System.out.println("id2 : "+idRow2);
        deleteFromTable(getItemTable(),idRow1); //delete the specified row
        deleteFromTable(getItemTable(),idRow2); //delete the specified row

        System.out.println("Lowest:"+actualLowest);


        if(Integer.parseInt(getNumItems())>1)
        {
            setNumItems(Character.toString(Integer.parseInt(getNumItems())-1));
            selectFurnitureType(getItemType(),getItemTable());
        }

        //deleteFromTable(getItemTable(),id);
        return actualLowest; //instead of return pass the value
    }

    public void writeOrderForm(String orderedItems) throws IOException
    {
        File outFile = new File("orderform.txt");
        FileWriter myWriter = new FileWriter(outFile);

        myWriter.write("Furniture Order Form\n\n");

        myWriter.write("Faculty Name: ");
        myWriter.write("\nContact: ");
        myWriter.write("\nDate: ");

        myWriter.write("\nOriginal Request: " + itemType + " " + itemTable + ", " + numItems);
        myWriter.write("\n\nItems Ordered\n");
        //write to file the IDs of items ordered
        myWriter.write(orderedItems);
        myWriter.write("\n\nTotal Price: " + "price");

        myWriter.close();
    }
public static void main(String[] args)
    {
        FinalProject myJDBC = new FinalProject("jdbc:mysql://localhost/inventory","NUMAN","TIGER");
        myJDBC.initializeConnection();
        //myJDBC.selectFurnitureType("Mesh","chair")

        myJDBC.userInput();

        myJDBC.selectFurnitureType(myJDBC.getItemType(),myJDBC.getItemTable());
        //myJDBC.writeManufacturers("lamp");
        myJDBC.close();

        /*System.out.println(myJDBC.selecttable("chair"));
        System.out.println(myJDBC.selecttable("desk"));
        System.out.println(myJDBC.selecttable("filing"));
        System.out.println(myJDBC.selecttable("lamp"));
        System.out.println(myJDBC.selecttable("manufacturer"));*/
    }
}    
