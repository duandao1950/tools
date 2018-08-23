package com.tools.async.awaitility;

/**
 * Created by zhoushu on 2018/4/12 0012.
 */
public interface CounterService extends Runnable {
    int getCount();
}

// 每隔1s, count累加1
class CounterServiceImpl implements CounterService {
    private volatile int count = 0;
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int index = 0; index < 5; index++) {
                        Thread.sleep(1000);
                        count += 1;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    public int getCount() {
        return count;
    }
}