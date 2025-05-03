public class Looper {

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();
    final MessageQueue mQueue;
    final Thread mThread;

    private static Looper sMainLooper;
    private Looper() {
        mQueue = new MessageQueue();
        mThread = Thread.currentThread();
    }
    public static void loop() {
        final Looper me = myLooper();
        for (;;) {
            if (!loopOnce(me)) {
                return;
            }
        }
    }

    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    private static boolean loopOnce(final Looper me) {
        Message msg = me.mQueue.next(); // might block
        System.out.println("message");
        if (msg == null) {
            return false;
        }
        try {
            msg.target.dispatchMessage(msg);
        } catch (Exception exception) {
            throw exception;
        }
        return true;
    }

    public static Looper getMainLooper() {
        synchronized (Looper.class) {
            return sMainLooper;
        }
    }


    public static void prepareMainLooper() {
        sThreadLocal.set(new Looper());
        synchronized (Looper.class) {
            if (sMainLooper != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            sMainLooper = myLooper();
        }
    }
}
