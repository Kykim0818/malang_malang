import java.util.*;

class Solution {
  public int[] solution(int[] fees, String[] records) {
    List<Integer> answer = new ArrayList<>();
    Map<String, Integer> carList = new HashMap<>();
    Map<String, List<String>> entryAndExit = new HashMap<>();

    // entryAndExit맵 초기화
    for(String record : records){
      String[] data = record.split(" ");
      String time = data[0];
      String car = data[1];
      carList.put(car, 0);

      if(entryAndExit.containsKey(car)) entryAndExit.get(car).add(time);
      else {
        entryAndExit.put(car, new ArrayList<>());
        entryAndExit.get(car).add(time);
      }
    }

    // 시간계산
    for(String key : entryAndExit.keySet()){
      int sumMinute = 0;
      List<String> timeList = entryAndExit.get(key);
      for(int i=0; i<timeList.size(); i+=2){
        String startTime = calcMinute(timeList.get(i));
        String endTime;
        if(i + 1 < timeList.size()) endTime = calcMinute(timeList.get(i + 1));
        else endTime = calcMinute("23:59");

        int minute = Integer.parseInt(endTime) - Integer.parseInt(startTime);
        sumMinute += minute;
      }
      int total = carList.get(key) + sumMinute;
      carList.put(key, total);
    }

    // key정렬
    Map<String, Integer> sortedCarList = new TreeMap<>(carList);

    // 요금계산
    for(String key : sortedCarList.keySet()){
      int totalMinute = carList.get(key);
      if(totalMinute <= fees[0]) answer.add(fees[1]);
      else{
        int tempSum = fees[1]+ ((totalMinute - fees[0]) / fees[2] + ((totalMinute - fees[0]) % fees[2] == 0 ? 0 : 1)) * fees[3];
        answer.add(tempSum);
      }
    }

    int[] answerArr = new int[answer.size()];
    for(int i=0; i< answer.size(); i++) answerArr[i] = answer.get(i);

    return answerArr;
  }

  private String calcMinute(String time) {
    String[] timeArr = time.split(":");
    int hour = Integer.parseInt(timeArr[0]) * 60;
    int minute = Integer.parseInt(timeArr[1]);
    return String.valueOf(hour + minute);
  }
}