package hsp;

public class 环形单向队列问题 {

    /**
     * 设定 n 个人围城一圈，从第k个人开始，每报数m就出队列，直到所有的都出了队列为止
     */
    public static void main(String[] args) {
        int n = 5, m = 2, k = 1;

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.init(n);
        circleSingleLinkedList.showBoy();

        /*
         * 需求实现：需要一个辅助指针helper，指向first节点的前一个节点，因为如果我们删除first的话，要拿到前一个才行
         * 报数的时候，first和helper指针都往前走
         *
         * 出圈的时候  first = first.next  helper.next = first
         */
        circleSingleLinkedList.popRes(m,k,n);

    }

    static class CircleSingleLinkedList {
        private Boy first = null;

        /**
         * 1.构建单向环形链表
         *   a.先创建第一个节点，并让first指向节点，并形成环形(next指向自己)
         *   b.每新增一个节点，就加入之前的环形即可(因为是单向，设定一个current指针)
         */
        public void init(int n) {
            if (n < 1) {
                System.out.println("数量不对");
                return;
            }
            // 辅助指针
            Boy current = new Boy(-1);
            for (int i = 1; i <= n; i++) {
                Boy boy = new Boy(i);
                // 如果是第一个，自成环形先
                if (i == 1) {
                    first = boy;
                    first.next = boy;
                    current = first;
                } else {
                    current.next = boy;
                    boy.next = first;
                    current = boy;
                }
            }
        }

        /**
         * 遍历当前环形链表看看结果
         */
        public void showBoy() {
            if (first == null) {
                System.out.println("没有人");
                return;
            }
            Boy current = first;
            while (true) {
                System.out.println("小孩编号：" + current.no);
                if (current.next == first) {
                    break;
                }
                current = current.next;
            }
        }

        /**
         * 出圈
         * @param countNo   报数到几就出圈
         * @param startNo   第几个小孩开始
         * @param allNos    小孩总数
         */
        public void popRes(int countNo, int startNo, int allNos) {
            if (first == null || startNo > allNos || startNo < 1) {
                System.out.println("参数有问题");
                return;
            }
            // 先把helper移动到位置上
            Boy helper = first;
            while (helper.next != first) {
                helper = helper.next;
            }

            // 报数开始前，first 和 helper移动 startNo - 1  次
            int i = 1;
            while (i < startNo) {
                first = first.next;
                helper = helper.next;
                i++;
            }

            // 开始报数出圈，first 和 helper移动 countNo - 1 次，开始出圈
            while (true) {
                if (helper == first) {
                    System.out.println("最后一个出圈的小孩：" + first.no);
                    first = null;
                    return;
                }

                for (int k = 1; k < countNo; k++) {
                    first = first.next;
                    helper = helper.next;
                }
                System.out.println("出圈的小孩：" + first.no);
                first = first.next;
                helper.next = first;
            }
        }
    }



    static class Boy {
        private int no;
        private Boy next;
        public Boy(int no) {
            this.no = no;
        }
    }

}
