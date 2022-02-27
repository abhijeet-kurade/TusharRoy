package DynamicProgramming;

import java.util.*;

public class WeightedJobScheduling {
    public static void main(String[] args) {

        //System.out.println(weightedJobScheduling(jobs));

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Node dm = new Node();
        Node prev = dm;

        for(int i=0; i<n; i++){
            int num = sc.nextInt();
            prev = push(prev, num);
        }

        Node curr = dm.next;
        while(curr  != null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
    }

    public static Node push(Node prev, int val){
        Node node = new Node();
        node.val = val;
        prev.next = node;
        return node;
    }

    static class Node{
        int val ;
        Node next;
    }


    public static int weightedJobScheduling(int[][] jobs){
        int n = jobs.length;
        Arrays.sort(jobs, (o1, o2)->{return o1[1]-o2[1];});
        int[] profit = new int[n];
        int maxProfit = 0;
        for(int i=0; i<n; i++){
            int maxPrev = 0;
            for(int j=i-1; j>=0; j--){
                if(jobs[i][0] >= jobs[j][1]) maxPrev = Math.max(maxPrev, profit[j]);
            }
            profit[i] = jobs[i][2] + maxPrev;
            maxProfit = Math.max(maxProfit, profit[i]);
        }
        return maxProfit;
    }


}
