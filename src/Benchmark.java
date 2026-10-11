import java.io.PrintWriter;
import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {100, 1000, 10000};

    public static void main(String[] args) throws Exception {
        PrintWriter file = new PrintWriter("results.csv");
        file.println("size,structure,insert_ns,search_ns,delete_ns");

        for (int n : SIZES) {
            int[] nums = makeNumbers(n);
            long[] bst = testBST(nums);
            long[] hash = testHash(nums);

            show(n, "BST", bst);
            show(n, "HashTable", hash);

            file.println(n + ",BST," + bst[0] + "," + bst[1] + "," + bst[2]);
            file.println(n + ",HashTable," + hash[0] + "," + hash[1] + "," + hash[2]);
        }

        file.close();
        System.out.println("Results saved to results.csv");
    }

    private static long[] testBST(int[] nums) {
        BST tree = new BST();

        long start = System.nanoTime();
        for (int x : nums) tree.insert(x);
        long insert = System.nanoTime() - start;

        start = System.nanoTime();
        for (int x : nums) tree.contains(x);
        long search = System.nanoTime() - start;

        start = System.nanoTime();
        for (int x : nums) tree.delete(x);
        long delete = System.nanoTime() - start;

        return new long[]{insert, search, delete};
    }

    private static long[] testHash(int[] nums) {
        HashTable table = new HashTable();

        long start = System.nanoTime();
        for (int x : nums) table.insert(x);
        long insert = System.nanoTime() - start;

        start = System.nanoTime();
        for (int x : nums) table.contains(x);
        long search = System.nanoTime() - start;

        start = System.nanoTime();
        for (int x : nums) table.delete(x);
        long delete = System.nanoTime() - start;

        return new long[]{insert, search, delete};
    }

    private static int[] makeNumbers(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i;

        Random random = new Random(42);
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    private static void show(int n, String name, long[] time) {
        System.out.println("Size: " + n);
        System.out.println("Structure: " + name);
        System.out.println("Insert: " + time[0] + " ns");
        System.out.println("Search: " + time[1] + " ns");
        System.out.println("Delete: " + time[2] + " ns");
        System.out.println();
    }
}
