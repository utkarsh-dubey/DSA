package com.company;
import java.util.Scanner;
import java.lang.Math;
public class Main {



    public static double removem(double a[],int size){
        double top=a[0];
        a[0]=a[size-1];
        heapifydownm(a,size-1);
        return top;
    }
    public static void insertm(double a[],int size,double num){
        a[size]=num;
        heapifyupm(a,size+1);

    }
    public static void heapifyupm(double a[],int size){
        int index=size-1;
        while((index-1)/2>=0 && a[(index-1)/2]<a[index]){
            swap(a,(index-1)/2,index);
            index=(index-1)/2;
        }

    }

    public static void heapifydownm(double a[],int size){
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

    public static void swap(double a[],int index1,int index2){
        double temp=a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
    public static void swapd(double a[],int index1,int index2){
        double temp=a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
    public static double remove(double a[],double second[],int size){
        double top=a[0];
        double top2=second[0];
        a[0]=a[size-1];
        second[0]=second[size-1];
        heapifydown(a,second,size-1);
        return top;
    }
    public static void insert(double a[],double second[],int size,double num,double num2){
        a[size]=num;
        second[size]=num2;
        heapifyup(a,second,size+1);

    }
    public static void heapifyup(double a[],double second[],int size){
        int index=size-1;

        while((index-1)/2>=0 && a[(index-1)/2]>a[index]){
            swapd(a,(index-1)/2,index);
            swap(second,(index-1)/2,index);
            index=(index-1)/2;
        }

    }

    public static void heapifydown(double a[],double second[],int size){
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
                swapd(a,index,index1);
                swap(second,index,index1);
            }
            index=index1;
        }

    }
//    public static void disp(double a[],int size){
//        for(int i=0;i<size;i++){
//            System.out.prdouble(a[i]+" ");
//        }
//        System.out.prdoubleln();
//    }
//    public static double redo(double kskills[],double kpays[],double k){
//        double totalpay=kpays[k]*kskills[0];
//        totalpay/=kskills[k];
//        kpays[0]=(double)totalpay;
//        double lowreference=kskills[0];
//        double temp=0;
//        for(int i=1;i<=k;i++){
//            temp=kpays[0]*kskills[i];
//            temp/=lowreference;
////            System.out.prdoubleln(temp);
//            if(temp>=kpays[i]){
//                totalpay+=temp;
//
//            }
//            else{
//                totalpay=redo(kskills,kpays,i);
//            }
//        }
//        return totalpay;
//    }

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

        int n=in.nextInt();
        int k=in.nextInt();
        double skills[]=new double[n];
        double pay[]=new double[n];
        for(int i=0;i<n;i++){
            skills[i]=in.nextInt();

        }
        for(int i=0;i<n;i++){
            pay[i]=in.nextInt();

        }
        double ratio[]=new double[n];
        double heapskills[]=new double[n];
        double heapratio[]=new double[n];
        for(int i=0;i<n;i++){
            ratio[i]=(pay[i])/skills[i];
            insert(heapratio,heapskills,i,ratio[i],skills[i]);
        }
        double number=0;
        double skill=0;
        double skillsheap[]=new double[k];
        double skillsum=0;
        for(int i=0;i<k;i++){
            skill=heapskills[0];
            skillsum+=skill;
            insertm(skillsheap,i,skill);
            number=remove(heapratio,heapskills,n-i);

        }
        int size=n-k;
//        System.out.prdoubleln(number);
//        System.out.prdoubleln(skillsum);
        double ans=Math.ceil(number*skillsum);
//        System.out.prdoubleln(ans);
        for(int i=k;i<n;i++){
            skill=heapskills[0];
//            System.out.prdoubleln(skill);
            double temp=removem(skillsheap,k);

            number=remove(heapratio,heapskills,size);
            size--;
            double ans2=Math.ceil((skillsum+skill-temp)*number);
            if(skill<temp){
                skillsum=(skillsum+skill-temp);
                if(ans2<ans) {
                    ans = ans2;
                }
                insertm(skillsheap,k-1,skill);

            }
            else{
                insertm(skillsheap,k-1,temp);
            }


        }
        System.out.println((int)(ans));
    }
}