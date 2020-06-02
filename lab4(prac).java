package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static int ut = 0;

    public static double dist(double x1,double y1,double x2,double y2){
        return Math.pow((Math.pow((x2-x1),2)+Math.pow((y2-y1),2)),0.5);
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int n;

        n = in.nextInt();
        double a[][]=new double[n][2];
        for(int i=0;i<n;i++){

           a[i][0]=in.nextDouble();
           a[i][1]=in.nextDouble();
        }
        double distance=0;
        for(int i=0;i<n;i++){

            for(int j=i;j<n;j++){
                if(dist(a[i][0],a[i][1],a[j][0],a[j][1])>distance){
                    distance=dist(a[i][0],a[i][1],a[j][0],a[j][1]);

                }
            }
        }

        System.out.print(Math.round(distance*100.0)/100.0);



    }

}