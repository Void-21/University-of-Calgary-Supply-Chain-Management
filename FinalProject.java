package edu.ucalgary.ensf409;
import java.sql.*;
import java.io.*;
import java.util.*;

class ConnectDatabase
{
    public final String DBURL;      //store the database url information
    public final String USERNAME;   //store the user's account username
    public final String PASSWORD;   //store the user's account password
    private Connection dbconnect;   //data member to establish connection with the database
    private ResultSet results;      // results contains the results from MySQL query

    public ConnectDatabase(String DBURL, String USERNAME, String PASSWORD) throws IOException
    {

        /*Constructor to initialize the DBURL,USERNAME and PASSWORD which are the fields required to
        establish connection with the DB*/

        this.DBURL = DBURL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

    }


    public void initializeConnection()
    {         //this creates a connection between the java files and the database
        try
        {
            dbconnect = DriverManager.getConnection(getDBURL(),getUSERNAME(),getPASSWORD());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public void setDbconnect(Connection dbconnect)
    {
        this.dbconnect = dbconnect;
    }

    public void setResults(ResultSet results)
    {
        this.results = results;
    }

    public String getUSERNAME()
    {
        return USERNAME;
    }

    public String getDBURL()
    {
        return DBURL;
    }

    public String getPASSWORD()
    {
        return PASSWORD;
    }

    public Connection getDbconnect()
    {
        return dbconnect;
    }

    public ResultSet getResults()
    {
        return results;
    }
}

class ProgramInput extends ConnectDatabase
{
    private String itemType;            //Type of item user wishes to buy
    private String itemTable;           //contains the table of the item which the user wishes to buy
    private String numItems;            // contains the number of items entered by the user
    private int flagInput=0;
    private int reqValue=0;

    public ProgramInput(String DBURL, String USERNAME, String PASSWORD) throws IOException
    {
        super(DBURL,USERNAME,PASSWORD);
    }


    public void setNumItems(String numItems) {
        this.numItems = numItems;
    }

    public void setItemTable(String itemTable) {
        this.itemTable = itemTable;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemTable() {
        return itemTable;
    }

    public String getItemType() {
        return itemType;
    }

    public String getNumItems() {
        return numItems;
    }
    public int getReqValue(){
        return reqValue;
    }

    public void userInput()
    {
        /*
        This method takes input from the user and calls checkValidItem to validate if the item exists in database
         */

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the item you would like to purchase : ");
        String item = myScanner.nextLine();
        System.out.println("Enter the number of items to purchase : ");
        this.numItems = myScanner.nextLine();
        reqValue = Integer.parseInt(numItems);
        //System.out.println("Item : "+item);
        //System.out.println("Quantity : " + numItems);
        checkValidItem(item);

        if(flagInput==0)
        {
            flagInput=1;
            System.out.println();
            System.out.println("Please check the orderform.txt file to see your order status");
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
        itemName=itemName.trim();
        int countSpace=0;

        for(int i=0;i<itemName.length();i++)
        {
            if(itemName.charAt(i)==' ')
            {
                countSpace++;
            }
        }

        if(!itemName.contains(" ")||countSpace>1)  //if the item name does not contain a space Re-enter the string
        {
            System.out.println();
            System.out.println("Item does not exists please enter a valid item"); //remove later
            System.out.println();
            userInput();
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
            Statement myStmt = getDbconnect().createStatement();

            setResults(myStmt.executeQuery("SELECT * FROM "+table+" WHERE type = '"+ itemType + "'"));
            while (getResults().next())
            {
                count++;
            }
            if(count>0)
            {
                setItemTable(table);
                setItemType(itemType);
                return;

            }
            System.out.println();
            System.out.println("Item does not exists please enter a valid item"); //remove later
            System.out.println();
            userInput();


        }
        catch (SQLException ex)
        {
            System.out.println();
            System.out.println("Item does not exists please enter a valid item"); //remove later
            System.out.println();
            userInput();

        }
    }
}

class DatabaseCalculation extends ProgramInput
{
    private int counter=0;
    File outFile = new File("orderform.txt");
    FileWriter myWriter = new FileWriter(outFile);
    public String str ="";
    int totalPrice=0;
    ArrayList<String> output = new ArrayList<String>();

    public DatabaseCalculation(String DBURL, String USERNAME, String PASSWORD) throws IOException
    {

        /*Constructor to initialize the DBURL,USERNAME and PASSWORD which are the fields required to
        establish connection with the DB*/

        super(DBURL,USERNAME,PASSWORD);

    }


    public void closes()
    {
        /* This method closes the result and connection fields used to establish connection with the database*/

        try {
            myWriter.close();
            getResults().close();
            getDbconnect().close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public String selectTable(String tablename)
    {
        /* This a method which displays the current state of the inventory database in MySQL */

        StringBuffer all = new StringBuffer();
        try {
            Statement myStmt = getDbconnect().createStatement();
            setResults(myStmt.executeQuery("SELECT * FROM " + tablename));
            all.append(tablename);
            all.append("\n");
            if (tablename.equals("lamp")) {
                while (getResults().next()) {
                    all.append("(" + getResults().getString("ID") + " // " + getResults().getString("TYPE") + " // " + getResults().getString("Base") + " // " +getResults().getString("Bulb") + " // " +getResults().getString("Price") + " // " + getResults().getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("manufacturer")) {
                while (getResults().next()) {
                    all.append("(" +getResults().getString("ManuID") + " // " + getResults().getString("Name") + " // " + getResults().getString("Phone") + " // " + getResults().getString("Province") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("filing")) {
                while (getResults().next()) {
                    all.append("(" + getResults().getString("ID") + " // " + getResults().getString("TYPE") + " // " +getResults().getString("Rails") + " // " + getResults().getString("Drawers") + " // " + getResults().getString("Cabinet") + " // " + getResults().getString("Price") + " // " + getResults().getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("desk")) {
                while (getResults().next()) {
                    all.append("(" + getResults().getString("ID") + " // " + getResults().getString("TYPE") + " // " + getResults().getString("Legs") + " // " + getResults().getString("Top") + " // " + getResults().getString("Drawer") + " // " + getResults().getString("Price") + " // " + getResults().getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename.equals("chair")) {
                while (getResults().next()) {
                    all.append("(" +getResults().getString("ID") + " // " + getResults().getString("TYPE") + " // " + getResults().getString("Legs") + " // " + getResults().getString("Arms") + " // " + getResults().getString("Seat") + " // " + getResults().getString("Cushion") + " // " + getResults().getString("Price") + " // " + getResults().getString("ManuID") + ")");
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
        /*
            this method selects all the tuples/rows that match the type of filing required by the user
            from the data base using 'type' as a key.
            the method also creates a 2d array of type string that selects all the columns that have values of the form
            (Y/N), this new 2d array is passed to the method checkFiling. 
        */
        String[][] filing2d = new String[count][7];
        String[][] filing = new String[count][5];
        int c =count;
        try
        {
            Statement myStmt = getDbconnect().createStatement();
            setResults(myStmt.executeQuery("SELECT * FROM filing WHERE Type ='"+type+"'"));
            int i=0;
            while(getResults().next())
            {
                filing2d[i][0] = getResults().getString("ID");
                filing[i][4] = getResults().getString("ID");
                filing2d[i][1] =getResults().getString("Type");
                filing2d[i][2] = getResults().getString("Rails");
                filing[i][0] =getResults().getString("Rails");
                filing2d[i][3] = getResults().getString("Drawers");
                filing[i][1] =getResults().getString("Drawers");
                filing2d[i][4] =getResults().getString("Cabinet");
                filing[i][2] =getResults().getString("Cabinet");
                filing2d[i][5] = String.valueOf(getResults().getInt("Price"));
                filing[i][3] = String.valueOf(getResults().getInt("Price"));
                filing2d[i][6] = getResults().getString("ManuID");
                i=i+1;
            }

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
        /*
            this method receives a 2d array from selectFiling method and uses it to check if the Filing can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed. 
            
            the way the method checks if the filing can be constructed is as follows, 
            there are three counter variables set to zero in the beginning. 
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of furniture exists.
            
            only if all the counters are greater than zero the required furniture can be constructed.
         */
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
            calculateLowestPrice(filing);
            return filing;
        }
        else
        {
            //output.add("    -Total Price: "+totalPrice+".00 $");
            //System.out.println(check);
            output.add("\nUnfortunately ");
            output.add(getReqValue()-counter+" ");
            output.add(getItemType() +" "+ getItemTable());
            if(getReqValue()-counter==1)
            {
                output.add(" cannot be constructed due to insufficient materials\n");
            }
            else
            {
                output.add("'s cannot be constructed due to insufficient materials\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add(getManufacturers(getItemTable()));
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    public String[][] lampSelect(String type, int count){
        /*
            this method selects all the tuples/rows that match the type of lamp required by the user
            from the data base using 'type' as a key.
            the method also creates a 2d array of type string that selects all the columns that have values of the form
            (Y/N), this new 2d array is passed to the method checkLamp. 
        */
        String[][] lamp2d = new String[count][6];
        String[][] lamp = new String[count][4];
        int c =count;
        try
        {
            Statement myStmt = getDbconnect().createStatement();
            setResults(myStmt.executeQuery("SELECT * FROM lamp WHERE Type ='"+type+"'"));
            int i=0;
            while(getResults().next())
            {
                lamp2d[i][0] = getResults().getString("ID");
                lamp[i][3] = getResults().getString("ID");
                lamp2d[i][1] = getResults().getString("Type");
                lamp2d[i][2] =getResults().getString("Base");
                lamp[i][0] =getResults().getString("Base");
                lamp2d[i][3] = getResults().getString("Bulb");
                lamp[i][1] =getResults().getString("Bulb");
                lamp2d[i][4] = String.valueOf(getResults().getInt("Price"));
                lamp[i][2] = String.valueOf(getResults().getInt("Price"));
                lamp2d[i][5] = getResults().getString("ManuID");
                i++;
            }

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
        /*
            this method receives a 2d array from selectLamp method and uses it to check if the Lamp can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed. 
            
            the way the method checks if the lamp can be constructed is as follows, 
            there are two counter variables set to zero in the beginning. 
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of furniture exists.
            
            only if all the counters are greater than zero the required furniture can be constructed.
         */ 
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
            calculateLowestPrice(lamp);
            return lamp;
        }
        else
        {
            //output.add("    -Total Price: "+totalPrice+".00 $");
            //System.out.println(check);
            output.add("\nUnfortunately ");
            output.add(getReqValue()-counter+" ");
            output.add(getItemType() +" "+ getItemTable());
            if(getReqValue()-counter==1)
            {
                output.add(" cannot be constructed due to insufficient materials\n");
            }
            else
            {
                output.add("'s cannot be constructed due to insufficient materials\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add(getManufacturers(getItemTable()));
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    public String[][] deskSelect(String type, int count){
        /*
            this method selects all the tuples/rows that match the type of desk required by the user
            from the data base using 'type' as a key.
            the method also creates a 2d array of type string that selects all the columns that have values of the form
            (Y/N), this new 2d array is passed to the method checkDesk. 
        */
        String[][] desk2d = new String[count][7];
        String[][] desk = new String[count][5];
        int c =count;
        try
        {
            Statement myStmt = getDbconnect().createStatement();
            setResults( myStmt.executeQuery("SELECT * FROM desk WHERE Type ='"+type+"'"));
            int i=0;
            while(getResults().next())
            {
                desk2d[i][0] = getResults().getString("ID");
                desk[i][4] =getResults().getString("ID");
                desk2d[i][1] = getResults().getString("Type");
                desk2d[i][2] = getResults().getString("Legs");
                desk[i][0] = getResults().getString("Legs");
                desk2d[i][3] = getResults().getString("Top");
                desk[i][1] = getResults().getString("Top");
                desk2d[i][4] = getResults().getString("Drawer");
                desk[i][2] = getResults().getString("Drawer");
                desk2d[i][5] = String.valueOf(getResults().getInt("Price"));
                desk[i][3] = String.valueOf(getResults().getInt("Price"));
                desk2d[i][6] = getResults().getString("ManuID");
                i++;
            }

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
        /*
            this method receives a 2d array from selectDesk method and uses it to check if the Desk can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed. 
            
            the way the method checks if the Desk can be constructed is as follows, 
            there are three counter variables set to zero in the beginning. 
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of furniture exists.
            
            only if all the counters are greater than zero the required furniture can be constructed.
         */
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
            calculateLowestPrice(desk);
            return desk;
        }
        else
        {
            //output.add("    -Total Price: "+totalPrice+".00 $");
            //System.out.println(check);
            output.add("\nUnfortunately ");
            output.add(getReqValue()-counter+" ");
            output.add(getItemType() +" "+ getItemTable());
            if(getReqValue()-counter==1)
            {
                output.add(" cannot be constructed due to insufficient materials\n");
            }
            else
            {
                output.add("'s cannot be constructed due to insufficient materials\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add(getManufacturers(getItemTable()));
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    public String[][] chairSelect(String type, int count){
        /*
            this method selects all the tuples/rows that match the type of chair required by the user
            from the data base using 'type' as a key.
            the method also creates a 2d array of type string that selects all the columns that have values of the form
            (Y/N), this new 2d array is passed to the method checkChair. 

        */
        String[][] chair2d = new String[count][8];
        String[][] chair = new String[count][6];
        int c =count;
        try
        {
            Statement myStmt = getDbconnect().createStatement();
            setResults(myStmt.executeQuery("SELECT * FROM chair WHERE Type = '"+type+"'"));
            int i=0;
            while(getResults().next())
            {
                chair2d[i][0] = getResults().getString("ID");
                chair[i][5] = getResults().getString("ID");
                chair2d[i][1] = getResults().getString("Type");
                chair2d[i][2] = getResults().getString("Legs");
                chair[i][0] = getResults().getString("Legs");
                chair2d[i][3] = getResults().getString("Arms");
                chair[i][1] = getResults().getString("Arms");
                chair2d[i][4] = getResults().getString("Seat");
                chair[i][2] = getResults().getString("Seat");
                chair2d[i][5] =getResults().getString("Cushion");
                chair[i][3] = getResults().getString("Cushion");
                chair2d[i][6] = String.valueOf(getResults().getInt("Price"));
                chair[i][4] = String.valueOf(getResults().getInt("Price"));
                chair2d[i][7] =getResults().getString("ManuID");
                i++;
            }

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
        /*
            this method receives a 2d array from selectChair method and uses it to check if the Chair can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed. 
            
            the way the method checks if the Chair can be constructed is as follows, 
            there are four counter variables set to zero in the beginning. 
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of furniture exists.
            
            only if all the counters are greater than zero the required furniture can be constructed.
         */
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
            calculateLowestPrice(chair);
            return chair;
        }
        else
        {
            //output.add("    ~Total Price: "+totalPrice+".00 $");
            //System.out.println(check);
            output.add("\nUnfortunately ");
            output.add(getReqValue()-counter+" ");
            output.add(getItemType() +" "+ getItemTable());
            if(getReqValue()-counter==1)
            {
                output.add(" cannot be constructed due to insufficient materials\n");
            }
            else
            {
                output.add("'s cannot be constructed due to insufficient materials\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add(getManufacturers(getItemTable()));
            output.add("--------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    public String[][] selectFurnitureType(String type, String tableName)
    {
        /*
                this method calls the respective select functions based on the table name and the type provided by the 
                user. the 'type' is used as the key to proceed with the function.
                the local variable count is incremented every time the required furniture type in the table is encountered 
                'count' represents the total number of elements that are present in the sql database of the required type
        */
        int count=0;

        try
        {
            Statement myStmt =getDbconnect().createStatement();
            //results = myStmt.executeQuery("SELECT COUNT(ID) FROM "+tableName+" WHERE Type='"+type+"'");
            setResults(myStmt.executeQuery("SELECT * FROM "+tableName+" WHERE Type='"+type+"'"));
            while (getResults().next()){
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
        /*
            this method accesses the sql database and returns all the manufacturers of a certain furniture 
            the list of manufacturers are written to the file in the final invoice of the transaction.
          
            the steps to receive the required rows are as follows
            1. select all the ManuID's from the specified table.
            2. select all the manufacturers from the manufacturers table using the ManuID
            3. select all the Names of the manufacturers using the key 'Name'
        */
        int i=0;
        StringBuilder manufacturers = new StringBuilder();
        Set<String> manu_ID = new HashSet<String>();
        try
        {
            Statement myStmt = getDbconnect().createStatement();
            setResults( myStmt.executeQuery("SELECT * FROM " + table));

            while( getResults().next() )
            {
                manu_ID.add( getResults().getString("ManuID") );
            }

            setResults(myStmt.executeQuery("SELECT * FROM manufacturer"));
            while( getResults().next() )
            {
                if(manu_ID.contains(getResults().getString("ManuID")))
                {
                    i++;
                    manufacturers.append(i +". ");
                    manufacturers.append( getResults().getString("Name") );
                    manufacturers.append("\n");
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
        /*
            this method deletes a specified row using the ID of the specified table.
            the deletion only takes place once the element has been used to construct the required furniture and therefore
            is not available for further use anymore.        
        */
        try
        {
            String query = "DELETE FROM "+ table +" WHERE ID = ?";
            PreparedStatement myStmt = getDbconnect().prepareStatement(query);
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

        //System.out.println(highestNumY);

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
                //System.out.println("id : "+id);
                //System.out.println("Lowest : "+tableData[rowsWithHighestNumY.get(k)][tableData[0].length - 2]);

                if(Integer.parseInt(getNumItems())>1)
                {
                    setNumItems(String.valueOf(Integer.parseInt(getNumItems())-1));
                    selectFurnitureType(getItemType(),getItemTable());
                }

                return Integer.parseInt(tableData[rowsWithHighestNumY.get(k)][tableData[0].length - 2]);
            }

            ArrayList<Integer> trackIndexPrices = new ArrayList<>();  //tracks which rows from 2d array are selected prices

            for (int i = 0; i < tableData.length; i++) {
                if (tableData[i][columnWithValN].contains("Y")) {
                    prices.add(Integer.parseInt(tableData[i][tableData[0].length - 2]));  //adds price of that column
                    trackIndexPrices.add(i);
                }
            }

            // if (prices.size()==0) not possible to calculate

            //System.out.println(prices);


            int lowestPriceY = prices.get(0);

            for (int i = 0; i < prices.size(); i++) {
                if (lowestPriceY > prices.get(i)) {
                    lowestPriceY = prices.get(i);
                }
            }

            idTracker.add(trackIndexPrices.get(prices.indexOf(lowestPriceY))+" "+rowsWithHighestNumY.get(k)); // keeps track of all indexes considered

            //System.out.println(idTracker);

            //System.out.println("lowest price of that col  " + lowestPriceY);


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
        //System.out.println(tempId);
        String idRow1=tableData[Integer.parseInt(tempId.substring(0,tempId.indexOf(" ")))][tableData[0].length-1];
        String idRow2=tableData[Integer.parseInt(tempId.substring(tempId.indexOf(" ")+1))][tableData[0].length-1];
        //System.out.println("id1 : "+idRow1);
        //System.out.println("id2 : "+idRow2);
        deleteFromTable(getItemTable(),idRow1); //delete the specified row
        deleteFromTable(getItemTable(),idRow2); //delete the specified row

        //System.out.println("Lowest:"+actualLowest);
        counter++;
        String value = numFormat(counter);
        totalPrice+=actualLowest;
        output.add("• The lowest cost to manufacture "+value+" item of "+getItemType()+" "+getItemTable()+" is - "+actualLowest+".00 $ \n");
        if(Integer.parseInt(getNumItems())>1)
        {
            setNumItems(String.valueOf(Integer.parseInt(getNumItems())-1));
            selectFurnitureType(getItemType(),getItemTable());
        }
        return actualLowest; //instead of return pass the value
    }
    
    
    public static String numFormat(int i) {
        /*
            this is a helper function used to properly format the output file , for example it changes the '1' to '1st' 
            '11' to '11th' and onwards.
        */
        String[] str = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + str[i % 10];

        }
    }

    public void writeOrderForm() throws IOException
    {
       /*
            this method is used to write to the output file.
            main formatting of the output file takes place in this function.
        */
        output.add(0,"--------------------------------------------------------------------------------------------------------------------\n");
        output.add(1,"                                              \t          SALES INVOICE                             \n");
        output.add(2,"--------------------------------------------------------------------------------------------------------------------\n");
        output.add(3,"• The Item: " + getItemType() +" "+ getItemTable() +"\n• Number of Items Requested: "+getReqValue()+"\n");
        for(int i=0;i<output.size();i++)
        {
            myWriter.write(output.get(i));
            if(i==output.size()-1)
            {
                myWriter.write("\n    ~Total Price: "+totalPrice+".00 $");
            }
        }
    }
}


public class FinalProject
{

    public static void main(String[] args) throws IOException
    {

        DatabaseCalculation myJDBC = new DatabaseCalculation("jdbc:mysql://localhost/inventory","zee","Zeemaan1234@");
        myJDBC.initializeConnection();
        myJDBC.userInput();

        myJDBC.selectFurnitureType(myJDBC.getItemType(),myJDBC.getItemTable());
        myJDBC.writeOrderForm();
        myJDBC.closes();

    }
}
