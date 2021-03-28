package edu.ucalgary.ensf409;
import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.util.*;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
public class FinalProjectTest {
    public final static String USERNAME = "NUMAN";
    public final static String PASSWORD = "TIGER";
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
        testobj.initialiazeConnection();
        //test if this works
    }
    @Test
    public void testFilingSelect() {
        FinalProject testobj = new FinalProject(DBURL,USERNAME,PASSWORD);
        String [][] testArray = new String [5][7];

        testArray[0][0] = 'F002';
        testArray[0][1] = 'F007';
        testArray[0][2] = 'F008';
        testArray[0][3] = 'F009';
        testArray[0][4] = 'F014';

        testArray[1][0] = 'Medium';
        testArray[1][1] = 'Medium';
        testArray[1][2] = 'Medium';
        testArray[1][3] = 'Medium';
        testArray[1][4] = 'Medium';

        testArray[1][0] = '';
        testArray[1][1] = '';
        testArray[1][2] = 'Medium';
        testArray[1][3] = 'Medium';
        testArray[1][4] = 'Medium';



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
