package lcp1;

/**
 * LCP 1. 猜数字<p>
 *
 * 小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。他们一共进行三次这个游戏，请返回 小A 猜对了几次？
 * 输入的guess数组为 小A 每次的猜测，answer数组为 小B 每次的选择。guess和answer的长度都等于3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * 输入：guess = [1,2,3], answer = [1,2,3]
 * 输出：3
 * 解释：小A 每次都猜对了。
 *
 * 输入：guess = [2,2,3], answer = [3,2,1]
 * 输出：1
 * 解释：小A 只猜对了第二次。
 *
 * 限制：
 * guess的长度 = 3
 * answer的长度 = 3
 * guess的元素取值为 {1, 2, 3} 之一。
 * answer的元素取值为 {1, 2, 3} 之一。
 *
 * @author WQL
 * @since 2019年12月19日 0019 17:02
 */
public class Test {

    public static void main(String[] args) {

        Test test = new Test();

        int[] guess = {2,2,1};
        int[] answer = {3,2,1};

        System.out.println(test.game(guess, answer));
    }

    int game(int[] guess, int[] answer) {
        int num = 0;
        int i = 0;
        while (i < 3) {
            num += (guess[i] == answer[i] ? 1 : 0);
            i++;
        }
        return num;
    }


}
