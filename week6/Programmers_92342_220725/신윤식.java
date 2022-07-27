import java.util.*;

class Solution {

  // n발; 쏘는 행위를 중심으로 중복조합
  // n = 5, 0 0 0 0 0 == 5발 모두 0점을 맞힘
  final int MAXSCORE = 11;
  int arrows, maxDiff = -1;

  int[] scores = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
  int[] apeach;
  int[] answer;

  public int[] solution(int n, int[] info) {
    int[] ryan = new int[info.length]; // ryan[i]: 10-i점수를 맞힌 화살의 개수
    arrows = n;
    apeach = info;
    answer = new int[11];

    getAnswer(0, 0, ryan);

    if(maxDiff == -1) return new int[]{-1};
    else return answer;
  }

  // index: 몇발을 쐈는지
  private void getAnswer(int index, int start, int[] ryan){
    if(index >= arrows){

      // 점수 계산
      int apeachTotal = 0, ryanTotal = 0;
      for(int i=0; i<MAXSCORE; i++){
        if(apeach[i] == 0 && ryan[i] == 0) continue;
        if(apeach[i] < ryan[i]) ryanTotal += (10-i);
        else apeachTotal += (10-i);
      }

      if(ryanTotal <= apeachTotal) return;
      if(maxDiff < ryanTotal - apeachTotal){
        maxDiff = ryanTotal - apeachTotal;
        for(int i=0; i<MAXSCORE; i++){
          answer[i] = ryan[i];
        }
      } else if(ryanTotal - apeachTotal == maxDiff){
        for(int i=MAXSCORE - 1; i>0; i--){
          if(ryan[i] > answer[i]){
            for(int j=0; j<MAXSCORE; j++) answer[j] = ryan[j];
            return;
          } else if(ryan[i] < answer[i]) {
            return;
          }
        }
      }
      return;
    }

    for(int i=start; i<MAXSCORE; i++){
      ryan[i]++;
      getAnswer(index + 1, i, ryan);
      ryan[i]--;
    }
  }
}