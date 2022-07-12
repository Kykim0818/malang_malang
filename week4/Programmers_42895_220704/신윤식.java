// https://born2bedeveloper.tistory.com/38 참조
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
class Solution {
  //순서쌍 a에 b 합치기
  public void unionSet(Set<Integer> union, Set<Integer> a, Set<Integer> b) {
    for (int n1 : a) {
      for (int n2 : b) {
        union.add(n1 + n2);
        union.add(n1 - n2);
        union.add(n1 * n2);
        if (n2 != 0)
          union.add(n1 / n2);
      }
    }
  }
  public int solution(int N, int number) {
    List<Set<Integer>> setList = new ArrayList<>();

    for (int i = 0; i < 9; i++)
      setList.add(new HashSet<>()); // 개수 별 해쉬셋
    setList.get(1).add(N); //1개로 만들 수 있는 건 나 자신뿐
    if (number == N)
      return 1;
    for (int i = 2; i < 9; i++) {
      for (int j = 1; j <= i / 2; j++) {
        unionSet(setList.get(i), setList.get(i - j), setList.get(j));
        unionSet(setList.get(i), setList.get(j), setList.get(i - j));
      }
      String n = Integer.toString(N);
      setList.get(i).add(Integer.parseInt(n.repeat(i))); //연속된 숫자 넣기
      for (int num : setList.get(i))
        if (num == number)
          return i;
    }
    return -1;
  }
}