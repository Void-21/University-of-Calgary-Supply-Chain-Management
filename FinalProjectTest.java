package edu.ucalgary.ensf409;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;


public class FinalProjectTest
{

    public final static String USERNAME = "zee";
    public final static String PASSWORD = "Zeemaan1234@";
    public final static String DBURL = "jdbc:mysql://localhost/inventory";

    @Test
    public void testConstructor() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
        assertTrue("DBURL is wrong",testobj.getDburl().equals(DBURL));
        assertTrue("USERNAME is wrong",testobj.getUsername().equals(USERNAME));
        assertTrue("PASSWORD is wrong",testobj.getPassword().equals(PASSWORD));
    }
    @Test
    public void testSetItemType() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
        testobj.setItemType("Mesh");
        assertTrue("getter for DBURL is wrong",testobj.getDburl().equals(DBURL));
    }
    @Test
    public void testSetItemTable() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
        //testobj.setItemTable("lamp");
        //assertTrue("Setter for Item table is wrong",testobj..equals(DBURL));
    }
    @Test
    public void testinitializeConnection() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
        testobj.initializeConnection();
        //test if this works
    }
    @Test
    public void testFilingSelect() throws FileNotFoundException {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
        String [][] testArray = new String [5][7];

        // testArray[0][0] = "F002";
        // testArray[0][1] = "Medium";
        // testArray[0][2] = "N";
        // testArray[0][3] = "N";
        // testArray[0][4] = "Y";
        // testArray[0][5] = "100";
        // testArray[0][6] = "004";

        // testArray[1][0] = "F007";
        // testArray[1][1] = "Medium";
        // testArray[1][2] = "N";
        // testArray[1][3] = "Y";
        // testArray[1][4] = "Y";
        // testArray[1][5] = "150";
        // testArray[1][6] = "002";

        // testArray[2][0] = "F008";
        // testArray[2][1] = "Medium";
        // testArray[2][2] = "Y";
        // testArray[2][3] = "N";
        // testArray[2][4] = "N";
        // testArray[2][5] = "50";
        // testArray[2][6] = "005";

        // testArray[3][0] = "F009";
        // testArray[3][1] = "Medium";
        // testArray[3][2] = "Y";
        // testArray[3][3] = "Y";
        // testArray[3][4] = "N";
        // testArray[3][5] = "150";
        // testArray[3][6] = "004";

        // testArray[2][0] = "F014";
        // testArray[2][1] = "Medium";
        // testArray[2][2] = "Y";
        // testArray[2][3] = "Y";
        // testArray[2][4] = "Y";
        // testArray[2][5] = "200";
        // testArray[2][6] = "002";
        testArray = arrayFromFile("filing",5,7);
        Boolean flag = false;

        String [][] fromFunc = testobj.filingSelect("Medium", 5);
        if (! checkIfSame(fromFunc, testArray))
        {
            System.out.println("Output from filingSelect is wrong!");
        }
    }

    public boolean checkIfSame(String [][] arr1, String [][] arr2)
    {
        int rows = arr1.length;
        int col = arr1[0].length;
        Boolean flag = false;

        for(int i=0; i < rows ; i++)
        {
            for( int j=0 ; j < col ; j++)
            {
                if( arr1[i][j].equals(arr2[i][j]))
                {
                    flag = true;
                }
                else
                {
                    return false;
                }

            }
        }
        return flag;
    }

    public String [][] arrayFromFile(String filename, int rows, int columns) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new BufferedReader(new FileReader( "C:\\Users\\hp\\IdeaProjects\\Ensf 409\\src\\edu\\ucalgary\\ensf409\\input.txt")));
        // int rows = 5;
        // int columns = 7;
        String [][] myArray = new String[rows][columns];
        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(",");
                for (int j=0; j<line.length; j++) {
                    myArray[i][j] = (line[j]);
                }
            }
        }
        return myArray;
    }

    @Test
    public void testCheckFiling() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testLampSelect() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testCheckLamp() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testDeskSelect() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testCheckDesk() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testChairSelect() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testCheckChair() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testSelectFurnitureType(){
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testGetManufacturers(){
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testDeleteFromTable(){
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testCalculateLowestPrice(){
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);

    }
    @Test
    public void testWriteOrderForm(){
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
    }

}
