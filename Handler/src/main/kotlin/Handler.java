public class Handler {

    final Callback mCallback;
    final Looper mLooper;
    final MessageQueue mQueue;
    public Handler(Callback callback) {
        this.mCallback = callback;
        mLooper = Looper.myLooper();
        mQueue = mLooper.mQueue;
    }

    public Handler(Looper looper) {
        mLooper = looper;
        mQueue = mLooper.mQueue;
        mCallback = null;
    }
    public void dispatchMessage(Message msg) {
        if (mCallback != null) {
            mCallback.handleMessage(msg);
        }
        handleMessage(msg);
    }
    public void handleMessage(Message msg) {
    }

    public final boolean sendMessage(Message msg) {
        MessageQueue queue = mQueue;
        if (queue == null) {
            RuntimeException e = new RuntimeException(
                    this + " sendMessageAtTime() called with no mQueue");
            return false;
        }
        return enqueueMessage(queue, msg);
    }

    private boolean enqueueMessage(MessageQueue queue, Message msg) {
        msg.target = this;
        return queue.enqueueMessage(msg);
    }

    public final Message obtainMessage(int what, Object obj) {
        return Message.obtain(this, what, obj);
    }
}
