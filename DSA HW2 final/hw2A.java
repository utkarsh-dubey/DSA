package com.company;
import java.io.*;
import java.util.*;

import static java.lang.System.exit;

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

    public static int getnextmax(ArrayList<ArrayList<Integer>> connect,ArrayList<Integer> opened){

        int maxsize=-1;
        int maxsizeindex=-1;
        for(int i=0;i<connect.size();i++){
            if(connect.get(i).size()>maxsize&&!opened.contains(i+1)){
                maxsize=connect.get(i).size();
                maxsizeindex=i;

            }
        }
        return maxsizeindex;
    }

    public static int getnextminday(ArrayList<ArrayList<Integer>> connect,int maxsizeindex,ArrayList<Integer> opened,int minday,int time[][],ArrayList<Integer> last){
        int nextminday=Integer.MAX_VALUE;


        for(int i=0;i<opened.size();i++){
            for(int j=0;j<=connect.get(maxsizeindex).size();j++){
                if(j==0){
//                    System.out.println("idhar?");
                    if(nextminday>time[opened.get(i)-1][maxsizeindex] &&time[opened.get(i)-1][maxsizeindex]!=0 ){
                        nextminday=time[opened.get(i)-1][maxsizeindex];
                    }
                    if(time[opened.get(i)-1][maxsizeindex]<=minday && ! last.contains(opened.get(i))){
                        nextminday=0;
                        break;

                    }

                }
                else{
//                    System.out.println("idhar??");
                    if(nextminday>time[opened.get(i)-1][connect.get(maxsizeindex).get(j-1)-1] && time[opened.get(i)-1][connect.get(maxsizeindex).get(j-1)-1]!=0){
                        nextminday=time[opened.get(i)-1][connect.get(maxsizeindex).get(j-1)-1];
                    }

                }


            }
            if(nextminday==0){
                break;
            }

        }






        return nextminday;


    }



    public static void main(String[] args) throws IOException {

        Reader.init(System.in);
        
       int test=Reader.nextInt();

       for(int T=0;T<test;T++){

           int n=Reader.nextInt();
           int l=Reader.nextInt();
           int k=Reader.nextInt();


           ArrayList<ArrayList<Integer>> connect=new ArrayList<>(n);
//           ArrayList<ArrayList<ArrayList<Integer>>> time=new ArrayList<>();
            for(int i=0;i<n;i++){
                ArrayList<Integer> temp=new ArrayList<>();
//                ArrayList<ArrayList<Integer>> temp2=new ArrayList<>();
//                temp2.add((temp));
                connect.add(temp);
            }
            int time[][]=new int[n][n];
            int maxsize=-1;
            int maxsizeindex=-1;
           for(int i=0;i<l;i++){
               int s=Reader.nextInt();
               int t=Reader.nextInt();
               connect.get(s-1).add(t);
               connect.get(t-1).add(s);
//               if(connect.get(s-1).size()>maxsize){
//                   maxsizeindex=s-1;
//                   maxsize=connect.get(s-1).size();
//               }
//               if(connect.get(t-1).size()>maxsize){
//                   maxsizeindex=t-1;
//                   maxsize=connect.get(t-1).size();
//               }
           }
//           if(maxsize==-1){
//               maxsize=1;
//               maxsizeindex=0;
//           }
           maxsize=connect.get(0).size();
           maxsizeindex=0;
           for(int i=0;i<k;i++){
               int u=Reader.nextInt();
               int v=Reader.nextInt();
               int d=Reader.nextInt();
//               System.out.println(u-1);
//               System.out.println(v-1);
               time[u-1][v-1]=d;
               time[v-1][u-1]=d;
           }
            ArrayList<Integer> visited=new ArrayList<>();
            ArrayList<Integer> opened=new ArrayList<>();

           int mindays=0;
//           System.out.println("idhar?");
           while(true){
               ArrayList<Integer> last=new ArrayList<>();
//               System.out.println(maxsizeindex);
               opened.add(maxsizeindex+1);
               last.add(maxsizeindex+1);
//               System.out.print("nahichalra");
//               System.out.println(maxsizeindex);
               for(int i=0;i<connect.get(maxsizeindex).size();i++){
                   opened.add(connect.get(maxsizeindex).get(i));
                   last.add(connect.get(maxsizeindex).get(i));
               }
               if(opened.size()==n){
                   break;
               }
               maxsizeindex=getnextmax(connect,opened);

//               if(maxsizeindex<n-1) {
//                   maxsizeindex += 1;
//               }
//               System.out.println(maxsizeindex);
               mindays+=getnextminday(connect,maxsizeindex,opened,mindays,time,last);


           }

            System.out.println(mindays);


       }



    }
} 