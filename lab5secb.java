package com.company;

import java.util.Scanner;


import java.util.*;
public class Main {

    public static void sub(int a[],int n,int num) {

        for(int i=0;i<n;i++) {

            a[i] -= num;

        }


    }
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        int t = obj.nextInt();
        for (int i = 0 ;i<t;i++){
            int n = obj.nextInt();
            int[] ar = new int[n];
            int[] temp = new int[n];
            for(int k = 0;k<n;k++){
                ar[k] = obj.nextInt();
                temp[k]=k+1;
            }

            sort(ar,temp,0,0,n-1,n-1);
//            for(int j=0;j<n;j++)
//            {
//                System.out.println(ar[j]);
//
//            }
//            for(int j=0;j<n;j++)
//            {
//                System.out.println(temp[j]);
//
//            }

            int index=1;
            int count=0;
            while(index!=0) {

                index = 0;
                for (int last = 0; last < n; last++) {
                    if (ar[last] >=0) {
                        index = temp[last];
                        break;
                    }
                }
                sub(ar,n,index);


                count++;
            }
            System.out.println(count-1);
        }
    }
    public static void faltu(int arr[],int temp[], int l,int templ, int m,int tempm, int r,int tempr){

        int n1 = m - l + 1;
        int n2 = r - m;



        int[] L = new int [n1];

        int[] R = new int [n2];
        int[] tempL = new int [n1];

        int[] tempR = new int [n2];


        for (int i=0; i<n1; ++i){
            L[i] = arr[l + i];
            tempL[i]=temp[templ+i];}
        for (int j=0; j<n2; ++j){
            R[j] = arr[m + 1+ j];
            tempR[j]=temp[tempm+1+j];}

        int i = 0, j = 0;


        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                temp[k]=tempL[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                temp[k]=tempR[j];
                j++;
            }
            k++;
        }


        while (i < n1)
        {
            arr[k] = L[i];
            temp[k]=tempL[i];
            i++;
            k++;
        }


        while (j < n2)
        {
            arr[k] = R[j];
            temp[k]=tempR[j];
            j++;
            k++;
        }
    }

    public static void sort(int[] arr,int temp[], int l,int templ, int r,int tempr)
    {
        if (l < r)
        {

            int m = (l+r)/2;

            sort(arr,temp, l,l,m ,m);
            sort(arr ,temp,m+1 ,m+1,r, r);

            faltu(arr,temp, l,l,m, m,r, r);
        }
    }


}
