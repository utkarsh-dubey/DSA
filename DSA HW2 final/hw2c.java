package com.company;


import java.io.*;
import java.util.*;
import java.util.stream.StreamSupport;


class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    static void init(InputStream input) {
        reader = new BufferedReader(
                new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            tokenizer = new StringTokenizer(
                    reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}

public class Main {

    static boolean blocked[];
    static int number[];
    static int parent[];
    static int ek;
    static int doo;
    static int direction[];

    public static int getparent(int index){
        ek=1;
        int parentindex=index;

        if(parent[index]==-1){
//            ek*=direction[index];
            return index;

        }
        else{
            for(int i=0;parent[parentindex]!=-1;i++){
                ek*=direction[parentindex];
                parentindex=parent[parentindex];
            }
            return parentindex;
        }

    }


    
   public static void updatedirection(int index1, int index2, int[] direction) {
       System.out.println("index coming- " + index1 + " " + index2);

       if (direction[index1] == 0 && direction[index2] == 0) {
           if (index1 < index2) {
               direction[index1] = 1;
               direction[index2] = -1;
           } else {
               direction[index2] = 1;
               direction[index1] = -1;
           }
       } else if (direction[index1] == 0) {
           if (direction[index2] == 1) {
               direction[index1] = -1;
           } else {
               direction[index2] = 1;
           }
       } else if (direction[index2] == 0) {
           if (direction[index1] == 1) {
               direction[index2] = -1;
           } else {
               direction[index2] = 1;
           }


       } else {
           if (index1 < index2) {
               if (direction[index1] == 1) {
                   direction[index2] = -1;
               } else {
                   direction[index2] = 1;
               }
           } else {
               if (direction[index2] == 1) {
                   direction[index1] = -1;
               } else {
                   direction[index1] = 1;
               }
           }

       }


       int one=getparent(index1);
       int two=getparent(index2);
       int gotparent=0;
//        System.out.println("dekhte hai parent "+one+"  "+two);
       if(one!=two){
//            System.out.println("chal bhi raha hai");
           if(number[one]>=number[two]){
               parent[two]=one;
               gotparent=one;
               number[one]+=number[two];
               number[two]=0;

           }
           else{
               parent[one]=two;
               gotparent=two;
               number[two]+=number[one];
               number[one]=0;
           }

       }
       else{
           gotparent=one;
       }
       if(direction[gotparent]==0){
           direction[gotparent]=1;
           if(gotparent==one){
               if(number[gotparent]%2==0){
                   direction[index2]=-1;
               }
               else{
                   direction[index2]=1;
               }
           }
           else if(gotparent==two){
               if(number[gotparent]%2==0){
                   direction[index1]=-1;
               }
               else{
                   direction[index1]=1;
               }
           }


       }
       else{
           if(gotparent==one){
               if(number[gotparent]%2==0){
                   direction[index2]=-1;
               }
               else{
                   direction[index2]=1;
               }
           }
           else if(gotparent==two){
               if(number[gotparent]%2==0){
                   direction[index1]=-1;
               }
               else{
                   direction[index1]=1;
               }
           }
       }
   }

   public static void update(int mainparent){
       for(int i=0;i<blocked.length;i++){
           if(parent[i]==mainparent||i==mainparent){
               blocked[i]=true;
           }

       }

   }

   public static void checkblocked(int index1, int index2, int[] direction) {

       int mainparent=getparent(index1);

       int total=number[mainparent];
       System.out.println("this  is "+mainparent+"  "+total);
       if(total%2==0){
           if(direction[mainparent]==direction[index2]){
               update(mainparent);
           }

       }
       else{

           System.out.println("karna padta hai "+direction[mainparent]+"   "+direction[index2]);
           if(direction[mainparent]!=direction[index2]){
               System.out.println("chalra hai");
               update(mainparent);
           }

       }



   }
    public static void join(int direction1,int direction2,int index1,int index2,int parent1,int parent2){

        if(direction1!=direction2){
            if (number[parent1]>=number[parent2]) {
                parent[parent2] = parent1;
//            direction[parent2]=-direction[parent2];
//                    gotparent=one;
                number[parent1] += number[parent2];
                number[parent2] = 0;

            } else {
                parent[parent1] = parent2;
//            direction[parent1]=-direction[parent1];
//                    gotparent=two;
                number[parent2] += number[parent1];
                number[parent1] = 0;
            }
        }
        else {

            if(number[parent1]>=number[parent2]){
                parent[parent2]=parent1;
                direction[parent2]=-direction[parent2];
//                    gotparent=one;
                number[parent1]+=number[parent2];
                number[parent2]=0;

            }
            else{
                parent[parent1]=parent2;
                direction[parent1]=-direction[parent1];
//                    gotparent=two;
                number[parent2]+=number[parent1];
                number[parent1]=0;
            }

        }
    }

    public static void main(String[] args) throws IOException {

        Reader.init(System.in);
        int n = Reader.nextInt();
        int q = Reader.nextInt();
        blocked = new boolean[n];
        number=new int[n];
        parent=new int[n];
        direction = new int[n];
        long special[] = new long[n];

        for (int i = 0; i < n; i++) {
            special[i] = Reader.nextInt();
            direction[i] = 1;
            blocked[i] = false;
            parent[i]=-1;
            number[i]=1;
        }

        for (int Q = 0; Q < q; Q++) {

            int choice = Reader.nextInt();



            if (choice == 1) {

                int index = Reader.nextInt();
                long change = Reader.nextInt();
                special[index-1] = change;


            } else if (choice == 2) {

                int index1 = Reader.nextInt();
                int index2 = Reader.nextInt();
//                System.out.println(index2);
                int parent1=getparent(index1-1);
                int direction1=ek;
                int parent2=getparent(index2-1);
                int direction2=ek;
//                if(index1==11&&index2==30){
//                    System.out.println("okay--"+parent1+"   "+parent2);
//                    System.out.println("direction--"+direction1+"  "+direction2);
//                }
                if (!blocked[parent1] && parent1==parent2) {



                    if(direction1==direction2){

                        blocked[parent1]=true;
                    }
//                    updatedirection(index1 - 1, index2 - 1, direction);
//
//                    checkblocked(index1 - 1, index2 - 1, direction);

                } else if(parent1!=parent2) {

                    join(direction1,direction2,index1-1,index2-1,parent1,parent2);
                    if(blocked[parent1]==true||blocked[parent2]==true){
                        blocked[parent1]=true;
                        blocked[parent2]=true;
                    }
                }
//                for (int i = 0; i < n; i++) {
//                    System.out.print(direction[i] + "  ");
//
//                }
//
//                System.out.println("parents");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(parent[i] + "  ");
//
//                }
//
//                System.out.println("number");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(number[i] + "  ");
//
//                }
//
//                if(index1==3&&index2==19) {
//                    System.out.println("check check-"+parent1+"  "+parent2);
//                    for (int i = 0; i < n; i++) {
//                    System.out.print(direction[i] + "  ");
//
//                }
//
//                System.out.println("parents");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(parent[i] + "  ");
//
//                }
//
//                System.out.println("number");
//                for (int i = 0; i < n; i++) {
//                    System.out.print(number[i] + "  ");
//
//                }
//
//                    System.out.println("blocks");
//                    for (int i = 0; i < n; i++) {
//                        System.out.print(blocked[i] + "  ");
//
//                    }
//
//                    System.out.println();
//                }

            } else {
                int index1=Reader.nextInt();
                int index2=Reader.nextInt();
                long velone=Reader.nextInt();
                int parent1=getparent(index1-1);
                int direction1=ek;
                int parent2=getparent((index2-1));
                int direction2=ek;
                if(blocked[parent1]||blocked[parent2]||parent1!=parent2){
                    System.out.println(0);
                }
                else{



//                    System.out.println("timepass");

                    long up=special[index1-1]*velone;
                    long down=special[index2-1];

                    long finali =gcd(up,down);
                    if(direction1!=direction2){
                        up=-up;

                    }
                    System.out.println(up/finali+"/"+down/finali);



                }




            }


        }

    }
    public static long gcd(long a,long b){
        if(a==0) {
            return b;
        }
        return gcd(b%a,a);
    }
}


