package com.GitBuff.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    private List<Exercise> mExercises;

    public Workout(){
        mExercises = new ArrayList<>();
    }

    public void addExercise(Exercise exerciseToAdd){
        mExercises.add(exerciseToAdd);
    }

    public void removeExercise(Exercise exerciseToRemove) {
        mExercises.remove(exerciseToRemove);
    }

    public List<Exercise> getExercises(){
        return mExercises;
    }



}
