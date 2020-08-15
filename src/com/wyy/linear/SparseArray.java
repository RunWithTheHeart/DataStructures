package com.wyy.linear;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static  void main(String[] args){
         //创建11*11的棋盘
          int arr[][] = new int[11][11];
          arr[0][2] = 1;
          arr[1][3] = 2;
          //遍历棋盘
        System.out.println("输出原始的二维数组");
        for (int[] row:arr) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转换为稀疏数组
        //1.判断数组中有效值的个数,创建稀疏数组
        int sum = 0;
        for (int i = 0; i <11; i++) {
            for (int j = 0; j < 11; j++) {
                if(arr[i][j] != 0){
                    sum++;
                }
            }
        }
        int [][] sparseArr =  new int[sum+1][3];
        //2.将有效值记入稀疏数组
        int count  =  0;//用于计数
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum; //有效值的个数
        for (int i = 0; i <11; i++) {
            for (int j = 0; j < 11; j++) {
                if(arr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }

        //遍历稀疏数组：
        System.out.println("转换的稀疏数组");
        for (int[] row:sparseArr) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        ////////////////////////////
        //将稀疏数组转换为原始数组
        //1.根据稀疏数组的第一行数据创建原始数组
        int convertArr[][] = new int [sparseArr[0][0]][sparseArr[0][1]];
        //2.遍历稀疏数组，赋值给原始二维数组
        for (int i = 1;i<=sum;i++) {
            convertArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //遍历还原的原始二维数组
        //遍历稀疏数组：
        System.out.println("遍历还原的原始二维数组：");
        for (int[] row:convertArr) {
            for (int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
