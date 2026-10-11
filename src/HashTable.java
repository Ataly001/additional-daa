
public class HashTable {
    private static class Node {
        final int val;
        Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node[] table;
    private int size;
    public HashTable() {
        table = new Node[16];
    }

    public boolean insert(int val) {
        if (contains(val)) {
            return false;
        }
        if ((size + 1.0) / table.length > 0.75) {
            resize();
        }
        int i = index(val, table.length);
        table[i] = new Node(val, table[i]);
        size++;
        return true;
    }

    public boolean contains(int val) {
        int i = index(val, table.length);
        Node cur = table[i];

        while (cur != null) {
            if (cur.val == val) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    public boolean delete(int val) {
        int i = index(val, table.length);
        Node cur = table[i];
        Node prev = null;

        while (cur != null) {
            if (cur.val == val) {
                if (prev == null) {
                    table[i] = cur.next;
                } else {
                    prev.next = cur.next;
                }

                size--;
                return true;
            }

            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
    private int index(int val, int cap) {
        return Math.floorMod(val, cap);
    }

    private void resize() {
        Node[] old = table;
        table = new Node[old.length * 2];

        for (Node head : old) {
            Node cur = head;

            while (cur != null) {
                Node next = cur.next;
                int i = index(cur.val, table.length);

                cur.next = table[i];
                table[i] = cur;
                cur = next;
            }
        }
    }
}