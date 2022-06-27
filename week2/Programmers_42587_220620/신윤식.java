import java.util.*;

class Solution {

  class Request{
    int firstIndex;
    int importance;

    public Request(int firstIndex, int importance) {
      this.firstIndex = firstIndex;
      this.importance = importance;
    }
  }

  public int solution(int[] priorities, int location) {
    PriorityQueue<Request> pq = new PriorityQueue<>((a,b) -> b.importance - a.importance);
    Queue<Request> q = new LinkedList<>();

    init(pq, q, priorities);

    int answer = -1;
    int printOrder = 1;
    while(!pq.isEmpty()){
      if(answer == printOrder) break;

      Request highestImportance = pq.poll();

      int tempSize = q.size();
      while(tempSize-- > 0){
        Request curRequest = q.remove();

        if(curRequest.importance < highestImportance.importance) q.add(curRequest);
        else{
          if(curRequest.firstIndex == location) answer = printOrder;
          else printOrder++;
          break;
        }
      }
    }

    return answer;
  }

  private void init(PriorityQueue<Request> pq, Queue<Request> q, int[] priorities) {
    for(int i=0; i< priorities.length; i++){
      pq.offer(new Request(i, priorities[i]));
      q.add(new Request(i, priorities[i]));
    }
  }
}

// Req: 중요도, 처음위치 -> queue, pq에 담기
// 0. printOrder = 1; 프린트되는 순서
// 1. pq의 원소가 없을때까지 반복
//  1. pq에서 중요도가 높은 순서대로 값 뽑기
//  2. q에서 remove
//      2-1. req의 값이 pq의 중요도보다 작으면 q에 넣기
//      2-2. req의 값이 pq의 중요도와 같으면(어차피 뽑아야 하는 req),
//          2-2-1. req의 처음위치가 location과 같은지 확인. 같다면, return printOrder;
//          2-2-2. req의 처음위치가 location과 다르면, printOrder++