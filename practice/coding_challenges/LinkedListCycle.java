/**
 Time Complexity O(n)
 Two pointers solution
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        
        // can't have a null head and have a cycle
        if (head == null) return false;
        
        // two pointers, start fast ahead one 
        ListNode slow = head;
        ListNode fast = head.next;
        
        
        while (fast != null && fast.next != null && slow != null) {
            
            // if fast gets to slow it means there is a cycle
            if (fast == slow) {
                return true;
            }
            // move fast and slow
            else {
                fast = fast.next.next;
                slow = slow.next;
            } 
        }
        // if fast doesn't get to slow it means there is no cycle
        return false;   
}}
