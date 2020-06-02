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

    static ArrayList<Integer>[] connections;
    static boolean checks[];
    static int distance1[],distance2[],parent[];

    public static void dfsone(int u,int v){

        parent[u]=v;
        if(checks[u]==true){
            distance2[u]=0;
        }
        for(int i=0;i<connections[u].size();i++){

            int x=connections[u].get(i);
            if(x!=v){
                dfsone(x,u);
                distance2[u]=Math.max(distance2[x]+1,distance2[u]);
            }

        }
    }

    public static void dfstwo(int u,int v){

        int minone=Integer.MIN_VALUE;
        int mintwo=Integer.MIN_VALUE;


        for(int i=0;i<connections[u].size();i++) {
            int x = connections[u].get(i);
            if (x != v) {
                if (minone <= distance2[x]) {
                    mintwo = minone;
                    minone = distance2[x];
                } else if (mintwo < distance2[x]) {
                    mintwo = distance2[x];

                }
            }
        }

        for(int i=0;i<connections[u].size();i++){
            int x=connections[u].get(i);
            if(x!=v){
                if(checks[x]){
                    distance1[x]=0;
                }
                distance1[x]=Math.max(distance1[x],distance1[u]+1);
                if(minone!=distance2[x]){
                    distance1[x]=Math.max(distance1[x],minone+2);
                }
                else{
                    distance1[x]=Math.max(distance1[x],mintwo+2);
                }
                dfstwo(x,u);
            }
        }


    }




    public static void main(String[] args) throws IOException {

        Reader.init(System.in);
        int n=Reader.nextInt();
        int h=Reader.nextInt();
        int x=Reader.nextInt();

        checks=new boolean[n+1];
        distance1=new int[n+1];
        distance2=new int[n+1];
        parent=new int[n+1];
        connections=new ArrayList[n+1];

        for(int i=0;i<=n;i++){
            connections[i]=new ArrayList<Integer>();
        }


        for(int i=0;i<n+1;i++){
            distance1[i]=Integer.MIN_VALUE;
            distance2[i]=Integer.MIN_VALUE;
            checks[i]=false;

        }
        for(int i=0;i<h;i++){
            int p=Reader.nextInt();
            checks[p]=true;
        }

        for(int i=0;i<n-1;i++){
            int u=Reader.nextInt();
            int v=Reader.nextInt();
            connections[u].add(v);
            connections[v].add(u);
        }

        dfsone(1,0);
        if(checks[1]){
            distance1[1]=0;
        }
        dfstwo(1,0);

        int finalcount=0;

        for(int i=1;i<=n;i++){
            if(distance1[i]<=x&&distance2[i]<=x){
                finalcount++;

            }
        }
        System.out.println(finalcount);





    }
}