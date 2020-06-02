package com.company;
import java.util.Scanner;
import java.lang.Math;
public class Main {

    public static void swap(int a[],int index1,int index2){
        int temp=a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
    public static int remove(int a[],int second[],int size){
        int top=a[0];
        int top2=second[0];
        a[0]=a[size-1];
        second[0]=second[size-1];
        heapifydown(a,second,size-1);
        return top;
    }
    public static void insert(int a[],int second[],int size,int num,int num2){
        a[size]=num;
        second[size]=num2;
        heapifyup(a,second,size+1);

    }
    public static void heapifyup(int a[],int second[],int size){
        int index=size-1;

        while((index-1)/2>=0 && a[(index-1)/2]<a[index]){
            swap(a,(index-1)/2,index);
            swap(second,(index-1)/2,index);
            index=(index-1)/2;
        }

    }

    public static void heapifydown(int a[],int second[],int size){
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
                swap(second,index,index1);
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
        int t=in.nextInt();
        for(int test=0;test<t;test++){
            int n=in.nextInt();
            int k=in.nextInt();
            int w[]=new int[n];
            int b[]=new int[n];
            int heap1[]=new int[n];
            int heap2[]=new int[n];
            long worktotal=0;
            long work=0;
            long bore=0;

            for(int i=0;i<n;i++){

                w[i]=in.nextInt();
                worktotal+=w[i];

            }
            for(int i=0;i<n;i++){
               b[i]=in.nextInt();

            }
            for(int i=0;i<n;i++){
                insert(heap1,heap2,i,w[i],b[i]);
            }
            int size=n;
            for(int i=0;i<k;i++){
                int temp2=heap2[0];
                int num=remove(heap1,heap2,size);
                size--;

                double temp=Math.ceil((double)num/2);
                bore+=temp*temp2;
                work+=temp;
                insert(heap1,heap2,size,num-(int)temp,temp2);



            }
            System.out.println(worktotal-work+" "+bore);

        }
    }
}