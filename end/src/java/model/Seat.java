/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Seat {
    private int id;
    private String row;
    private int number;

    public Seat() {
    }

    public Seat(String row, int number) {
        this.row = row;
        this.number = number;
    }

    public Seat(int id, String row, int number) {
        this.id = id;
        this.row = row;
        this.number = number;
    }
    
    

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Seat{" + "row=" + row + ", number=" + number + '}';
    }

    
 
    
}
