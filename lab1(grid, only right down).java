

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

    public static int ut=0;
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner in = new Scanner(System.in);
        int n, m, x, y;
        Main z = new Main();
        n = in.nextInt();
        m = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();

        int a[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = in.nextInt();

            }
        }
        if(a[0][0]==0 || a[n-1][m-1]==0){
            System.out.print(0);

        }
        else {
            int count1 = 0, count2 = 0;
            count1 = z.calc(a, 0, 0, x - 1, y - 1);
            count2 = z.calc(a, x - 1, y - 1, n - 1, m - 1);
            System.out.print(count1 * count2);
        }

    }

        static int calc(int a[][],int point1,int point2,int end1,int end2){
            int a1=0;
            if(point1==end1 && point2==end2){
                return 1;
            }
            else if(a[point1][point2]==0){
                return 0;
            }
            if(point1<end1 && point2<end2){
                a1=calc(a,point1,point2+1,end1,end2)+calc(a,point1+1,point2,end1,end2);
                return a1;
            }
            else if(point1<end1){
                a1+=calc(a,point1+1,point2,end1,end2);
                return a1;
            }
            else if(point2<end2){
                a1+=calc(a,point1,point2+1,end1,end2);
                return a1;
            }
            else{
                return 0;
            }

        }

    }






