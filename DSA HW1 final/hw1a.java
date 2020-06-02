package com.company;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

        String a=in.next();
        int lcount=0,rcount=0;

        for(int i=0;i<a.length();i++){
            if((i+1)%2==0){
                if(a.charAt(i)=='R'){
                    lcount++;
                }
                else{
                    rcount++;
                }
            }
            else{
                if(a.charAt(i)=='R'){
                    rcount++;
                }
                else{
                    lcount++;
                }
            }
        }
        if(lcount==rcount){
            System.out.println("YES");

        }
        else{
            System.out.println("NO");
        }
    }
}