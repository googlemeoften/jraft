package cn.edu.jraft.common;

import com.alipay.remoting.util.StringUtils;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class RaftScheduler implements TimerTask {
    private final AtomicBoolean start = new AtomicBoolean(false);

    private final int timeoutMs;
    private static Timer timer;

    public RaftScheduler(String name, int timeoutMs) {
        timer = new HashedWheelTimer(new DefaultThreadFactory(name));
        this.timeoutMs = timeoutMs;
    }

    public void schedule() {
        if (!start.compareAndSet(false, true)) {
            return;
        }
        timer.newTimeout(this, adjustTimeout(timeoutMs), TimeUnit.MILLISECONDS);
    }

    public void run(Timeout timeout) {
        onTrigger();
        timer.newTimeout(this, adjustTimeout(timeoutMs), TimeUnit.MILLISECONDS);
    }

    protected abstract void onTrigger();

    protected int adjustTimeout(int timeoutMs) {
        return timeoutMs;
    }


    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        public DefaultThreadFactory(String poolName) {
            if (StringUtils.isBlank(poolName)) {
                poolName = "pool";
            }
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = poolName + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
            if (t.isDaemon()) t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

}
