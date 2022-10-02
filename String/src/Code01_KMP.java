public class Code01_KMP {
    public static void main(String[] args) {
        int possibilites = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println(" 测试开始");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilites, strSize);
            String match = getRandomString(possibilites, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println(" 匹配错误Oops!");
            }
        }
        System.out.println("测试结束");
    }


    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int x = 0; // str1 中比对到的位置
        int y = 0; // str2 中比对到的位置
        // 没有必要有字符串开头的位置,只有比对到的位置,因为开头的位置可以通过长度推断出来
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) { // 当y越界的时候,说明一直相等,这时候就找到了,如果x越界说明所有的里面都没str2
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) { //y是第一个字符,即 y == 0
                x++;
            } else {
                y = next[y];
            }
        }
        // 这里实际上就是判断跳出while的条件,y越界说明str2滑过了整体匹配成功(x-y是因为 x跳到了下一个减去str2的长度),
        // x越界说明str1到头了都没匹配到
        return y == str2.length ? x - y : -1;
    }
    public static int[] getNextArray(char[] str2){
        if (str2.length == 1) {
            return new int[] { -1};
        }
        int[] next = new int[str2.length];

        //前两个数是确定的,没有例外
        next[0] = -1;
        next[1] = 0;
        int i = 2; // i指目前在哪个位置求next数组的值,不管跳到哪,是跟所在位置的前一个比,就是跟i - 1比
        int cn = 0; // cn跳到的位置的值跟i - 1 位置的值比,cn是由 i - 1 位置的next数组信息确定的,有值一样的,信息就是cn + 1,cn一开始在0,是因为1 位置的值跟1信息的位置的值比
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) { // 这里是相等的时候跳出来的值
                next[i++] = ++cn; // ++cn一是为了让cn+ 1,二是为了让下个i位置的cn是对的
            } else if (cn > 0) { // 一直比下去
                cn = next[cn];
            } else { // 比到 <=0 说明没有字串,这时候,信息也是0,i也要移动
                next[i++] = 0;
            }
        }
        return next;

    }
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for ( int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

}
