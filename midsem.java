package com.company;

import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {



    public static void main(String[] args) {


        Scanner in=new Scanner(System.in);

        int n=in.nextInt();
        long t=in.nextLong();

        long a[]=new long[n];
        double sum=0;
        double mul=1;
        for(int i=0;i<n;i++){
            a[i]=in.nextLong();

            mul*=a[i];
        }
        for(int i=0;i<n;i++){
            sum+=(long)(mul/a[i]);
        }
        long num=(long)((t*mul)/sum);
//    System.out.println(num);
        
        int flag=0;
        while(flag!=1){
            long finals=0;
            for(int i=0;i<n;i++){
                finals+=num/a[i];

            }
//        System.out.println(finals);
            if(finals>t){
                finals=finals-1;
                num--;
                flag=1;
            }
            else{
                num++;
            }
        }
        if(num>1000000000){
            System.out.print(1000000000);
        }
        else {
            System.out.print(num);
        }






    }
}



