package com.company;
//import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {

    Node head;
    public static int[] split(int a[],int s,int e){
        int b[]=new int[e-s];
        for(int i=0, j=s;j<e;j++,i++){
            b[i]=a[j];
        }
        return b;
    }
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
    public static int bin(int low,int high,int t[],int num){

        for(int i=0;i<high;i++){
            if(t[i]==num){
                return i;

            }
        }
        return -1;
    }
    public static void disps(int inorder[],int pre[],int n){
        int index=bin(0,n,inorder,pre[0]);

        if(index!=0){
            disps(inorder, split(pre,1,n),index);

        }
        if(index!=n-1){
            disps(split(inorder,index+1,n),split(pre,index+1,n),n-index-1);
        }
        System.out.print(pre[0]+" ");
    }
    public static boolean check(int[] a,int n){
        for(int i=1;i<n;i++){
            if(a[i]<a[i-1]){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
//        int m=in.nextInt();
        int a[]=new int[n];
        int b[]=new int[n];
        for(int i=0;i<n;i++){

                a[i]=in.nextInt();

        }
        for(int i=0;i<n;i++){

            b[i]=in.nextInt();

        }

        disps(b,a,n);
        System.out.println();
        if(check(b,n)){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
    }
}
