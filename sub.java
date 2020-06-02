package com.company;

import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static double find(long[] a,long t,double low,double high){
        double mid=(int)((low+high)/2);
        if(low>high){
            return 0;
        }
        if(sum(a,mid)<=t && sum(a,mid+1)>t){
            return mid;

        }
        if(sum(a,mid)<t){
            return find(a,t,mid+1,high);
        }
        else{
            return find(a,t,low,mid-1);
        }

    }

    public static double sum(long[] a,double num){
        double sum=0;
        for(int i=0;i<a.length;i++){
            sum+=Math.floor(num/a[i]);
        }
        return sum;
    }

    public static void main(String[] args) {


        Scanner in=new Scanner(System.in);

        int n=in.nextInt();
        long t=in.nextLong();

        long a[]=new long[n];
//    long sum=0;
//    long mul=1;
        for(int i=0;i<n;i++){
            a[i]=in.nextLong();

//        mul*=a[i];
        }
//    for(int i=0;i<n;i++){
//
//            sum += mul / a[i];
//
//    }
//    long num=(t*mul)/sum;
////    System.out.println(num);
//
//    int flag=0;
//    long finals=0;
//    while(flag!=1){
//        finals=0;
//        for(int i=0;i<n;i++){
//
//                finals += num / a[i];
//
//
//        }
////        System.out.println(finals);
//        if(finals>t){
//            finals=finals-1;
//            num--;
//            flag=1;
//        }
//        else{
//            num++;
//
//        }
//    }
        double num=find(a,t,1,1000000000);
        if(num!=0) {


            System.out.print((int) num);
        }
        else{
            System.out.print(1000000000);
        }






    }
}



