package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WeightedJobScheduling {
    public static void main(String[] args) {


        int[][] jobs = {
                {4,6,5},
                {6,7,4},
                {1,3,5},
                {2,5,6},
                {7,9,4},
                {5,8,11}
        };
        //System.out.println(weightedJobScheduling(jobs));
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
