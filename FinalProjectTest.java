package edu.ucalgary.ensf409;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import java.io.*;
import java.sql.*;
import java.util.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FinalProjectTest {

    public final static String USERNAME = "NUMAN";
    public final static String PASSWORD = "TIGER";
    public final static String DBURL = "jdbc:mysql://localhost/inventory";
    private Connection dbConnect;
    private ResultSet results;
    public void chair() throws SQLException {
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
    public void desk() throws SQLException {
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
    public void lamp() throws SQLException {
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
    public void filing() throws SQLException {
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
    public void atest_Constructor() throws IOException {
        /* Checks if the constructor initializes all the values correctly */

        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        assertTrue("DBURL is wrong", testObj.getDburl().equals(DBURL));
        assertTrue("USERNAME is wrong", testObj.getUsername().equals(USERNAME));
        assertTrue("PASSWORD is wrong", testObj.getPassword().equals(PASSWORD));
    }
    @Test
    public void btest_SelectFurnitureTypeTaskChair() throws IOException  , SQLException {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Task";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("3");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C0914", "Task", "N", "N", "Y", "Y", "50", "002"},
                {"C1148", "Task", "Y", "N", "Y", "Y", "125", "003"},
                {"C3405", "Task", "Y", "Y", "N", "N", "100", "003"}};
        chair();
        assertEquals("selectFurnitureType does not return the correct rows for Task Chair",result,expected);
    }
    @Test
    public void ctest_SelectFurnitureTypeMeshChair() throws IOException  , SQLException {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Mesh";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("4");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C0942", "Mesh", "Y", "N", "Y", "Y", "175", "005"},
                {"C6748", "Mesh", "Y", "N", "N", "N", "75", "003"},
                {"C8138", "Mesh", "N", "N", "Y", "N", "75", "005"},
                {"C9890", "Mesh", "N", "Y", "N", "Y", "50", "003"}};
        chair();
        assertEquals("selectFurnitureType does not return the correct rows for Mesh Chair",result,expected);
    }
    @Test
    public void dtest_SelectFurnitureTypeKneelingChair() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Kneeling";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("2");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected =
                {{"C1320", "Kneeling", "Y", "N", "N", "N", "50", "002"},
                        {"C3819", "Kneeling", "N", "N", "Y", "N", "75", "005"}};
         chair();
        assertEquals("selectFurnitureType does not return the correct rows for Kneeling Chair",result,expected);
    }

    @Test
    public void dtest_SelectFurnitureTypeErgonomicChair() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Ergonomic";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("3");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C4839", "Ergonomic", "N", "N", "N", "Y", "50", "002"},
                {"C5409", "Ergonomic", "Y", "Y", "Y", "N", "200", "003"},
                {"C5789", "Ergonomic", "Y", "N", "N", "Y", "125", "003"}};
         
        assertEquals("selectFurnitureType does not return the correct rows for Ergonomic Chair",result,expected);
    }
    @Test
    public void ftest_SelectFurnitureTypeExecutiveChair() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the chairSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Executive";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("3");
        String[][] result = testObj.selectFurnitureType(type, table);
        String[][] expected ={{"C2483", "Executive", "Y", "Y", "N", "N", "175", "002"},
                {"C5784", "Executive", "Y", "N", "N", "Y", "150", "004"},
                {"C7268", "Executive", "N", "N", "Y", "N", "75", "004"}};
         chair();
        assertEquals("selectFurnitureType does not return the correct rows for Executive Chair",result,expected);
    }
    @Test
    public void etest_SelectFurnitureTypeSmallFiling() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the filingSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Small";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("5");
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
    public void ftest_SelectFurnitureTypeMediumFiling() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the filingSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Medium";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("3");
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
    public void gtest_SelectFurnitureTypeLargeFiling() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the filingSelect
         function by using the type and number of occurrences of the specific type of chair, chairSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Large";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("3");
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
    public void htest_SelectFurnitureTypeDeskLamp() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the lampSelect
         function by using the type and number of occurrences of the specific type of lamp, lampSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
         */

        String type = "Desk";
        String table = "lamp";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("7");
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
    public void itest_SelectFurnitureTypeSwingArmLamp() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the lampSelect
         function by using the type and number of occurrences of the specific type of lamp, lampSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
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
    public void jtest_SelectFurnitureTypeStudyLamp() throws IOException  , SQLException {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the lampSelect
         function by using the type and number of occurrences of the specific type of lamp, lampSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
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
    public void ktest_SelectFurnitureTypeTraditionalDesk() throws IOException  , SQLException   {
       /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the deskSelect
         function by using the type and number of occurrences of the specific type of desk, deskSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
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
    public void ltest_SelectFurnitureTypeStandingDesk() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the deskSelect
         function by using the type and number of occurrences of the specific type of desk, deskSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
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
    public void mtest_SelectFurnitureTypeAdjustableDesk() throws IOException  , SQLException   {
        /*
         This test , tests 2 functions, it first calls the selectFurnitureType function, which calls the deskSelect
         function by using the type and number of occurrences of the specific type of desk, deskSelect returns a
         2D string which is then returned by selectFurnitureType. this final returned 2D string is then matched with our
         expected output to complete the testing.
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
    public void ntest_CalculateLowestPriceExecutiveChair() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */

        String type = "Executive";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");

        String[][] input= {{"Y", "Y", "N", "N", "175","C2483"},
                {"Y", "N", "N", "Y", "150","C5784"},
                {"N", "N", "Y", "N", "75", "C7268"}};

        int result=testObj.calculateLowestPrice(input);
        int expected = 400;
         chair();
        assertTrue("The lowest price calculated for Executive chair is incorrect",result==expected);

    }
    @Test
    public void otest_CalculateLowestPriceTaskChair() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */

        String type = "Task";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");

        String[][] input= {{"N", "N", "Y", "Y", "50","C0914"},
                {"Y", "N", "Y", "Y", "125","C1148"},
                {"Y", "Y", "N", "N", "100","C3405"}};

        int result=testObj.calculateLowestPrice(input);
        int expected = 150;
         chair();
        assertTrue("The lowest price calculated for Task chair is incorrect",result==expected);

    }
    @Test
    public void ptest_CalculateLowestPriceMeshChair() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */

        String type = "Mesh";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");

        String[][] input= {{"Y", "N", "Y", "Y", "175","C0942"},
                {"Y", "N", "N", "N", "75","C6748" },
                {"N", "N", "Y", "N", "75","C8138"},
                {"N", "Y", "N", "Y", "50","C9890"}};

        int result=testObj.calculateLowestPrice(input);
        int expected = 200;
         chair();
        assertTrue("The lowest price calculated for Mesh chair is incorrect",result==expected);

    }
    @Test
    public void qtest18_CalculateLowestPriceErgonomicChair() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */

        String type = "Ergonomic";
        String table = "chair";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");

        String[][] input= {{"N", "N", "N", "Y", "50","C4839"},
                {"Y", "Y", "Y", "N", "200","C5409"},
                {"Y", "N", "N", "Y", "125","C5789"}};

        int result=testObj.calculateLowestPrice(input);
        int expected = 250;
         chair();
        assertTrue("The lowest price calculated for Ergonomic chair is incorrect",result==expected);

    }
    @Test
    public void qtest18_CalculateLowestPriceSmallFiling() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */
        String type = "Small";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("2");
        testObj.selectFurnitureType(type,table);
        testObj.writeOrderForm();
        testObj.closes();

        String result1= readFile(9);
        String expected1 ="• The lowest cost to manufacture 1st item of Small filing is - 100.00 $ [Items Reused : F001,F013]" ;
        String result2 = readFile(10);
        String expected2 ="• The lowest cost to manufacture 2nd item of Small filing is - 125.00 $ [Items Reused : F005,F006]";
        filing();
        System.out.println(result1+" "+result2);
        assertTrue("The lowest price calculated for 1st Small filing is incorrect",result1.equals(expected1));
        assertTrue("The lowest price calculated for Small filing is incorrect",result2.equals(expected2));
    }
    public void qtest18_CalculateLowestPriceMediumFiling() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */

        String type = "Medium";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");

        String[][] input= {{"N", "N", "N", "Y", "50","C4839"},
                {"Y", "Y", "Y", "N", "200","C5409"},
                {"Y", "N", "N", "Y", "125","C5789"}};

        int result1=testObj.calculateLowestPrice(input);
        int expected1 = 200;
        int result2=testObj.calculateLowestPrice(input);
        int expected2 = 200;
        int result3=testObj.calculateLowestPrice(input);
        int expected3 = 200;
        filing();
        assertTrue("The lowest price calculated for 1st Medium filing is incorrect",result1==expected1);
        assertTrue("The lowest price calculated for 2nd Medium filing is incorrect",result2==expected2);
        assertTrue("The lowest price calculated for 3rd Medium filing is incorrect",result3==expected3);
    }
    public void qtest18_CalculateLowestPriceLargeFiling() throws IOException  , SQLException   {
        /*
            Tests all the possible lowest prices for the provided item in the appropriate order (Cheapest to most Expensive option)
         */

        String type = "Large";
        String table = "filing";
        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        testObj.initializeConnection();
        testObj.setItemType(type);
        testObj.setItemTable(table);
        testObj.setNumItems("1");

        String[][] input= {{"N", "N", "N", "Y", "50","C4839"},
                {"Y", "Y", "Y", "N", "200","C5409"},
                {"Y", "N", "N", "Y", "125","C5789"}};

        int result1=testObj.calculateLowestPrice(input);
        int expected1 = 100;
        int result2=testObj.calculateLowestPrice(input);
        int expected2 = 125;
        filing();
        assertTrue("The lowest price calculated for 1st Small filing is incorrect",result1==expected1);
        assertTrue("The lowest price calculated for Small filing is incorrect",result2==expected2);
    }
    public String readFile(int line) throws FileNotFoundException {
            File file = new File("C:\\Users\\user\\Desktop\\ENSF409\\FinalProject\\orderform.txt");                  // input file
            Scanner sc = new Scanner(file);// scanner to move through the input file
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
