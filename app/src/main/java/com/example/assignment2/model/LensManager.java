package com.example.assignment2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens>{
    private List<Lens> allLens =  new ArrayList<>();

    public void add(Lens a){
        //Method to add new lens to the manager
        allLens.add(a);
    }

    @Override
    public Iterator<Lens> iterator() {
        //override to make the class iterable
        return allLens.iterator();
    }

    public int size() {
        //returns the number of lenses stored in the manager
        return allLens.size();
    }

    public Lens get(int i) {
        //returns the lens at index i
        return allLens.get(i);
    }

    public boolean checkCameraIndex(int num){
        //checks if the index provided is valid
        return (num >= -1 & num < allLens.size());
    }

    public boolean checkExit(int num){
        //checks if the user wants to exit the application
        return num == -1;
    }

    public boolean checkAperture(double aperture, int num){
        //checks if the aperture is too low for a lens
        return aperture >= allLens.get(num).getMax_Aperture();
    }
}
