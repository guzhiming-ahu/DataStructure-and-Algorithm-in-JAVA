package data_structure.line.stack;

public interface Stack<T> {

    /**
     * 判断是否为空栈
     *
     * @return 是否为空栈
     */
    boolean isEmpty();

    /**
     * 获取栈元素数量
     *
     * @return 元素数量
     */
    int size();

    /**
     * 入栈
     *
     * @param elem  入栈元素
     */
    void push(T elem);

    /**
     * 入栈
     *
     * @param elem 入栈元素
     */
    default void pushIfNotNull(T elem) {
        if (elem != null) {
            push(elem);
        }
    }

    /**
     * 获取栈顶元素,而不弹出
     *
     * @return 栈顶元素
     */
    T top();

    /**
     * 弹出栈顶元素
     *
     * @return 栈顶元素
     */
    T pop();
}
