package Util;

import java.util.Stack;

public class StackUtil {
    private Stack<String> stack = new Stack<>();

    public void push(String item) {
        stack.push(item);
    }

    public String pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    public String peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}