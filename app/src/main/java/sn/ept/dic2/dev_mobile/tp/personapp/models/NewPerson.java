package sn.ept.dic2.dev_mobile.tp.personapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class NewPerson {

    @SerializedName("prenom")
    String firstName;

    @SerializedName("nom")
    String  lastName;

    String email;

    String dateNaissance;

    String clef;

    public NewPerson(String firstName, String lastName, String email, String dateNaissance, String clef){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.clef = clef;
    }
}
