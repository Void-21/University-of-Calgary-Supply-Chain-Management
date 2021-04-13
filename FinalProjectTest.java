package edu.ucalgary.ensf409;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import java.io.*;
import java.sql.*;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FinalProjectTest{
    /**
     *  DOCUMENTATION FOR JUNIT TESTING:
     *
     *  initializeConnection(): is a method that establishes a connection between the .java files and the sql database.
     *
     *  testConstructor() performs the following task:
     *  It Checks if the constructor initializes all the values correctly and prints out an error message in the event of
     *  failing any of the checks.
     *
     *  chair() ,desk() ,filing() ,lamp() perform the following task:
     *  These functions are called before each test to restore the database with the original contents, as the contents
     *  get manipulated by each test restoring the database after each test is necessary to ensure every test is able
     *  to perform its required task.
     *
     *  testSelectFurnitureTypeTaskChair() and similar functions perform the following task:
     *  Tests two functions, it first calls the selectFurnitureType() function, which calls the chairSelect
     *  function by using the type and number of occurrences of the specific type of chair, chairSelect() function
     *  returns a 2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched
     *  with our expected output to complete the testing.
     *
     *  testCalculateLowestPriceTaskChair() and similar functions perform the following task:
     *  Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
     *  Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
     *  checks if the output text file is correctly written to or not.
     *  Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
     *  is calculated correctly or not.
     *
     */

    public final static String USERNAME = "NUMAN";              //USERNAME should be changed to match the one set by the user on their system
    public final static String PASSWORD = "TIGER";              //PASSWORD should be changed to match the one set by the user on their system
    public final static String DBURL = "jdbc:mysql://localhost/inventory";           //DBURL should be changed to match the one set by the user on their system
    public final static String filePath ="C:\\Users\\user\\Desktop\\ENSF409\\FinalProject\\orderform.txt";
    private Connection dbConnect;
    public void chair() {
        /*
            ~ this method is used to restore the database with its original contents after execution of each test.
              As each test calls a function and every function in our code is interlinked, this leads to calling several functions
              which in response manipulate the database.
            ~ restoring the database is necessary to ensure that the next test has all the table elements to be able to
              perform its check for a different function
            ~ this function in particular restores only the "chair" table with its original contents(which were provided by ensf409 dept.)
         */
        try {
            initializeConnection();
            Statement myStmt1 = dbConnect.createStatement();
            myStmt1.executeUpdate("DROP TABLE IF EXISTS CHAIR");

            Statement myStmt2 = dbConnect.createStatement();
            myStmt2.executeUpdate("CREATE TABLE CHAIR (  ID    char(5) not null,  Type   varchar(25),  Legs   char(1),  Arms   char(1),  Seat   char(1),  Cushion   char(1),     Price   integer,  ManuID   char(3),  primary key (ID),  foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE )");

            Statement myStmt3 = dbConnect.createStatement();
            myStmt3.executeUpdate("INSERT INTO CHAIR (ID, Type, Legs, Arms, Seat, Cushion, Price, ManuID) VALUES ('C1320', 'Kneeling', 'Y', 'N', 'N', 'N', 50, '002'), ('C3405', 'Task', 'Y', 'Y', 'N', 'N', 100, '003'), ('C9890', 'Mesh', 'N', 'Y', 'N', 'Y', 50, '003'), ('C7268', 'Executive', 'N', 'N', 'Y', 'N', 75, '004'), ('C0942', 'Mesh', 'Y', 'N', 'Y', 'Y', 175, '005'), ('C4839', 'Ergonomic', 'N', 'N', 'N', 'Y', 50, '002'), ('C2483', 'Executive', 'Y', 'Y', 'N', 'N', 175, '002'), ('C5789', 'Ergonomic', 'Y', 'N', 'N', 'Y', 125, '003'), ('C3819', 'Kneeling', 'N', 'N', 'Y', 'N', 75, '005'), ('C5784', 'Executive', 'Y', 'N', 'N', 'Y', 150, '004'), ('C6748', 'Mesh', 'Y', 'N', 'N', 'N', 75, '003'), ('C0914', 'Task', 'N', 'N', 'Y', 'Y', 50, '002'), ('C1148', 'Task', 'Y', 'N', 'Y', 'Y', 125, '003'), ('C5409', 'Ergonomic', 'Y', 'Y', 'Y', 'N', 200, '003'), ('C8138', 'Mesh', 'N', 'N', 'Y', 'N', 75, '005')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void desk(){
        /*
            ~ this method is used to restore the database with its original contents after execution of each test.
              As each test calls a function and every function in our code is interlinked, this leads to calling several functions
              which in response manipulate the database.
            ~ restoring the database is necessary to ensure that the next test has all the table elements to be able to
              perform its check for a different function
            ~ this function in particular restores only the "desk" table with its original contents(which were provided by ensf409 dept.)
         */
        try {
            initializeConnection();
            Statement myStmt1 = dbConnect.createStatement();
            myStmt1.executeUpdate("DROP TABLE IF EXISTS DESK");

            Statement myStmt2 = dbConnect.createStatement();
            myStmt2.executeUpdate("CREATE TABLE DESK (  ID    char(5) not null,  Type   varchar(25),  Legs   char(1),  Top   char(1),  Drawer   char(1),     Price   integer,  ManuID   char(3),  primary key (ID),  foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE )");

            Statement myStmt3 = dbConnect.createStatement();
            myStmt3.executeUpdate("INSERT INTO DESK (ID, Type, Legs, Top, Drawer, Price, ManuID) VALUES ('D3820', 'Standing', 'Y', 'N', 'N', 150, '001'), ('D4475', 'Adjustable', 'N', 'Y', 'Y', 200, '002'), ('D0890', 'Traditional', 'N', 'N', 'Y', 25, '002'), ('D2341', 'Standing', 'N', 'Y', 'N', 100, '001'), ('D9387', 'Standing', 'Y', 'Y', 'N', 250, '004'), ('D7373', 'Adjustable', 'Y', 'Y', 'N', 350, '005'), ('D2746', 'Adjustable', 'Y', 'N', 'Y', 250, '004'), ('D9352', 'Traditional', 'Y', 'N', 'Y', 75, '002'), ('D4231', 'Traditional', 'N', 'Y', 'Y', 50, '005'), ('D8675', 'Traditional', 'Y', 'Y', 'N', 75, '001'), ('D1927', 'Standing', 'Y', 'N', 'Y', 200, '005'), ('D1030', 'Adjustable', 'N', 'Y', 'N', 150, '002'), ('D4438', 'Standing', 'N', 'Y', 'Y', 150, '004'), ('D5437', 'Adjustable', 'Y', 'N', 'N', 200, '001'), ('D3682', 'Adjustable', 'N', 'N', 'Y', 50, '005')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void lamp(){
        /*
            ~ this method is used to restore the database with its original contents after execution of each test.
              As each test calls a function and every function in our code is interlinked, this leads to calling several functions
              which in response manipulate the database.
            ~ restoring the database is necessary to ensure that the next test has all the table elements to be able to
              perform its check for a different function
            ~ this function in particular restores only the "lamp" table with its original contents(which were provided by ensf409 dept.)
         */
        try {
            initializeConnection();
            Statement myStmt1 = dbConnect.createStatement();
            myStmt1.executeUpdate("DROP TABLE IF EXISTS LAMP");

            Statement myStmt2 = dbConnect.createStatement();
            myStmt2.executeUpdate("CREATE TABLE LAMP (  ID    char(4) not null,  Type   varchar(25),  Base   char(1),  Bulb   char(1),     Price   integer,  ManuID   char(3),  primary key (ID),  foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE )");

            Statement myStmt3 = dbConnect.createStatement();
            myStmt3.executeUpdate("INSERT INTO LAMP (ID, Type, Base, Bulb, Price, ManuID) VALUES ('L132', 'Desk', 'Y', 'N', 18, '005'), ('L980', 'Study', 'N', 'Y', 2, '004'), ('L487', 'Swing Arm', 'Y', 'N', 27, '002'), ('L564', 'Desk', 'Y', 'Y', 20, '004'), ('L342', 'Desk', 'N', 'Y', 2, '002'), ('L982', 'Study', 'Y', 'N', 8, '002'), ('L879', 'Swing Arm', 'N', 'Y', 3, '005'), ('L208', 'Desk', 'N', 'Y', 2, '005'), ('L223', 'Study', 'N', 'Y', 2, '005'), ('L928', 'Study', 'Y', 'Y', 10, '002'), ('L013', 'Desk', 'Y', 'N', 18, '004'), ('L053', 'Swing Arm', 'Y', 'N', 27, '002'), ('L112', 'Desk', 'Y', 'N', 18, '005'), ('L649', 'Desk', 'Y', 'N', 18, '004'), ('L096', 'Swing Arm', 'N', 'Y', 3, '002')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void filing(){
        /*
            ~ this method is used to restore the database with its original contents after execution of each test.
              As each test calls a function and every function in our code is interlinked, this leads to calling several functions
              which in response manipulate the database.
            ~ restoring the database is necessary to ensure that the next test has all the table elements to be able to
              perform its check for a different function
            ~ this function in particular restores only the "filing" table with its original contents(which were provided by ensf409 dept.)
         */
        try {
            initializeConnection();
            Statement myStmt1 = dbConnect.createStatement();
            myStmt1.executeUpdate("DROP TABLE IF EXISTS FILING");

            Statement myStmt2 = dbConnect.createStatement();
            myStmt2.executeUpdate("CREATE TABLE FILING (  ID    char(4) not null,  Type   varchar(25),  Rails   char(1),  Drawers   char(1),  Cabinet   char(1),     Price   integer,  ManuID   char(3),  primary key (ID),  foreign key (ManuID) references MANUFACTURER(ManuID) ON UPDATE CASCADE )");

            Statement myStmt3 = dbConnect.createStatement();
            myStmt3.executeUpdate("INSERT INTO FILING (ID, Type, Rails, Drawers, Cabinet, Price, ManuID) VALUES ('F001', 'Small', 'Y', 'Y', 'N', 50, '005'), ('F002', 'Medium', 'N', 'N', 'Y', 100, '004'), ('F003', 'Large', 'N', 'N', 'Y', 150, '002'), ('F004', 'Small', 'N', 'Y', 'Y', 75, '004'), ('F005', 'Small', 'Y', 'N', 'Y', 75, '005'), ('F006', 'Small', 'Y', 'Y', 'N', 50, '005'), ('F007', 'Medium', 'N', 'Y', 'Y', 150, '002'), ('F008', 'Medium', 'Y', 'N', 'N', 50, '005'), ('F009', 'Medium', 'Y', 'Y', 'N', 100, '004'), ('F010', 'Large', 'Y', 'N', 'Y', 225, '002'), ('F011', 'Large', 'N', 'Y', 'Y', 225, '005'), ('F012', 'Large', 'N', 'Y', 'N', 75, '005'), ('F013', 'Small', 'N', 'N', 'Y', 50, '002'), ('F014', 'Medium', 'Y', 'Y', 'Y', 200, '002'), ('F015', 'Large', 'Y', 'N', 'N', 75, '004')");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testConstructor() throws IOException {
        /* Checks if the constructor initializes all the values correctly and prints out an error message in the event of
           failing any of the checks.
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        assertEquals("DBURL is wrong", testObj.getDburl(), DBURL);
        assertEquals("USERNAME is wrong", testObj.getUsername(), USERNAME);
        assertEquals("PASSWORD is wrong", testObj.getPassword(), PASSWORD);
    }
    @Test
    public void testCalculateLowestPriceMeshChair() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("chair");
        testObj.setItemType("mesh");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{"Y", "N", "Y", "Y", "175","C0942"},
                {"Y", "N", "N", "N", "75", "C6748"},
                {"N", "N", "Y", "N", "75", "C8138"},
                {"N", "Y", "N", "Y", "50", "C9890"}};
        int result = testObj.calculateLowestPrice(input);
        chair();
        assertEquals("lowest price calculated is incorrect",result,200);

    }

    @Test
    public void testCalculateLowestPriceTaskChair() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("chair");
        testObj.setItemType("task");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{ "N", "N", "Y", "Y", "50", "C0914"},
                {"Y", "N", "Y", "Y", "125", "C1148"},
                {"Y", "Y", "N", "N", "100","C3405"}};
        int result = testObj.calculateLowestPrice(input);
        chair();
        assertEquals("lowest price calculated is incorrect",result,150);

    }

    @Test
    public void testCalculateLowestPriceErgonomicChair() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("chair");
        testObj.setItemType("ergonomic");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{"N", "N", "N", "Y", "50","C4839"},
                {"Y", "Y", "Y", "N", "200","C5409"},
                {"Y", "N", "N", "Y", "125","C5789"}};
        int result = testObj.calculateLowestPrice(input);
        chair();
        assertEquals("lowest price calculated is incorrect",result,250);

    }

    @Test
    public void testCalculateLowestPriceExecutiveChair() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("chair");
        testObj.setItemType("executive");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{"Y", "Y", "N", "N", "175","C2483"},
                {"Y", "N", "N", "Y", "150", "C5784"},
                {"N", "N", "Y", "N", "75", "C7268"}};
        int result = testObj.calculateLowestPrice(input);
        chair();
        assertEquals("lowest price calculated is incorrect",result,400);

    }

    @Test
    public void testCalculateLowestPriceSwingArmLamp() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("lamp");
        testObj.setItemType("swing arm");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{ "Y", "N", "27", "L053"},
                {"N", "Y" ,"3" ,"002","L096"},
                {"Y", "N", "27", "002","L487"},
                {"N", "Y", "3","L879"}};
        int result = testObj.calculateLowestPrice(input);
        lamp();
        assertEquals("lowest price calculated is incorrect",result,30);

    }

    @Test
    public void testCalculateLowestPriceDeskLamp() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("lamp");
        testObj.setItemType("desk");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{"Y", "N", "18","L013"},
                {"Y", "N", "18","L112"},
                {"Y", "N", "18","L132"},
                {"N", "Y", "2","L208",},
                {"N", "Y", "2", "L342"},
                {"Y", "Y", "20", "L564"},
                {"Y", "N", "18", "L649"}};
        int result = testObj.calculateLowestPrice(input);
        lamp();
        assertEquals("lowest price calculated is incorrect",result,20);

    }

    @Test
    public void testCalculateLowestPriceStudyLamp() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("lamp");
        testObj.setItemType("study");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{"N", "Y", "2", "L223"},
                {"Y", "Y", "10", "L928"},
                {"N", "Y", "2", "L980"},
                {"Y", "N", "8", "L982"}};
        int result = testObj.calculateLowestPrice(input);
        lamp();
        assertEquals("lowest price calculated is incorrect",result,10);

    }

    @Test
    public void testCalculateLowestPriceTraditionalDesk() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("desk");
        testObj.setItemType("traditional");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input = {{"N", "N", "Y" ,"25", "D0890"},
                {"N", "Y", "Y", "50", "D4231"},
                {"Y" ,"Y", "N", "75", "D8675"},
                {"Y" ,"N", "Y", "75" ,"D9352"}};
        int result = testObj.calculateLowestPrice(input);
        desk();
        assertEquals("lowest price calculated is incorrect",result,100);
    }
    @Test
    public void testCalculateLowestPriceStandingDesk() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("desk");
        testObj.setItemType("stading");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input = {{"Y", "N", "Y", "200", "D1927"},
                {"N", "Y", "N", "100", "D2341"},
                {"Y", "N" ,"N", "150", "D8675"},
                {"N", "Y", "Y", "150", "D4438"},
                {"Y", "Y", "N", "250", "D9387"}};
        int result = testObj.calculateLowestPrice(input);
        desk();
        assertEquals("lowest price calculated is incorrect",result,300);
    }
    @Test
    public void testCalculateLowestPriceAdjustableDesk() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("desk");
        testObj.setItemType("adjustable");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input = {{"N", "Y", "N", "150", "D1030"},
                {"Y", "N", "Y", "250", "D2746"},
                {"N", "N", "Y", "50", "D3682"},
                {"N", "Y", "Y", "200", "D4475"},
                {"Y", "N", "N", "200", "D5437"},
                {"Y" ,"Y", "N" ,"350", "D7373"} };
        int result = testObj.calculateLowestPrice(input);
        desk();
        assertEquals("lowest price calculated is incorrect",result,400);
    }
    @Test
    public void testCalculateLowestPriceMediumFiling() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("filing");
        testObj.setItemType("medium");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{ "N","N","Y","100","F002"},
                { "N","Y","Y","150","F007"},
                { "Y","N","N","50","F008"},
                { "Y","Y","N","100","F009"},
                { "Y","Y","Y","200","F014"}};
        int result = testObj.calculateLowestPrice(input);
        filing();
        assertEquals("lowest price calculated is incorrect",result,200);
    }
    @Test
    public void testCalculateLowestPriceSmallFiling() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("filing");
        testObj.setItemType("small");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{ "Y","Y","N","50","F001"},
                { "N","Y","Y","75","F004"},
                { "Y","N","Y","75","F005"},
                { "Y","Y","N","50","F006"},
                { "N","N","Y","50","F013"}};
        int result = testObj.calculateLowestPrice(input);
        filing();
        assertEquals("lowest price calculated is incorrect",result,100);
    }
    @Test
    public void testCalculateLowestPriceLargeFiling() throws IOException
    {
        /*
           This test checks the return value of the calculateLowestPrice function and compares
           the return value with the expected value.
           The testing process involves passing a 2D array of all the boolean values and the price of the specific table and type
           which then returns an integer
        */
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemTable("filing");
        testObj.setItemType("large");
        testObj.setNumItems("1");
        testObj.setReqValue(1);
        String[][] input ={{ "N","N","Y","150","F003"},
                { "Y","N","Y","225","F010"},
                { "N","Y","Y","225","F011"},
                { "N","Y","N","75","F012"},
                { "Y","N","N","75","F015"}};
        int result = testObj.calculateLowestPrice(input);
        filing();
        assertEquals("lowest price calculated is incorrect",result,300);
    }
    @Test
    public void testSelectFurnitureTypeTaskChair() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Task";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C0914", "Task", "N", "N", "Y", "Y", "50", "002"},
                {"C1148", "Task", "Y", "N", "Y", "Y", "125", "003"},
                {"C3405", "Task", "Y", "Y", "N", "N", "100", "003"}};
        chair();
        assertEquals("selectFurnitureType does not return the correct rows for Task Chair",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeMeshChair() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Mesh";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C0942", "Mesh", "Y", "N", "Y", "Y", "175", "005"},
                {"C6748", "Mesh", "Y", "N", "N", "N", "75", "003"},
                {"C8138", "Mesh", "N", "N", "Y", "N", "75", "005"},
                {"C9890", "Mesh", "N", "Y", "N", "Y", "50", "003"}};
        chair();
        assertEquals("selectFurnitureType does not return the correct rows for Mesh Chair",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeKneelingChair() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Kneeling";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected =
                {{"C1320", "Kneeling", "Y", "N", "N", "N", "50", "002"},
                        {"C3819", "Kneeling", "N", "N", "Y", "N", "75", "005"}};
         chair();
        assertEquals("selectFurnitureType does not return the correct rows for Kneeling Chair",result,expected);
    }

    @Test
    public void testSelectFurnitureTypeErgonomicChair() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Ergonomic";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C4839", "Ergonomic", "N", "N", "N", "Y", "50", "002"},
                {"C5409", "Ergonomic", "Y", "Y", "Y", "N", "200", "003"},
                {"C5789", "Ergonomic", "Y", "N", "N", "Y", "125", "003"}};
         chair();
        assertEquals("selectFurnitureType does not return the correct rows for Ergonomic Chair",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeExecutiveChair() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Executive";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C2483", "Executive", "Y", "Y", "N", "N", "175", "002"},
                {"C5784", "Executive", "Y", "N", "N", "Y", "150", "004"},
                {"C7268", "Executive", "N", "N", "Y", "N", "75", "004"}};
         chair();
        assertEquals("selectFurnitureType does not return the correct rows for Executive Chair",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeSmallFiling() throws IOException {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the filingSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Small";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{ "F001","Small","Y","Y","N","50","005"},
                { "F004","Small","N","Y","Y","75","004"},
                { "F005","Small","Y","N","Y","75","005"},
                { "F006","Small","Y","Y","N","50","005"},
                { "F013","Small","N","N","Y","50","002"}};
         filing();
        assertEquals("selectFurnitureType does not return the correct rows for Small Filing",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeMediumFiling() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the filingSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Medium";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{ "F002","Medium","N","N","Y","100","004"},
                { "F007","Medium","N","Y","Y","150","002"},
                { "F008","Medium","Y","N","N","50","005"},
                { "F009","Medium","Y","Y","N","100","004"},
                { "F014","Medium","Y","Y","Y","200","002"}};
         filing();
        assertEquals("selectFurnitureType does not return the correct rows for Medium Filing",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeLargeFiling() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the filingSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Large";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{ "F003","Large","N","N","Y","150","002"},
                { "F010","Large","Y","N","Y","225","002"},
                { "F011","Large","N","Y","Y","225","005"},
                { "F012","Large","N","Y","N","75","005"},
                { "F015","Large","Y","N","N","75","004"}};
         filing();
        assertEquals("selectFurnitureType does not return the correct rows for Large Filing",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeDeskLamp() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the lampSelect
         function by using the type and number of occurrences of the specific type of lamp, lampSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Desk";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected = {{"L013", "Desk", "Y", "N", "18", "004"},
                {"L112", "Desk", "Y", "N", "18", "005"},
                {"L132", "Desk", "Y", "N", "18", "005"},
                {"L208", "Desk", "N", "Y", "2", "005"},
                {"L342", "Desk", "N", "Y", "2", "002"},
                {"L564", "Desk", "Y", "Y", "20", "004"},
                {"L649", "Desk", "Y", "N", "18", "004"}};
         lamp();
        assertEquals("selectFurnitureType does not return the correct rows for Desk Lamp", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeSwingArmLamp() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the lampSelect
         function by using the type and number of occurrences of the specific type of lamp, lampSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Swing Arm";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("7");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected = {{"L053", "Swing Arm", "Y", "N", "27", "002"},
                {"L096", "Swing Arm" ,"N", "Y" ,"3" ,"002"},
                {"L487", "Swing Arm", "Y", "N", "27", "002"},
                {"L879" ,"Swing Arm" ,"N", "Y", "3", "005"}};
         lamp();
        assertEquals("selectFurnitureType does not return the correct rows for Swing Arm Lamp", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeStudyLamp() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the lampSelect
         function by using the type and number of occurrences of the specific type of lamp, lampSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */
        String type = "Study";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("4");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected = {{"L223", "Study", "N", "Y", "2", "005"},
                {"L928", "Study", "Y", "Y", "10", "002"},
                {"L980", "Study", "N", "Y", "2", "004"},
                {"L982", "Study", "Y", "N", "8", "002"}};
        lamp();
        assertEquals("selectFurnitureType does not return the correct rows for Study Lamp", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeTraditionalDesk() throws IOException{
       /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the deskSelect
         function by using the type and number of occurrences of the specific type of desk, deskSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Traditional";
        String table = "desk";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("4");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected = {{"D0890" ,"Traditional" ,"N", "N", "Y" ,"25", "002"},
                {"D4231", "Traditional" ,"N", "Y", "Y", "50", "005"},
                {"D8675", "Traditional" ,"Y" ,"Y", "N", "75", "001"},
                {"D9352" ,"Traditional" ,"Y" ,"N", "Y", "75" ,"002"}};
         desk();
        assertEquals("selectFurnitureType does not return the correct rows for Traditional Desk", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeStandingDesk() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the deskSelect
         function by using the type and number of occurrences of the specific type of desk, deskSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Standing";
        String table = "desk";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("5");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected = {{"D1927", "Standing", "Y", "N", "Y", "200", "005"},
                {"D2341", "Standing", "N", "Y", "N", "100", "001"},
                {"D3820", "Standing", "Y", "N" ,"N", "150", "001"},
                {"D4438", "Standing", "N", "Y", "Y", "150", "004"},
                {"D9387", "Standing", "Y", "Y", "N", "250", "004"}};
         desk();
        assertEquals("selectFurnitureType does not return the correct rows for Standing Desk", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeAdjustableDesk() throws IOException{
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the deskSelect
         function by using the type and number of occurrences of the specific type of desk, deskSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.

         prints out an error message in the event of failing any of the checks.
         */

        String type = "Adjustable";
        String table = "desk";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("5");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected = {{"D1030" ,"Adjustable", "N", "Y", "N", "150", "002"},
                {"D2746", "Adjustable", "Y", "N", "Y", "250", "004"},
                {"D3682", "Adjustable", "N", "N", "Y", "50", "005"},
                {"D4475" ,"Adjustable", "N", "Y", "Y", "200", "002"},
                {"D5437", "Adjustable", "Y", "N", "N", "200", "001"},
                {"D7373" ,"Adjustable" ,"Y" ,"Y", "N" ,"350", "005"} };
         desk();
        assertEquals("selectFurnitureType does not return the correct rows for Adjustable Desk", result, expected);
    }

    @Test
    public void test_CheckOutputFileExecutiveChair() throws IOException{
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */

        String type = "Executive";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("13");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Executive chair is - 400.00 $ [Items Reused : C2483,C7268,C5784]" ;
        String numNotPossible=readFile(11);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        chair();
        assertTrue("The lowest price calculated for 1st Executive chair is incorrect",result1.equals(expected1));
        assertEquals("Unable to correctly calculate the number of Executive chairs not possible",Integer.parseInt(numNotPossible),12);


    }
    @Test
    public void test_CheckOutputFileTaskChair() throws IOException{
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */

        String type = "Task";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("11");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Task chair is - 150.00 $ [Items Reused : C3405,C0914]" ;
        String numNotPossible=readFile(11);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        chair();
        assertTrue("The lowest price calculated for 1st Task chair is incorrect",result1.equals(expected1));
        assertEquals("Unable to correctly calculate the number of Task chairs not possible",Integer.parseInt(numNotPossible),10);


    }
    @Test
    public void test_CheckOutputFileMeshChair() throws IOException{
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Mesh";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("15");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Mesh chair is - 200.00 $ [Items Reused : C6748,C9890,C8138]" ;
        String numNotPossible=readFile(11);
          
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
          
        chair();
        assertTrue("The lowest price calculated for 1st Mesh chair is incorrect",result1.equals(expected1));
        assertEquals("Unable to correctly calculate the number of Mesh chairs not possible",Integer.parseInt(numNotPossible),14);

    }
    @Test
    public void test_CheckOutputFileErgonomicChair() throws IOException{
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */

        String type = "Ergonomic";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("69");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Ergonomic chair is - 250.00 $ [Items Reused : C5409,C4839]" ;
        String numNotPossible=readFile(11);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        chair();
        assertTrue("The lowest price calculated for 1st Ergonomic chair is incorrect",result1.equals(expected1));
        assertEquals("Unable to correctly calculate the number of Ergonomic chairs not possible",Integer.parseInt(numNotPossible),68);


    }
    @Test
    public void test_CheckOutputFileKneelingChair() throws IOException  {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */

        String type = "Kneeling";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("3");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String numNotPossible=readFile(10);
          
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
          
        chair();
        assertEquals("Unable to correctly calculate the number of Kneeling chairs not possible",Integer.parseInt(numNotPossible),3);


    }
    @Test
    public void test_CheckOutputFileSmallFiling() throws IOException  {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Small";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("23");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Small filing is - 100.00 $ [Items Reused : F001,F013]" ;
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Small filing is - 125.00 $ [Items Reused : F005,F006]";
        String numNotPossible=readFile(12);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        filing();
        assertEquals("The lowest price calculated for 1st Small filing is incorrect",result1,expected1);
        assertTrue("The lowest price calculated for 2nd Small filing is incorrect",result2.equals(expected2));
        assertEquals("Unable to correctly calculate the number of Small filing not possible",Integer.parseInt(numNotPossible),21);

    }
    @Test
    public void test_CheckOutputFileMediumFiling() throws IOException  {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not

         */

        String type = "Medium";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("9");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Medium filing is - 200.00 $ [Items Reused : F008,F007]" ;
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Medium filing is - 200.00 $ [Items Reused : F009,F002]";
        String result3 = readFile(11);
        String expected3 ="• The lowest cost to manufacture 3rd item of Medium filing is - 200.00 $ [Items Reused : F014]";
        String numNotPossible=readFile(13);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        filing();
        assertTrue("The lowest price calculated for 1st Medium filing is incorrect", result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd Medium filing is incorrect", result2.equals(expected2));
        assertTrue("The lowest price calculated for 3rd Medium filing is incorrect", result3.equals(expected3));
        assertEquals("Unable to correctly calculate the number of Medium filing not possible",Integer.parseInt(numNotPossible),6);

    }
    @Test
    public void test_CheckOutputFileLargeFiling() throws IOException  {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */

        String type = "Large";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("6");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Large filing is - 300.00 $ [Items Reused : F010,F012]" ;
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Large filing is - 300.00 $ [Items Reused : F015,F011]";
        String numNotPossible=readFile(12);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        filing();
        assertTrue("The lowest price calculated for 1st Large filing is incorrect", result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd Large filing is incorrect", result2.equals(expected2));
        assertEquals("Unable to correctly calculate the number of Large filing not possible",Integer.parseInt(numNotPossible),4);

    }
    @Test
    public void test_CheckOutputFileTraditionalDesk() throws IOException
    {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Traditional";
        String table = "desk";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("6");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Traditional desk is - 100.00 $ [Items Reused : D8675,D0890]" ;
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Traditional desk is - 125.00 $ [Items Reused : D9352,D4231]";
        String numNotPossible=readFile(12);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        desk();
 
        assertTrue("The lowest price calculated for 1st traditional desk is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd traditional desk is incorrect",result2.equals(expected2));
        assertEquals("Unable to correctly calculate the number of Traditional desk not possible",Integer.parseInt(numNotPossible),4);

    }
    @Test
    public void test_CheckOutputFileAdjustableDesk() throws IOException
    {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Adjustable";
        String table = "desk";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("7");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Adjustable desk is - 400.00 $ [Items Reused : D2746,D1030]";
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Adjustable desk is - 400.00 $ [Items Reused : D5437,D4475]";
        String result3 = readFile(11);
        String expected3 ="• The lowest cost to manufacture 3rd item of Adjustable desk is - 400.00 $ [Items Reused : D7373,D3682]";
        String numNotPossible=readFile(13);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        desk();
        assertTrue("The lowest price calculated for 1st Adjustable desk is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd Adjustable desk is incorrect",result2.equals(expected2));
        assertTrue("The lowest price calculated for 3rd Adjustable desk is incorrect",result3.equals(expected3));
        assertEquals("Unable to correctly calculate the number of Adjustable desk not possible",Integer.parseInt(numNotPossible),4);


    }

    @Test
    public void test_CheckOutputFileStandingDesk() throws IOException
    {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Standing";
        String table = "desk";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("10");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Standing desk is - 300.00 $ [Items Reused : D1927,D2341]";
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Standing desk is - 300.00 $ [Items Reused : D3820,D4438]";
        String numNotPossible=readFile(12);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        desk();
 
        assertTrue("The lowest price calculated for 1st Standing desk is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd Standing desk is incorrect",result2.equals(expected2));
        assertEquals("Unable to correctly calculate the number of Standing desk not possible",Integer.parseInt(numNotPossible),8);


    }

    @Test
    public void test_CheckOutputFileDeskLamp() throws IOException
    {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Desk";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("13");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Desk lamp is - 20.00 $ [Items Reused : L013,L208]";
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Desk lamp is - 20.00 $ [Items Reused : L112,L342]";
        String result3 = readFile(11);
        String expected3 ="• The lowest cost to manufacture 3rd item of Desk lamp is - 20.00 $ [Items Reused : L564]";
        String numNotPossible=readFile(13);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        lamp();
 
        assertTrue("The lowest price calculated for 1st desk lamp is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd desk lamp is incorrect",result2.equals(expected2));
        assertTrue("The lowest price calculated for 3rd desk lamp is incorrect",result3.equals(expected3));
        assertEquals("Unable to correctly calculate the number of desk lamp not possible",Integer.parseInt(numNotPossible),10);

    }

    @Test
    public void test_CheckOutputFileSwingArmLamp() throws IOException
    {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Swing Arm";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("21");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Swing Arm lamp is - 30.00 $ [Items Reused : L053,L096]";
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Swing Arm lamp is - 30.00 $ [Items Reused : L487,L879]";
        String numNotPossible=readFile(12);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        lamp();
        assertTrue("The lowest price calculated for 1st swing arm lamp is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for 2nd swing arm lamp is incorrect",result2.equals(expected2));
        assertEquals("Unable to correctly calculate the number of swing arm lamp not possible",Integer.parseInt(numNotPossible),19);


    }

    @Test
    public void test_CheckOutputFileStudyLamp() throws IOException
    {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)

            Test is carried out by comparing the output text file with the expected answer. Therefore the Test simultaneously
            checks if the output text file is correctly written to or not.

            prints out an error message in the event of failing any of the checks.

            Test is also designed to check if the number of items that were not manufactured (due to inadequate materials)
            is calculated correctly or not
         */
        String type = "Study";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("8");
        testObj.setReqValue(Integer.parseInt(testObj.getNumItems()));
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Study lamp is - 10.00 $ [Items Reused : L928]";
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Study lamp is - 10.00 $ [Items Reused : L982,L223]";
        String numNotPossible=readFile(12);
        numNotPossible=numNotPossible.substring(numNotPossible.indexOf(" ")+1);
        numNotPossible=numNotPossible.substring(0,numNotPossible.indexOf(" "));
        lamp();
 
        assertTrue("The lowest price calculated for 1st study lamp is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for 2d study lamp is incorrect",result2.equals(expected2));
        assertEquals("Unable to correctly calculate the number of study lamp not possible",Integer.parseInt(numNotPossible),6);


    }
    @Test
    public void testWriteFileWritesFile()
    {
        File file = new File("C:\\Users\\user\\Desktop\\ENSF409\\FinalProject\\orderform.txt");
        assertTrue(file.exists());
    }
    public String readFile(int line) throws FileNotFoundException {
        /*
            helper function to read the contents of the file specified below.
            read the line specified as an argument.
         */

        // input file path must be changed to match the filepath on the users system.(orderform.txt)
            File file = new File(filePath); // input file
            Scanner sc = new Scanner(file);                     // scanner to move through the input file
            int counter=1;
            String result=new String();
        while(sc.hasNextLine())
        {
            if(counter==line)
            {
                result=sc.nextLine();
            }
                counter++;
            sc.nextLine();
        }
        return result;

    }
    public void initializeConnection()
    {
        //this establishes a connection between the java files and the database

        try
        {
            dbConnect = DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
