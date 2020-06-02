import java.io.*;

public class Main {
    static class Node{
        int count;
        boolean sorted;
        Node(int n,boolean s)
        {
            count=n;
            sorted=s;
        }
    }

    public static boolean check(int a[],int n,int index){
        for(int i=0;i<index;i++){
            if(a[i]==n){
                return false;
            }
        }
        return true;
    }
    public static Node delete(int a[],int n,int index,int count,boolean sorted){
        int i;
        for(i=0;i<index;i++){
            if(a[i]==n){
                a[i]=Integer.MAX_VALUE;
                count--;
                sorted=false;
                break;
            }
        }
        Node n1=new Node(count,sorted);
        return n1;
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
    public static int bin(int[] arr,int left,int right,int key){
        int mid = 0;
        while (left < right) {
            mid = (right + left) >> 1;
            if (arr[mid] == key) {
                while (mid + 1 < right && arr[mid + 1] == key)
                    mid++;
                break;
            }
            else if (arr[mid] > key)
                right = mid;

            else
                left = mid + 1;
        }
        while (mid > -1 && arr[mid] > key)
            mid--;

        return mid + 1;
    }
    public static int binarySearch(int arr[], int l, int r, int x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x)
                return mid;
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in=new BufferedReader((new InputStreamReader(System.in)));
        int n=Integer.parseInt(in.readLine());
        int a[]=new int[n];
        int index=0;
        int count=0;
        String k;
        String s[];
        boolean sorted=true;

        for(int i=0;i<n;i++){
            s=in.readLine().split(" ");
            k=s[0];
            if(k.compareTo("INSERT")==0) {
                int m = Integer.parseInt(s[1]);
                if (sorted) {
                    int k1=binarySearch(a,0,index-1,m);
                    if(k1==-1) {
                        a[index]=m;
                        index++;
                        count++;
                    }

                } else {
                    if (check(a, m, index)) {
                        a[index] = m;
                        index++;
                        count++;
                    }
                }
            }
            else if(k.compareTo("DELETE")==0){
                int m=Integer.parseInt(s[1]);
                Node n1=delete(a,m,index,count,sorted);
                count=n1.count;
                sorted=n1.sorted;
            }
            else if(k.compareTo("FIND")==0){
                int m=Integer.parseInt(s[1]);
                if(m>count){
                    System.out.println("-1");
                    continue;
                }
                if(!sorted) {
                    sort(a, 0, index - 1);
                }
                index=count;
                System.out.println(a[m-1]);
                sorted=true;
            }
            else if(k.compareTo("COUNT")==0){
                int m=Integer.parseInt(s[1]);
                if(!sorted) {
                    sort(a, 0, index - 1);
                }
                index=count;
                int flag;
                flag=bin(a,0,index,m);
                System.out.println(flag);
                sorted=true;
            }
        }

    }
}