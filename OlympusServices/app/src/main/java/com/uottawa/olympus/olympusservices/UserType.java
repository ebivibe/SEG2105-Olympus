package com.uottawa.olympus.olympusservices;


import java.io.Serializable;

/**
 * UserType is the abstract object that is the parent class
 * of all users in this program with all common methods and
 * fields set in this one class.
 */

public abstract class UserType {

    //field for the username attached to the userType.
    String username;
    //field for the password hash attached to the userType.
    String hash;
    //field for the firstname attached to the userType.
    String firstname;
    //field for the lastname attached to the userType.
    String lastname;
    //field for the salt attached to the userType.
    String salt;


    /**
     * Constructor filling out all the field values with given parameters
     * entered by a new user for the app.
     *
     * @param username String object containing the username.
     * @param password String object containing the password.
     * @param firstname String object containing the firstname.
     * @param lastname String object containing the lastname.
     */
    UserType(String username, String password, String firstname, String lastname){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;

        this.salt = PasswordEncryption.generateSalt();
        this.hash = PasswordEncryption.encrypt(password, salt);
    }

    /**
     * Constructor filling out all the field values with given parameters
     * entered by a new user for the app.
     *
     * @param username String object containing the username.
     * @param hash String object containing the password hash.
     * @param firstname String object containing the firstname.
     * @param lastname String object containing the lastname.
     * @param salt String object containing the salt.
     */
    UserType(String username, String hash, String salt, String firstname, String lastname){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;

        this.salt = salt;
        this.hash = hash;
    }

    /**
     * Abstract method to get the role of each userType child in the app.
     *
     * @return String object specifying the role of userType.
     */
    public abstract String getRole();

    /**
     * Gets the username field of userType.
     *
     * @return String of the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the hash field of userType.
     *
     * @return String of the hash.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Gets the firstname field of userType.
     *
     * @return String of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Gets the lastname field of userType.
     *
     * @return String of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets the salt field of userType.
     *
     * @return String of salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the username field with given parameters.
     *
     * @param username String for new username.
     */
    public void setUsername(String username) {
        //remember to call updateUser(String username, String password, String firstname, String lastname)
        //in activity whenever a setter is called. DBHelper requires a Context (Activity) to be initialized
        //so cannot be initialized in this class
        this.username = username;

    }

    /**
     * Sets the password field with given parameters.
     *
     * @param password String of new password.
     */
    public void setPassword(String password) {
        //remember to call updateUser(String username, String password, String firstname, String lastname)
        //in activity whenever a setter is called. DBHelper requires a Context (Activity) to be initialized
        //so cannot be initialized in this class
        this.salt = PasswordEncryption.generateSalt();
        this.hash = PasswordEncryption.encrypt(password, salt);
    }

    /**
     * Sets the firstname field with given parameters.
     *
     * @param firstname String of new firstname.
     */
    public void setFirstname(String firstname) {
        //remember to call updateUser(String username, String password, String firstname, String lastname)
        //in activity whenever a setter is called. DBHelper requires a Context (Activity) to be initialized
        //so cannot be initialized in this class
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        //remember to call updateUser(String username, String password, String firstname, String lastname)
        //in activity whenever a setter is called. DBHelper requires a Context (Activity) to be initialized
        //so cannot be initialized in this class
        this.lastname = lastname;
    }

    /**
     * Compares this userType and another userType to see if there fields are equal.
     *
     * @param other Usertype object that is compared to this userType.
     */
    public boolean equals(UserType other){
        if(this.username.equals(other.username)&&this.hash.equals(other.hash)&&
                this.firstname.equals(other.firstname)&&this.lastname.equals(other.lastname)
                &&this.salt.equals(other.salt)){
            return true;
        }
        return false;
    }
}
