class Solution {
  // 백준 두용액과 거의 유사함. a + b = 0 or a + b + c = 0이나 같은문제임
  // a + b + c = 0 -> a + b = -c로 생각하고 -c를 0과같은 기준점이라 생각하면 동일한 문제임. 두 포인터 알고리즘
  // Set<List<Ingeter>> check; Set자료구조는 원소가 list여도 list자체에 대한 중복검사를 진행하여 list구성이 중복되지않음
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> answer = new ArrayList<>();

    int N = nums.length;

    Arrays.sort(nums);

    for(int i=0; i<N - 1; i++){
      if(i > 0 && nums[i] == nums[i - 1]) continue;
      int sIdx = i + 1;
      int eIdx = N - 1;

      while(sIdx < eIdx){
        int sum = nums[sIdx] + nums[eIdx] + nums[i];

        if(sum == 0){
          answer.add(Arrays.asList(nums[i], nums[sIdx], nums[eIdx]));
          sIdx++; eIdx--;
          while(nums[sIdx] == nums[sIdx - 1] && sIdx < eIdx) sIdx++;
          while(nums[eIdx] == nums[eIdx + 1] && sIdx < eIdx) eIdx--;
        } else if(sum < 0){
          sIdx++;
        } else{
          eIdx--;
        }
      }
    }
    return answer;
  }
}