import java.util.*;

class Solution{

  // return: id_list에 담긴 id순서대로 각 유저가 받은 결과 메일 수를 담아서 리턴
  // 1.map<신고당한 유저id, 신고한 유저 목록>
  // 2.map<유저id, index>
  // 3.int[] reported; reported[유저id]: 유저id가 신고당한 횟수

  /**
   * 1번 for문 안에서  -> value가져오기
   * 가져온 value에 대해 2번 map을 통해 해당 유저가 있는 reported[유저id]의 숫자와 K를 비교
   * K이상이면 answer[1번 for문의 key]++
   */

  public int[] solution(String[] id_list, String[] report, int K){
    int[] reported = new int[id_list.length];
    Map<String, Set<String>> reportInfo = new HashMap<>();
    Map<String, Integer> mappingTable = new HashMap<>();

    setData(reported, id_list, report, reportInfo, mappingTable);
    int[] answer = getAnswer(K, id_list, reported, reportInfo, mappingTable);

    return answer;
  }

  private int[] getAnswer(int K, String[] id_list, int[] reported, Map<String, Set<String>> reportInfo, Map<String, Integer> mappingTable) {
    int[] answer = new int[reported.length];
    for(int i=0; i<reported.length; i++){
      if(reported[i] >= K){
        Set<String> reportlist = reportInfo.get(id_list[i]);
        for(String user : reportlist){
          int idx = mappingTable.get(user);
          answer[idx]++;
        }
      }
    }
    return answer;
  }

  private void setData(int[] reported, String[] id_list, String[] report, Map<String, Set<String>> reportInfo, Map<String, Integer> mappingTable) {
    for(int i=0; i<id_list.length; i++) mappingTable.put(id_list[i], i);

    for(String info : report){
      String[] users = info.split(" ");
      if(!reportInfo.containsKey(users[1])) reportInfo.put(users[1], new HashSet<>());

      reportInfo.get(users[1]).add(users[0]);
    }

    for(Map.Entry<String, Set<String>> entry : reportInfo.entrySet()){
      String reportedUser = entry.getKey();
      int idx = mappingTable.get(reportedUser);
      reported[idx] = entry.getValue().size();
    }
  }


}