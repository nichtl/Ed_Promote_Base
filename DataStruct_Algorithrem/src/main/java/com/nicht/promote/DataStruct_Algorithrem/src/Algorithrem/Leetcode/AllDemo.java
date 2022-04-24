package com.nicht.promote.DataStruct_Algorithrem.src.Algorithrem.Leetcode;

import org.springframework.util.CollectionUtils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/8/30
 * @Link
 */
public class AllDemo {
    public static Double percent(int a, int b) {
        String result = null;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        System.out.println((float) a / (float) b * 100);
        if (b == 0) return 0.0;
        result = numberFormat.format((float) a / (float) b * 100);
        return Double.parseDouble(result);
    }

    public static int findRepeatNumber(int[] nums) {
        int res = -1;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[nums[i]] == nums[i]) {
                res = nums[i];
                break;
            }
            temp = nums[nums[i]];
            nums[nums[i]] = nums[i];
            nums[i] = temp;
        }
        return res;
    }

    /*if(matrix.length == 0 || matrix[0].length == 0) return false;
       int rows = matrix.length;
       int cols = matrix[0].length;
       int i = rows - 1, j = 0;
       while(i >= 0 && j < cols) {
           if(matrix[i][j] < target){
               j++;
           } else if(matrix[i][j] > target){
               i--;
           } else {
               return true;
           }
       }
       return false;*/
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        boolean res = false;
        int row = matrix[0].length;
        int x = matrix.length - 1;
        int y = 0;
        while (x >= 0 && y < row) {
            int num = matrix[x][y];
            if (num == target) {
                res = true;
                break;
            }
            if (num < target) {
                y++;
            }
            if (num > target) {
                x--;
                y = 0;
            }

        }

        System.out.println(x + ":" + y);
        return res;
    }

    public static int minimumDifference(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        nums = Arrays.stream(nums).sorted().toArray();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            min = Math.min(min, nums[i + k - 1] - nums[i]);
        }
        return min;

    }

    public static String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, ((o1, o2) -> {
            return o1.length() == o2.length() ? o1.length() - o2.length() : o1.compareTo(o2);
        }));
        return nums[nums.length - k];
    }
    public int[] plusOne(int[] digits) {
        digits[digits.length-1]+=1;

        if( digits[digits.length-1]>9){
            int temp = digits[digits.length-1];
            digits[digits.length-1]=temp/10;
            //digits= new CopyOnWriteArrayList<>(digits).add(temp%10);
            return digits;
        }
        return digits;
    }

    public static int numWays(int n) {
        int a = 0, b = 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }

    //[3,4,5,1,2]
    /* int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]) {
                high = pivot;
            } else if (numbers[pivot] > numbers[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];*/
    public static int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        int min = Integer.MIN_VALUE;
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] < numbers[right]) {
                right = mid;
            }
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;//  [3,4,5,1,2]
            } else {
                right -= 1;
            }
        }
        return numbers[left];
    }

    public static int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return mvcdfs(0, 0, visited, k);
    }

    public static int mvcdfs(int i, int j, boolean[][] visited, int k) {
        if (i < 0 || j < 0 || i >= visited.length || j >= visited[0].length || k < getsum(i, j) || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        return mvcdfs(i, j + 1, visited, k) + mvcdfs(i + 1, j, visited, k) + 1;
    }

    public static int getsum(int i, int j) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum;
    }

    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        if (head.next == null) {
            return new int[]{head.val};
        }
        LinkedList<Integer> listNodes = new LinkedList<>();
        while (head != null) {
            listNodes.add(head.val);
            head = head.next;
        }
        int[] nums = new int[listNodes.size()];
        for (int i = listNodes.size() - 1; i >= 0; i--) {
            nums[i] = listNodes.pop();
        }
        return nums;
    }

     class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    ArrayList<Integer> list = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length - 1;
        int n = matrix[0].length;
        boolean[][] visit = new boolean[m][n];
        dfsOrder(matrix, visit, 0, 0);
        return list;
    }

    public void dfsOrder(int[][] matrix, boolean[][] visit, int x, int y) {
        if (x >= matrix.length || x < 0 || y < 0 || y >= matrix[0].length || visit[x][y]) {
            return;
        }
        visit[x][y] = true;
        list.add(matrix[x][y]);
        dfsOrder(matrix, visit, x, y++);
        dfsOrder(matrix, visit, x++, y);
        dfsOrder(matrix, visit, x, y--);
        dfsOrder(matrix, visit, x--, y);
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }

            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

        }
        return -1;
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[]{};
        QuickSort(arr, 0, arr.length - 1);
        int[] ams = Arrays.copyOfRange(arr, 0, k);
        return ams;
    }

    public static void QuickSort(int[] a, int left, int right) {
        int l = left;  // 左索引
        int r = right; // 右索引
        int temp = 0;
        int pivot = a[(left + right) / 2]; // 中间值
        //while 循环的目的是遍历让比pivot小的值放到坐标，比pivot大的值放在右边
        while (l < r) {
            while (a[l] < pivot) {
                l += 1;            // 如果找的值小于 给定的基准值 一直找下去 直到 找到比 基准值大的 下标
            }
            while (a[r] > pivot) {
                r -= 1; // 一直找到比 中间值小 =的 小标
            }
            if (l >= r) {
                break;   // 两侧的 l r 向 中间值 index 逼进 当 l > r 说明已经完成了寻找过程
            }
            // 此时 交换
            temp = a[l];
            a[l] = a[r];
            a[r] = temp;
            //在交换完毕后判断  在交换完毕后 可能左右侧存在等于 中间值的 情况
            // 那么在下次进行遍历时可能回一直 卡住，一直在交换 两个相同位置的值
        }
        if (l == r) {  //当 l 和 r 重合了  也满足while（l<r） 会死循环，让他们错开
            l += 1;
            r -= 1;
        }
        //  递归完成全部排序
        // r 为 基准数前一位
        // l 为基准数后一位
        // 此时第一躺 排序完成了 基准数 左小  右大
        if (left < r) {    //对基准数左侧进行排序
            QuickSort(a, left, r);        // 所有小于中间值的 组
        }
        if (right > l) {
            QuickSort(a, l, right);         // 所有大于中间值的组
        }

    }

    public static void quickSort(int[] arr) {
        int left = 0, right = arr.length - 1;
        int leftmax = Integer.MIN_VALUE, rightmin = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (left + right) / 2;
            while (left < mid) {
                if (arr[left] > arr[mid]) {
                    leftmax = left;
                    break;
                }
                left++;
            }
            while (right > mid) {
                if (arr[right] < arr[mid]) {
                    rightmin = right;
                    break;
                }
                right--;
            }
            arr[left] = arr[left] ^ arr[right];
            arr[right] = arr[right] ^ arr[left];
            arr[left] = arr[left] ^ arr[right];
            if (left == right) {
                left++;
                right--;
            }
        }


    }

    public static int majorityElement(int[] nums) {
        int halflen = nums.length / 2;
        int res = -1;
        if (nums.length == 1) return nums[0];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0, len = nums.length - 1; i <= len; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) >= halflen) {
                res = nums[i];
                break;
            }
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }

        return res;
    }

    public static int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        Queue<TreeNode> q1 = new LinkedList<>();
        ArrayList<Integer> resList = new ArrayList<>();
        TreeNode node = null;
        q1.offer(root);
        while (!q1.isEmpty()) {
            node = q1.poll();
            if (node.left != null) q1.offer(node.left);
            if (node.right != null) q1.offer(node.right);
            resList.add(node.val);
        }
        return resList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            if (k > i) {
                return true;
            }
            k = Math.max(k, i + nums[i]);
        }
        return false;
    }


    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList();
        ArrayList s = new ArrayList();
        List<List<Integer>> res = new LinkedList<>();
        q.offer(root);
        TreeNode node = null;
        while (!q.isEmpty()) {

            List<Integer> temp = new LinkedList<>();
            for (int i = q.size(); i > 0; i--) {
                node = q.poll();
                temp.add(q.poll().val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            res.add(temp);

        }
        return res;
    }

    public static boolean binarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        boolean search = false;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                search = true;
                break;
            }
            if (nums[mid] > target) end = mid;
            else start = mid;
        }
        return search;
    }

    public char firstUniqChar(String s) {
        char C = ' ';
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                C = s.charAt(i);
                break;
            }
        }
        return C;
    }

    static class TreeNode {
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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void treePrint(TreeNode root){
        if(root ==null) {  return;}
        System.out.println(root.val);
        treePrint(root.left);
        treePrint(root.right);
    }
    public static int numIslands(char[][] grid) {
        int landsum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    landsum += 1;
                    dfsZero(grid, i, j);
                }
            }
        }
        return landsum;
    }

    public static void dfsZero(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfsZero(grid, i + 1, j);
        dfsZero(grid, i - 1, j);
        dfsZero(grid, i, j + 1);
        dfsZero(grid, i, j - 1);
    }

    public static int[] singleNumbers(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            n ^= nums[i];
        }
        int target = 1;//初始位0001
        while ((target & n) == 0) {//如果target第一个二进制位不为1，就将target左移一位位0010，然后与相与，判断ret第二位是否为一.按此循环，知道找到ret的第一个为1的二进制位
            target = target << 1;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & target) == 0) {//第一组
                a ^= num;
            } else {//第二组
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    public String minNumber(int[] nums) {
        String[] strnum = new String[nums.length];

        for (int i = 0, len = nums.length; i < len; i++) {
            strnum[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strnum, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = strnum.length; i < len; i++) {
            sb.append(strnum[i]);
        }
        return sb.toString();
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> tmp = new HashMap<>();
        Integer i = 1;
        ListNode tempnode = headA;
        while (tempnode != null) {
            tmp.put(headA, i++);
            tempnode = tempnode.next;
        }
        tempnode = headB;
        while (tempnode != null) {
            if (tmp.containsKey(tempnode)) return tempnode;
            tempnode = tempnode.next;
        }
        return null;
    }

    public static int[] exchange(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            if (nums[start] % 2 == 0 && nums[end] % 2 != 0) {
                nums[start] = nums[start] ^ nums[end];
                nums[end] = nums[start] ^ nums[end];
                nums[start] = nums[start] ^ nums[end];
                start++;
                end--;
            }
            if (start == end) {
                start++;
                end--;
            }
            if (nums[start] % 2 != 0) {
                start++;
            }
            if (nums[end] % 2 == 0) {
                end--;
            }
        }
        return nums;

    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {

        return null;
    }

//输入一个字符串，打印出该字符串中字符的所有排列。
//
//
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//
//
//
// 示例:
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
//
//
//
//
// 限制：
//
// 1 <= s 的长度 <= 8
// Related Topics 字符串 回溯 👍 423 👎 0
    public String[] permutation(String s) {
        return null;
    }

    public static int add(int a, int b) {
        System.out.println(a ^ b);
        System.out.print((a & b) << 1);
        return b == 0 ? a : add(a ^ b, (a & b) << 1);
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0, len = grid.length; i < len; i++) {
            for (int j = 0, len2 = grid[0].length; j < len2; j++) {
                if (grid[i][j] == 1) {
                    int s = dfsZero(grid, i, j);
                    maxArea = Math.max(maxArea, s);
                }
            }
        }
        return maxArea;
    }

    public static int dfsZero(int[][] grid, int i, int j) {
        System.out.println(i + "::" + j);
        if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || 0 == grid[i][j]) {
            return 0;
        }
        grid[i][j] = 0;
        int area = dfsZero(grid, i - 1, j) +
                dfsZero(grid, i + 1, j) +
                dfsZero(grid, i, j - 1) +
                dfsZero(grid, i, j + 1) + 1;
        return area;
    }

    public static boolean isHappy(int n) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        while (true) {
            if (map.containsKey(n)) return n == 1;
            map.put(n, true);
            n = getSum(n);
        }
    }

    public static int getSum(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2, len = nums.length; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] path = new int[m][n];
        for (int i = 0; i < m; i++) {
            path[0][m] = 1;
        }
        for (int i = 0; i < n; i++) {
            path[n][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                path[i][j] = path[i - 1][j] + path[i][j - 1];
            }
        }
        return path[m - 1][n - 1];
    }

    public List<List<String>> solveNQueens(int n) {
        char[][] queue = new char[n][n];
        Arrays.fill(queue, '.');


        return null;
    }

    public boolean containQueueList(char[][] queue, int x, int y) {
        return false;
    }


    public boolean isRightPosition(char[][] queue, int x, int y) {


        return true;
    }

    /**
     * 890. 查找和替换模式
     *
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
       List<String>  res = new ArrayList<>();
        for (String word:words) {
            if(isMatch(word,pattern)){
                res.add(word);
            }
        }
        return res;
    }
    public static boolean isMatch(String word, String pattern) {
        if (word.length() != pattern.length()) return false;
        HashMap<Character, Character> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, len = word.length(); i < len; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                sb.append(map.get(pattern.charAt(i)));
                continue;
            }
            if(!map.containsKey(pattern.charAt(i))&&map.containsValue(word.charAt(i))){
                return  false;
            }
            map.put(pattern.charAt(i), word.charAt(i));
            sb.append(word.charAt(i));
        }
        return sb.toString().equals(word);
    }
    /**1328. 破坏回文串
     *
     * @param palindrome
     * @return
     */
    public  static String breakPalindrome(String palindrome) {
        if(palindrome.length() == 1)
            return "";
        int len = palindrome.length() / 2;
        char[] letter = palindrome.toCharArray();
        for(int i = 0; i < len; i++)
        {
            if(letter[i] != 'a')
            {
                letter[i] = 'a';
                break;
            }
        }
        if(palindrome.equals(String.valueOf(letter)))
            letter[letter.length - 1] = 'b';
        return String.valueOf(letter);

    }

    /**5894. 至少在两个数组中出现的值*
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @return
     */
    public static List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
     List<Integer>  res = new ArrayList<>();
     HashMap<Integer,Integer> map= new HashMap<>();
       for (int i = 0,len=nums3.length; i <len ; i++) {
             map.put(nums3[i],3);
        }
        for (int i = 0,len=nums2.length; i <len ; i++) {
            if(map.containsKey(nums2[i])&&map.get(nums2[i])!=2){
                res.add(nums2[i]);
            }
            map.put(nums2[i],2);
        }
        for (int i = 0,len=nums1.length; i <len ; i++) {
            if(map.containsKey(nums1[i])){
                res.add(nums1[i]);
            }
        }
     return  res.stream().distinct().collect(Collectors.toList());
    }

    /**5895. 获取单值网格的最小操作数
     *
     * @param grid
     * @param x
     * @return
     */
    public static int minOperations(int[][] grid, int x) {
        int  row= grid.length;
        int  col= grid[0].length;
        boolean[][]vistied = new boolean[row][col];
        for (int i = 0; i <row ; i++) {
            for (int j = 0; j < col; j++) {
                if(Search(i,j,grid,vistied,x,grid[i][j])){
                    return grid[i][j];
                }
            }
        }
        return  -1;
    }
    /**以当前坐标进行搜索判断是否能够使得所有元素等于 grid[i]
     * return boolean
     */
    public static   boolean Search(int row,int column,int[][] grid,boolean[][]vistied,int x,int target){
        if(row>= grid.length||row<0||column>= grid[0].length-1||column<0||vistied[row][column]){
            return true;
        }
        if(!CanEqualsValue(grid[row][column],x,target)){
            return  false;
        }
        vistied[row][column]=true;
        boolean  res = Search(row+1, column, grid, vistied, x,target)&&
                       Search(row-1, column, grid, vistied, x,target)&&
                       Search(row, column+1, grid, vistied, x,target)&&
                       Search(row, column-1, grid, vistied, x,target);
        vistied[row][column]=false;
        return  res;
    }
    public static boolean  CanEqualsValue(int value,int x,int target){
        if(value==target) return  true;
        if(value<target){
            while (value<=target){
                if(value==target) return  true;
                if(value>target) return false;
                value+=x;
            }
        }
        else{
            while(value>=target){
                if(value==target) return  true;
                if(value<target) return false;
                value-=x;
            }
        }
        return  false;
    }

    /**1436. 旅行终点站
     *
     * @param
     */
    public static String destCity(List<List<String>> paths) {
        String  res  ="";
      HashMap<String,String>  map  = new HashMap<>();
        paths.forEach(node->{
            map.put(node.get(0),node.get(1));
        });
        if(paths.size()==1) {
            return  paths.get(0).get(1);
        }
        for (int i = 0,len= paths.size(); i <len ; i++) {
           if(!map.containsKey(paths.get(i).get(1))&&map.containsValue(paths.get(i).get(1))){
               res = paths.get(i).get(1);
           }
        }
        return  res;
    }

    /**
     *
     * @param n
     * @return
     */
    public static List<String> fizzBuzz(int n) {
        List<String>  res  = new LinkedList<>();
        for (int i = 1; i <=n ; i++) {
            res.add(i%3==0&&i%5==0?"FizzBuzz":(i%3==0?"Fizz":(i%5==0?"Buzz":String.valueOf(i))));
        }
        return  res;
    }


    /**剑指 Offer II 103. 最少的硬币数目
     *
     * @param coins
     * @param amount
     * @return
     */
    public static  int coinChange(int[] coins, int amount) {



        return   -1;
    }

    public static String countAndSay(int n) {
     String  str = "1";
     int len  =str.length();
        for (int i = 2; i <=n ; i++) {
            StringBuilder  sb  =new StringBuilder();
            int start =0;
            int pos=0;
            while(pos<len){
                while(pos<len&&str.charAt(pos)==str.charAt(start)){
                    pos++;
                }
                sb.append(Integer.toString(pos-start)).append(str.charAt(start));
                start=pos;
            }
            str= sb.toString();
        }
        return  str;

    }

    public static String newNsum (String  pren){
        HashMap <Character,Integer> map  = new HashMap<>();
        StringBuilder sb  =new StringBuilder();
        map.put(pren.charAt(0),1);
        int len=pren.length();
        for (int i = 1; i<len;i++) {
          if(map.containsKey(pren.charAt(i))){
              map.put(pren.charAt(i),map.get(pren.charAt(i))+1);
              continue;
          }
          map.put(pren.charAt(i),1);
          sb.append(map.get(pren.charAt(i-1))).append(pren.charAt(i-1));
          map.remove(pren.charAt(i-1));
        }
        sb.append(map.get(pren.charAt(len-1))).append(pren.charAt(len-1));
        return  sb.toString();
    }
    public static int peakIndexInMountainArray(int[] arr) {
        int left  =0;
        int right = arr.length;
        while(left<right){
            int mid= (left+right)/2;
            if(arr[mid-1]<arr[mid]&&arr[mid]>arr[mid+1]){
                return  mid;
            }
            if(arr[mid]>arr[mid+1]){
                right=mid;
            }else{
                left=mid;
            }

        }
        return -1;


    }

    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception
    {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);// 设置年月日，最少3个参数
        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        Calendar curr = min;
        while (curr.before(max))
        {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    public static int findComplement(int num) {
        int mask  = -1;
        while((num&mask)>0){
            System.out.println("sasa"+(num&mask));
            mask<<=1;
        }
        return(~mask)^num;
    }
    public static  boolean isNullobj(Object o){
        return  true;
    }
    public int minMoves(int[] nums) {
       int min = Integer.MAX_VALUE;
     for (int num: nums){
       min  =  Math.min(num,min);
     }
     int ans =0;
     for(int num:nums){
         ans+=num-min;
     }
     return  ans;
    }
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    HashMap<Integer,Integer>  map   =  new HashMap<>();
        for (int i = 0,len=nums2.length; i <len ; i++) {
              if(i==len-1){
                  map.put(nums2[i],-1);
                  break;
              }
              map.put(nums2[i],nums2[i+1]);
        }
        int[]  res  = new int[nums1.length];
        for (int i = 0,len=nums1.length; i <len ; i++) {
            res[i]  = nums1[i]>map.get(nums1[i])?-1:map.get(nums1[i]);
        }
        return  res;


    }

    public static long findNb(long m) {
        // your code
        int i  = 1 ;
        int sum = 0 ;

        while(sum<m){
            i++;
           int tmp= i*(i+1)/2;
           sum = tmp*tmp;
        }
        return  sum == m?i:-1;
    }

    public static String accum(String s) {
        // your code
        StringBuilder  sb  = new StringBuilder();
        int charnum=0;
        for (int i = 0,len=s.length(); i <len ; i++) {
            if(('a'<=s.charAt(i)&&s.charAt(i)<='z')||('A'<=s.charAt(i)&&s.charAt(i)<='Z')){
                charnum++;
                for (int j = 0; j <charnum ; j++) {
                    if(j==0){
                        sb.append("-")
                                .append(String.valueOf(s.charAt(i)).toUpperCase());
                    }
                    sb.append(String.valueOf(s.charAt(i)).toLowerCase());
                }
            }else{
                sb.append(s.charAt(i));
            }

        }

        return  sb.substring(1);

    }

    public static boolean getXO (String str) {
        // Good Luck!!
        int xnum=0;
        int onum=0;
        str=str.toLowerCase();
        for (int i = 0,len=str.length(); i <len ; i++) {
            if(str.charAt(i)=='x'){
               xnum++;
            }
            if(str.charAt(i)=='o'){
                onum++;
            }
        }
        return  onum==xnum;
    }


    public static int persistence(long n) {
        // your code
        int sum=0;
        while(n>9){
            n=getNum(n);
            sum++;
        }
        return sum;
    }

    public static long getNum(long n){
        long res =1;
        while(n!=0){
            res*=n%10;
            n=n/10;
        }

        return res;
    }

    public static boolean scramble(String str1, String str2) {
        // your code
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0,len=str1.length(); i <len ; i++) {
            if(map.containsKey(str1.charAt(i))){
                map.put(str1.charAt(i),map.get(str1.charAt(i))+1);
                continue;
            }
            map.put(str1.charAt(i),1);
        }

        for (int i = 0,len=str2.length(); i <len ; i++) {
            if(!map.containsKey(str2.charAt(i))) {return  false;}
            if(map.containsKey(str2.charAt(i)) ){
                if(map.get(str2.charAt(i))  <=0){
                    return false;
                }
                map.put(str2.charAt(i),map.get(str2.charAt(i))-1);
            }
        }

        return  true;
    }


    public static String camelCase(String input) {



        return null;
    }

    public String spinWords(String sentence) {
        String []  strArr =sentence.split(" ");
        for (int i = 0,len=strArr.length; i <len ; i++) {
            if(strArr[i].length()>=5){
                strArr[i] = rervers(strArr[i]);
            }
        }
        return Arrays.stream(strArr).toString();
    }

    public  static   String rervers(String s){
        char []  chars  = s.toCharArray();
        char temp;
        for (int i =chars.length-1,len=chars.length-1; i < Math.floor(chars.length/2); i++) {
            temp = chars[len-i];
            chars[len-i] = chars[i];
            chars[i] = temp;
        }
        return String.valueOf(chars);
    }


    // 786. 第 K 个最小的素数分数
    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        if(arr.length<=2){
            return  arr;
        }
        if(k==1) return new int[]{1,arr[arr.length-1]};
        PriorityQueue<int []> q =new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0]*o2[1],o2[0]*o1[1]);
            }
        });

                for (int i = 0,len=arr.length; i <len ; i++) {
                    for (int j = i+1; j <len ; j++) {
                        q.add(new int[]{arr[i],arr[j]});
                    }
                }

        for (int i = 0; i <k ; i++) {
             q.poll();
        }
        return  q.poll();
    }

    public static int findNthDigit(int n) {
        if (n <= 9) return n;
        long start=1;
        long count=9;
        int digit=1;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long number =  start+(n-1)/digit;
        return  Long.toString(number).charAt((n - 1) % digit) - '0';

         }




    public void dfsSearch(int [][] array){
     int  row = array.length;
     int  col = array[0].length;
     boolean [][] visit =new boolean[row][col];
     int i=0,j=0;
     int  total=row*col;
     StringBuilder  sb  = new StringBuilder();
     while(total>0){
         if(i<=row-1 && j<= col-1 && !visit[i][j]){
             sb.append(array[i][j++]);
         }






     }
    }


    public static int sumIntervals(int[][] intervals) {
        // TODO: implement this method
        if ( intervals==null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        int sum =0;
        int start =intervals[0][0],end=intervals[0][1];
        for (int[]  v:intervals) {
            if(v[0]>start && v[0]>end){
                sum+=end-start;start=v[0];end=v[1];
            }else{
                end = Math.max(end,v[1]);
            }
        }
        return sum+=end-start;
       /* return intervals == null ? 0 : (int) Arrays.stream(intervals)
                .flatMapToInt(interval -> IntStream.range(interval[0], interval[1]))
                .distinct()
                .count();*/
       // return megereIntervals(intervals).stream().mapToInt(e -> e[1] - e[0]).sum() ;
    }
    public static List<int[]> megereIntervals(int [][] intervals){
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        List<int[] > res = new ArrayList<>();
        for (int i = 0,len=intervals.length; i <len ; i++) {
            int start = intervals[i][0] ,end = intervals[i][1];
            if(res.size()==0|| res.get(res.size()-1)[1] < start){
                res.add(new int[]{start, end});
            }
            else{
                res.get(res.size()-1)[1] = Math.max(res.get(res.size()-1)[1], end);
            }
        }
        return  res;
    }

    public static int longestSlideDown(int[][] pyramid) {
        // Code Goes Here..
        Stack<int []> stack =  new Stack<>();
        for (int [] item: pyramid) { stack.push(item); }
        int [] res = stack.pop();
        while(!stack.isEmpty()){
            int [] tmp = stack.pop();
            for (int i = 0,len=tmp.length; i <len ; i++) {
                tmp[i] = Math.max(tmp[i]+res[i],tmp[i]+res[i+1]);
            }
            res = tmp;
        }
        return  res[0];
    }

    public static String part(long n) {
        // your code

        return "";
    }
    public  List<int []> step_one(long n){

     return null;
    }
    public static long nextSmaller(long n)
    {
        char[] carr = String.valueOf(n).toCharArray();
        int len = carr.length, i;
        for (i = len - 1; i > 0; i--) {
            if (carr[i] < carr[i - 1]) break;
        }
        if (i == 0) return -1;
        else {
            int x = carr[i - 1], min = i;
            for (int j = i + 1; j < len; j++) {
                if (carr[j] < x && carr[j] > carr[min]) {
                    min = j;
                }
            }
            char temp = carr[i-1];
            carr[i-1] = carr[min];
            carr[min] = temp;
            String[] sarr = String.valueOf(carr).split("");
            java.util.Arrays.sort(sarr, i, len, java.util.Collections.reverseOrder());
            long r = Long.valueOf(String.join("", sarr));
            return String.valueOf(r).length() == len ? r : -1;
        }
    }

    public static String SwapChar(String str, int a, int b)
    {
        char[] newStr = str.toCharArray();
        newStr[a] = str.charAt(b);
        newStr[b] = str.charAt(a);
        return new String(newStr);
    }
    public  static  boolean  invalid(String s){
        if(s.length()==1) {return  true;}
        if(s.replaceAll(String.valueOf(s.charAt(0)),"").length()==0) {return  true;}
        Integer  min  = Integer.parseInt(String.valueOf(s.charAt(0)));
        for (int i = 1,len=s.length(); i <len ; i++) {
            min = Math.min(min,Integer.parseInt(String.valueOf(s.charAt(i))));
        }
        if(min == Integer.parseInt(String.valueOf(s.charAt(0)))) {return  true;}
        return  false;
    }

    public int getMaxDep(TreeNode root){
       if(root ==null) return 0;
       int rightdep = getMaxDep(root.right)+1;
       int leftdep  = getMaxDep(root.left)+1;
       return  Math.max(rightdep,leftdep);
    }

    public int[][] merge(int[][] intervals) {
      Arrays.sort(intervals,(o1,o2)->Integer.compare(o1[0],o2[0]));
      List<int[]> res = new ArrayList<>(16);
        int start =intervals[0][0],end=intervals[0][1];
        for (int [] t : intervals) {
            if(t[0]>end){
                res.add(new int[]{start,end});
                start=t[0];
                end=t[1];
            }
            else {
                end=Math.max(end,t[1]);
            }
        }
        res.add(new int[]{start,end});
        return  res.toArray(new int[res.size()][]);
    }

    public  static  int threeSumClosest(int[] nums, int target) {
        if(nums.length==3) return  Arrays.stream(nums).sum();
        Arrays.sort(nums);
        int sum=0;
        int len =nums.length;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i <len ; i++) {
            if(i>0&&nums[i]==nums[i-1]) continue;
            int l = i+1;
            int r = len-1;
            while(l<r){
                int tmp = nums[i] + nums[l]+ nums[r];
                if(tmp==target) return  tmp;

                if(Math.abs(tmp-target)<min){
                    min=Math.abs(tmp-target);
                    sum=tmp;
                }
                if(tmp>target){
                    r--;
                }else {
                    l++;
                }
            }
        }
          return sum;
    }

    public static List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    public static void recoverTree(TreeNode root) {

        reTree(root);

    }

    public static void reTree(TreeNode treeNode){
        if(treeNode==null) return;

        if( treeNode.left!=null&& treeNode.left.val> treeNode.val){
               int tmp = treeNode.left.val;
             treeNode.left.val = treeNode.val;
             treeNode.val = tmp;
        }

        if(treeNode.right!=null&& treeNode.right.val< treeNode.val){
            int tmp = treeNode.right.val;
            treeNode.right.val = treeNode.val;
            treeNode.val = tmp;
        }
        reTree(treeNode.left);
        reTree(treeNode.right);
    }


    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int len = hand.length;
        if(len==0||len%groupSize!=0){
            return  false;
        }
        if(groupSize==1){
            return  true;
        }

       return  canAccess(hand, groupSize);
    }
    public static boolean canAccess(int[] hand, int groupSize){
        boolean res = true;
        Arrays.sort(hand);
        int  start=0;
        int  end  = groupSize-1;
        while (start<hand.length){
            if(hand[end]-hand[start]!= groupSize-1){
                res=false;
                break;
            }
                start+=groupSize;
                end  +=groupSize;
        }
        return res;
    }

    /*1-9  12
        10 - 99 180
        100 - 999  2700
        1000-9999 2701-36000
        10000-99999 36001-450000
        */
    public static void main(String[] args) throws Exception {
        System.out.println(numberOfMatches(14));
       // System.out.println(isNStraightHand(new int[]{1,2,3,6,2,3,4,7,8},3));
        TreeNode root =new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(2);
        //reTree(root);
        //treePrint(root);
        int [] s  = new int[]{6,1,-2,-4};
        //  System.out.println(letterCombinations("23"));
        // System.out.println(threeSumClosest(s,1));
        // System.out.println("16#*2".replaceAll("[10#*]",""));
        // String  s = "1234567";
        //System.out.println(Arrays.toString(s.toCharArray()));
        int i=6;
        // System.out.println(nextSmaller(236));
        // 4948   9  00009
        //4948   0  90009
        //       0  99000
        //System.out.println(longestSlideDown(new int[][]{{3}, {7, 4}, {2, 4,  6}, {8, 5, 9, 3}}));
        // System.out.println(findNthDigit(1000000000));
        /* System.out.println(kthSmallestPrimeFraction(new int[]{1,2,3,5},3));
        System.out.println(persistence(39));*/

        /*
            String  AllDemo  = "camelCasing";
            System.out.println(AllDemo.replaceAll("([A-Z])"," $1"));
        */


        // System.out.println(accum("abcd"));
        int js = 2147483647;
        System.out.println(js+1);
        // System.out.println(findNb(1951195342281763600L));


        String  str = "xxxoool";

        System.out.println();

        // System.out.println(findComplement(5));

        /* for (String string : getMonthBetween("2008-01", "2020-06"))
        {
            System.out.println(string);
        }
        System.out.println(new Date());*/
        // 311221
        //System.out.println(newNsum("11"));
        HashMap map  = new HashMap();
        map.put("1",1);
        int [] nums1 =  new int[]{40,48,61,75,100,99,98,39,30,10}, nums2 = new int[]{2,3}, nums3 = new int[]{3};
        /*System.out.println(peakIndexInMountainArray(nums1));
        int [][] grid = new int[][]{{2,4},{6,8}};
        System.out.println(CanEqualsValue(2,2,4));
        System.out.println(Arrays.toString(fizzBuzz(3).toArray()));*/
        //System.out.println( twoOutOfThree(nums1,nums2,nums3));
        //System.out.println(percent(0, 0));
         /*    List<?extends String> strings  = new LinkedList<>();
        String [] nums = new String[]{"2","21","12","1"};
        int   []  num = new int[]{1};
        ListNode  node = new ListNode(1);
        node.next=new ListNode(3);
        node.next.next=new ListNode(2);
        majorityElement(num);
        Arrays.stream(getLeastNumbers(num,2)).forEach(System.out::println);*/
        // int [] nums = new int[]{6,18,27,40,46,57,59,66,72,91};
        // int[][] grid = new int [][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        // System.out.println(binarySearch(nums,59));
        // exchange(nums);
        //  System.out.println(add(19,1));
        // System.out.println(isMatch("ccc","abb"));;
        // System.out.println(isHappy(19));
        // System.out.println(maxAreaOfIsland(grid));
    }

    public boolean increasingTriplet(int[] nums) {
       int a= nums[0];
       int b = Integer.MAX_VALUE;
        for (int i = 1,len=nums.length; i <len ; i++) {
              if(nums[i]<= a) {a=nums[i];}
              else if(nums[i]<=b){b=nums[i];}
              else {return  true;}
        }
        return  false;

    }
    //1688. 比赛中的配对次数
    public static int numberOfMatches(int n) {
     return numberOfMatches(n,0);
    }
    public static int numberOfMatches(int n,int value){
     if(n == 1) return  value;
        value +=  n%2==0?n/2:(n-1)/2;
        n= n%2==0?n/2:(n-1)/2+1;
     return numberOfMatches(n,value);
    }


}
