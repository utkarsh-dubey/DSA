package com.company;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int d){
            data=d;
            left=null;
            right=null;
        }
    }

    public static void swap(int a[],int index1,int index2){
        int temp=a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
    public static int remove(int a[],int size){
        int top=a[0];
        a[0]=a[size-1];
        heapifydown(a,size-1);
        return top;
    }
    public static void insert(int a[],int size,int num){            // < for maxheap; > for  minheap
        a[size]=num;
        heapifyup(a,size+1);

    }
    public static void heapifyup(int a[],int size){
        int index=size-1;
        while((index-1)/2>=0 && a[(index-1)/2]>a[index]){
            swap(a,(index-1)/2,index);
            index=(index-1)/2;
        }

    }

    public static void heapifydown(int a[],int size){
        int index=0;

        while(2*index+1<size  ){
            int index1=2*index+1;
            if(2*index+2<size && a[2*index+2]<a[2*index+1]){
                index1=2*index+2;
            }
            if(a[index]<a[index1]){
                break;
            }
            else{
                swap(a,index,index1);
            }
            index=index1;
        }

    }
    public static void disp(int a[],int size){
        for(int i=0;i<size;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static Node maketree(int a[],int size){

        int start=remove(a,size--);
        int prev=start;
        Node centre=new Node(0);
        Node left,right;
        while(size>1){

            int max=remove(a,size--);
            Node tempcen=new Node(max+prev);
            if(prev<=max){
                left=new Node(prev);
                right=new Node(max);
            }
            else{
                right=new Node(prev);
                left=new Node(max);

            }
            tempcen.left=left;
            tempcen.right=right;
            insert(a,size++,max+prev);
            int end=remove(a,size--);
            if(end<=prev+max){
                centre.left=new Node(end);
                centre.right=tempcen;
            }
            else{
                centre.left=tempcen;
                centre.right=new Node(end);
            }
//            insert(a,size++,prev+max+end);
            prev=end;


        }

        return centre;
    }

    public static void dispbst(Node head){
        if(head.left!=null){
            dispbst(head.left);
        }
        System.out.println(head.data);
        if(head.right!=null){
            dispbst(head.right);
        }
    }



    public static String[] getcode(int a[],int size,Dictionary store){

        Node tree=maketree(a,size);
        dispbst(tree);
        String[] re=new String[]{" a","b","c"};
        return re;
    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

        int n=in.nextInt();
        int num[]=new int[n];
        int a[]=new int[n];
        int ans[]=new int[n];
        for(int i=0;i<n;i++){
            num[i]=in.nextInt();
            insert(a,i,num[i]);
            if(i<2){
                ans[i]=-1;
            }
            else{
                int first=remove(a,1+i--);
                int second=remove(a,1+i--);
//                System.out.println(i);
                int third=remove(a,1+i--);
                ans[i+3]=first^second^third;
                insert(a,++i,first);
                insert(a,++i,second);
                insert(a,++i,third);

            }


        }
        int q=in.nextInt();
        for(int i=0;i<q;i++){
            int k=in.nextInt();

            System.out.println(ans[k-1]);
        }





    }
}