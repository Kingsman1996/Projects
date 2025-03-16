package thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private List<Thread> threads;

    public ThreadManager() {
        this.threads = new ArrayList<>();
    }

    public void startThreads(List<Runnable> list) {
        for (Runnable item : list) {
            Thread thread = new Thread(item);
            threads.add(thread);
            thread.start();
        }
    }

    public void waitThreads() {
        for (Thread item : threads) {
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
