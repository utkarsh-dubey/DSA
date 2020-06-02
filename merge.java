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
