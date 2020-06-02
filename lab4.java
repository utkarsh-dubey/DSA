package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static int ut = 0;


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int n;

        n = in.nextInt();
        double d;
        d=in.nextDouble();
        double a[]=new double[n];
        for(int i=0;i<n;i++){
            a[i]=in.nextDouble();
            }

        for(int i=0;i<d;i++){

            double ch=in.nextDouble();

            int flag=-1;
            for(int j=0;j<n;j++){
                if(a[j]>ch){
                    flag=j;
                    break;
                }
            }

            System.out.println(flag);



        }



    }

}