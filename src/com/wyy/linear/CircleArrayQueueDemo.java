package com.wyy.linear;

import java.util.Scanner;

/**
 * 环形数组队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args){
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列中");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); //接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value  = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                   try {
                       int  res = queue.getQueue();
                       System.out.printf("取出的数据是%d\n",res);
                   }catch (Exception e){
                       System.out.println(e.getMessage());
                   }
                   break;
                case 'h':
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                        break;
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

    //使用数组模拟一个队列--编写一个ArrayQueue类
    static class ArrayQueue{
        private int maxSize; //表示数组的最大容量
        private int front; //队列头
        private int rear; //队列尾
        private int[] arr; //该数据用于存放数据，模拟队列

        //创建队列的构造器
        public ArrayQueue(int arrMaxSize){
            maxSize  = arrMaxSize;
            arr = new int[maxSize];
            front = 0; //指向队列头部，即指向队列的第一个元素
            rear = 0; //指向队列的最后一个元素的后一个位置，希望空出一个约定位子
        }

        //判断队列是否满
        public  boolean isFull(){
            return  (rear + 1) % maxSize == front;
        }

        //判断队列是否为空
        public boolean isEmpty(){
            return rear == front;
        }

        //向队列中添加数据
        public  void  addQueue(int num){
            //判断队列是否满
            if(isFull()){
                System.out.println("队列满，不能再添加值");
                return;
            }
            //直接将数据加入
            arr[rear] = num;
            //rear后移，取模
            rear = (rear + 1)%maxSize;
        }

        //获取队列数据，出队列
        public  int getQueue(){
            //判断队列是否为空
            if (isEmpty()){
                throw  new RuntimeException("队列为空");
            }
            //先将值赋给临时变量，然后再取出队列头的值，front后移
            int value =  arr[front];
            front =( front+1)%maxSize;
            return  value;
        }

        //显示队列的所有数据
        public void showQueue(){
           if(isEmpty()){
               System.out.println("队列为空");
               return;
           }
            for (int i = front; i < front + size(); i++) {
                System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
            }
        }
        //获取当前队列的有效值的个数
        public  int size(){
            return  (rear + maxSize - front ) % maxSize;
        }

       //显示队列的头数据
        public  int  headQueue(){
            if( isEmpty()){
                throw new RuntimeException("队列为空");
            }
            return  arr[front + 1];
        }

    }

}
