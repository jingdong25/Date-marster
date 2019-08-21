package com.example.date.bean;

public class Bean  {
    private String day;
    private int column;
    private int row;
    private int stauts;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getStauts() {
        return stauts;
    }

    public void setStauts(int stauts) {
        this.stauts = stauts;
    }

    public Bean(String day, int column, int row, int stauts) {
        this.day = day;
        this.column = column;
        this.row = row;
        this.stauts = stauts;
    }
}
