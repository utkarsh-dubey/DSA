
// ques 1

package com.company;
import java.util.Scanner;



public class Main{

    public static int fab(int n){
        if(n==1){
            return 0;
        }
        if(n==2){
            return 1;
        }
        if (n == 3) {
            return 1;


        }
        return fab(n-1)+fab(n-2)+fab(n-3);
    }
    public static void main(String[] args){

        Scanner in =new Scanner(System.in);
        int n=in.nextInt();

        System.out.print(fab(n));
    in.close();

    }
}



//ques 2

package com.company;
import java.util.Scanner;



public class Main{

    public static int check(int m,int n,int k){

        if(m==0&&n==0){
            return 1;
        }
        if(k==0){
            return 0;
        }


        return check(m-2,n-1,k-1)+check(m-1,n-2,k-1)+check(m+1,n-2,k-1)+check(m+2,n+1,k-1)+check(m+1,n+2,k-1)+check(m+2,n-1,k-1);












    }
    public static void main(String[] args){

        Scanner in =new Scanner(System.in);
        int m=in.nextInt();
        int n=in.nextInt();
        int k=in.nextInt();
        if(m>2*k || n>2*k){
            System.out.print("NO");
        }
        else {
            int temp=m+n-2;
            if(temp%3==0&&k>=temp/3){
                System.out.print("YES");

            }
            else{
               temp=check(m-1,n-1,k);
               if(temp>=1){
                   System.out.print("YES");

               }
               else{
                   System.out.print("NO");
               }
            }
        }

    in.close();

    }
}
