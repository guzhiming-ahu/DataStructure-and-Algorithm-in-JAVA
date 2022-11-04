package com.xtremeglory.problem.list;

import com.xtremeglory.data_structure.Stack;
import com.xtremeglory.data_structure.impls.iteration.list.ArrayStack;

//解析表达式的后缀表达式，并计算该后缀表达式
public class SuffixExpression {

    public static int nextNum(char c) {
        return '0' <= c && c <= '9' ? (c - '0') : -1;
    }

    /**
     * 栈外符号优先级
     * 1.为什么需要为符号指定优先级？
     * 符号优先级用于模拟计算过程中的先后顺序，例如乘除法总是优先于加减法之前运算。
     * 在计算时，更高优先级的运算总是处于栈顶，当未运算符号优先级更高时，则入栈；接近栈顶的元素总是最先弹出参与运算，所以体现了运算优先顺序
     * 此外，当未运算符号优先级更低时，栈顶弹出进行运算，直到站内符号的优先级比未运算符号更低时停止出栈，未运算符号入栈，也同样体现运算优先顺序
     * 2.括号的优先级？
     * 一对括号内的表达式可以视为一个后缀表达式,它相对于括号外的表达式式独立的，是被整体计算的一部分
     * 括号在栈外时，’(‘的优先级最高，’)‘的优先级最低
     * 括号在栈内时，’(‘的优先级最低，’)‘的优先级最高
     * 这样能保证一对括号内的表达式能被整体计算，
     * 1）因为’(‘任何情况下都能压栈，入栈后优先级降低为最低，之后括号内的表达式被不断被压栈
     * 2）遇到’)‘表示表达式的结尾，会一直出栈直到遇到匹配的‘(’，实现了括号内表达式被整体计算
     * 3.为什么要区分栈内和栈外符号的优先级？
     * 因为括号的存在，需要入栈前优先级高，入栈后优先级低，以实现括号内表达式被整体计算
     * 此外，同类型符号栈外总是高于栈内，使得累加/累乘等运算可以实现
     *
     * @param sym 未运算的符号
     * @return 符号优先级
     */
    public static int priorityOutStack(char sym) {
        switch (sym) {
            case ')':
                return 1;
            case '+':
            case '-':
                return 3;
            case '*':
            case '/':
                return 5;
            case '(':
                return 7;
            default:
                return -1;
        }
    }

    public static int priorityInStack(char sym) {
        switch (sym) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 4;
            case ')':
                return 6;
            default:
                return -1;
        }
    }

    /**
     * 转换中缀表达式为后缀表达式
     *
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    public static String f(String expression) {
        Stack<String> expStk = new ArrayStack<>();
        Stack<Character> symStk = new ArrayStack<>();
        int index = 0;
        while (index < expression.length()) {
            int num = nextNum(expression.charAt(index));
            if (num != -1) {
                expStk.push(String.valueOf(num));
            } else {
                if (symStk.isEmpty() || priorityOutStack(expression.charAt(index)) > priorityInStack(symStk.top())) {
                    symStk.push(expression.charAt(index));
                } else {
                    StringBuilder suffix = new StringBuilder();
                    suffix.append(expStk.pop());
                    if (expression.charAt(index) == ')') {
                        while (!expStk.isEmpty() && symStk.top() != '(') {
                            suffix.append(expStk.pop());
                            suffix.append(symStk.pop());
                        }
                        expStk.push(suffix.toString());
                        symStk.pop();
                    } else {
                        while (!expStk.isEmpty() && priorityOutStack(expression.charAt(index)) < priorityInStack(symStk.top())) {
                            suffix.append(expStk.pop());
                            suffix.append(symStk.pop());
                        }
                        expStk.push(suffix.toString());
                        symStk.push(expression.charAt(index));
                    }
                }
            }
            index++;
        }
        StringBuffer suffix = new StringBuffer(expStk.pop());
        while (!expStk.isEmpty()) {
            suffix.append(expStk.pop());
            suffix.append(symStk.pop());
        }
        System.out.println(suffix);
        return suffix.toString();
    }

    /**
     * 计算后缀表达式
     *
     * @param suffix 后缀表达式
     * @return 后缀表达式的值
     */
    public static double g(String suffix) {
        Stack<Double> numStk = new ArrayStack<>();
        int index = 0;
        while (index < suffix.length()) {
            int num = nextNum(suffix.charAt(index));
            if (num != -1) {
                numStk.push((double) num);
            } else {
                switch (suffix.charAt(index)) {
                    case '+':
                        numStk.push(numStk.pop() + numStk.pop());
                        break;
                    case '-':
                        numStk.push(numStk.pop() - numStk.pop());
                        break;
                    case '*':
                        numStk.push(numStk.pop() * numStk.pop());
                        break;
                    case '/':
                        numStk.push(numStk.pop() / numStk.pop());
                        break;
                }
            }
            index++;
        }
        return numStk.pop();
    }
}
