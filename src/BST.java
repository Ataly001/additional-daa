public class BST {
    private static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    private Node root;
    private int size;

    public boolean insert(int val) {
        if (root == null) {
            root = new Node(val);
            size++;
            return true;
        }

        Node cur = root;
        while (true) {
            if (val == cur.val) {
                return false;
            }
            if (val < cur.val) {
                if (cur.left == null) {
                    cur.left = new Node(val);
                    size++;
                    return true;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Node(val);
                    size++;
                    return true;
                }
                cur = cur.right;
            }
        }
    }

    public boolean contains(int val) {
        Node cur = root;
        while (cur != null) {
            if (val == cur.val) {
                return true;
            }
            cur = val < cur.val ? cur.left : cur.right;
        }
        return false;
    }

    public boolean delete(int val) {
        Node par = null;
        Node cur = root;

        while (cur != null && cur.val != val) {
            par = cur;
            cur = val < cur.val ? cur.left : cur.right;
        }
        if (cur == null) {
            return false;
        }

        if (cur.left != null && cur.right != null) {
            Node nextPar = cur;
            Node next = cur.right;
            while (next.left != null) {
                nextPar = next;
                next = next.left;
            }
            cur.val = next.val;
            par = nextPar;
            cur = next;
        }

        Node sub = cur.left != null ? cur.left : cur.right;
        if (par == null) {
            root = sub;
        } else if (par.left == cur) {
            par.left = sub;
        } else {
            par.right = sub;
        }
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public int height() {
        if (root == null) {
            return 0;
        }
        java.util.ArrayDeque<Node> q = new java.util.ArrayDeque<>();
        q.add(root);
        int h = 0;
        while (!q.isEmpty()) {
            int level = q.size();
            h++;
            for (int i = 0; i < level; i++) {
                Node n = q.remove();
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
        }
        return h;
    }
}


