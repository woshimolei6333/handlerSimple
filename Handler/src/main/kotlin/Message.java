public class Message {
    public static final Object sPoolSync = new Object();
    private static int sPoolSize = 0;
    private static Message sPool;
    Message next;

    Handler target;
    public int what;
    public Object obj;

    public Message() {
    }
    public static Message obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                Message obtainObject = sPool;
                sPool = obtainObject.next;
                obtainObject.next = null;
                sPoolSize--;
                return obtainObject;
            }
        }
        return new Message();
    }

    public static Message obtain(Handler h, int what, Object obj) {
        Message m = obtain();
        m.target = h;
        m.what = what;
        m.obj = obj;

        return m;
    }
}
