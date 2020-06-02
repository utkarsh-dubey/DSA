 package com.company;

import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static void merge(int a[],int s,int mid,int end ){
        int a1=(mid-s)+1;
        int a2=(end-mid);
        int[] one=new int[a1];
        int[] two=new int[a2];
        for(int i=0;i<a1;i++){
            one[i]=a[s+i];
        }
        for(int i=0;i<a2;i++){
            two[i]=a[mid+i+1];
        }
        int left=s;
        int i=0;
        int j=0;
        while(i<a1&&j<a2){
            if(one[i]<two[j]){
                a[left]=one[i];
                i++;
            }
            else{
                a[left]=two[j];
                j++;
            }
            left++;
        }
        while(i<a1){
            a[left]=one[i];
            i++;
            left++;
        }
        while(j<a2){
            a[left]=two[j];
            j++;
            left++;
        }
    }
    public static void sort(int a[],int s,int end){
        int mid=(s+end)/2;
        if(s<end) {
            sort(a, s, mid);
            sort(a, mid + 1, end);
            merge(a, s, mid, end);
        }
    }


    public static void main(String[] args) {


    Scanner in=new Scanner(System.in);

    int n=in.nextInt();
    int q=in.nextInt();

    int a[]=new int[n];

    for(int i=0;i<n;i++){
        a[i]=in.nextInt();

    }
    sort(a,0,n-1);
    for(int i=0;i<q;i++){
        int check=in.nextInt();
        System.out.println(a[n-check]);
    }



    }
}

