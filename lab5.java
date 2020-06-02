package com.company;

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
        int k=s,i=0,j=0;

        while(i<a1&&j<a2){
            if(one[i]<two[j]){
                a[k]=one[i];
                i++;

            }
            else{
                a[k]=two[j];

                j++;
            }
            k++;
        }

        while(i<a1){
            a[k]=one[i];
            i++;
            k++;

        }

        while(j<a2){
            a[k]=two[j];
            j++;
            k++;
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

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int a[]=new int[n];

        for(int i=0;i<n;i++){
            a[i]=in.nextInt();

        }
        sort(a,0,n-1);

        int num=0;

        for(int i=0;i<n;i+=2){
            num+=a[i+1]-a[i];
        }

        System.out.print(num);

    }
}



