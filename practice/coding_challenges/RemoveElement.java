/*
time complexity: O(n)

solved using two pointers (i and j)
i is a slow runner that tracks the final number of elements
j is a fast runner that we use to iterate over the list
*/
class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
               nums[i] = nums[j]; 
               i++; 
            } 
        } return i;    
    }
}
