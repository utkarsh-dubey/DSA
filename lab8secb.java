package com.company;
import java.util.Scanner;

public class Main {

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
    public static void insert(int a[],int size,int num){
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
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int k=in.nextInt();
        int a[]=new int[n];
        int size=0;
        for(int i=0;i<n;i++){
            int num=in.nextInt();
            insert(a,i,num);
        }
//        disp(a,n);
        size=n;
        int count=0;
        int flag=1;
        int sum=0;
        while(flag!=0){
//            disp(a,size);
            if(size<2){
                if(a[0]<k){
                    count=-1;
                }
                break;
            }
            if(a[0]>=k){
                break;
            }
            int one=remove(a,size);
            size--;
//            System.out.println(one);
            int two=remove(a,size);
//            System.out.println(two);
            size--;
            sum+=(one+two);
            count++;
//            System.out.println(sum);
            insert(a,size,sum);

            size++;
//            disp(a,size);
            if(((one>=k)|| (two>=k))&& sum>=k){
                break;
            }
            sum=0;


        }

        System.out.print(count);

    }
}