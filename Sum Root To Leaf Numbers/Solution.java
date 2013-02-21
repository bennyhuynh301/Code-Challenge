public class Solution {
    int sum;
    StringBuilder fringe;
    
    public Solution() {
        this.sum = 0;
        this.fringe = new StringBuilder();
    }
    
    private void addNumbers(TreeNode node) {
        if (node != null) {
            this.fringe.append(node.val);
            System.out.println("Fringe: " + this.fringe);
        }
        else {
            return;
        }
        if (node.left == null && node.right == null) {
            this.sum += Integer.parseInt(fringe.toString());
            this.fringe.deleteCharAt(this.fringe.length()-1);
            System.out.println("Fringe: " + this.fringe);
        }
        else {
            addNumbers(node.left);
            addNumbers(node.right);
            this.fringe.deleteCharAt(this.fringe.length()-1);
            System.out.println("Fringe: " + this.fringe);
        }
    }
    
    public int sumNumbers(TreeNode root) {
        addNumbers(root);
        return this.sum;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode leftRoot = new TreeNode(1);
        TreeNode rightRoot = new TreeNode(3);
        TreeNode left_leftChild = new TreeNode(4);
        TreeNode right_leftChild = new TreeNode(5);
        TreeNode left_rightChild = new TreeNode(6);
        TreeNode right_rightChild = new TreeNode(7);

        root.left = leftRoot;
        //root.right = rightRoot;
        //leftRoot.left = left_leftChild;
        //leftRoot.right = right_leftChild;
        //rightRoot.left = left_rightChild;
        //rightRoot.right = right_rightChild;

        Solution solution = new Solution();
        System.out.println("Sum: " + solution.sumNumbers(root));
    }

}