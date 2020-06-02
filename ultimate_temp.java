package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static int ut=0;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int n, m, x, y;
        Main z = new Main();
        n = in.nextInt();
        m = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();

        int a[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();

            }
        }
        if(a[0][0]==0){
            System.out.print(0);
        }
        else {
            int count = 0, count1 = 0;
            int r = 0, d = 0;
            d = x - 1;
            r = y - 1;
            String num = "";
            for (int i = 0; i < d; i++) {
                num += "d";
            }
            for (int i = 0; i < r; i++) {
                num += "r";
            }
            int up = z.fact(d + r);
            int down = (z.fact(d)) * (z.fact(r));
            int per = up / down;

            String[] arr = new String[per];

            z.printPermutn((num), "", arr);

            for (int i = 0; i < ut; i++) {
                if (z.coun(arr[i], a, 0, 0)) {
                    count++;
                }

            }
            ut = 0;
            d = n - x;
            r = m - y;
            num = "";
            for (int i = 0; i < d; i++) {
                num += "d";
            }
            for (int i = 0; i < r; i++) {
                num += "r";
            }
            up = z.fact(d + r);
            down = (z.fact(d)) * (z.fact(r));
            per = up / down;

            String[] arr1 = new String[per];

            z.printPermutn((num), "", arr1);

            for (int i = 0; i < ut; i++) {
                if (z.coun(arr1[i], a, x - 1, y - 1)) {
                    count1++;
                }

            }

            System.out.println(count * count1);


        }

    }

//
//    static String[] all(String sr, int time, int len,String[] arr) {
//
//        if (time == len) {
//            arr[ut]=
//        }
//            else{
//        for( int i=time;i< len + 1;i++){
//
//
//        sr[time], sr[i] = sr[i], sr[time];
//        all(sr, time+1, len,arr);
//        sr[time], sr[i] = sr[i], sr[time]}
//    }}
//    permute(["a","b","c"],0,2)

    static void printPermutn(String str, String ans,String[] arr)
    {


        if (str.length() == 0) {
            if(!search(arr,ans)){

                arr[ut]=ans;
                ut++;
                return;
            }

//            return;
        }

        for (int i = 0; i < str.length(); i++) {


            char ch = str.charAt(i);


            String ros = str.substring(0, i) +
                    str.substring(i + 1);


            printPermutn(ros, ans + ch,arr);
        }
    }




    static int fact(int num){
                int temp=1;


                for(int i=1;i<=num;i++){
                    temp*=i;
                }
                return temp;

        }


    static boolean coun(String arr,int a[][],int n,int m){
        int x=n;
        int y=m;
        int flag=0;
        for(int i=0;i<arr.length();i++){
            if(arr.charAt(i)=='r'){
                if(a[x][y+1]==0){
                    flag=1;
                    break;
                }
                else{
                    y++;
                }
            }
            else{
                if(a[x+1][y]==0){
                    flag=1;
                    break;
                }
                else{
                    x++;
                }
            }

        }
        if(flag==1){
            return false;
        }
        else{
            return true;
        }

    }

    static boolean search(String[] arr,String a){
        int n=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null){
            if(arr[i].equals(a)){
                n=1;
                break;
            }}
        }
        if(n==1){
            return true;

        }
        else{
            return false;
        }
    }




    }

