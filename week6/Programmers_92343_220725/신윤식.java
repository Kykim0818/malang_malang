import java.util.*;

class Solution {
  /**
   * 2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요? 두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요. 요일의 이름은 일요일부터 토요일까지 각각 SUN,MON,TUE,WED,THU,FRI,SAT
   *
   * 입니다. 예를 들어 a=5, b=24라면 5월 24일은 화요일이므로 문자열 "TUE"를 반환하세요.
   *
   * 제한 조건
   * 2016년은 윤년입니다.
   * 2016년 a월 b일은 실제로 있는 날입니다. (13월 26일이나 2월 45일같은 날짜는 주어지지 않습니다)
   *
   * // 1월을 기준으로 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
   *     // 1월 1일? 1 % 7 => 1(금)
   *     // 1월 2일? 2 % 7 => 2(토)
   *     // 1월 3일? 3 % 7 => 3(일)
   *     // 1월 4일? 4 % 7 => 4(월)
   *     // 1월 5일? 5 % 7 => 5(화)
   *     // 1월 6일? 6 % 7 => 6(수)
   *     // 1월 7일? 7 % 7 => 0(목)
   *     // 1월 15일? 15 % 7 => 1(금)
   *     // sol) 1월 ~ a월까지 일수를 모두 더하고 + b일만큼 더한후 % 7한 결과에 요일을 맵핑하여 리턴시키자
   */
  public static void main(String[] args) {
      int a = 5, b = 24;
      solution(a, b);
  }
  public int solution(int a, int b) {
    Map<Integer, String> day = new HashMap<>();
    day.put(0, "FRI");
    day.put(1, "SAT");
    day.put(2, "SUN");
    day.put(3, "MON");
    day.put(4, "TUE");
    day.put(5, "WED");
    day.put(6, "THR");

    int[] calendar = new int[13];
    calendar[2] = 29;
    calendar[4] = calendar[6] = calendar[9] = calendar[11] = 30;
    calendar[1] = calendar[3] = calendar[5] = calendar[7] = calendar[8]= calendar[10] = calendar[12] =31;

    int countDay = 0;
    for(int i=1; i<=a; i++){
      countDay += calendar[i];
    }

    countDay -= 1; // 1/1일을 기준으로 그 차이를 알아야 함
    countDay %= 7;

    System.out.println(day.get(countDay));



    return answer;
  }

}

