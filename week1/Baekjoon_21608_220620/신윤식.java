import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  static int N;
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};
  static int[][] map; // 학생들이 자리할 배열
  static boolean[][] visited;
  static List<Integer> studentsList;
  static Map<Integer, List<Integer>> studentsMap;

  public static void main(String[] args) throws IOException {
    inputData();
    getAnswer();
  }

  private static void getAnswer() {
    for(int curStudent : studentsList){
      int[][] tempMap = new int[N][N];
      List<Integer> bestFriends = studentsMap.get(curStudent);

      // 첫번째 조건 체크
      int maxCnt = 0;
      List<int[]> firstCandidate = new ArrayList<>();
      for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
          int firstCnt = 0;
          if(map[i][j] == 0){
            for(int k=0; k<4; k++){
              int newI = i + dx[k];
              int newJ = j + dy[k];

              if(newI < 0 || newI >= N || newJ < 0 || newJ >= N) continue;

              int target = map[newI][newJ];
              for(int friend : bestFriends){
                if(target == friend) firstCnt++;
              }
            }
            tempMap[i][j] = firstCnt;
            maxCnt = Integer.max(maxCnt, firstCnt);
          }
        }
      }
      checkCondition(maxCnt, firstCandidate, tempMap);

      // 첫번째 조건 만족하는 경우가 1개라면 바로 배치
      if(firstCandidate.size() == 1){
        int x = firstCandidate.get(0)[0];
        int y = firstCandidate.get(0)[1];
        map[x][y] = curStudent;
        visited[x][y] = true;
        continue;
      }

      // 두번째 조건 체크
      maxCnt = 0;
      tempMap = new int[N][N];
      List<int[]> secondCandidate = new ArrayList<>();
      for(int[] candidate : firstCandidate){
        int secondCnt = 0;
        int x = candidate[0];
        int y = candidate[1];

        for(int i=0; i<4; i++){
          int nx = x + dx[i];
          int ny = y + dy[i];

          if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
          if(map[nx][ny] == 0) secondCnt++;
        }
        tempMap[x][y] = secondCnt;
        maxCnt = Integer.max(maxCnt, secondCnt);
      }

      if(maxCnt == 0) secondCandidate = firstCandidate;
      else checkCondition(maxCnt, secondCandidate, tempMap);

      //세번째 조건 체크
      Collections.sort(secondCandidate, new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
          if(o1[0] < o2[0]) return -1;
          else if(o1[0] > o2[0]) return 1;

          return o1[1] - o2[1];
        }
      });

      int finalX = secondCandidate.get(0)[0];
      int finalY = secondCandidate.get(0)[1];
      map[finalX][finalY] = curStudent;
      visited[finalX][finalY] = true;
    }

    int answer = getSatisFaction();
    System.out.println(answer);
  }

  private static int getSatisFaction() {
    int total = 0;
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        int cnt = 0;
        List<Integer> myFriends = studentsMap.get(map[i][j]);
        for(int k=0; k<4; k++){
          int nx = i + dx[k];
          int ny = j + dy[k];

          if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
          for(int myFriend : myFriends){
            if(map[nx][ny] == myFriend) cnt++;
          }
        }
        total += calcSatisFaction(cnt);
      }
    }
    return total;
  }

  private static int calcSatisFaction(int cnt) {
    if(cnt == 0) return 0;
    else if(cnt == 1) return 1;
    else if(cnt == 2) return 10;
    else if(cnt == 3) return 100;
    else return 1000;
  }

  private static void checkCondition(int maxCnt, List<int[]> candidate, int[][] tempMap) {
    for(int i=0; i<N; i++){
      for(int j=0; j<N; j++){
        if(!visited[i][j] && tempMap[i][j] == maxCnt){
          candidate.add(new int[]{i, j});
        }
      }
    }
  }

  private static void inputData() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.valueOf(br.readLine());
    map = new int[N][N];
    visited = new boolean[N][N];
    studentsMap = new HashMap<>();
    studentsList = new ArrayList<>();

    for(int i=0; i<N*N; i++){
      String[] input = br.readLine().split(" ");
      int student1 = Integer.valueOf(input[0]);
      int student2 = Integer.valueOf(input[1]);
      int student3 = Integer.valueOf(input[2]);
      int student4 = Integer.valueOf(input[3]);
      int student5 = Integer.valueOf(input[4]);

      List<Integer> lst = new ArrayList<>();
      lst.add(student2);
      lst.add(student3);
      lst.add(student4);
      lst.add(student5);
      studentsMap.put(student1, lst);
      studentsList.add(student1);
    }
  }
}
