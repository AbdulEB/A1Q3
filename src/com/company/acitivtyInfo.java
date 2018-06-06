package com.company;

/* Author: Abdul El Badaoui
* Student Number: 5745716
* Description: An Object class to hold the weight, step and stop of each activity
* */
public class acitivtyInfo {
    private int weight;
    private int step;
    private int stop;
    //stores the current weight time, step and stop when instance of the object is created
    protected acitivtyInfo(int weight, int step, int stop){
        this.weight=weight;
        this.step=step;
        this.stop =stop;
    }
    //returns the weight
    public int getWeight(){
        return weight;
    }
    //returns the Step
    public int getStep(){
        return step;
    }
    //returns the Stop
    public int getStop(){
        return stop;
    }


}
