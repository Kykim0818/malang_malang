import java.util.*;

public class Solution {

  final int MAX = 2;

  int same;
  int[] answer = new int[MAX];
  List<Number> winNumsList = new ArrayList<>();

  public int[] solution(int[] lottos, int[] win_nums) {

    initializeWinList(winNumsList, win_nums);
    getAnswer(lottos);

    return answer;
  }

  private void getAnswer(int[] lottos) {
    boolean isZero = false;

    for(int i=0; i<lottos.length; i++){
      if(lottos[i] == 0) {
        isZero = true;
        continue;
      }
      for(int j=0; j<winNumsList.size(); j++){
        if(lottos[i] == winNumsList.get(j).number){
          winNumsList.get(j).setCheck(true);
          same++;
        }

      }
    }

    if(isZero){
      int maxValue = same;
      for(int i=0; i<lottos.length; i++){
        if(lottos[i] == 0){
          for(int j=0; j<winNumsList.size(); j++){
            if(!winNumsList.get(j).check){
              winNumsList.get(j).setCheck(true);
              maxValue++;
              break;
            }
          }
        }
      }
      answer[0] = maxValue;
      answer[1] = same;
      convertAnswer();
    }else{
      answer[0] = answer[1] = same;
      convertAnswer();
    }
  }

  private void initializeWinList(List<Number> winNumsList, int[] win_nums) {
    for(int i=0; i<win_nums.length; i++){
      winNumsList.add(new Number(win_nums[i], false));
    }
  }

  private void convertAnswer() {
    for(int i=0; i<MAX; i++){
      if(answer[i] == 6) answer[i] = 1;
      else if(answer[i] == 5) answer[i] = 2;
      else if(answer[i] == 4) answer[i] = 3;
      else if(answer[i] == 3) answer[i] = 4;
      else if(answer[i] == 2) answer[i] = 5;
      else answer[i] = 6;
    }
  }
}

class Number{
  int number;
  boolean check;

  public Number(int number, boolean check) {
    this.number = number;
    this.check = check;
  }

  private void setCheck(boolean check){
    this.check = check;
  }
}

// 0. same = 0; 같은 숫자가 있다면 += 1, class Number{ int number, boolean check} -> winNimsList담기
// 1. lottos와 winNimsList를 비교하여 same카운트; 같은 번호가 있다면 number.check = true; + 0의 존재유무 파악
// 2. 0존재하는 경우,
//      2.1. 0에 대해
//          2.1.1. winNumsList를 순회하며 check가 false인 경우 same++; check = true;
// 3. 0존재하지 않는 경우,
//      3.1. 최고 순위 = 최저 순위; [최고, 최저]