package com.company;
import java.util.Scanner;


public class Main{

    public static int con(long a,long b,long c){
        if(a==b){
            return 0;

        }
        if(a*c<=b){
            int temp= (int) ((Math.log(b/a)/Math.log(c))+1);
            System.out.println(temp);
            System.out.println(a*(long)(Math.pow(c,temp)));
            return temp+con(a*(long)(Math.pow(c,temp-1)),b,c);
        }
        else{

           if((a-1)*c==b || (a-1)==b){
               return 1+con(a-1,b,c);
           }
           else{
               return Math.max(0,1+con(a-2,b,c));
           }
        }
    }

    public static void main(String[] args){

        Scanner in =new Scanner(System.in);
        int n=in.nextInt();



        for(int i=0;i<n;i++){
            int count=0;
            long a=in.nextLong();
            long b=in.nextLong();
            long c=in.nextLong();
            if(c==1){
                System.out.println(-1);
            }
            else {
                count = con(a, b, c);

                System.out.println(count);
            }

        }


    }
}
