public class Palindrome {
  public boolean isPalindrome(String s) {
    // time-complexity of replaceAll is 0(n)
    s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    int forward = 0;
    int backward = s.length()-1;
    // time-complexity of while loop is O(n)
    while (backward > forward) {
      char forwardChar = s.charAt(forward++);
      char backwardChar = s.charAt(backward--);

      if (forwardChar != backwardChar) {
        return false;
      }
    }
    return true;
  }
}
