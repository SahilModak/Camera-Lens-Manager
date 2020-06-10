package com.example.assignment2.model;

public class depthCalculator {
    private double COC;
    private Lens lens;
    private double selectedAperture;
    private double subjectDistance;



    public depthCalculator(double COC, Lens lens, double selectedAperture, double subjectDistance) {
        this.selectedAperture = selectedAperture;
        this.subjectDistance = subjectDistance*1000;
        this.lens = lens;
        this.COC = COC;
    }

    public double hyperfocalDistance(){
        /*
        function to calculate hyperfocal distance
        returns the distance as a double in meters
         */
        return (lens.getFocal_Length()*lens.getFocal_Length())/(selectedAperture*COC)/1000;
    }

    public double nearFocalPoint(){
        /*
        function to calculate near focal point distance
        returns the distance as a double in meters
         */
        return ((hyperfocalDistance()*1000*subjectDistance) /(hyperfocalDistance()*1000 +
                (subjectDistance - lens.getFocal_Length())))/1000;
    }

    public double farFocalPoint(){
        /*
        function to calculate far focal point distance
        returns the distance as a double in meters
         */
        if(subjectDistance > hyperfocalDistance()*1000){
            //returns positive infinity because everything is in focus beyond the hyperfocal point
            return Double.POSITIVE_INFINITY;
        }
        return ((hyperfocalDistance()*1000*subjectDistance) /(hyperfocalDistance()*1000 -
                (subjectDistance - lens.getFocal_Length())))/1000;
    }

    public double depthofField(){
        /*
        function to calculate depth of field
        returns the distance as a double in meters
         */
        return farFocalPoint() - nearFocalPoint();
    }
}
