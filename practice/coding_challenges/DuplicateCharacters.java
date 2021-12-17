// O(n) time complexity (iterates over whole charArray)
// Uses an ArrayList and HashSet
// For anything not added to the HashSet, if it's not already in 
// the ArrayList, add it to the ArrayList...

import java.util.*;

public class DuplicateCharacters {
    public ArrayList<Character> duplicateCharacters(String s) {
        ArrayList<Character> array = new ArrayList<>();
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toLowerCase().replaceAll(" ", "").toCharArray()) {
            if (!set.contains(c)) {
                set.add(c);
            } else if (!array.contains(c)) {
                array.add(c);
            }
        } 
        return array;
    }
