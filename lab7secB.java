package com.company;
import java.util.Scanner;
import java.io.*;

public class Main {

    Node head;

    public static class Node{
        int data;
        Node left,right;
        Node(int d){
            data=d;
            left=null;
            right=null;
        }
    }

    public static Node insert(Node head,int data){
        Node node=new Node(data);
        if (head == null) {
            head=node;
            return head;
        }
        if(data<=head.data){
            if(head.left==null){
                head.left=node;
                return head;
            }
            else{
                return insert(head.left,data);
            }
        }
        else{
            if(head.right==null){
                head.right=node;
                return head;
            }
            else{
                return insert(head.right,data);
            }
        }
    }
    public static void disp(Node head){
        if(head.left!=null){
            disp(head.left);
        }
        System.out.println(head.data);
        if(head.right!=null){
            disp(head.right);
        }
    }

    public static int bottom_sum(Node head){
        if(head.left!=null && head.right!=null){
//            System.out.println((head.data));
            return head.right.data+head.left.data+bottom_sum(head.right)+bottom_sum(head.left);
        }
        if(head.left!=null){
//            System.out.println(head.data);
            return head.left.data+bottom_sum(head.left);
        }
        if(head.right!=null){
//            System.out.println(head.data);
            return head.right.data+bottom_sum(head.right);
        }
        return 0;
    }

    public static void dostuff(Node head){
        if(head.left!=null){
            dostuff(head.left);
        }
        System.out.println(bottom_sum(head));
        if(head.right!=null){
            dostuff(head.right);
        }
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();
        Node head=new Node(m);
        for(int i=0;i<n-1;i++){
            int k=in.nextInt();
            Node temp=head;
            head=insert(head,k);
            head=temp;
        }

        dostuff(head);
    }
}
