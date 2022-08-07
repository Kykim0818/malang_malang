import java.util.*;

class Solution {
  int[] maxCount = new int[11];
  List<String> answer = new ArrayList<>();
  Map<String, Integer> allCouresMenu = new HashMap<>(); // (k,v) = (메뉴구성, 카운트수)

  public String[] solution(String[] orders, int[] course) {
    String[] sortedOrders = new String[orders.length];
    int idx = 0;
    for(String order : orders){
      char[] chars = order.toCharArray();
      Arrays.sort(chars);
      String newStr = new String(chars);
      sortedOrders[idx++] = newStr;
    }

    for(String order : sortedOrders){
      for(int menuCount : course){
        getAllMenu(0, 0, menuCount, order, ""); // 조합으로 menuCount개만틈의 menu구하기
      }
    }

    for(Map.Entry<String, Integer> entry : allCouresMenu.entrySet()){
      String key = entry.getKey();
      if(maxCount[key.length()] == entry.getValue()) answer.add(key);
    }
    Collections.sort(answer);

    return answer.toArray(new String[answer.size()]);
  }

  private void getAllMenu(int level, int start, int menuCount, String order, String sum) {
    if(level >= menuCount) {
      // map에 등록
      if(allCouresMenu.containsKey(sum)) {
        int count = allCouresMenu.get(sum) + 1;
        maxCount[menuCount] = Math.max(maxCount[menuCount], count);
        allCouresMenu.replace(sum, count);

      }
      else allCouresMenu.put(sum, 1);
      return;
    }

    for(int i=start; i<order.length(); i++){
      getAllMenu(level + 1, i + 1, menuCount, order, sum + order.charAt(i));
    }
  }
}