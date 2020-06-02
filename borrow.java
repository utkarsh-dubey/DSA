
import java.util.Scanner;

public class Main {

    public static class Node
    {
        public int data;
        public Node next;
        public Node prev;
        Node(int n,Node ne,Node pr)
        {
            data=n;
            next=ne;
            prev=pr;
        }
    };

    public static void main(String[] args) {
        Node curr=new Node(1, null,null);
        Scanner input=new Scanner(System.in);
        int q=input.nextInt();
        Node head=curr;
        int count=1;
        Node mid1=curr;
        Node mid=curr;
        Node rear=curr;
        for(int i=0;i<q;i++)
        {
            int read=input.nextInt();
            if(read==2)
            {
                //Delete first
                head = head.next;
                count-=1;
                if(count%2==0)
                {
                    mid1=mid1.next;
                }
                else
                {
                    mid=mid.next;
                }
            }
            else if(read==3)
            {
                //Print the middle element
                if(count%2==0)
                {
                    System.out.print(mid.data);
                    System.out.print(" ");
                    System.out.print(mid1.data);
                    System.out.println("");
                }
                else
                {
                    System.out.println(mid.data);
                }
            }
            else
            {
                //Insert at End
                count+=1;
                int read1=input.nextInt();
                Node ne=new Node(read1,null,null);
                ne.prev=rear;
                rear.next=ne;
                rear=ne;
                if(count%2==1)
                {
                    mid=mid.next;
                }
                else
                {
                    mid1=mid1.next;
                }
            }

        }
    }
}