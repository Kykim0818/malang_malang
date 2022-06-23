public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    int answer;
    public int minCameraCover(TreeNode root) {
        answer = 0;
        
        if(getMinCameraCover(root) == 0) answer++;
        
        return answer;
    }
    
    //0: 카메라 없음
    //1: 카메라로 커버됨 또는 노드가 없음
    //2: 카메라
    public int getMinCameraCover(TreeNode root) {
        if(root == null) return 1;
        
        int left = getMinCameraCover(root.left);
        int right = getMinCameraCover(root.right);
        
        if(left == 0 || right == 0) {
            answer++;
            return 2;
        }
        
        if(left == 2 || right == 2)
            return 1;
        
        return 0;
    }
}
