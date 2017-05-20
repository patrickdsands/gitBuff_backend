package com.GitBuff.model;

public class Exercise {

    private int mRestTime;
    private String mName;

    public Exercise(String name, int restTime){
        mName = name;
        mRestTime = restTime;
    }

    public String getName() {
        return mName;
    }

    public int getRestTime(){
        return mRestTime;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        return mName.equals(exercise.mName);
    }

    @Override
    public int hashCode() {
        return mName.hashCode();
    }
}
