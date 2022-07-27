import java.util.*;

class Solution {

  int answer = 0;
  List<List<Integer>> graph;

  public int solution(int[] info, int[][] edges) {
    initGraph(info, edges);
    getAnswer(info, edges);

    return answer;
  }

  private void getAnswer(int[] info, int[][] edges) {
    List<Integer> possibleNode = new ArrayList<>();
    possibleNode.add(0);
    dfs(0, 0, 0, info, possibleNode);
  }

  private void initGraph(int[] info, int[][] edges) {
    graph = new ArrayList<>();
    for(int i=0; i<info.length; i++) graph.add(new ArrayList<>());

    for(int i=0; i<edges.length; i++){
      int parent = edges[i][0];
      int child = edges[i][1];

      graph.get(parent).add(child);
    }
  }

  private void dfs(int curNode, int sheep, int wolf, int[] info, List<Integer> possibleNode) {
    if(info[curNode] == 0) sheep++;
    else wolf++;

    answer = Math.max(sheep, answer);

    if(sheep <= wolf) return;

    List<Integer> copyList = new ArrayList<>();
    for (int node : possibleNode) copyList.add(node);
    for(int i=0; i<graph.get(curNode).size(); i++){
      copyList.add(graph.get(curNode).get(i));
    }
    copyList.remove((Integer)curNode);

    for(int nextNode : copyList){
      dfs(nextNode, sheep, wolf, info, copyList);
    }
  }
}

