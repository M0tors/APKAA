package com.ostaszewski.damian.aplikacja3;

/**
 * Created by student on 24.04.2017.
 */

public class Ctelefon {


    int _id;

    String _imie;
    String _nazwisko;
    String _numer;

    public Ctelefon() {
    }

    public Ctelefon(int id, String imie, String nazwisko, String numer) {
        this._id = id;
        this._imie = imie;
        this._nazwisko = nazwisko;
        this._numer = numer;

    }

    public Ctelefon(String imie, String nazwisko, String numer){
        this._imie = imie;
        this._nazwisko = nazwisko;
        this._numer = numer;
    }

    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    public String getImie(){
        return this._imie;
    }

    public void setName(String imie){
        this._imie = imie;
    }

    public String getNazwisko(){
        return this._nazwisko;
    }

    public void setNazwisko(String nazwisko){
        this._nazwisko = nazwisko;
    }

    public String getNumer(){
        return this._numer;
    }

    public void setNumer(String numer){
        this._numer = numer;
    }
}
