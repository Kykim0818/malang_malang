# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        '''
        nc = not covered by cam,
        cwoc = covered without camera in its node
        cwc = covered with camera in its node
        
        
        '''
        def dp(node: TreeNode) -> tuple:
        
            if not node:
                return 0,0,1
            
            l = dp(node.left)
            r = dp(node.right)
            
            nc = l[1] + r[1]
            cwoc = min(l[2] + min(r[1:]), r[2] + min(l[1:]))
            cwc = 1 + min(l) + min(r)
            
            return nc, cwoc, cwc
        
        return min(dp(root)[1:])