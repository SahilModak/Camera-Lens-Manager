package com.example.assignment2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens>{
    private List<Lens> allLens =  new ArrayList<>();

    private LensManager(){
        // stops anyone else from initializing a variable of the class.
    }

    public static LensManager Instance;

    public static LensManager getInstance(){
        if(Instance == null){
            Instance = new LensManager();
            Instance.add(new Lens("Canon", 1.8, 50));
            Instance.add(new Lens("Tamron", 2.8, 90));
            Instance.add(new Lens("Sigma", 2.8, 200));
            Instance.add(new Lens("Nikon", 4, 200));
        }
        return Instance;
    }

    public void add(Lens a){
        //Method to add new lens to the manager
        allLens.add(a);
    }

    public void delete(int position){
         allLens.remove(position);
    }

    public void edit(int position, String make, double aperture, int focallength){
        allLens.get(position).setMake(make);
        allLens.get(position).setFocal_Length(focallength);
        allLens.get(position).setMax_Aperture(aperture);
    }

    public List<Lens> getLensList(){
        return allLens;
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
