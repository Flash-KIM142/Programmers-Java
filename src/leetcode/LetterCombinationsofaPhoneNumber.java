package leetcode;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {

    public List<String> letterCombinations(String digits) {
        int len = digits.length();
        List<String> answer = new ArrayList<>();
        if(digits.equals("")) {
            return answer;
        }

        Map<Character, List<Character>> dial_map = new HashMap<>();
        dial_map.put('2', List.of('a', 'b', 'c'));
        dial_map.put('3', List.of('d','e','f'));
        dial_map.put('4', List.of('g','h','i'));
        dial_map.put('5', List.of('j','k','l'));
        dial_map.put('6', List.of('m','n','o'));
        dial_map.put('7', List.of('p','q','r','s'));
        dial_map.put('8', List.of('t','u','v'));
        dial_map.put('9', List.of('w','x','y','z'));

        Queue<Node> q = new LinkedList<>();
        for(Character c: dial_map.get(digits.charAt(0))) {
            String tmp = "";
            q.add(new Node(1, tmp + c));
        }

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(cur.nextPos == len) {
                answer.add(cur.curString);
                continue;
            }

            for(Character c: dial_map.get(digits.charAt(cur.nextPos))) {
                String nextString = cur.curString + c;
                q.add(new Node(cur.nextPos+1, nextString));
            }
        }


        return answer;
    }

    class Node {
        int nextPos;
        String curString;

        Node(int nextPos, String curString) {
            this.nextPos = nextPos;
            this.curString = curString;
        }
    }
}
