package com.example.uchinokoreco.data;

public class DiaryData {
    private  int petsListId;
    private String message;
    public DiaryData(int petsListId, String message){
        this.petsListId =petsListId;
        this.message = message;
    }
    public int getPetListId() {
        return petsListId;
    }

    public void setsPetsListId(int petsListId) {
        this.petsListId = petsListId;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String massage){
        this.message = massage;
    }
}