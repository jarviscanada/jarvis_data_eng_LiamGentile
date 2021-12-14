/*
Time Complexity O(n)
Leveraging a Set (won't add duplicates)  
so I can simply compare the length of the input array to the size of the new set
*/

class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            set.add(num);
        }
        if (nums.length > set.size()) {
            return true;        
        }
        else return false;  
    }
}
