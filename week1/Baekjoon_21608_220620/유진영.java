import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Student {
        int r, c;
        int[] friends;

        public Student(int f1, int f2, int f3, int f4) {
            this.r = 0;
            this.c = 0;
            friends = new int[] {f1, f2, f3, f4};
        }
    }

    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static Map<Integer, Student> students;
    static int N, nearEmpty[][], classroom[][];

    static Map<Integer, Integer> score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        score = new HashMap() {{put(0, 0); put(1, 1); put(2, 10); put(3, 100); put(4, 1000); }};

        N = Integer.parseInt(br.readLine());
        students = new HashMap<>();
        nearEmpty = new int[N+2][N+2]; //학생들 각 주변의 빈칸 갯수
        classroom = new int[N+2][N+2]; //자리

        //빈칸 갯수 세기
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                //빈칸 갯수 계산
                for(int d=0; d<4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr<1 || nc<1 || nr>N || nc>N) continue;

                    nearEmpty[nr][nc]++;
                }
            }
        }

        for(int n=0; n<Math.pow(N, 2); n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int stdntNum = Integer.parseInt(st.nextToken());
            Student student = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            students.put(stdntNum, student);
            findSeat(stdntNum, student.friends);
        }

        //만족도 조사
        int totalScore = 0;
        for(int key : students.keySet()) {
            Student stdnt = students.get(key);

            int count = 0;
            for(int friend : stdnt.friends) {
                if(Math.abs(students.get(friend).r - stdnt.r) + Math.abs(students.get(friend).c - stdnt.c) == 1)
                    count++;
            }

            totalScore += score.get(count);
        }

        System.out.println(totalScore);


    }

    public static void findSeat(int num, int[] friends) {
        int[][] nearFriend = new int[N+2][N+2];//주변에 좋아하는 학생 얼마나 있는지

        for(int number : friends) {
            if(!students.containsKey(number)) continue;

            Student stdnt = students.get(number);

            //아직 자리를 못정했으면 아웃
            if(stdnt.r == 0 && stdnt.c == 0)  continue;

            for(int d=0; d<4; d++) {
                int nr = stdnt.r + dr[d];
                int nc = stdnt.c + dc[d];

                if(nr<1 || nc<1 || nr>N || nc>N) continue;

                nearFriend[nr][nc]++; //좋아하는 학생 주변 자리 +1
            }
        }

        int maxFriend = -1;
        int maxEmpty = -1;
        int resultR = 0, resultC = 0;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                //이미 자리를 잡은 사람은 아웃
                if(classroom[r][c] != 0) continue;

                if(maxFriend < nearFriend[r][c]) {
                    resultR = r;
                    resultC = c;
                    maxFriend = nearFriend[r][c];
                    maxEmpty = nearEmpty[r][c];
                } else if(maxFriend == nearFriend[r][c] && maxEmpty < nearEmpty[r][c]) {
                    resultR = r;
                    resultC = c;
                    maxEmpty = nearEmpty[r][c];
                }
            }
        }

        classroom[resultR][resultC] = num;
        students.get(num).r = resultR;
        students.get(num).c = resultC;

        for(int d=0; d<4; d++) {
            int nr = resultR + dr[d];
            int nc = resultC + dc[d];

            if(nr<1 || nc<1 || nr>N || nc>N || classroom[nr][nc] != 0)
                continue;

            nearEmpty[nr][nc]--;
        }
    }
}
