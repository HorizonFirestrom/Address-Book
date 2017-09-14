/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Md. Imran
 */
public class PersonQueries {

    private static final String URL = "jdbc:mysql://localhost:3306/addressbook";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection = null; //manages the connection
    private PreparedStatement selectAllPeople = null;
    private PreparedStatement selectPeopleByLastName = null;
    private PreparedStatement insertNewPerson = null;

    //constructor
    public PersonQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //create query that selects all entries in the AddressBook
            selectAllPeople = connection.prepareStatement("SELECT * FROM Addresses");

            //create query that selects entries with a specific last name
            selectPeopleByLastName = connection.prepareStatement("SELECT * FROM Addresses WHERE LastName = ?");

            //create insert that adds a new entry into the database
            insertNewPerson = connection.prepareStatement("INSERT INTO  Addresses" + "(FirstName, LastName, Email, PhoneNumber)" + "VALUES (?, ?, ?, ?)");
        }//end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }//end catch 
    }//end personQueries constructor

    //select all addresses in the database
    public List< Person> getAllPeople() {
        List< Person> results = null;
        ResultSet resultSet = null;

        try {
            //executeQuery returns ResultSet containing matching entries
            resultSet = selectAllPeople.executeQuery();
            results = new ArrayList< Person>();

            while (resultSet.next()) {
                results.add(new Person(
                        resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")));
            }//end while
        }//end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }//end catch
        finally {
            try {
                resultSet.close();
            }//end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }//end catch
        }//end finally
        return results;
    }//end method getAllPeople

    //select person by last name
    public List< Person> getPeopleByLastName(String name) {
        List< Person> results = null;
        ResultSet resultSet = null;

        try {
            selectPeopleByLastName.setString(1, name);//specify last name

            //executeQuery returns ResultSet containing matching entries
            resultSet = selectPeopleByLastName.executeQuery();

            results = new ArrayList< Person>();

            while (resultSet.next()) {
                results.add(new Person(resultSet.getInt("addressID"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("PhoneNumber")
                ));
            }//end while
        }//end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }//end catch
        finally {
            try {
                resultSet.close();
            }//end try
            catch (SQLException sqlException) {
                sqlException.printStackTrace();
                close();
            }//end catch
        }//end finally

        return results;

    }//end method getPeopleByName
    
    //add an entry
    public int addPerson(String fname, String lname, String email, String num){
    int result = 0;
    
    //set parameters, then execute insertNewPerson
    try{
    insertNewPerson.setString(1,fname);
    insertNewPerson.setString(2,lname);
    insertNewPerson.setString(3,email);
    insertNewPerson.setString(4,num);
    
    //insert the new entry: returns # of rows updated
    result = insertNewPerson.executeUpdate();
    }//end try
    catch (SQLException sqlException){
    sqlException.printStackTrace();
    close();
    }//end catch
    
    return result;
    }//end method addPerson
    
    //close the database connection
    public void close(){
    try{
    connection.close();
    }
    catch (SQLException sqlException){
    sqlException.printStackTrace();
    }//end catch
    }//end method close

}//end class PersonQueries
