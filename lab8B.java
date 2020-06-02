package com.company;
import java.util.Scanner;

public class Main {

    public static void swap(long a[],int index1,int index2){
        long temp=a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
    public static long remove(long a[],int size,int flag){
        long top=a[0];
        a[0]=a[size-1];
        if(flag==0) {
            heapifydown(a, size - 1);
        }
        else{
            heapifydowns(a, size - 1);
        }
        return top;
    }
    public static void insert(long a[],int size,long num,int flag){
        a[size]=num;
        if(flag==0) {
            heapifyup(a, size + 1);
        }
        else{
            heapifyups(a, size + 1);
        }

    }
    public static void heapifyup(long a[],int size){
        int index=size-1;
        while((index-1)/2>=0 && a[(index-1)/2]<a[index]){
            swap(a,(index-1)/2,index);
            index=(index-1)/2;
        }

    }
    public static void heapifyups(long a[],int size){
        int index=size-1;
        while((index-1)/2>=0 && a[(index-1)/2]>a[index]){
            swap(a,(index-1)/2,index);
            index=(index-1)/2;
        }

    }

    public static void heapifydown(long a[],int size){
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
    public static void heapifydowns(long a[],int size){
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
    public static void disp(long a[],int size){

        for(int i=0;i<size;i++){

                System.out.print(a[i] + " ");

        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            long a[] = new long[n];
            long sum = 0;
            long sums[] = new long[n];
            long heapmax[] = new long[n];
            for (int i = 0; i < n; i++) {
                int num = in.nextInt();
                a[i]=num;
//                insert(a, i, num);
                sum += num;
//                insert(heapmax, i, sum, 0);
                sums[i] = sum;
//                disp(heapmax,i);

            }
            insert(heapmax,0,sum,0);
            int size=1;
            long total=sum;
            for(int i=0;i<n-1;i++){
                sum=total-sums[i];
                insert(heapmax,size,sum,0);
                size++;
//                disp(heapmax,size);


            }
            int temp=n-1;
            for(int i=0;i<k;i++){

                long ultimatetemp=remove(heapmax,size,0);
                System.out.print(ultimatetemp+" ");

                size--;
                total-=a[temp];

                insert(heapmax,size,total,0);
                if(temp>0) {
                    temp--;
                }
                size++;
//                disp(heapmax,size);
            }



            t--;
            System.out.println();
        }
    }
}