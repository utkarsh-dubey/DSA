package com.company;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.*;

class Node{
    int data;
    double ch;
    Node left;
    Node right;
}

public class Main {
    public static boolean arraysorted(double store[][]){

        for(int i=1;i<store.length;i++){
            if(store[i-1][1]>store[i][1]){
                return false;
            }
        }
        return true;
    }
    public static void dispbst(Node head){
        if(head.left!=null){
            dispbst(head.left);
        }
        System.out.println(head.ch);
        if(head.right!=null){
            dispbst(head.right);
        }
    }
    public static double find(double[][] store, int num){

        int at=0;
        double ch=0;
        for(int i=0;i<store.length;i++){
            if(store[i][1]==num){
                ch=store[i][0];
                at=i;

            }
        }

        store[at][1] = Integer.MIN_VALUE;

        if(ch==0){
            return -1;
        }
        return ch;
    }
    public static Node getleft(Node every[],int value,int size,double ch){

        Node re=null;
        for(int i=0;i<size;i++){
            if(every[i]!=null&&every[i].data==value){
                re=every[i];
            }
        }
        if(re==null){
            re=new Node();
            re.data=value;
            re.ch=ch;
            return re;
        }
        return re;
    }

    public static Node getright(Node every[],int value,int size,double ch){

        Node re=null;
        for(int i=0;i<size;i++){
            if(every[i]!=null&&every[i].data==value){
                re=every[i];
            }
        }
        if(re==null){
            re=new Node();
            re.data=value;
            re.ch=ch;
            return re;
        }
        return re;
    }
    public static String getcode(Node root, String s,double ch)
    {


        if (root!=null &&root.left==null&&root.right==null&& root.ch>=97&&root.ch==ch) {
//            System.out.println("upper");
            return s;
        }
        if(root.left!=null && root.right!=null) {
//            System.out.println("second");
            return getcode(root.left, s + "0", ch) + getcode(root.right, s + "1", ch);
        }
        else if(root.left!=null){
//            System.out.println("third");
            return getcode(root.left, s + "0", ch);
        }
       else if(root.right!=null){
//            System.out.println("fourth");
           return getcode(root.right,s+"1",ch);
        }
       else{
//            System.out.println("fifth");
           return "";
        }
    }
    public static char present(double store[][],double code){
        for(int i=0;i<store.length;i++){
            if(store[i][1]==code)
            { return (char)store[i][0];}
        }
        return '2';
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);

//        Dictionary store=new Hashtable();


        int t=in.nextInt();

        for(int T=0;T<t;T++){
            PriorityQueue<Integer> a=new PriorityQueue<Integer>(Collections.reverseOrder());
            int n=in.nextInt();

            Node[] every=new Node[2*n];

            int size=0;
            double store[][]=new double[n][2];
            for(int i=0;i<n;i++){
                String hm=in.next();
                char ch=hm.charAt(0);
                int num=in.nextInt();
                store[i][0]=(int)ch;
                store[i][1]=num;
                a.add(num);
            }
            String msg=in.next();
            boolean he=arraysorted(store);
            Node root=null;
            double tempo=store[0][0];
            while(a.size()>1){
                int rightvalue=a.poll();
                double rightch=find(store,rightvalue);
                Node right=getright(every,rightvalue,size,rightch);

                int leftvalue=a.poll();
                double leftch=0;

                    leftch = find(store, leftvalue);

//                System.out.println(leftch);
                Node left=getleft(every,leftvalue,size,leftch);


                Node temp=new Node();

                temp.data=left.data+right.data;
                temp.ch=-1;
                temp.left=left;
                temp.right=right;

                root=temp;
                every[size]=temp;
                size++;

                a.add(leftvalue+rightvalue);


            }
//            dispbst(root);
            if(he) {
                root.left.ch = tempo;
            }
            if(n!=1) {
                for (int i = 0; i < n; i++) {

//                    System.out.println("dikaat");
//                    System.out.println((char) store[i][0]);
                    store[i][1] = Double.parseDouble(getcode(root, "",  store[i][0]));
                }
            }
            else{
                store[0][1]=0;
            }
            int prev=0;

            for(int i=1;i<msg.length()+1;i++){

                char prin=present(store,Double.parseDouble(msg.substring(prev,i)));
                if(prin!='2'){
                    System.out.print(prin);
                    prev=i;
                }

            }
            System.out.println();

        }

    }
}