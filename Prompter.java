package com.GitBuff;

import com.GitBuff.model.Exercise;
import com.GitBuff.model.Workout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prompter {

    private BufferedReader mReader;
    private Workout mWorkout;
    private Map<String,String> mMenu;
    private Queue<Exercise> mExerciseQueue;

    public Prompter(){
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mWorkout = new Workout();
        mExerciseQueue = new ArrayDeque<>();
        mMenu = new HashMap<>();
        mMenu.put("Add","Add an exercise to your workout.");
        mMenu.put("View","View all exercises in your workout.");
        mMenu.put("Remove","Remove an exercise from your workout.");
        mMenu.put("Go","Start your exercise!");
        mMenu.put("Quit","Exit the program.");
    }

    private String promptAction() throws IOException {
        System.out.printf("There are %d exercises in your workout. Your options are: %n",
                mExerciseQueue.size());
        for(Map.Entry entry: mMenu.entrySet()){
            System.out.printf("%s - %s%n", entry.getKey(), entry.getValue());
        }
        System.out.print("What do you want to do? ");
        String choice = mReader.readLine();
        return choice.trim().toLowerCase();
    }

    public void run(){
        String choice = "";
        do{
            try {
                choice = promptAction();
                switch(choice){
                    case "add":
                        Exercise exerciseToAdd = promptNewExercise();
                        mWorkout.addExercise(exerciseToAdd);
                        mExerciseQueue.add(exerciseToAdd);
                        System.out.printf("%n'%s' added to your workout!%n%n",exerciseToAdd);
                        break;
                    case "view":
                        availableExercises();
                        break;
                    case "remove":
                        availableExercises();
                        Exercise exerciseToRemove = promptRemoveExercise();
                        mWorkout.removeExercise(exerciseToRemove);
                        mExerciseQueue.remove(exerciseToRemove);
                        break;
                    case "go":
                        beginExercise();
                        break;
                    case "quit":
                        System.out.println("\nThanks for exercising with me today!");
                        break;
                    default:
                        System.out.printf("%nUnknown command: '%s'. Try again!%n%n", choice);
                }

            } catch (IOException ioe) {
                System.out.println("Problem with your input...");
                ioe.printStackTrace();
            }
        }while(!choice.equals("quit"));
    }

    private Exercise promptRemoveExercise() throws IOException {
        System.out.print("Enter the name of the exercise to remove: ");
        String exerciseToRemove = mReader.readLine();
        return new Exercise(exerciseToRemove,0); //TODO: Check if adding 0 here is ok.
    }

    private Exercise promptNewExercise() throws IOException {
        System.out.print("\nEnter the name of the exercise: ");
        String exerciseName = mReader.readLine();
        System.out.print("Enter your desired rest time(in seconds): ");
        String restAsString = mReader.readLine();
        int restTime = Integer.parseInt(restAsString.trim());
        return new Exercise(exerciseName.trim(),restTime);
    }

    private void beginExercise(){
        Exercise exercise = mExerciseQueue.poll();
        if(exercise == null){
            System.out.println("\nThere are no exercises in your workout. Enter 'add' to " +
                "add an exercise!\n");
        }else{
            System.out.printf("%nCurrent Exercise: '%s' %nRest time: %d seconds%n%n",
                    exercise.getName(),
                    exercise.getRestTime());
            mExerciseQueue.remove(exercise);
        }
    }

    private void availableExercises(){
        if(mExerciseQueue.isEmpty()){
            System.out.println("\nThere are no exercises in your workout. Enter 'add' to" +
                    " add an exercise!");
        }
        System.out.println("\nAvailable Exercises: ");
        int counter = 1;
        for(Exercise exercise : mWorkout.getExercises()){
            System.out.printf("%d) %s%n",counter, exercise.getName());
            counter++;
        }
    }

}













