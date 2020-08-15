package com.wyy.linear;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static  void main(String[] args){

        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        //test3 测试单链表的反转
        System.out.println("反转前：");
        singleLinkedList.showList();

      /*  System.out.println("反转后：");
        reverseSingleLinkedList(singleLinkedList.getHead());
        singleLinkedList.showList();*/

        //测试test4
        System.out.println("反向输出为：");
        reversePrint(singleLinkedList.getHead());


       //测试插入指点节点位置
        /*singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);*/

        /*singleLinkedList.showList();
        //测试修改节点数据
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后：");
        //显示
        singleLinkedList.showList();

        //1.测试单链表的有效节点个数
        System.out.println("单链表的有效节点个数为:"+getLength(singleLinkedList.getHead()));
        //2.获取倒数第k个元素
        HeroNode node = getIndexNode(singleLinkedList.getHead(),5);
        System.out.println("倒数第k个节点是："+ node);*/


        //删除一个节点
        singleLinkedList.delete(1);
        singleLinkedList.delete(2);
        singleLinkedList.delete(3);
        singleLinkedList.delete(4);
        System.out.println("删除后:");
        singleLinkedList.showList();
    }

    //test4：将单链表反向输出
    public  static void reversePrint(HeroNode head){
        if(head.next ==null){
            return;
        }
        //创建一个栈，将节点依次放入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur !=null){
            stack.push(cur);
            cur= cur.next;
        }
        //输出栈中元素
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }


    //test3.反转单向链表
    public static  void reverseSingleLinkedList(HeroNode head){

        if(head.next ==null ||head.next.next ==null){
            return;
        }
        //1、先定义一个节点reverseHead = new HeroNode();
        //2、遍历链表，每遍历一个节点就将其取出，并放在新的链表reversHead的最前端
        //3、原来的链表的head.next = reverseHead.next
        HeroNode cur = head.next;
        HeroNode next = null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");

        while(cur!=null){
            next = cur.next ;   //保存当前节点的下一个节点
            cur.next = reverseHead.next;  //将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur链接到新的链表上
            cur = next; //cur后移
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;

    }

    //test2.查找单链表中倒数第K个节点
    public static HeroNode getIndexNode(HeroNode head,int index){
        if(head.next == null){
            return null;
        }
        //1.获取单链表的长度length
        //2.length - index  就是倒数第k个元素
        int length = getLength(head);
        int count  = 0;
        if(index < 0 || index >length){ //校验数据
            return null;
        }

        HeroNode temp = head.next;
        while(count != length - index ){
            count++;
            temp = temp.next;
        }
        return  temp;
    }

    //test1.方法：获取单链表的有效节点个数
    public static int getLength(HeroNode head){
        if(head.next ==null){
            return 0;  //空链表
        }
        int count = 0;//用于计数
        HeroNode temp = head.next;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    //定义一个SingleLinkedList管理英雄
    static class SingleLinkedList{
        //先初始化一个头节点，头节点不动（不存放数据）
        private HeroNode head = new HeroNode(0,"","");

        //返回头节点
        public  HeroNode getHead(){
            return head;
        }


        //添加节点到单向列表中
        //1.找到当前链表的最后节点
        //2.将最后这个节点的next指向新的节点
        public void add(HeroNode heroNode){
            //因为头节点不动，需要一个辅助遍历temp
            HeroNode temp = head;
            //遍历链表，找到最后一个节点
            while(true){
                if(temp.next == null){
                    break;
                }
                //如果没有找到最后一个节点，将temp后移
                temp = temp.next;
            }
            //将最后的节点的next指向新的节点
            temp.next =  heroNode;
        }
        //添加节点到指定的位置（排序）
        //1.找到当前链表的最后节点
        //2.将最后这个节点的next指向新的节点
        public void addByOrder(HeroNode heroNode){
            //因为头节点不动，需要一个辅助遍历temp
            HeroNode temp = head;
            Boolean flag = false; //用于记录，当前排名是否已存在
            //遍历链表，找到最后一个节点
            while(true){
                if(temp.next == null){
                    break;
                }
                if(temp.next.no > heroNode.no){ //就在temp的后面插入
                    break;
                } else if (temp.next.no == heroNode.no) {
                    flag = true;  //说明编号已存在
                    break;
                }
                temp =  temp.next; //后移，遍历当前链表
            }
            if(flag){
                //已存在该节点数据
                System.out.printf("要插入的数据的英雄编号%d已存在，不能重复插入\n",heroNode.no);
            }else{
                //1.将要插入的节点指向temp的下一个节点位置
                //2.将temp指向要插入的节点
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        //单链表节点的修改
        public  void update(HeroNode newHeroNode){
            //判断是否为空
            if(head.next == null){
                System.out.println("链表为空");
                return;
            }
            //定义一个辅助变量
            HeroNode temp = head.next;
            boolean flag = false; //表示是否找到该节点
            while(true){
                if(temp == null){
                    break; //已经遍历完
                }
                if(temp.no == newHeroNode.no){
                    //找到
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //根据flag判断是否找到要修改的节点
            if(flag){
                temp.name = newHeroNode.name;
                temp.nickName = newHeroNode.nickName;
            }else{
                System.out.printf("没有找到要修改的英雄编号%d\n",newHeroNode.no);
            }
        }

        //删除链表节点
        //1.需要一个temp辅助节点找到待删除节点的前一个节点
        //2.比较时 ，是temp.next.no 和需要删除节点的no比较
        public  void delete(int no){
            //设置一个temp
            HeroNode temp = head;
            boolean flag = false; //标志是否找到待删除节点的
            while(true){
                if(temp.next == null){
                    break;
                }
                if(temp.next.no == no){
                    //找到要删除的节点的前一个节点temp
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            //
            if(flag){
                temp.next  = temp.next.next;
            }else{
                System.out.println("要删除的节点不存在");
            }
        }


        //遍历链表
        public  void showList(){
            //判断链表是否为空
            if(head.next == null){
                System.out.println("链表为空链表");
                return;
            }
            //头节点不动，需要辅助变量遍历
            HeroNode temp = head.next;
            while(true){
                //判断是否到链表最后
                if(temp == null){
                    break;
                }
                //输出链表节点的信息
                System.out.println(temp.toString());
                //将temp后移，指向下一个节点
                temp =  temp.next;
            }
        }

    }




    //定义水浒英雄排名heroNode，每个HeroNode对象就是一个节点
    static class HeroNode{
        public int no;//英雄排名
        public String name;
        public String nickName;
        public HeroNode next;//指向下一个节点

        //构造器
        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }



        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
