package com.company;
import com.sun.org.apache.regexp.internal.RE;

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

    static long dist[];
    static int index;
    public static boolean bin(ArrayList<Integer> a,int low,int high,int t){

        if(high>=low){
            int mid=(int)(high+low)/2;

            if(a.get(mid)==t){
                index=mid;
                return true;
            }
            if(a.get(mid)>t){
//
                return bin(a,low,mid-1,t);
            }
            else {
//
                return bin(a,mid +1, high, t);
            }

        }
        else {
            index=-1;
            return false;
        }
    }
    public static class check implements Comparator<Integer>{

        public int compare(Integer a,Integer b){
            return Long.compare(dist[a],dist[b]);
        }

    }

    public static long getholdtime(ArrayList<Integer>[] timing,int timestamp,int at){
        long hold=0;

//        if(timing[at].contains((int)timestamp)){
//            int index=timing[at].indexOf((int)timestamp);
//
//        }
        while(bin(timing[at],0,timing[at].size()-1,(int)timestamp)){
            while(index<timing[at].size()&&timing[at].get(index)==timestamp) {
                hold++;
                timestamp++;
                index++;
            }
            break;
        }

        return hold;
    }

    public static void main(String[] args) throws IOException {

        Reader.init(System.in);
        int n=Reader.nextInt();
        int m=Reader.nextInt();

        ArrayList<ArrayList<pair>> connections = new ArrayList<ArrayList<pair>>();

        for(int i=0;i<n;i++){
            ArrayList<pair> temp=new ArrayList<pair>();
            connections.add(temp);
        }

        for(int i=0;i<m;i++){
            int a=Reader.nextInt();
            int b=Reader.nextInt();
            int c=Reader.nextInt();
            pair first=new pair(b-1,c);
            pair second=new pair(a-1,c);
            connections.get(a-1).add(first);
            connections.get(b-1).add(second);
        }
        ArrayList<Integer> timing[]=new ArrayList[n];

        for(int i=0;i<n;i++){

            timing[i]=new ArrayList<Integer>();
            int k=Reader.nextInt();
            for(int j=0;j<k;j++){
                int temp=Reader.nextInt();
                timing[i].add(temp);
            }
        }

        dist=new long[n];
        boolean visited[]=new boolean[n];
        PriorityQueue<Integer> next=new PriorityQueue<>(n,new check());

        for(int i=0;i<n;i++){
            dist[i]=Long.MAX_VALUE;
        }
        dist[0]=0;
        next.add(0);

        for(int dj=0;!next.isEmpty()&&!visited[n-1];dj++){

            int x;
            x=next.poll();
            if(visited[x]){
                continue;
            }

            visited[x]=true;

            long hold=getholdtime(timing,(int)dist[x],x);

            for(int i=0;i<connections.get(x).size();i++){
                pair temp=connections.get(x).get(i);
                long nextpossible=temp.getsecond()+dist[x]+hold;
                if(dist[temp.getfirst()]>nextpossible){
                    dist[temp.getfirst()]=nextpossible;
                }
                if(!visited[temp.getfirst()]){
                    next.add(temp.getfirst());
                }

            }

        }

        if(dist[n-1]!=Long.MAX_VALUE){
            System.out.println(dist[n-1]);
        }
        else{
            System.out.println(-1);
        }





    }

}

class pair {
    int a,b;
    public pair(int x, int y){
        a=x;
        b=y;
    }
    public int getfirst(){
        return this.a;
    }
    public int getsecond(){
        return this.b;
    }
}