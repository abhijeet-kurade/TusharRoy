package BinarySearch;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {

    }

    public static double medianOfTwoSortedArrays(int[] arr1, int[] arr2){
        int m = arr1.length, n = arr2.length;
        if(m>n) return medianOfTwoSortedArrays(arr2, arr1);
        int left =0, right = m,half = (m+n+1)/2;

        while(left<=right){
            int mid1 = left + (right-left) /2;
            int mid2 = half - mid1;

            int l1 = mid1==0?Integer.MIN_VALUE:arr1[mid1-1];
            int r1 = mid1==m?Integer.MAX_VALUE:arr1[mid1];
            int l2 = mid2==0?Integer.MIN_VALUE:arr2[mid2-1];
            int r2 = mid2==n?Integer.MAX_VALUE:arr2[mid2];

            if(l1<=r2 && l2<=r1){
                if((m+n)%2 == 0) return ( Math.max(l1,l2) + Math.min(r1,r2) )/2.0;
                return Math.max(l1,l2);
            }
            else if(l1>r2) right = mid1-1;
            else left = mid1+1;
        }

        return 0;
    }
}
