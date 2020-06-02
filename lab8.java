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
        while((index-1)/2>=0 && a[(index-1)/2]<a[index]){
            swap(a,(index-1)/2,index);
            index=(index-1)/2;
        }

    }

    public static void heapifydown(int a[],int size){
        int index=0;

        while(2*index+1<size  ){
            int index1=2*index+1;
            if(2*index+2<size && a[2*index+2]>a[2*index+1]){
                index1=2*index+2;
            }
            if(a[index]>a[index1]){
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
        int k=in.nextInt();
        int n=in.nextInt();
        int a[]=new int[n];
        for(int i=0;i<n;i++){
            int num=in.nextInt();
            insert(a,i,num);
        }
//        disp(a,n);

        int sum=0;
        int size=n;
        for(int i=0;i<k;i++){
            int temp=remove(a,size);
//            System.out.println(temp);
            size--;
            sum+=(temp);

//            System.out.println(sum);
            temp=(temp-(temp/2));
            insert(a,size,(temp));
            size++;
        }
        System.out.println(sum);



    }
}