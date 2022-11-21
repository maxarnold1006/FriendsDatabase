package com.missouristate.arnold.friendsdatabase;

public class Friend {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Friend( int newId, String newFirstName, String newLastName, String newEmail ) {
        setId( newId );
        setFirstName( newFirstName );
        setLastName( newLastName );
        setEmail( newEmail );
    }

    public void setId( int newId ) {
        id = newId;
    }

    public void setFirstName( String newFirstName ) {
        firstName = newFirstName;
    }

    public void setLastName( String newLastName ) {
        lastName = newLastName;
    }

    public void setEmail( String newEmail ) {
        email = newEmail;
    }


    public int getId( ) {
        return id;
    }

    public String getFirstName( ) {
        return firstName;
    }

    public String getLastName( ) {
        return lastName;
    }

    public String getEmail( ) {
        return email;
    }

    public String toString( ) {
        return id + "; " + firstName + "; " + lastName + "; " + email;
    }
}