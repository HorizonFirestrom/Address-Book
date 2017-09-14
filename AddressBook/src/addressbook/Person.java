/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

/**
 *
 * @author Md. Imran
 */
public class Person {

    private int addressID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

//no-argument constructor
    {
    }// end no-argument Person constructor

//constructor
    public Person(int id, String first, String last, String emailAddress, String phone) {
        setAddressID(id);
        setFirstName(first);
        setLastName(last);
        setEmail(emailAddress);
        setPhoneNumber(phone);

    }// end five -argument Person constructor

//sets the AddressID
    public void setAddressID(int id) {
        addressID = id;
    }//end method setAddressID

    public int getAddressID() {
        return addressID;
    }//end method getAddressID

//sets the firstName
    public void setFirstName(String first) {
        firstName = first;
    }//end method setFirstName

    public String getFirstName() {
        return firstName;
    }//end method getFirstName

//sets the lastname
    public void setLastName(String last) {
        lastName = last;
    }//end method setLastName

//returns the lastName
    public String getLastName() {
        return lastName;
    }//end of method getLastName

//set the email Address
    public void setEmail(String emailAddress) {
        email = emailAddress;
    }//end of method setEmail

//returns the email address
    public String getEmail() {
        return email;
    }//end method getEmail

//sets the phone number
    public void setPhoneNumber(String phone) {
        phoneNumber = phone;
    }//end method setPhoneNumber

//returns the phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }//end of method getPhoneNumber

}//end class Person
