public class FindLargestSmallest {
 
 public int findLargestSmallest(int[] array, String lOrS) {
 
 //assign first element of an array to largest and smallest
 int smallest = array[0];
 int largest = array[0];
 
 for(int i=1; i< arr.length; i++) {
  if(arr[i] > largest)
    largest = arr[i];
  else if (arr[i] < smallest)
  smallest = arr[i];
 }
 if (lOrS = "l") {
   return largest;
 } return smallest;
 }
   
 } 
 
