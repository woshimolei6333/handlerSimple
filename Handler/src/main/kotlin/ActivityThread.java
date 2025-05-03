public class ActivityThread {

    private static final int MSG_UPDATE_TEXT = 1;
    private static final int MSG_TASK_COMPLETE = 2;


    public static void main(String[] args) {
        Looper.prepareMainLooper();
        Handler mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_UPDATE_TEXT:
                        String text = (String) msg.obj;
                        System.out.println("在主线程收到消息: " + text);
                        // 这里可以更新UI，例如设置TextView的文本
                        // textView.setText(text);
                        break;
                    case MSG_TASK_COMPLETE:
                        System.out.println( "后台任务完成");
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 模拟耗时操作
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通过Handler发送消息到主线程
                Message message = mainHandler.obtainMessage(MSG_UPDATE_TEXT, "来自后台线程的消息");
                mainHandler.sendMessage(message);
            }
        }).start();
        Looper.loop();
    }
}
