package com.company;
//format of lca code is taken from gfg but is first understood and written by myself only

import java.util.*;
import java.util.Scanner;
import java.lang.Math;
public class Main {

    public static long dist=0;
    public static class Node{
        ArrayList<Integer> connections=new ArrayList<Integer>();
        ArrayList<Long> distance=new ArrayList<Long>();
        int a;
        Node(){
            a=0;
        }
    }
    public static Node[] bankers;

    public static int m[][];
    public static int l[];
    public static int log;
    public static ArrayList<Integer> g[];
   public static void dfs(int u, int p)
    {
        m[u][0] = p;
        for (int i = 1; i <= log; i++) {
            m[u][i] = m[m[u][i - 1]][i - 1];
        }
        for (int v=0;v<g[u].size(); v++) {
            if (g[u].get(v) != p) {
                l[g[u].get(v)] = l[u] + 1;
                dfs(g[u].get(v), u);
            }
        }
    }
    public static int lca(int u, int v)
    {
        if (l[u] < l[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int i=log;
        while(i>=0){
            if ((l[u] - (int)Math.pow(2, i)) >= l[v])
                u = m[u][i];
            i--;
        }
        if (u == v) {
            return u;
        }
        i=log;
        while(i>=0){
            if (m[u][i] != m[v][i]) {
                u = m[u][i];
                v = m[v][i];
            }
            i--;
        }
        return m[u][0];
    }


    public static ArrayList<Integer> euler=new ArrayList<Integer>();
    public static ArrayList<Integer> newind=new ArrayList<Integer>();
    public static ArrayList<Integer> parent=new ArrayList<Integer>();






    public static boolean checkescape(int one,int two,int end,int start){
        if(start==end){
            return true;
        }
//        System.out.println(one);
//        System.out.println(two);

        int first=lca(one,two);

//        System.out.println(first);

        if(first==one){
            int tempo=two;
            two=one;
            one=tempo;
        }
//        System.out.println(one);
//        System.out.println(two);

        int second=lca(one,start);

        if(second==one){
            return false;
        }
        return true;
    }
    public static boolean checkescapetemp(Node[] bank,int start,int end,long one,long two,ArrayList been){

//        System.out.println("start-"+start);
        if(bank[start-1].connections.isEmpty()){
//            System.out.println("first");
            return false;
        }
        if((bank[start-1].connections.contains(one)&&bank[(int)one-1].connections.size()==1)||(bank[start-1].connections.contains(two)&&bank[(int)two-1].connections.size()==1)){
//            System.out.println("third");
            return false;
        }
        if(bank[start-1].connections.contains(end)&&!(start==one||start==two)){
//            System.out.println("second");
            return true;
        }

        if(bank[start-1].connections.size()==1&&been.contains(bank[start-1].connections.get(0))){
//            System.out.println("fourth");
            return false;
        }
        boolean flag=false;
        for(int i=0;i<bank[start-1].connections.size();i++){
//            if((((start!=one)&&(bank[start-1].connections.get(i)==two))||((start!=two)&&(bank[start-1].connections.get(i)==one)))){
//                System.out.println("haan yahi dikaat de gya");
//            }
            if(!been.contains(bank[start-1].connections.get(i))&&!((((start==one)&&(bank[start-1].connections.get(i)==two))||((start==two)&&(bank[start-1].connections.get(i)==one))))){
//                System.out.println("bro-"+bank[start-1].connections.get(i));
                been.add(start);
                boolean temp=checkescapetemp(bank,bank[start-1].connections.get(i),end,one,two,been);
                if(temp){
                    flag=true;
                    break;

                }
                flag=flag||temp;
            }

        }
        return flag;
    }


    public static long checkdistance(Node[] bank,int start,int end,long one,long two,ArrayList been,long distance){

//        System.out.println("start-"+start);
//        if(distance==-1){
//            return 0;
//        }
        if(bank[start-1].connections.isEmpty()){
//            System.out.println("first");
            return -(long)Math.pow(10,30);
        }
//        if((bank[start-1].connections.contains(one)&&bank[(int)one-1].connections.size()==1)||(bank[start-1].connections.contains(two)&&bank[(int)two-1].connections.size()==1)){
////            System.out.println("third");
//            return -1;
//        }
        if(bank[start-1].connections.contains(end)){
//            System.out.println("second");
//            System.out.println("distance-"+distance);
            return distance+bank[start-1].distance.get(bank[start-1].connections.indexOf(end));
        }

        if(bank[start-1].connections.size()==1&&been.contains(bank[start-1].connections.get(0))){
//            System.out.println("fourth");
            return -(long)Math.pow(10,30);
        }
        boolean flag=false;
        long temp=distance;
        for(int i=0;i<bank[start-1].connections.size();i++){
//            if((((start!=one)&&(bank[start-1].connections.get(i)==two))||((start!=two)&&(bank[start-1].connections.get(i)==one)))){
//                System.out.println("haan yahi dikaat de gya");
//            }
            if(!been.contains(bank[start-1].connections.get(i))){
//                System.out.println("bro-"+bank[start-1].connections.get(i));
                been.add(start);
                temp+=bank[start-1].distance.get(i);
//                System.out.println(temp);

                temp=checkdistance(bank,bank[start-1].connections.get(i),end,one,two,been,temp);
//                System.out.println(temp);
                if(temp>0){

                    break;

                }

            }

        }
        return temp;
    }


    public static void getdist(Node[] bank,ArrayList<Long> dist,int one,int two,int start,int p,long l){
        if(bank[start-1].a==1){
            dist.add(l);
        }
        for(int k=0;k<bank[start-1].connections.size();k++){
            if(bank[start-1].connections.get(k)!=p && check(one,two,bank[start-1].connections.get(k),start)) {
                getdist(bank, dist, one, two, bank[start - 1].connections.get(k), start, l + bank[start - 1].distance.get(k));
            }
        }
    }





    public static boolean check(int one,int two,int start,int end) {
        if(!(one!=start||two!=end)||!(one!=end||two!=start)) {
            return false;
        }
        return true;
    }   


    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        Node[] bankers=new Node[n];
        for(int i=0;i<n;i++){
            bankers[i]=new Node();
        }


        int s=in.nextInt();
        int q=in.nextInt();
        int e=in.nextInt();
        int t[][]=new int[n-1][3];
        for(int i=0;i<n-1;i++){
            t[i][0]=in.nextInt();
            t[i][1]=in.nextInt();
            t[i][2]=in.nextInt();
            bankers[(int)t[i][0]-1].connections.add((int)t[i][1]);
            bankers[(int)t[i][1]-1].connections.add((int)t[i][0]);
            bankers[(int)t[i][1]-1].distance.add((long)t[i][2]);
            bankers[(int)t[i][0]-1].distance.add((long)t[i][2]);

        }
//        System.out.println("dudu");

        log = (int)Math.ceil(Math.log(n) / Math.log(2));
        m = new int[n + 1][log + 1];
        g = new ArrayList[n + 1];
        l = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(m[i], -1);
        }
//        System.out.println("dudu");

        for (int i = 0; i <=n; i++) {
            g[i] = new ArrayList<>();
        }

//        System.out.println("dudu");
        int safe[]=new int[s];
        for(int i=0;i<s;i++) {
            safe[i] = in.nextInt();
            bankers[safe[i]-1].a=1;

        }
//        for(int i=0;i<n;i++){
//            System.out.print((i+1)+"-");
//            for(int j=0;j<bankers[i].connections.size();j++){
//                System.out.print(bankers[i].connections.get(j)+" ");
//            }
//            System.out.println();
//        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<bankers[i-1].connections.size();j++){
                g[i].add(bankers[i-1].connections.get(j));
            }
        }



//        System.out.println(lca(4,8));


//              for(int i=0;i<n;i++){
//            System.out.print((i+1)+"-");
//            for(int j=0;j<bankers[i].connections.size();j++){
//                System.out.print(bankers[i].connections.get(j)+" ");
//            }
//            System.out.println();


        for(int k=0;k<q;k++){
            dfs(e,e);
            int i=in.nextInt();
            int r=in.nextInt();
            int blocked1=t[i-1][0];
            int blocked2=t[i-1][1];
            if(blocked1>blocked2){
                int temp=blocked1;
                blocked1=blocked2;
                blocked2=temp;
            }
            if(checkescape(blocked1,blocked2,e,r)){
                System.out.println("escaped");
            }
            else{
                int flag=0;
                ArrayList<Long> dist=new ArrayList<Long>();
                long tempdist=(long)Math.pow(10,30);
//                for(int z=0;z<s;z++){
//
//                    if(safe[z]==r){
//                        System.out.println("0");
//                        flag=1;
//                        break;
//                    }
                    long len=0;
                    getdist(bankers,dist,blocked1,blocked2,r,-1,len);
//                    dfs(safe[z],safe[z]);
//                    if(checkescape(blocked1,blocked2,safe[z],r)){
//                        dfs(e,e);

                    if(dist.isEmpty()) {
                        System.out.println("oo");
                    }
                    else {
                        long lowest=(long)Math.pow(10,30);
//                        System.out.println(dist.get(0));
                        for(int z=0;z<dist.size();z++) {
                            if(dist.get(z)<lowest) {
//                                System.out.println("boom boom");
                                lowest=dist.get(z);
                            }

                        }
                        System.out.println(lowest);
                    }



//                        if(dist!=0){
//                            if(dist<tempdist){
//                                tempdist=dist;
//                            }
//                            flag=1;

                        }

                    }



//                if(flag==1){
//                    System.out.println(tempdist);
//                }
//                else{
//                    System.out.println("oo");
//                }
            }
            }

