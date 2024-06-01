package com.example.aplikasikalkulator;

public class HistoryModel {

    private int nilai1, nilai2, hasil;
    private String operator;

    public HistoryModel(int nilai1, int nilai2, int hasil, String operator) {
        this.nilai1 = nilai1;
        this.nilai2 = nilai2;
        this.hasil = hasil;
        this.operator = operator;
    }

    public int getNilai1() {
        return nilai1;
    }

    public void setNilai1(int nilai1) {
        this.nilai1 = nilai1;
    }

    public int getNilai2() {
        return nilai2;
    }

    public void setNilai2(int nilai2) {
        this.nilai2 = nilai2;
    }

    public int getHasil() {
        return hasil;
    }

    public void setHasil(int hasil) {
        this.hasil = hasil;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
