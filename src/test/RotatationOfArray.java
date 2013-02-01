/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author PenGuin
 */
public class RotatationOfArray {
    //向左旋转数组

    public static void method1(int[] test, final int rotateStep) {
        //直接一步一步向左移
        //costTime = 1000
        for (int j = 0; j < rotateStep; j++) {

            int temp = 0;
            int i;
            temp = test[0];

            for (i = 1; i < test.length; i++) {
                test[i - 1] = test[i];
            }

            test[i - 1] = temp;
        }

    }

    public static void method2(int[] test, final int rotateStep) {
        //借助于rotateStep长的数组
        //costTime = 1
        int[] buffer = new int[rotateStep];
        System.arraycopy(test, 0, buffer, 0, rotateStep);//这个比直接用循环来的快一些

        for (int i = rotateStep; i < test.length; i++) {
            test[i - rotateStep] = test[i];
        }
        System.arraycopy(buffer, 0, test, rotateStep, buffer.length);
    }

    public static void method3(int[] test, final int rotateStep) {
        //不借助于额外数组
        //2 > costTime > 1
        int temp = 0;
        int index1 = 0;
        int index2 = 0;
        int commonDivisor = gcdOfMethod3(test.length, rotateStep);//这个是数学家算出来的


        for (int i = 0; i < commonDivisor; i++) {
            temp = test[i];

            for (int j = 0; j < test.length; j++) {
                index1 = (j * rotateStep + i) % test.length;
                index2 = ((j + 1) * rotateStep + i) % test.length;

                if (index2 == i) {
                    break;
                }

                //System.out.println(index1);
                test[index1] = test[index2];
                //    System.out.println(index1);
            }

            test[index1] = temp;


        }
    }

    public static int gcdOfMethod3(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcdOfMethod3(b, a % b);
        }
    }

    public static void method4(int[] test, final int rotateStep, int start, int end) {
        int temp = 0;
        if ((end - (start + rotateStep) + 1) == rotateStep) {
            swapArrayOfMethod4(test, rotateStep, start, start + rotateStep);
            return;
        } else if ((end - (start + rotateStep) + 1) < rotateStep) {
            swapArrayOfMethod4(test, (end - (start + rotateStep) + 1), start, start + rotateStep);
            method4(test, (2 * rotateStep + start - end - 1), (end - rotateStep  + 1), end);
            return;
        } else {
            swapArrayOfMethod4(test, rotateStep, start, end - rotateStep + 1);
            method4(test, rotateStep, start, end - rotateStep);
            return;
        }

    }

    public static void swapArrayOfMethod4(int[] test, int swapLength, int startLeft, int startRight) {
        int temp = 0;
        for (int i = 0; i < swapLength; i++) {
            //     System.out.println(temp);
            temp = test[startLeft + i];
            test[startLeft + i] = test[startRight + i];
            test[startRight + i] = temp;
        }
    }

    public static void method5(int[] test, final int rotateStep) {
        //利用数学找到简单的解决方案
        //1 > costTime > 0
        reverseOfMethod5(test, 0, rotateStep - 1);
        reverseOfMethod5(test, rotateStep, test.length - 1);
        reverseOfMethod5(test, 0, test.length - 1);

    }

    public static void reverseOfMethod5(int[] test, int start, int end) {
        int temp = 0;
        while (start < end) {
            temp = test[start];
            test[start] = test[end];
            test[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        /*要执行的程序*/
        int[] test = new int[10];


        for (int i = 0; i < test.length; i++) {
            test[i] = i + 1;
        }

        int rotateStep = 9;
        if (rotateStep >= test.length) {
            rotateStep = rotateStep % test.length;
        }

        method4(test, rotateStep, 0, test.length - 1);

//
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }

        long end = System.currentTimeMillis();

        System.out.println("总时间 = " + (end - start));
    }
}
