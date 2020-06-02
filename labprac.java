package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

//    int a=1;
//    Main head;
//    class sub{
//        int a;
//        sub next;
//        sub(){
//            a=1;
//            next=null;
//        }
//    }

//    public static Main nexti(Main a){
//        a.head=
//        return temp;
//
//    }
//    static void disp(Main A){
//        System.out.println(A.a);
//    }
//    static void enter(Main A,int b){
//        A.a=b;
//    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        LinkedList<String> hm = new LinkedList<String>();

        hm.add("1");
//        Main a=new Main();
        int k=0;
        String num=" ";
        int num1=0;
        int current=0;

        for(int i=0;i<n;i++){
//            if(i==n-3){
//                in.next();
//            }

            k=in.nextInt();
            if(k==1){
                num1=in.nextInt();
                num=Integer.toString(num1);

                hm.add(current+1,num);
            }
            else if(k==2){
                if(hm.size()!=current+1){
                    current++;
                }
                else{
                    current=0;
                }

            }
            else if(k==3){
                System.out.println(hm.get(current));
            }

        }









    }
}



