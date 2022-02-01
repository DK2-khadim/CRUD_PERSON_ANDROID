package sn.ept.dic2.dev_mobile.tp.personapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

    @SerializedName("prenom")
    @Expose
    String firstName;

    @SerializedName("nom")
    @Expose
    String lastName;

    @Expose
    String email;

    @Expose
    String clef;

    @Expose
    String dateNaissance;

    @Expose

    String dateModification;
    @Expose
    String dateEnregistrement;


    public Person(String firstName, String lastName, String email, String clef) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email = email;
        this.clef = clef;
    }


    public Person(String firstName, String lastName, String email, String clef, String dateEnregistrement, String dateModification, String dateNaissance){
        this.firstName  = firstName;
        this.lastName = lastName;
        this.email = email;
        this.clef = clef;
        this.dateEnregistrement = dateEnregistrement;
        this.dateModification = dateModification;
        this.dateNaissance = dateNaissance;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClef() {
        return clef;
    }

    public void setClef(String clef) {
        this.clef = clef;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDateModification() {
        return dateModification;
    }

    public String getDateInSimpleFormat() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = (Date) formatter.parse(getDateEnregistrement());
        return formatter.format(date);
    }

    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public String getDateEnregistrement() {
        return dateEnregistrement;
    }

    public void setDateEnregistrement(String dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }
}
