package com.company;

import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

   public static class Node{
       int data;
       Node next;
       Node(int a){
           data=a;
           next=null;

       }
   }

//   public static Node disp(Node head){
//       Node temp=head;
//       while(temp!=null){
//           System.out.print(temp.data+" ");
//            temp=temp.next;
//       }
//       System.out.println();
//       return head;
//   }

    public static void main(String[] args) {


    Scanner in=new Scanner(System.in);

    int n=in.nextInt();
    int q=in.nextInt();
    int first=in.nextInt();
    Node head=new Node(first);
    Node cur=head;
    for(int i=0;i<n-1;i++) {
        int num = in.nextInt();
        Node node = new Node(num);
        cur.next = node;
        cur = node;


    }

    for(int i=0;i<q;i++){
        int k=in.nextInt();
        if(k==1){
            System.out.println(head.data);
        }
        if(k==2){
            if(head.next!=null){
                head=head.next;
            }


        }
        if(k==3){
            int num=in.nextInt();
            Node temp=new Node(num);
//            if(head.next!=null){
//                temp.next=head.next;
//                head.next=temp;
//
//            }
//            else{
//                head.next=temp;
//            }
            temp.next=head.next;
            head.next=temp;



        }
//        head=disp(head);
    }



    }
}


