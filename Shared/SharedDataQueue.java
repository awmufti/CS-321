package Shared;
import java.util.LinkedList;
import java.util.Queue;

public class SharedDataQueue {
    private final Queue<Form> queue = new LinkedList<>();

    // Method to add data to the queue
    public synchronized void enqueue(Form Form) {
        queue.add(Form);
        // Optionally notify if needed for real-time processing
        notifyAll();
    }

    // Method to retrieve data from the queue
    public synchronized Form dequeue() {
        return queue.poll();
    }

    // Check if the queue is empty
    public synchronized boolean isEmpty() {
        return queue.size()==0;
    }
    public synchronized int size(){
        return queue.size();
    }
}