/*
Dynamic Programming approach to Fibonacci
Avoid repeated work by storing Fibonacci numbers calculated so far
Time Complexity: O(n) vs. Recursion O(2^n)
*/

class Fibonacci {
    public int fib(int n) {
        // list to store numbers calculate so far
        int f[] = new int[n+2];
        
        // the first two numbers are 0 and 1
        f[0] = 0;
        f[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            //add the previous 2 numbers and store it
            f[i] = f[i-1] + f[i-2];
        }
    return f[n];
}}
