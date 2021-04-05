package edu.ucalgary.ensf409;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.*;


public class FinalProjectTest {

    public final static String USERNAME = "NUMAN";
    public final static String PASSWORD = "TIGER";
    public final static String DBURL = "jdbc:mysql://localhost/inventory";

    @Test
    public void testConstructor() throws IOException {
        /* Checks if the constructor initializes all the values correctly */

        DatabaseCalculation testObj = new DatabaseCalculation(DBURL, USERNAME, PASSWORD);
        assertTrue("DBURL is wrong", testObj.getDburl().equals(DBURL));
        assertTrue("USERNAME is wrong", testObj.getUsername().equals(USERNAME));
        assertTrue("PASSWORD is wrong", testObj.getPassword().equals(PASSWORD));
    }

    @Test
    public void testSelectFurnitureTypeTaskChair() throws IOException
    {
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

        assertEquals("selectFurnitureType does not return the correct rows for Task Chair",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeMeshChair() throws IOException
    {
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
        String[][] expected ={{"C0942", "Mesh", "Y", "N", "Y", "Y", "100", "005"},
                {"C6748", "Mesh", "Y", "N", "N", "N", "75", "003"},
                {"C8138", "Mesh", "N", "N", "Y", "N", "75", "005"},
                {"C9890", "Mesh", "N", "Y", "N", "Y", "50", "003"}};

        assertEquals("selectFurnitureType does not return the correct rows for Mesh Chair",result,expected);
    }

    @Test
    public void testSelectFurnitureTypeKneelingChair() throws IOException
    {
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

        assertEquals("selectFurnitureType does not return the correct rows for Kneeling Chair",result,expected);
    }

    @Test
    public void testSelectFurnitureTypeErgonomicChair() throws IOException
    {
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
    public void testSelectFurnitureTypeExecutiveChair() throws IOException
    {
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

        assertEquals("selectFurnitureType does not return the correct rows for Executive Chair",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeSmallFiling() throws IOException
    {
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

        assertEquals("selectFurnitureType does not return the correct rows for Small Filing",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeMediumFiling() throws IOException
    {
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
                { "F009","Medium","Y","Y","N","150","004"},
                { "F014","Medium","Y","Y","Y","200","002"}};

        assertEquals("selectFurnitureType does not return the correct rows for Medium Filing",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeLargeFiling() throws IOException
    {
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

        assertEquals("selectFurnitureType does not return the correct rows for Large Filing",result,expected);
    }
    @Test
    public void testSelectFurnitureTypeDeskLamp() throws IOException {
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

        assertEquals("selectFurnitureType does not return the correct rows for Desk Lamp", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeSwingArmLamp() throws IOException {
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

        assertEquals("selectFurnitureType does not return the correct rows for Swing Arm Lamp", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeStudyLamp() throws IOException {
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

        assertEquals("selectFurnitureType does not return the correct rows for Study Lamp", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeTraditionalDesk() throws IOException {
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
        assertEquals("selectFurnitureType does not return the correct rows for Traditional Desk", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeStandingDesk() throws IOException {
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
      assertEquals("selectFurnitureType does not return the correct rows for Standing Desk", result, expected);
    }
    @Test
    public void testSelectFurnitureTypeAdjustableDesk() throws IOException {
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
                {"D5437", "Adjustable", "Y", "N", "N", "50", "001"},
                {"D7373" ,"Adjustable" ,"Y" ,"Y", "N" ,"350", "005"} };
        assertEquals("selectFurnitureType does not return the correct rows for Adjustable Desk", result, expected);
    }
}
