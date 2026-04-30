// Author: Sebastian Pasara
// Course: CS2
// Semester: Fall 2025
import java.util.*;

public class EscapeRoomDecoder{
    
    public ArrayList<String> generatePasswords(String tiles){
        ArrayList<String> results = new ArrayList<>();

        // sorts to handle duplicates
        // convert string to char
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);

        // backtrack
        boolean[] visited = new boolean[chars.length];
        StringBuilder current = new StringBuilder();
        backtrack(chars, visited, current, results);

        // print total count
        System.out.println("Total valid passwords: " + results.size());
        return results;
    }

    private void backtrack(char[] chars, boolean[] visited, 
    StringBuilder current, ArrayList<String> results){
        
        // checking if it has at least 2 valid char
        if(current.length() >= 2){
            String password = current.toString();
            if(!isPalindrome(password)){
                results.add(password);
            }
        }
        // add each remaining char
        for(int i = 0; i < chars.length; i++){
            // skip
            if(visited[i]){
                continue;
            }
            // skip dups
            if (i > 0 && chars[i] == chars[i-1] && !visited[i-1]){
                continue;
            }
            // add char to current password
            visited[i] = true;
            current.append(chars[i]);

            // recursive
            backtrack(chars, visited, current, results);
            
            // backtrack by removing char
            current.deleteCharAt(current.length() - 1);
            visited[i] = false;
        }
    }

    private boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;

        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}