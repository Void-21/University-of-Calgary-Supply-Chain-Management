/**
 * This project is completed by group 60 (ENSF 409)
 *
 * @author Zeeshan Chougle <a href="mailto:zeeshan.chougle@ucalgary.ca">Zeeshan.chougle@ucalgary.ca</a>
 * @author Mohamed Numan <a href="mailto:mohamed.numan@ucalgary.ca">mohamed.numan@ucalgary.ca</a>
 * @author Mahtab Khan <a href="mohammadmahtab.khan@ucalgary.ca">mohammadmahtab.khan@ucalgary.ca</a>
 * @author Umar Baloch <a href="mailto:"umarbaloch84@gmail.com">umarbaloch84@gmail.com</a>
 * @version 3.1
 * @since 1.0
 *
 */

package edu.ucalgary.ensf409;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class ConnectDatabase
{

    /**
     * ConnectDatabase class contains methods and instances to establish connection with the database
     *
     * Note : DBURL,USERNAME and PASSWORD have to be passed through the constructor to establish connection to
     * the database on the User's System
     *
     */

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
    {
        //this establishes a connection between the java files and the database
        try
        {
            dbconnect = DriverManager.getConnection(getDburl(),getUsername(),getPassword());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void setDbconnect(Connection dbconnect)
    {
        //setter to set dbconnect

        this.dbconnect = dbconnect;
    }

    public void setResults(ResultSet results)
    {
        //setter to set results

        this.results = results;
    }

    public String getUsername()
    {
        //getter to get USERNAME

        return USERNAME;
    }

    public String getDburl()
    {
        //getter to get DBURL

        return DBURL;
    }

    public String getPassword()
    {
        //getter to get PASSWORD

        return PASSWORD;
    }

    public Connection getDbconnect()
    {
        //getter to get dbconnect

        return dbconnect;
    }

    public ResultSet getResults()
    {
        //getter to get results

        return results;
    }
}

class ProgramInput extends ConnectDatabase
{

    /**
     * ProgramInput class inherits from ConnectDatabase
     *
     * This class is responsible for obtaining the input from the user via command line and checking the validity of the
     * input
     */


    private String itemType;            //Type of item user wishes to buy
    private String itemTable;           //contains the table of the item which the user wishes to buy
    private String numItems;            //contains the number of items entered by the user
    private int flagInput=0;            //flag to keep track of whether input operation has already been performed
    private int reqValue=0;             //Stores integer value of numItems

    public ProgramInput(String DBURL, String USERNAME, String PASSWORD) throws IOException
    {
        // Constructor which calls the super constructor of parent class

        super(DBURL,USERNAME,PASSWORD);
    }


    public void setNumItems(String numItems)
    {
        //Setter to set the numItems

        this.numItems = numItems;
    }

    public void setItemTable(String itemTable)
    {
        //Setter to set itemTable

        this.itemTable = itemTable;
    }

    public void setItemType(String itemType)
    {
        //Setter to set itemType

        this.itemType = itemType;
    }

    public String getItemTable()
    {
        //Getter to get itemTable

        return itemTable;
    }

    public String getItemType()
    {
        //Getter to get itemType

        return itemType;
    }

    public String getNumItems()
    {
        //Getter to get numItems

        return numItems;
    }
    public int getReqValue()
    {
        //Getter to get reqValue

        return reqValue;
    }

    public void setReqValue(int reqValue) {
        this.reqValue = reqValue;
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

    private void checkValidItem(String itemName)
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

        if(!itemName.contains(" ")||countSpace>1&&!itemName.equals("swing arm lamp"))
        {
            /*if the item name does not contain a space Re-enter the string or if it contains a space it should be
            "swing arm lamp"*/

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
            System.out.println("Item does not exists please enter a valid item");
            System.out.println();
            userInput();


        }
        catch (SQLException ex)
        {
            System.out.println();
            System.out.println("Item does not exists please enter a valid item");
            System.out.println();
            userInput();

        }
    }
}

class DatabaseCalculation extends ProgramInput
{
    /**
     *
     * DatabaseCalculation class inherits from ProgramInput
     *
     * This class is responsible for all the MySQL operations which include : extraction of data from the database and
     * modifying the table after certain orders are satisfied. It even contains calculateLowestPrice which is the key
     * function responsible for calculating the lowest price. Finally, it is responsible for writing the results
     * to the output file.
     */


    private int counter=0;
    public String str ="";
    public boolean firstTime=true;
    public String manufacturers;
    int totalPrice=0;
    ArrayList<String> output = new ArrayList<String>();
    File outFile = new File("orderform.txt");
    FileWriter myWriter = new FileWriter(outFile);

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
    
    private String[][] checkFiling(String[][] filing, int count) throws IOException {
        /*
            this method receives a 2d array from selectFiling method and uses it to check if the Filing can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed.
            the way the method checks if the filing can be constructed is as follows,
            there are three counter variables set to zero in the beginning.
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of
            furniture exists, only if all the counters are greater than zero the required furniture can be constructed.
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
                output.add(" cannot be constructed due to insufficient materials.\n");
            }
            else
            {
                output.add("s cannot be constructed due to insufficient materials.\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add(manufacturers);
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    private String[][] lampSelect(String type, int count){
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
    private String[][] checkLamp(String[][] lamp, int count) throws IOException {   int c1=0,c2=0;
        /*
            this method receives a 2d array from selectLamp method and uses it to check if the Lamp can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed.
            the way the method checks if the lamp can be constructed is as follows,
            there are two counter variables set to zero in the beginning.
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part
            of furniture exists. only if all the counters are greater than zero the required furniture can be constructed.
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
                output.add(" cannot be constructed due to insufficient materials.\n");
            }
            else
            {
                output.add("s cannot be constructed due to insufficient materials.\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add(manufacturers);
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    private String[][] deskSelect(String type, int count){
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
    private String[][] checkDesk(String[][] desk, int count) throws IOException {   int c1=0,c2=0,c3=0;
        /*
            this method receives a 2d array from selectDesk method and uses it to check if the Desk can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed.
            the way the method checks if the Desk can be constructed is as follows,
            there are three counter variables set to zero in the beginning.
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of
            furniture exists. only if all the counters are greater than zero the required furniture can be constructed.
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
                output.add(" cannot be constructed due to insufficient materials.\n");
            }
            else
            {
                output.add("s cannot be constructed due to insufficient materials.\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add(manufacturers);
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add("Sorry for the inconvenience.\n");
            return null;
        }
    }
    private String[][] chairSelect(String type, int count){
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
    private String[][] checkChair(String[][] chair, int count) throws IOException {   int c1=0,c2=0,c3=0,c4=0;
        /*
            this method receives a 2d array from selectChair method and uses it to check if the Chair can be constructed
            if it can be constructed it passes the 2d array further to the method calculateLowestPrice
            if it cannot be constructed the else block of the code is executed.
            the way the method checks if the Chair can be constructed is as follows,
            there are four counter variables set to zero in the beginning.
            whenever a 'Y' is encountered in a row , its respective counter is incremented. 'Y' depicts that the part of
            furniture exists. only if all the counters are greater than zero the required furniture can be constructed.
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
                output.add(" cannot be constructed due to insufficient materials.\n");
            }
            else
            {
                output.add("s cannot be constructed due to insufficient materials.\n");
            }
            output.add("\nPlease contact one of the manufacturers mentioned below for more information.\n");
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            output.add(manufacturers);
            output.add("------------------------------------------------------------------------------------------------------------------------------------------------------\n");
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
        if(firstTime)
        {
            manufacturers = getManufacturers(getItemTable());
            firstTime=false;
        }
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
        StringBuilder manufacturers = new StringBuilder();
        if(table.equals("chair"))
        {
            manufacturers.append("1. Office Furnishings   : 587-890-4387\n");
            manufacturers.append("2. Chairs R Us          : 705-667-9481\n");
            manufacturers.append("3. Furniture Goods      : 306-512-5508\n");
            manufacturers.append("4. Fine Office Supplies : 403-980-9876\n");
        }
        if(table.equals("desk"))
        {
            manufacturers.append("1. Academic Desks       : 236-145-2542\n");
            manufacturers.append("2. Office Furnishings   : 587-890-4387\n");
            manufacturers.append("3. Furniture Goods      : 306-512-5508\n");
            manufacturers.append("4. Fine Office Supplies : 403-980-9876\n");
        }
        if(table.equals("lamp"))
        {
            manufacturers.append("1. Office Furnishings   : 587-890-4387\n");
            manufacturers.append("2. Furniture Goods      : 306-512-5508\n");
            manufacturers.append("3. Fine Office Supplies : 403-980-9876\n");
        }
        if(table.equals("filing"))
        {
            manufacturers.append("1. Office Furnishings   : 587-890-4387\n");
            manufacturers.append("2. Furniture Goods      : 306-512-5508\n");
            manufacturers.append("3. Fine Office Supplies : 403-980-9876\n");
        }
        return manufacturers.toString();
    }

    private void deleteFromTable(String table, String objectID)
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
        This method calculates the lowest int price for the passed tableData and returns a integer value of it by
        following this approach:
        * Step 1 : It firstly checks if the 2D string (the table for this item) passed has 4,5 or 6 columns.
        * Step 2 : For the specific table it then uses multiple nested loops to append the prices of all possible
                   combinations to "prices" arraylist.
        * Step 3 : It then returns the lowest integer element in the arraylist which is the lowest price for our item.
        * Step 4 : the algorithm simaltaneously keeps track of the IDs of those prices and then calls the deleteFromTable()
                    to delete the rows whose prices togather cost the lowest price for that item.
        */

        ArrayList<Integer> prices = new ArrayList<>();
        ArrayList<String> idTracker = new ArrayList<>();

        int lowestPrice;

        if(tableData[0].length==6)
        {
            for(int i=0;i< tableData.length;i++)
            {
                for(int j=0;j< tableData.length;j++)
                {
                    for(int k=0;k< tableData.length;k++)
                    {
                        for(int l=0;l<tableData.length;l++)
                        {
                            if(tableData[i][0].contains("Y")&&tableData[j][1].contains("Y")&&tableData[k][2].contains("Y")
                                    &&tableData[l][3].contains("Y"))
                            {
                                int rowsPrice=0;
                                List<Integer> rows = new ArrayList<>();
                                rows.add(i);
                                rows.add(j);
                                rows.add(k);
                                rows.add(l);
                                List<Integer> newList = rows.stream().distinct().collect(Collectors.toList());
                                String id = new String();
                                for(int m=0;m<newList.size();m++)
                                {
                                    rowsPrice+=Integer.parseInt(tableData[newList.get(m)][tableData[0].length-2]);
                                    id+=newList.get(m)+" ";
                                }
                                prices.add(rowsPrice);
                                idTracker.add(id);
                            }
                        }
                    }
                }
            }

            lowestPrice=Collections.min(prices);
            String tempId = idTracker.get(prices.indexOf(lowestPrice));
            tempId=tempId.substring(0,tempId.length()-1);

            totalPrice += lowestPrice;
            String[] strId = tempId.split(" ");
            String itemId ="";

            for(int i=0;i< strId.length;i++)
            {
                itemId+=tableData[Integer.parseInt(strId[i])][tableData[0].length-1]+",";
                deleteFromTable(getItemTable(),tableData[Integer.parseInt(strId[i])][tableData[0].length-1]);
            }

            itemId=itemId.substring(0,itemId.length()-1);

            counter++;
            String value = numFormat(counter);
            output.add("• The lowest cost to manufacture " + value + " item of " + getItemType() + " " + getItemTable()
                    + " is - " + lowestPrice + ".00 $ [Items Reused : "+itemId+"]\n");

            if (Integer.parseInt(getNumItems()) > 1)
            {
                setNumItems(String.valueOf(Integer.parseInt(getNumItems()) - 1));
                selectFurnitureType(getItemType(), getItemTable());
            }

            return lowestPrice;

        }

        else if(tableData[0].length==5)
        {
            for(int i=0;i< tableData.length;i++)
            {
                for(int j=0;j< tableData.length;j++)
                {
                    for(int k=0;k<tableData.length;k++)
                    {
                        if(tableData[i][0].contains("Y")&&tableData[j][1].contains("Y")&&tableData[k][2].contains("Y"))
                        {
                            int rowsPrice=0;
                            List<Integer> rows = new ArrayList<>();
                            rows.add(i);
                            rows.add(j);
                            rows.add(k);
                            List<Integer> newList = rows.stream().distinct().collect(Collectors.toList());
                            String id = new String();
                            for(int m=0;m<newList.size();m++)
                            {
                                rowsPrice+=Integer.parseInt(tableData[newList.get(m)][tableData[0].length-2]);
                                id+=newList.get(m)+" ";
                            }
                            prices.add(rowsPrice);
                            idTracker.add(id);
                        }
                    }
                }
            }

            lowestPrice=Collections.min(prices);
            String tempId = idTracker.get(prices.indexOf(lowestPrice));
            tempId=tempId.substring(0,tempId.length()-1);

            totalPrice += lowestPrice;
            String[] strId = tempId.split(" ");
            String itemId ="";

            for(int i=0;i< strId.length;i++)
            {
                itemId+=tableData[Integer.parseInt(strId[i])][tableData[0].length-1]+",";
                deleteFromTable(getItemTable(),tableData[Integer.parseInt(strId[i])][tableData[0].length-1]);
            }

            itemId=itemId.substring(0,itemId.length()-1);

            counter++;
            String value = numFormat(counter);
            output.add("• The lowest cost to manufacture " + value + " item of " + getItemType() + " " + getItemTable()
                    + " is - " + lowestPrice + ".00 $ [Items Reused : "+itemId+"]\n");

            if (Integer.parseInt(getNumItems()) > 1)
            {
                setNumItems(String.valueOf(Integer.parseInt(getNumItems()) - 1));
                selectFurnitureType(getItemType(), getItemTable());
            }

            return lowestPrice;
        }

        else
        {
            for(int i=0;i< tableData.length;i++)
            {
                for(int j=0;j<tableData.length;j++)
                {
                    if(tableData[i][0].contains("Y")&&tableData[j][1].contains("Y"))
                    {
                        int rowsPrice=0;
                        List<Integer> rows = new ArrayList<>();
                        rows.add(i);
                        rows.add(j);
                        List<Integer> newList = rows.stream().distinct().collect(Collectors.toList());
                        String id = new String();
                        for(int m=0;m<newList.size();m++)
                        {
                            rowsPrice+=Integer.parseInt(tableData[newList.get(m)][tableData[0].length-2]);
                            id+=newList.get(m)+" ";
                        }
                        prices.add(rowsPrice);
                        idTracker.add(id);
                    }
                }
            }

            lowestPrice=Collections.min(prices);
            String tempId = idTracker.get(prices.indexOf(lowestPrice));
            tempId=tempId.substring(0,tempId.length()-1);

            totalPrice += lowestPrice;
            String[] strId = tempId.split(" ");
            String itemId ="";

            for(int i=0;i< strId.length;i++)
            {
                itemId+=tableData[Integer.parseInt(strId[i])][tableData[0].length-1]+",";
                deleteFromTable(getItemTable(),tableData[Integer.parseInt(strId[i])][tableData[0].length-1]);
            }

            itemId=itemId.substring(0,itemId.length()-1);

            counter++;
            String value = numFormat(counter);
            output.add("• The lowest cost to manufacture " + value + " item of " + getItemType() + " " + getItemTable()
                    + " is - " + lowestPrice + ".00 $ [Items Reused : "+itemId+"]\n");

            if (Integer.parseInt(getNumItems()) > 1)
            {
                setNumItems(String.valueOf(Integer.parseInt(getNumItems()) - 1));
                selectFurnitureType(getItemType(), getItemTable());
            }

            return lowestPrice;
        }

    }


    private static String numFormat(int i) {
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
        output.add(0,"------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        output.add(1,"                                              \t          \t\tSALES INVOICE                                               \n");
        output.add(2,"------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        output.add(3,"• Faculty Name: \n");
        output.add(4,"• Contact: \n");
        output.add(5,"• Date: \n");
        output.add(6,"• The Item: " + getItemType() +" "+ getItemTable() +"\n• Number of Items Requested: "
                +getReqValue()+"\n");
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

    /**
     * This class contains only the main function which creates objects of all the other classes to carry out the
     * functionalities of the code by calling their member functions
     *
     * A description on how to run the code is added in the read me file.
     *
     * @param args
     * @throws IOException
     */


    public static void main(String[] args) throws IOException
    {

        DatabaseCalculation myJDBC = new DatabaseCalculation("jdbc:mysql://localhost/inventory","scm","ensf409");
        myJDBC.initializeConnection();
        myJDBC.userInput();

        myJDBC.selectFurnitureType(myJDBC.getItemType(),myJDBC.getItemTable());
        myJDBC.writeOrderForm();
        myJDBC.closes();

    }
}
