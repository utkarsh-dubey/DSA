package com.company;
import java.util.Scanner;
import java.lang.Math;
public class Main {

    Node head;
    public static class Node{
        int number;
        int dist;
        Node next;
        Node(int n){
            number=n;
            dist=0;
            next=null;
        }
    }


    public static void finaliselist(Main list,int target,int dist){
        Node temp=list.head;
        while(list.head!=null){
            if(list.head.number==target){
                list.head.dist=dist;

            }
            list.head=list.head.next;
        }
        list.head=temp;
    }





    public static boolean present(int s[],int r){
        for(int i=0;i<s.length;i++){
            if(s[i]==r){
                return true;
            }
        }
        return false;
    }
    public static int getindex(int t[][],int n){
        for(int i=0;i<t.length;i++){
            if(n==t[i][0]){
                return i;
            }
            if(n==t[i][1]){
                return i+1;
            }
        }
        return 0;
    }

    public static long getdist(int s,int e,int t[][],Main list){
//        int indexstart=getindex(t,s);
//        int indexend=getindex(t,e);
        long dist=0;
//        if(indexstart==indexend){
//            return (long)t[indexend][2];
//        }
//        else{
//            if(indexstart<indexend){
//                for(int i=indexstart;i<indexend;i++){
//                    dist+=t[i][2];
//                }
//            }
//            else{
//                for(int i=indexend;i<indexstart;i++){
//                    dist+=t[i][2];
//                }
//            }
//        }

        if(s<e){
            Node temp=list.head;
            while(list.head.number!=e){
                if(list.head.number>=s){
                    dist+=list.head.dist;

                }
                list.head=list.head.next;

            }
            list.head=temp;
        }
        else{
            Node temp=list.head;
            while(list.head.number!=s){
                if(list.head.number>=e){
                    dist+=list.head.dist;

                }
                list.head=list.head.next;

            }
            list.head=temp;

        }
        return dist;
    }

    public static long nearest(int s[],int r,int one,int two,int t[][],Main list){

        long lowest=(long)Math.pow(10,20);
        for(int i=0;i<s.length;i++){

            if((s[i] <= one && one < r ) || (s[i] >= two && r < two)){
                continue;
            }
            else{
                long dist=getdist(s[i],r,t,list);
                if(dist<lowest){
                    lowest=dist;
                }
            }

        }

        if(lowest==(long)Math.pow(10,20)){
            return -1;
        }
        else{
            return lowest;
        }

    }
    public static void disp(Main list){
        Node temp=list.head;
        while(temp!=null){
            System.out.print(temp.number+"-"+temp.dist+" ");
            temp=temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {

        Scanner in=new Scanner(System.in);
        Main list=new Main();
        Node start=new Node(1);
        list.head=start;
        int n=in.nextInt();
        int s=in.nextInt();
        int q=in.nextInt();
        int e=in.nextInt();
        int t[][]=new int[n-1][3];

        for(int i=1;i<n;i++){
            Node node=new Node(i+1);
            start.next=node;

//            list.head=start;
            start=start.next;

        }

//        disp(list);

        for(int i=0;i<n-1;i++){
            t[i][0]=in.nextInt();

            t[i][1]=in.nextInt();
            t[i][2]=in.nextInt();
            finaliselist(list,Math.min(t[i][0],t[i][1]),t[i][2]);

        }
//        disp(list);
        int safe[]=new int[s];
        for(int i=0;i<s;i++){
            safe[i]=in.nextInt();

        }
        int i=0;
        int r=0;


        for(int k=0;k<q;k++){
            i=in.nextInt();
            r=in.nextInt();
            int blocked1=t[i-1][0];
            int blocked2=t[i-1][1];
            if(blocked1>blocked2){
                int temp=blocked1;
                blocked1=blocked2;
                blocked2=temp;
            }
            if(e==r){
                System.out.println("escaped");

            }
            else {
                if ((e <= blocked1 && blocked2 <= r )|| (e >= blocked2 && r <= blocked1)) {
                    if(present(safe,r)){
                        System.out.println("0");
                    }
                    else{
                        long near=nearest(safe,r,blocked1,blocked2,t,list);
                        if(near==-1){
                            System.out.println("oo");
                        }
                        else{
                            System.out.println(near);
                        }
                    }

                } else {
                    System.out.println("escaped");
                }
            }
        }
    }
}
