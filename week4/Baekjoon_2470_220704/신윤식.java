import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  static int N;
  static int answer = Integer.MAX_VALUE; // 0과 가장 가까운 거리
  static int[] answerList = new int[2];
  static int[] water;

  public static void main(String[] args) throws IOException {
    inputData();
    getAnswer();
  }

  private static void inputData() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    water = new int[N];
    String[] inputNums = br.readLine().split(" ");
    for(int i=0; i<N; i++) water[i] = Integer.parseInt(inputNums[i]);
    Arrays.sort(water);
  }

  private static void getAnswer() {
    int sIdx = 0, eIdx = N - 1;
    while(sIdx < eIdx){
      int left = water[sIdx];
      int right = water[eIdx];
      int mix = left + right;
      int interval = Math.abs(mix - 0);

      if(answer > interval){
        answer = interval;
        answerList[0] = left;
        answerList[1] = right;
      }

      if(mix > 0) eIdx--;
      else if(mix <0) sIdx++;
      else break;
    }
    System.out.println(answerList[0] + " " +  answerList[1]);
  }
}
