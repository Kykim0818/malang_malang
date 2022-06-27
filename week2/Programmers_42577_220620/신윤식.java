import java.util.*;

class Solution {

  // 아이디어: A(비교 주체) -> B(비교 대상). B의 관점에서 접근

  public boolean solution(String[] phone_book) {

    Map<String, Boolean> phoneMap = new HashMap<>(); // value가 Boolean인 이유, 메모리를 가장적게 소모하기 위해
    Set<Integer> phoneLen = new HashSet<>(); // 타켓이 되는 문자열을 주어진 길이내에서 파싱하기 위해

    for (int i = 0; i < phone_book.length; i++){
      phoneMap.put(phone_book[i], true);
      phoneLen.add(phone_book[i].length());
    }

    // O(10^6*20)
    for (int i = 0; i < phone_book.length; i++){
      for(int phoneSize : phoneLen){
        if(phoneSize <= phone_book[i].length()){
          String prefix = phone_book[i].substring(0, phoneSize);

          if(prefix.equals(phone_book[i])) continue; // 자기 자신 거르기
          if(phoneMap.containsKey(prefix)) return false;
        }
      }
    }
    return true;
  }
}