class Solution:
    def minCameraCover(self, root: Optional[TreeNode]) -> int:
        self.result = 0

        def dfs(node):
            # camera, monitor bool for each
            if not node:
                return False, True

            c_l, m_l = dfs(node.left)
            c_r, m_r = dfs(node.right)

            camera, monitor = False, False
            if c_l == True or c_r == True:
                monitor = True
            if m_l == False or m_r == False:
                camera = True
                self.result += 1
                monitor = True

            return camera, monitor


        c, m = dfs(root)
        if m == False:
            return self.result + 1
        return self.result
