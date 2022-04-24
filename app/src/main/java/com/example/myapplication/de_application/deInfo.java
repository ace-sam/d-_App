package com.example.myapplication.de_application;


public class deInfo {

    int nbrFaces;
    String[] listDesFaces=null;


    public deInfo(int nbrFaces){
        this.nbrFaces=nbrFaces;
        //this.listDesFaces=new int[nbrFaces];
    }

    public void setListSize(){
        this.listDesFaces=new String[nbrFaces];

    }

    public void AddAt(String id,int pos){
        listDesFaces[pos]=id;
    }
    public String[] getarray(){
        return listDesFaces;
    }

}
