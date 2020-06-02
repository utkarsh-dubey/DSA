import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class selfmaxheap {

    double[] arr = new double[100000];
    int len = 0;


    public static selfmaxheap decreaseanelement(selfmaxheap mh, int pos , double data){
        // we have a max heap already created.
        mh.arr[pos-1] = mh.arr[pos-1] - data;

        int flag = 0;
        while(flag == 0){
            int lc = 2*pos;
            int rc = (2*pos) + 1;
            if(rc <= mh.len){
                double m;
                int i;
                if(mh.arr[lc - 1] > mh.arr[rc-1]){
                    m = mh.arr[lc-1];
                    i = lc;
                }
                else{
                    m = mh.arr[rc -1];
                    i = rc;
                }

                if(mh.arr[pos-1] < m){
                    double temp = mh.arr[i-1];
                    mh.arr[i-1] = mh.arr[pos -1];
                    mh.arr[pos-1] = temp;
                    pos = i;
                }
                else
                    flag = 1;
            }
            else{
                if(lc <= mh.len){
                    if(mh.arr[pos-1] < mh.arr[lc-1]){
                        double temp = mh.arr[lc-1];
                        mh.arr[lc-1] = mh.arr[pos-1];
                        mh.arr[pos-1] = temp;
                        pos = lc;
                    }
                    else
                        flag = 1;
                }
                else
                    flag = 1;
            }
        }
        return mh;
    }

    /*
    for procedure of all functions,
    refer the handmade notes obviously made by me
    you lazy piece of shit, just go and see,
    it will hardly take 5 mins and you'll have a quick revision too.
    thank yourself.
     */

    public static selfmaxheap deleterootnode(selfmaxheap mh){
        int i = mh.len;
        int n = i-1;
        double temp;
        mh.arr[0] = mh.arr[i-1];
        mh.len --;
        // heapify from 1 to n now

        i = 1;
        int flag = 0;
        while(flag == 0) {
            int lc = 2 * i;
            int rc = (2 * i) + 1;
            if (rc <= n) {
                double m = Math.max(mh.arr[lc - 1], mh.arr[rc - 1]);
                int index;
                if (mh.arr[lc - 1] == m)
                    index = lc;
                else
                    index = rc;

                if (mh.arr[i - 1] < m) {
                    temp = mh.arr[index - 1];
                    mh.arr[index - 1] = mh.arr[i - 1];
                    mh.arr[i - 1] = temp;
                    i = index;
                } else
                    flag = 1;
            } else {
                if (lc <= n) {
                    if (mh.arr[i - 1] < mh.arr[lc - 1]) {
                        temp = mh.arr[lc - 1];
                        mh.arr[lc - 1] = mh.arr[i - 1];
                        mh.arr[i - 1] = temp;
                        i = lc;
                    } else
                        flag = 1;
                } else
                    flag = 1;
            }
        }

        return mh;
    }

    /*
    deleting root node n number of times will give us the sorted array,
    this sorting is called hep sort.
    nice. ;)
     */

    public static selfmaxheap insertheap(selfmaxheap mh, double data) {
        if (mh.len == 0) {
            mh.arr[0] = data;
            mh.len ++;
        }
        else {
            mh.len++;
            mh.arr[mh.len - 1] = data;
            int i = mh.len;
            int flag = 0;

            while(flag == 0){
                if(i == 1){
                    break;
                }
                if(mh.arr[(int)Math.floor(i/2) - 1] < mh.arr[i-1]){
                    double temp =  mh.arr[(int)Math.floor(i/2) - 1];
                    mh.arr[(int)Math.floor(i/2) - 1] = mh.arr[i-1];
                    mh.arr[i-1] = temp;
                }
                else
                    flag = 1;
                i = (int)Math.floor(i/2);
            }
        }
        return mh;
    }

    public static void main(String[] args) throws IOException {
        selfmaxheap mh = new selfmaxheap();
        Reader.init((System.in));
        int n = Reader.nextInt();
        int k = Reader.nextInt();
        double[] s = new double[n];
        double[] d = new double[n];
        for(int i = 0;i<n;i++)
            s[i] = Reader.nextDouble();
        for(int i=0;i<n;i++)
            d[i] = Reader.nextDouble();

        double sum = Math.pow(10,9);
        double[][] ratio = new double[n][2];
        for (int  i=0;i<n;i++){
            ratio[i][0] = d[i]/s[i];
            ratio[i][1] = i;
        }

        ratio = mergeSort(ratio);

//        for(int i=0;i<n;i++){
//            System.out.println(ratio[i][0] + " " + ratio[i][1]);
//        }

        int totalskill = 0;
        for(int i=0;i<k;i++){
            insertheap(mh,s[(int) ratio[i][1]]);
            totalskill += s[(int) ratio[i][1]];
        }
        double r = ratio[k-1][0];
        int ans = ((int)Math.ceil(r*totalskill));
        System.out.println(ans);
        double maxskill = mh.arr[0];
//        System.out.println(maxskill);
        for (int i=k;i<n;i++){
            if(s[(int) ratio[i][1]] < maxskill){
                totalskill -= mh.arr[0];
                totalskill += s[(int) ratio[i][1]];
                deleterootnode(mh);
                insertheap(mh,s[(int) ratio[i][1]]);
                maxskill = mh.arr[0];
                r = ratio[i][0];
                ans = Math.min(ans,(int)Math.ceil(r*totalskill));
            }
        }
        System.out.println(ans);


    }
    public static double[][] mergeSort(double[][] array) {
        if (array.length > 2) {
            double[][] left = new double[array.length/2][2];
            double[][] right = new double[(array.length)-left.length][2];

            for (int index = 0 ; index < array.length ; index++) {
                if (index < left.length) {
                    left[index][1] = array[index][1];
                    left[index][0] = array[index][0];

                } else {
                    right[-left.length + index][0] = array[index][0];
                    right[-left.length + index][1] = array[index][1];

                }

            }
            left = mergeSort(left);
            right = mergeSort(right);

            int lIndex = 0;
            int rIndex = 0;

            while (lIndex < left.length && rIndex < right.length) {

                if (left[lIndex][0] < right[rIndex][0]) {
                    array[lIndex + rIndex][0] = left[lIndex][0];
                    array[rIndex + lIndex][1] = left[lIndex][1];
                    lIndex++;

                } else if (left[lIndex][0] == right[rIndex][0]) {
                    if (left[lIndex][1] < right[rIndex][1]) {
                        array[lIndex + rIndex][0] = left[lIndex][0];
                        array[rIndex + lIndex][1] = left[lIndex][1];
                        lIndex++;

                    } else {
                        array[lIndex + rIndex][0] = right[rIndex][0];
                        array[rIndex + lIndex][1] = right[rIndex][1];
                        rIndex++;

                    }

                } else {
                    array[lIndex + rIndex][0] = right[rIndex][0];
                    array[rIndex + lIndex][1] = right[rIndex][1];
                    rIndex++;

                }

            }
            while (lIndex < left.length) {
                array[lIndex + rIndex][0] = left[lIndex][0];
                array[rIndex + lIndex][1] = left[lIndex][1];
                lIndex++;

            }
            while (rIndex < right.length) {
                array[lIndex + rIndex][0] = right[rIndex][0];
                array[rIndex + lIndex][1] = right[rIndex][1];
                rIndex++;

            }



        } else if (array.length == 2) {
            if (array[1][0] < array[0][0] || (array[1][0] == array[0][0] && array[1][1] < array[0][1])) {
                double temp1 = array[0][0];
                double temp2 = array[0][1];

                array[0][0] = array[1][0];
                array[0][1] = array[1][1];

                array[1][0] = temp1;
                array[1][1] = temp2;

            }
        }
        return array;

    }


    static class Reader {
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

}



