class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer;
        int[] rank = new int[] {6, 6, 5, 4, 3, 2, 1};
        
        int correctCount = 0; //일치하는 번호 개수
        int erasedCount = 0; //알아볼 수 없는 번호 개수
        
        boolean[] win_nums_check = new boolean[46];
        for(int i=0; i<win_nums.length; i++) {
            win_nums_check[win_nums[i]] = true;
        }
        
        for(int i=0, j=0; i<lottos.length; i++) {
            if(lottos[i] == 0)
                erasedCount++;
            else if(win_nums_check[lottos[i]]) {
                correctCount++;
            }
        }
        
        answer = new int[] {rank[correctCount + erasedCount], rank[correctCount]};
        
        return answer;
    }
}
