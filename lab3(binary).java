package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static int ut = 0;

    public static int bin(double low,double high,double t){

        if(high>=low){
            int mid=(int)(high+low)/2;


            if((mid*Math.log10(mid))>=t){
//
                return bin(low,mid-1,t);
            }
            else {
//
                return bin(mid +1, high, t);
            }

        }
        else {
            return (int)low;
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        double n;

        n = in.nextDouble();

        for(long i=0;i<n;i++){

            double k=in.nextDouble();
            double t=in.nextDouble();
            double temp=t/k;

            double p=2*Math.pow(10,9);

            if(t<k){
                System.out.println(0);
            }
//            else if(t==k){
//                System.out.println(1);
//            }
            else {
                int num = bin(1, p, temp);
//            System.out.println(k*num*Math.log10(num));
//            System.out.println(k*(num+1)*Math.log10(num+1));
//            System.out.println(k*(num-1)*Math.log10(num-1));
//            System.out.println(t);
                if (k * num * Math.log10(num) > t && ((num - 1) * k * Math.log10(num - 1)) <= t) {
                    System.out.println(num - 1);
                } else if (((num + 1) * k * Math.log10(num + 1)) <= t) {
                    System.out.println(num + 1);

                } else {
                    System.out.println(num);
                }


            }
        }


    }

}