package ch.lable;

public class Demo {
    public static void main(String[] args) {
        /**
         * 在一个二维整数矩阵中搜索目标值 target，找到后返回其坐标 (row, col)。若目标值不存在，返回 (-1, -1)。
         */
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int x = 11;
        out:
            for (int i = 0; i < matrix.length; i++) {
                int[] obj = matrix[i];
                for (int j = 0; j < obj.length; j++) {
                    int yuansu = obj[j];
                    if (yuansu == x) {
                        System.out.println("{" + (i+1) + "," + (j+1) + "} " );
                        break out;
                    }
                }
            }

        System.out.println("{" + (-1) + "," + (-1) + "} " );
        System.out.println("执行完成");
    }
}
