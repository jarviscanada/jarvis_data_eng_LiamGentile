/*
Time Complexity: push O(n), pop O(1), peek O(1), empty O(1) 
We need two stacks in order to ensure FIFO
We empty stack one using pop, add the stack to stack 2, 
and then add x before adding everything back to stack 1
our front variable tracks the front position
*/

class MyQueue {
    
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();
    private int front;

    public MyQueue() {
    
    }
    
    public void push(int x) {
        if (stack1.isEmpty())
            front = x;
        
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
        stack2.push(x);
        
        while (!stack2.isEmpty())
            stack1.push(stack2.pop());
    }
    
    public int pop() {
        if (!stack1.empty())
            front = stack1.peek();
        return stack1.pop();
    }
    
    public int peek() {
        return front;
    }
    
    public boolean empty() {
        return stack1.isEmpty();
    }
}
