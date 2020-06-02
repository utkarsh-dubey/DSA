package com.company;

import java.util.Scanner;
import java.io.*;

public class Main {

    Node head;
    Node last;
    public static class Node{
        long data;
        int index;
        Node next;
        Node(long d,int ind){
            data=d;
            index=ind;
            next=null;
        }
    }

    public static Main insert(Main thing,long data){

        if(thing.head==null){
            Node node=new Node(data,1);
            thing.head=node;
            thing.last=node;
            return thing;
        }
        Node node=new Node(data,thing.last.index+1);
        thing.last.next=node;

        thing.last=node;
        return thing;
    }
//    public static Main disp(Main a){
//        System.out.print("list contains=");
//        if(a.head==null){
//
//            return a;
//        }
//        Node temp=a.head;
//         while(a.head.next!=null){
//             System.out.print(a.head.data+" ");
//             a.head=a.head.next;
//         }
//         System.out.print(a.head.data);
//         a.head=temp;
//         System.out.println();
//         return a;
//    }
    public static Main delete(Main thing){
        if(thing.head==thing.last){
            thing.head=null;
            thing.last=null;
            return thing;
        }
        thing.head=thing.head.next;
        int i=1;
        Node temp=thing.head;
        temp.index=i;
        while(thing.head.next!=null){
            thing.head.index=i;
            i++;
            thing.head=thing.head.next;
        }
        thing.head.index=i;
        thing.head=temp;


        return thing;
    }
    public static Main inc(Main thing,int x,long d){
        Node temp=thing.head;
//        System.out.println("head="+thing.head.index);
        if(x==thing.last.index){
            while(thing.head.next!=null){

                thing.head.data+=d;
                thing.head=thing.head.next;
            }
//            temp.data+=d;
            thing.head.data+=d;
            thing.head=temp;
            return thing;
        }
        while(thing.head.index!=thing.last.index-x){
//            System.out.println("index="+thing.head.index);
            thing.head=thing.head.next;

        }
        while(thing.head.next!=null){
            thing.head=thing.head.next;
            thing.head.data+=d;


        }
//        thing.head.data+=d;
        thing.head=temp;
        return thing;
    }
    public static void main(String[] args) {

        Scanner in= new Scanner(System.in);
        int n=in.nextInt();
        Main a=new Main();

        for(int i=0;i<n;i++){
            String k=in.next();
            if(k.equals("enqueue")){
                long m=in.nextLong();
                a=insert(a,m);
//                a=disp(a);
                System.out.println(a.head.data);
            }
            else if(k.equals("dequeue")){
                a=delete(a);
//                a=disp(a);
                if(a.head==null){
                    System.out.println("EMPTY");
                }
                else{
                    System.out.println(a.head.data);
                }

            }
            else if(k.equals("inc")){
                int x=in.nextInt();
                long d=in.nextLong();
                a=inc(a,x,d);
//                a=disp(a);
                System.out.println(a.head.data);

            }
        }



    }
}
