package com.company;
/* Author: Abdul El Badaoui
* Student Number: 5745716
* Description: The following program provides the optimal path that needs to be taken in a greedy algorithm Amazing
* Race problem. This problem was solved using the Matroid example from class, and was the bases to construct this
* algorithm
* */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    static Scanner fileInput;// to read in a file
    static PrintWriter fileOutput; // to output the file in the end

    public static void main(String[] args) {
        //try catch clasue to handle the input/output files
        try {
            fileInput = new Scanner(new FileInputStream(new File("a1q3in.txt")));
            fileOutput = new PrintWriter("a1q3out.txt", "UTF-8");// output file creation
            int m = fileInput.nextInt(); // number of scenarios
            for (int i =1; i<=m ; i++){
                fileOutput.println("Scenario Number " +i+ "!");
                inputs();// read in the rest of the inputs per each scenario
            }
            fileOutput.close();//closes the file out put write

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }
    //method to initalize all the values and complete the problem
    public static void inputs(){
        int n = fileInput.nextInt();// number of steps
        int k = fileInput.nextInt();//number of stops
        int e  = fileInput.nextInt();// the constant entry time
        int x = fileInput.nextInt();// the constant exist time
        int [] t = new int [n-1];// array to hold the transfer time between steps
        for (int i=0; i<n-1; i++){//store the transfer times
            t[i]= fileInput.nextInt();
        }
        int [][] a= new int [n][k];// activity time
        ArrayList<acitivtyInfo> S = new ArrayList<>();// create an array list of the object class I created

        for (int i =0; i<n; i++){//Stores the activities (elements) in the arraylist
            for (int j=0; j<k ; j++){
                a[i][j] = fileInput.nextInt();
                acitivtyInfo p =  new acitivtyInfo(a[i][j], i, j);
                S.add(p);
            }
        }
        //Creating the optimal solution list when the greedyAmazingRace method returns the solution
        ArrayList<acitivtyInfo> A = greedyAmazingRace(S, n);

        int minimumOverallTime = e;// start to add the the overall minimum time answer
        for (int i=0; i<n; i++){//add all the elements weight that is in A and the transfer times between each step
            if (i>0){
                minimumOverallTime+= t[i-1];
            }
            minimumOverallTime+=A.get(i).getWeight();
        }
        minimumOverallTime+=x;// add the exit time
        fileOutput.println("The Minimum Overall Time is: " +minimumOverallTime);
        for (int i=0; i<n; i++){//print out the path
            fileOutput.println("Step " +(A.get(i).getStep()+1)+ ":    Stop " +(A.get(i).getStop()+1));
        }
        fileOutput.println();

    }
    //the greedy algorithm for the amazing race
    public static ArrayList<acitivtyInfo> greedyAmazingRace(ArrayList<acitivtyInfo> S, int n){
        ArrayList<acitivtyInfo> A = new ArrayList<>();// creates the solution that will returned
        //sorting the Arraylist S using the following lambda expression  for weights
        Collections.sort(S, (alpha, beta) -> alpha.getWeight()
                < beta.getWeight() ? -1 : alpha.getWeight() == beta.getWeight() ? 0 : 1);
        int check;// an int to compare steps value of each element in A
        for (int i=0; i<S.size(); i++){// for loop to find the optimal elements for A
            check = S.get(i).getStep();//check is set to the current element of arraylist S's step
            if (!l_M(check, A)){//boolean method to check no steps are duplicated in arraylist A with check
                A.add(S.get(i));//add current element to A
                if (A.size()==n) break;// break the loop if the arraylist A is the size of "n" steps
            }
        }
        //sorting the Arraylist A using the following lambda expression  for steps
        Collections.sort(A, (alpha, beta) -> alpha.getStep()
                < beta.getStep() ? -1 : alpha.getStep() == beta.getStep() ? 0 : 1);

        return A;//returns Arraylist A
    }
    //the method to check the if A union S.get(i) from the above method is in I
    public static boolean l_M(int check, ArrayList<acitivtyInfo> A){

        for (int i =0; i<A.size(); i++){
            if (A.get(i).getStep()==check){
                return true;//if true it is not in I
            }
        }
        return false;//if it is false then it is in I
    }

}
