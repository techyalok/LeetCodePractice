//LeetCode Link: https://leetcode.com/problems/two-sum/
package Array;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] r= new int[2];
        for(int num=0; num<nums.length; num++){
            for(int num1=num+1; num1<nums.length; num1++){
                if(target == nums[num]+nums[num1]){
                    r[0]=num;
                    r[1]=num1;
                    return r;
                }
            }
        }
        return r;
    }

    public static void main(String[] args){
      int[] nums={2,11,7,15};
      int target=9;
      int[] result = twoSum(nums, target);
      System.out.println(result[0]+" "+result[1]);
    }
}
