package com.company;
import java.util.*;
import java.util.Scanner;
import java.lang.Math;
public class Main {


    public static class Node{
        ArrayList<Integer> connections=new ArrayList<Integer>();
        ArrayList<Integer> distance=new ArrayList<Integer>();
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
            return -1;
        }
        if((bank[start-1].connections.contains(one)&&bank[(int)one-1].connections.size()==1)||(bank[start-1].connections.contains(two)&&bank[(int)two-1].connections.size()==1)){
//            System.out.println("third");
            return -1;
        }
        if(bank[start-1].connections.contains(end)&&!((start==one||start==two)&&(end==one||end==two))){
//            System.out.println("second");
//            System.out.println("distance-"+distance);
            return distance+bank[start-1].distance.get(bank[start-1].connections.indexOf(end));
        }

        if(bank[start-1].connections.size()==1&&been.contains(bank[start-1].connections.get(0))){
//            System.out.println("fourth");
            return -1;
        }
        boolean flag=false;
        long temp=distance;
        for(int i=0;i<bank[start-1].connections.size();i++){
//            if((((start!=one)&&(bank[start-1].connections.get(i)==two))||((start!=two)&&(bank[start-1].connections.get(i)==one)))){
//                System.out.println("haan yahi dikaat de gya");
//            }
            if(!been.contains(bank[start-1].connections.get(i))&&!((((start==one)&&(bank[start-1].connections.get(i)==two))||((start==two)&&(bank[start-1].connections.get(i)==one))))){
//                System.out.println("bro-"+bank[start-1].connections.get(i));
                been.add(start);
                temp+=bank[start-1].distance.get(i);
//                System.out.println(temp);

                temp=checkdistance(bank,bank[start-1].connections.get(i),end,one,two,been,temp);
//                System.out.println(temp);
                if(temp!=0){

                    break;

                }

            }

        }
        return temp;
    }


    public static ArrayList<Integer> euler=new ArrayList<Integer>();
    public static ArrayList<Integer> newind=new ArrayList<Integer>();
    public static ArrayList<Integer> parent=new ArrayList<Integer>();

    public static void makeeuller(Node[] bank,int root,ArrayList parent,int current) {


        for (int i = 0; i < bank[root - 1].connections.size(); i++) {

            if (bank[root - 1].connections.get(i) == parent.get(parent.size() - 1) && bank[root - 1].connections.size() == 1) {
                euler.add(current);
                newind.set(root - 1, current);
                parent.remove(parent.size() - 1);
                return;
            }

        }

    }



    //    }
    public static void merge(double a[],double b[],int s,int mid,int end ){
        int a1=(mid-s)+1;
        int a2=(end-mid);
        double[] one=new double[a1];
        double[] one1=new double[a1];
        double[] two=new double[a2];
        double[] two2=new double[a2];
        for(int i=0;i<a1;i++){
            one[i]=a[s+i];
            one1[i]=b[s+i];
        }
        for(int i=0;i<a2;i++){
            two[i]=a[mid+i+1];
            two2[i]=b[mid+i+1];
        }
        int left=s;
        int i=0;
        int j=0;
        while(i<a1&&j<a2){
            if(one[i]<two[j]){
                a[left]=one[i];
                b[left]=one1[i];
                i++;
            }
            else{
                a[left]=two[j];
                b[left]=two2[j];
                j++;
            }
            left++;
        }
        while(i<a1){
            a[left]=one[i];
            b[left]=one1[i];
            i++;
            left++;
        }
        while(j<a2){
            a[left]=two[j];
            b[left]=two2[j];
            j++;
            left++;
        }
    }
    public static void sort(double a[],double b[],int s,int end){
        int mid=(s+end)/2;
        if(s<end) {
            sort(a, b,s, mid);
            sort(a, b,mid + 1, end);
            merge(a,b, s, mid, end);
        }
    }
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
    public static void disp(double a[],int size){
        for(int i=0;i<size;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

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
            bankers[(int)t[i][1]-1].distance.add(t[i][2]);
            bankers[(int)t[i][0]-1].distance.add(t[i][2]);

        }
//        System.out.println("dudu");

        log = (int)Math.ceil(Math.log(n) / Math.log(2));
        m = new int[n + 1][log + 1];
        g = new ArrayList[n + 1];
        l = new int[n + 1];

        for (int i = 0; i <= n; i++)
            Arrays.fill(m[i], -1);
//        System.out.println("dudu");

        for (int i = 0; i <=n; i++)
            g[i] = new ArrayList<>();

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

        dfs(e,e);

//        System.out.println(lca(4,8));


//              for(int i=0;i<n;i++){
//            System.out.print((i+1)+"-");
//            for(int j=0;j<bankers[i].connections.size();j++){
//                System.out.print(bankers[i].connections.get(j)+" ");
//            }
//            System.out.println();


        for(int k=0;k<q;k++){
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
                System.out.println("0");
            }
        }



    }
}
