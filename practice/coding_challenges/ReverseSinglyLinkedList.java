// O(n) Time Complexity (O(1) each iteration and # of iterations = LinkedList size)

class ReverseSinglyLinkedList{
    public ListNode reverseList(ListNode head) {
        
        // imagine null (1) -> 2 -> 3 -> 4 -> 5 null
        ListNode prev = null;
      
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
            
            // after 1 iteration... null <- 1  (2) -> 3 -> 4 -> 5 null
        }
        
        return prev;
    }
}
