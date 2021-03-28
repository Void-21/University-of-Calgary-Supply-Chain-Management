
 /**
 * @author Zeeshan Chougle <a href="mailto:zeeshan.chougle@ucalgary.ca">Zeeshan.chougle@ucalgary.ca</a>
  * @author Mohamed Numan <a hreaf="mailto:mohamed.numan@ucalgary.ca">mailto:mohamed.numan@ucalgary.ca</a>
  * @author Mahtab Khan <a hreaf="mailto:mohammadmahtab.khan@ucalgary.ca">mailto:mohammadmahtab.khan@ucalgary.ca</a>
  *@author Muhammed Umar <a hreaf="mailto:umarbaloch84@gmail.com">mailto:mailto:umarbaloch84@gmail.com</a>
 * @version 2.5
 * @since 1.0
 */


package edu.ucalgary.ensf409;
import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
import java.io.*;
import java.util.*;

public class FinalProject
{

    /**
     * This Class utilizes the data from inventory.sql database and calculates the lowest possible price for the users input item
     * It contains the following functions :
     * 1. FinalProject(String,String,String) : Constructor
     * 2. String getDburl() : getter
     * 3. String getUsername() : getter
     * 4. String getPassword() : getter
     * 5. String getNumItems() : getter
     * 6. String setItemType() : setter
     * 7. String getItemType() : getter
     * 8. String getItemTable() : getter
     * 9. setItemTable(String) : setter
     * 10. setNumItems(String) : setter
     * 11. initializeConnection() : establishes connection with database
     * 12. closes() : closes connection with database
     * 13. userInput() : Takes input from User.
     * 14. checkValidItem(String) :  checks if the input provided by user exsists in database
     * 15. selectTable(String) : Displays the current state of the passsed table
     * 16. String[][] filingSelect(String, int) :
     * 17. String[][] checkFiling(String[][] filing, int :
     * 18. String[][] lampSelect(String,int) :
     * 19. String[][] checklamp(String[][],int) :
     * 20. String[][] deskSelect(string,int) :
     * 21. String[][] checkDisk(String[][],int) :
     * 22. String[][] chairSelect(String[][],int):
     * 23. String[][] checkchair(String[][],int) :
     * 24. String[][] selectFurnitureType(String type, String tableName) :
     * 25. writeManufacturers(String table) : writes the manufacturer's detail in the output file
     * 26. String getManufacturers(String) : returns manufacturers details
     * 27. deleteFromTable(String, String) : Deletes the passed row in the MySQL table
     * 28. int calculateLowestPrice(String[][]) : calculates the lowest possible price for the passed table
     * 29. writeOrderForm(ArrayList<Integer>) : Writes the formatted output in the texfile
     */

    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbconnect; //data member to establish connection with the database
    private ResultSet results; // results conatins the results from MySQL query
    private String itemType; //Type of item user wishes to buy
    private String itemTable; //contains the table of the item which the user wishes to buy
    private String numItems; // contains the number of items enetered by the user
    private String faculty;  // contains the faculty name for user input
    private String phoneNumber; // contains the phone number for user input

    private int counter=0;
    File outFile = new File("orderform.txt");
    FileWriter myWriter = new FileWriter(outFile);
    public String str ="";

    public FinalProject(String DBURL, String USERNAME, String PASSWORD) throws IOException {
        /*Constructor to initialize the DBURL,USERNAME and PASSWORD which are the fields required to
        establish connection with the DB*/

        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }
    public String getDburl()                // getter for DBURL
    {
        return DBURL;
    }

    public String getUsername() {           // getter for username
        return USERNAME;
    }

    public String getPassword() {           // getter for password
        return PASSWORD;
    }
    public String getNumItems()             // getter for numItems
    {
        return this.numItems;
    }

    public void setItemType(String itemType)  //Setter for itemType
    {
        this.itemType = itemType;
    }

    public String getItemType()   // getter for getItemType
    {
        return itemType;
    }

    public String getItemTable()  // getter for itermTable
    {
        return itemTable;
    }

    public void setItemTable(String itemTable)  //setter for itemTable
    {
        this.itemTable = itemTable;
    }

    public void setNumItems(String numItems)  //setter for numItems
    {
        this.numItems = numItems;
    }

    public void initializeConnection(){         //this creates a connection between the java files and the database
        try{
            dbconnect = DriverManager.getConnection(getDburl(),getUsername(),getPassword());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void closes()
    {
        /* This method closes the result and connection fields used to establish connection with the database*/

        try {
            myWriter.close();
            results.close();
            dbconnect.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void checkValidItem(String itemName)
    {
        /*
            This function checks the validity of the Item name entered by the user by comparing the item Type and Table
            name with all the enteries in the MySQL database and see if a match exists
         */

        String table ;    // table name for that item in the database
        String itemType; // type of the item

        if(!itemName.contains(" "))  //if the item name does not contain a space Re-enter the string
        {
            userInput();
            System.out.println();
            return;
        }

        itemName = itemName.toLowerCase();

        if(!itemName.equals("swing arm lamp"))  //Special case given for input with 3 words
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

            System.out.println("Item does not exist in database please re-enter item :");

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
        /*
        This method takes input from the user and calls checkValidItem to validate if the item exists in database
         */

        Scanner myScanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the item you would like to purchase : ");
        String item = myScanner.nextLine();
        System.out.println("Enter the number of items to purchase : ");
        this.numItems = myScanner.nextLine();


        checkValidItem(item);

        System.out.println("Enter your faculty :  ");
        this.faculty = myScanner.nextLine();
        System.out.println("Enter your phone number : ");
        this.phoneNumber = myScanner.nextLine();
    }

    public String selectTable(String tablename)
    {
        /* This a method which displays the current state of the inventory database in MySQL */

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
        catch (SQLException e)
        {
            System.out.println("Unable to connect to database");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("file error");
        }
        return filing2d;
    }
    public String[][] checkFiling(String[][] filing, int count) throws IOException {
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
            str += "ORDER SUMMARY:\n"+"The Item: " + getItemType() +" "+ getItemTable() +"\nNumber of Items Requested: "+getNumItems()+"\n\nUnfortunately this item cannot be constructed with the available materials.\n" +"Please contact one of the manufacturers mentioned below for more information.\n\n";
            str+=getManufacturers(getItemTable());
            str+="\n\nSorry for the inconvenience.";
            writeOrderForm(str,check);
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
        catch (SQLException | IOException ex)
        {
            ex.printStackTrace();
            System.out.println("Unable to connect to database");
        }
        return lamp2d;
    }
    public String[][] checkLamp(String[][] lamp, int count) throws IOException {   int c1=0,c2=0;
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
            String str = "ORDER SUMMARY:\n"+"The Item: " + getItemType() +" "+ getItemTable() +"\nNumber of Items Requested: "+getNumItems()+"\n\nUnfortunately this item cannot be constructed with the available materials.\n" +"Please contact one of the manufacturers mentioned below for more information.\n\n";
            str+=getManufacturers(getItemTable());
            str+="\n\nSorry for the inconvenience.";
            writeOrderForm(str,check);
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
        catch (SQLException | IOException ex)
        {
            System.out.println("Unable to connect to database");
        }
        return desk2d;
    }
    public String[][] checkDesk(String[][] desk, int count) throws IOException {   int c1=0,c2=0,c3=0;
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
            String str = "ORDER SUMMARY:\n"+"The Item: " + getItemType() +" "+ getItemTable() +"\nNumber of Items Requested: "+getNumItems()+"\n\nUnfortunately this item cannot be constructed with the available materials.\n" +"Please contact one of the manufacturers mentioned below for more information.\n\n";
            str+=getManufacturers(getItemTable());
            str+="\n\nSorry for the inconvenience.";
            writeOrderForm(str,check);
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
        catch (SQLException | IOException ex)
        {
            System.out.println("Unable to connect to database");
        }
        return chair2d;
    }
    public String[][] checkChair(String[][] chair, int count) throws IOException {   int c1=0,c2=0,c3=0,c4=0;
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
            String str = "ORDER SUMMARY:\n"+"The Item: " + getItemType() +" "+ getItemTable() +"\nNumber of Items Requested: "+getNumItems()+"\n\nUnfortunately this item cannot be constructed with the available materials.\n" +"Please contact one of the manufacturers mentioned below for more information.\n\n";
            str+=getManufacturers(getItemTable());
            str+="\n\nSorry for the inconvenience.";
            writeOrderForm(str,check);
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

    public int calculateLowestPrice(String[][] tableData) throws IOException {

         /*

        This method calculates the lowest int price for the passed tableData and returns a string value of it by
        following this approach:
        * Step 1 : Start by considering the object with the most number of reusable parts (indicated by "Y").
        * Step 2 : Calculate all the possible cost options for this object and append the cheapest option to collectionOfLowestItem ArrayList
        * Step 3 : Using ArrayLists idTracker and trackIndexPrices to contain the ids of all the cheapest options considered in each iteration
                   for the objects with this specific number of "Y" (reusable parts)
        * Step 4 : repeat Step 1,2 and 3 to determine the lowest possible options for the objects containing the second most
                    ,third most,4th most and so on... reusable parts
        * Step 5 : Extract the smallest element in the collectionOfLowestItem ArrayList which will be the Lowest
                    possible price
        * Step 6 : Extract the corresponding ids for the lowest prices from idTracker ArrayList and delete these records
                    from the database
        * Step 7 : Finally if the numOfItems requested by user is >1 call setfurniture again to repeat all the steps for
                    the next Item as long as numOfItems<=1

        */

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
                    setNumItems(String.valueOf(Integer.parseInt(getNumItems())-1));
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

        String idRow1=tableData[Integer.parseInt(tempId.substring(0,tempId.indexOf(" ")))][tableData[0].length-1];
        String idRow2=tableData[Integer.parseInt(tempId.substring(tempId.indexOf(" ")+1))][tableData[0].length-1];

        deleteFromTable(getItemTable(),idRow1); //delete the specified row
        deleteFromTable(getItemTable(),idRow2); //delete the specified row


        counter++;
        str +="\nthe lowest cost of constructing "+counter+" item: "+actualLowest +".\n";
        //writeOrderForm(str,true);

        if(Integer.parseInt(getNumItems())>1)
        {
            setNumItems(String.valueOf(Integer.parseInt(getNumItems())-1));
            selectFurnitureType(getItemType(),getItemTable());
        }
        writeOrderForm(str,true);
        //deleteFromTable(getItemTable(),id);
        return actualLowest; //instead of return pass the value
    }



    public void writeOrderForm(String order,boolean checker) throws IOException
    {

        myWriter.write("ORDER SUMMARY:\n");
        int val = Integer.parseInt(getNumItems())+1;
        myWriter.write("The Item: " + getItemType() + " " + getItemTable() + "\nNumber of Items Requested: " + val + "\n");

        myWriter.write(order);
        myWriter.write("\n");

        myWriter.write(order);
        myWriter.write("\n");
    }
