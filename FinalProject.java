package edu.ucalgary.ensf409;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinalProject {
    public final String DBURL; //store the database url information
    public final String USERNAME; //store the user's account username
    public final String PASSWORD; //store the user's account password
    private Connection dbconnect;
    private ResultSet results;
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
    public String selecttable(String tablename) {
        StringBuffer all = new StringBuffer();
        try {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + tablename);
            all.append(tablename);
            all.append("\n");
            if (tablename == "lamp") {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Base") + " // " + results.getString("Bulb") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename == "manufacturer") {
                while (results.next()) {
                    all.append("(" + results.getString("ManuID") + " // " + results.getString("Name") + " // " + results.getString("Phone") + " // " + results.getString("Province") + ")");
                    all.append("\n");
                }
            }
            if (tablename == "filing") {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Rails") + " // " + results.getString("Drawers") + " // " + results.getString("Cabinet") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename == "desk") {
                while (results.next()) {
                    all.append("(" + results.getString("ID") + " // " + results.getString("TYPE") + " // " + results.getString("Legs") + " // " + results.getString("Top") + " // " + results.getString("Drawer") + " // " + results.getString("Price") + " // " + results.getString("ManuID") + ")");
                    all.append("\n");
                }
            }
            if (tablename == "chair") {
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
    public void selectFurnitureType(String type, String tableName){
        List<List> chairList2D = new ArrayList <List>();
        List<String> chairList1D = new ArrayList<String>();
        List<String> deskList1D = new ArrayList<String>();
        List<String> filingList1D = new ArrayList<String>();
        List<String> lampList1D = new ArrayList<String>();
        try
        {
            Statement myStmt = dbconnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM "+ tableName +" WHERE Type ='"+type+"'");

            if(tableName.equals("chair"))
            {
                chairList1D.add(results.getString(type));
                while( results.next() )
                {
                    chairList1D.add(results.getString("ID"));
                    chairList1D.add( results.getString("Legs") );
                    chairList1D.add( results.getString("Arms") );
                    chairList1D.add( results.getString("Seat") );
                    chairList1D.add( results.getString("Cushion") );
                    chairList1D.add( String.valueOf(results.getInt("Price")) );
                    chairList1D.add( results.getString("ManuID") );
                    //chairList1D.add("\n");
                }
                System.out.println(chairList1D);
            }
            if(tableName.equals("desk"))
            {
                deskList1D.add(results.getString(type));
                while(results.next())
                {
                    deskList1D.add(results.getString("ID"));
                    deskList1D.add( results.getString("Legs") );
                    deskList1D.add( results.getString("Top") );
                    deskList1D.add( results.getString("Drawer") );
                    deskList1D.add( String.valueOf(results.getInt("Price")) );
                    deskList1D.add( results.getString("ManuID") );
                }
                System.out.println(deskList1D);
            }
            if(tableName.equals("filing"))
            {
                filingList1D.add(results.getString(type));
                while( results.next() )
                {
                    filingList1D.add(results.getString("ID"));
                    filingList1D.add( results.getString("Rails") );
                    filingList1D.add( results.getString("Drawers") );
                    filingList1D.add( results.getString("Cabinet") );
                    filingList1D.add( String.valueOf(results.getInt("Price")) );
                    filingList1D.add( results.getString("ManuID") );
                }
                System.out.println(filingList1D);
            }
            if(tableName.equals("lamp"))
            {
                lampList1D.add(results.getString(type));
                while( results.next() )
                {
                    lampList1D.add(results.getString("ID"));
                    lampList1D.add( results.getString("Base") );
                    lampList1D.add( results.getString("Bulb") );
                    lampList1D.add( String.valueOf(results.getInt("Price")) );
                    lampList1D.add( results.getString("ManuID") );
                }
                System.out.println(lampList1D);
            }
            myStmt.close();
        }
        catch (SQLException ex)
        {
            System.out.println("Unable to connect to database");
        }
        }
    public static void main(String[] args)
    {
        FinalProject myJDBC = new FinalProject("jdbc:mysql://localhost/inventory","NUMAN","TIGER");
        myJDBC.initializeConnection();
        myJDBC.selectFurnitureType("Mesh","chair");
        myJDBC.selectFurnitureType("Small","filing");
        /*System.out.println(myJDBC.selecttable("chair"));
        System.out.println(myJDBC.selecttable("desk"));
        System.out.println(myJDBC.selecttable("filing"));
        System.out.println(myJDBC.selecttable("lamp"));
        System.out.println(myJDBC.selecttable("manufacturer"));*/
    }
}
