![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
# University-of-Calgary-Supply-Chain-Management (HYL 2021)
ENSF409 FINAL TEAM PROJECT - WINTER 2021

## **Description**

This is an application to calculate the cheapest combination of available inventory items that can be used to fill a specific order. It utilizes a MySQL database (inventory) provided by HYL, to extract data pertaining to the availability of reusbale parts to create user requested objects. The application updates the database when certain products are purchased to recylce its parts.

## **Current features**

(1) Accepts Input from the User through a user friendly GUI. The code enables the user to return the item name in case the item name does not exist in the database.

![image](https://user-images.githubusercontent.com/59512700/112756840-2306f880-8fa4-11eb-89e7-351cc493d909.png)

(2) Calculates all the possible combinations for choosing the lowest possible price option for manufacturing the requested object.

(3) Regardless of wether the object requested by the user can be created or not an output text file is created which display the status of the request.

(4) The project includes a Test File which validates the functionality of each method in the FinalProject.java file.
## UML Diagram
![image](https://user-images.githubusercontent.com/53919885/114313651-5a2eeb00-9b08-11eb-92cf-c17c692f7d80.png)

## **Java imports used** 

(1) import java.sql.*

(2) import java.io.*

(3) import java.util.*

(4) import org.junit.*

(5) import static org.junit.Assert.*

## **How to run**

(1) Clone the repository

(2) Run the FinalProject.java file through command line or IDE of your choice.

(3) Run the FinalProjectTest.java for Unit Testing purposes.

## **Done by :** 

1. Zeeshan Chougle - 30094417
2. Muhammed Umar - 30062451
3. Mohammed Numan - 30086940
4. Mohammad Mahtab Khan - 3010408
