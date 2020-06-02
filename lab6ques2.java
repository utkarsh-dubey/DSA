package com.company;



import java.util.Scanner;
import java.io.*;

public class Main {

    Node top;

    public static class Node {
        int data;
        int index;
        Node next;

        Node(int d,int ind) {
            data = d;
            index=ind;
            next = null;
        }
    }
    public static Main pop(Main stack) {
        if(stack.top==null) {
            return stack;
        }
        stack.top=stack.top.next;
        return stack;
    }
    public static Main insert(Main stack,int a) {



        if(stack.top==null){
            Node node=new Node(a,1);
            stack.top=node;
            return stack;
        }
        while(stack.top!=null&&stack.top.data<a){
            stack=pop(stack);
        }
        if(stack.top==null){
            Node node=new Node(a,1);
            stack.top=node;
            return stack;
        }
        Node node=new Node(a,stack.top.index+1);
        node.next=stack.top;
        stack.top=node;
        return stack;

    }
//    public static Main count(Main stack) {
//    int count=0;
//        if(stack.top==null) {
//            System.out.println(count);
//            return stack;
//        }
//        Node temp=stack.top;
//        while(stack.top.next!=null) {
//            count++;
//            stack.top = stack.top.next;
//        }
//        System.out.println(count+1);
//        stack.top=temp;
//        return stack;
//
//
//    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Main a = new Main();
        int num[] = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            a = insert(a, num[i]);
            System.out.println(a.top.index);
        }


    }
}
