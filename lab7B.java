import java.io.*;

public class Main {

    public static boolean check(int a[],int n,int index){
        for(int i=0;i<index;i++){
            if(a[i]==n){
                return false;
            }
        }
        return true;
    }
    public static int delete(int a[],int n,int index,int count){
        int flag=0;
        int i;
        for(i=0;i<index;i++){
            if(a[i]==n){
                a[i]=Integer.MAX_VALUE;
                flag=1;
                count--;
                break;
            }
        }
        return count;
    }
    public static void merge(int a[],int s,int mid,int end ){
        int a1=(mid-s)+1;
        int a2=(end-mid);
        int[] one=new int[a1];
        int[] two=new int[a2];
        for(int i=0;i<a1;i++){
            one[i]=a[s+i];
        }
        for(int i=0;i<a2;i++){
            two[i]=a[mid+i+1];
        }
        int left=s;
        int i=0;
        int j=0;
        while(i<a1&&j<a2){
            if(one[i]<two[j]){
                a[left]=one[i];
                i++;
            }
            else{
                a[left]=two[j];
                j++;
            }
            left++;
        }
        while(i<a1){
            a[left]=one[i];
            i++;
            left++;
        }
        while(j<a2){
            a[left]=two[j];
            j++;
            left++;
        }
    }
    public static void sort(int a[],int s,int end){
        int mid=(s+end)/2;
        if(s<end) {
            sort(a, s, mid);
            sort(a, mid + 1, end);
            merge(a, s, mid, end);
        }
    }
    public static int bin(int low,int high,int[] t,int num){

        if(high>=low){
            int mid=(int)(high+low)/2;


            if(t[mid]==num){
//
                return mid;
            }
            else if(t[mid]>num){
//
                return bin(low, mid-1,t,num );
            }
            else{
                return bin(mid+1,high,t,num);
            }
        }
        else {
            return -1;
        }
    }
    public static int brute(int a[],int n,int index){
        int i;
        for(i=0;i<index;i++){
            if(a[i]>n){
                break;
            }
        }
        return i;
    }
    public static void disp(int a[],int index){
        for(int i=0;i<index;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) throws IOException {

//        Scanner in = new Scanner(System.in);

        BufferedReader in=new BufferedReader((new InputStreamReader(System.in)));
        int n=Integer.parseInt(in.readLine());
        int a[]=new int[n];
        int index=0;
        int count=0;
        String k;
        String s[];

        for(int i=0;i<n;i++){
            s=in.readLine().split(" ");
            k=s[0];
            if(k.compareTo("INSERT")==0){
                int m=Integer.parseInt(s[1]);
                if(check(a,m,index)){
                    a[index]=m;
                    index++;
                    count++;
                }
            }
            else if(k.compareTo("DELETE")==0){
                int m=Integer.parseInt(s[1]);
                count=delete(a,m,index,count);
            }
            else if(k.compareTo("FIND")==0){
                int m=Integer.parseInt(s[1]);
                if(m>count){
                    System.out.println("-1");
                    continue;
                }
                sort(a,0,index-1);
                index=count;
//                disp(temp,index);
                System.out.println(a[m-1]);
            }
            else if(k.compareTo("COUNT")==0){
                int m=Integer.parseInt(s[1]);
//                disp(temp,index);
//                disp(a,index);
                sort(a,0,index-1);
                index=count;
//                disp(a,index);
//                disp(temp,index);
                int flag;
                flag=bin(0,index,a,m);
                if(flag!=-1){
                    if(flag==0){
                        System.out.println("0");
                    }
                    else {
                        System.out.println(flag);
                    }
                }
                else{
                    flag=brute(a,m,index);
                    if(flag==0){
                        System.out.println("0");
                    }
                    else {
                        System.out.println(flag);
                    }

                }

            }
        }

    }
}