package com.example.assignment2.model;

public class Lens {
    private String make;
    private double max_Aperture;
    private int focal_Length;

    public Lens(String make, double max_Aperture, int focal_Length){
        //constructor for the class
        this.make = make;
        this.max_Aperture = max_Aperture;
        this.focal_Length = focal_Length;
    }

    public double getMax_Aperture() {
        //getter function
        return max_Aperture;
    }

    public int getFocal_Length() {
        //getter function
        return focal_Length;
    }

    @Override
    public String toString() {
        // override to print lens information
        return make + "  " +
                focal_Length + "mm  F" +
                max_Aperture;

    }
}
