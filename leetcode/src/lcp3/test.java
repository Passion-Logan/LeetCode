package lcp3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * LCP 3. 机器人大冒险<p>
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 *
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 *
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/programmable-robot
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 *
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 *
 *
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 *
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *
 *
 * 限制：
 * 2 <= command的长度 <= 1000
 * command由U，R构成，且至少有一个U，至少有一个R
 * 0 <= x <= 1e9, 0 <= y <= 1e9
 * 0 <= obstacles的长度 <= 1000
 * obstacles[i]不为原点或者终点
 *
 *
 * @author WQL
 * @since 2019年12月19日 0019 17:23
 */
public class test {

    public static void main(String[] args) {
        test test = new test();
        /*int x = 3, y = 2;
        String command = "URR";
        int[][] obstacles = {};*/
        /*int x = 1486, y = 743;
        String command = "RRU";
        int[][] obstacles = {{5, 5}, {9, 4}, {9, 7}, {6, 4}, {7, 0}, {9, 5}, {10, 7}, {1, 1}, {7, 5}};*/
        /*int x = 410, y = 6491;
        String command = "RRU";
        int[][] obstacles = {{6, 2}, {1, 4}, {8, 0}, {5, 3}, {6, 3}, {1, 2}, {2, 2}, {4, 0}, {9, 5}};*/
        int x = 7856, y = 9033;
        String command = "RUUR";
        int[][] obstacles = {{10, 5}, {1, 6}, {6, 10}, {3, 0}, {0, 3}, {0, 10}, {6, 2}};

        Arrays.sort(obstacles, (a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        System.out.println(Arrays.deepToString(obstacles));

        int maxX = obstacles.length == 0 ? -1 : obstacles[obstacles.length - 1][0];
        int maxY = obstacles.length == 0 ? -1 : obstacles[obstacles.length - 1][1];
        int[][] path = test.line(command, x, y, maxX, maxY);


        // 获取全部路线
        System.out.println(Arrays.deepToString(path));
        // 判断是否能安全到达终点
        System.out.println(test.security(path, obstacles));


        System.out.println(test.robot(command, obstacles, x, y));
    }

    private int[][] line(String command, int x, int y, int maxX, int maxY) {
        int i = 0;
        boolean flag = true;

        char[] data = command.toCharArray();
        int length = data.length;
        int[] xs = new int[data.length];
        int[] ys = new int[data.length];

        if (maxX < 0) {
            return null;
        }

        while (flag) {
            if (i == 0) {
                xs[i] = data[i] == 'R' ? 1 : 0;
                ys[i] = data[i] == 'U' ? 1 : 0;
            } else {
                xs[i] = data[i] == 'R' ? xs[i-1]+1 : xs[i-1];
                ys[i] = data[i] == 'U' ? ys[i-1]+1 : ys[i-1];
            }
            if ((xs[i] > maxX && ys[i] > maxY) || (xs[i] == x && ys[i] == y)) {
                int[][] line = new int[i+1][2];
                int j = 0;
                while (j <= i) {
                    line[j][0] = xs[j];
                    line[j][1] = ys[j];
                    j++;
                }
//                System.out.println(Arrays.deepToString(line));
                return line;
            }
            i++;
            // 判断数组扩容或者缩容
            if (i == length) {
                xs = Arrays.copyOf(xs, i*2);
                ys = Arrays.copyOf(ys, i*2);
                int oldLength = data.length;
                data = Arrays.copyOf(data, data.length*2);
                for (int z = 0; z < oldLength; z++) {
                    data[oldLength+z] = data[z];
                }
                length = i * 2;
            }
        }

        return null;
    }

    private boolean security(int[][] path, int[][] obstacles) {
        if (obstacles.length == 0) {
            return true;
        }

        for (int i = 0; i < obstacles.length; i++) {
            for (int j = 0; j < path.length; j++) {
                if (path[j][0] > obstacles[i][0] && path[j][1] > obstacles[i][1]) {
                    continue;
                }
                if (path[j][0] == obstacles[i][0] && path[j][1] == obstacles[i][1]) {
                    System.out.println("[" + obstacles[i][0] + ", " + obstacles[i][1] + "]");
                    return false;
                }
            }
        }

        return true;
    }

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        //多次循环 找到模式
        //学到了新的存储坐标的方法  左坐标左移30 | 右坐标
        int xx=0,yy=0;
        Set<Long> ss=new HashSet<>();
        ss.add(((long)xx << 30) | yy);
        for(int i=0;i<command.length();i++){
            if(command.charAt(i)=='U'){
                yy++;
            }else{
                xx++;
            }
            ss.add(((long)xx << 30) | yy);
        }
        int cir=Math.min(x/xx,y/yy);
        if(ss.contains(((long)(x-cir*xx) << 30) | (y-cir*yy))==false){
            return false;
        }
        for(int[] s:obstacles){
            if(s.length!=2) continue;
            int x1=s[0];
            int y1=s[1];
            if(x1 >x || y1>y) continue;
            cir=Math.min(x1/xx,y1/yy);
            if(ss.contains(((long)(x1-cir*xx) << 30) | (y1-cir*yy))==true){
                return false;
            }
        }
        return true;
    }
}
