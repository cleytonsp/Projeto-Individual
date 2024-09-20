package Util;

import java.util.LinkedList;
import java.util.Queue;

public class QueueUtil {
    private Queue<String> queue = new LinkedList<>();

    public void enqueue(String item) {
        queue.add(item);
    }

    public String dequeue() {
        return queue.isEmpty() ? null : queue.poll();
    }

    public String peek() {
        return queue.isEmpty() ? null : queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}