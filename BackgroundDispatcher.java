class BackgroundDispatcher implements Dispatcher {

    private HandlerThread mBackgroundThread = new HandlerThread("Background");
    private Handler mBackgroundHandler ;

    BackgroundDispatcher() {
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    @Override
    public void dispatch(Runnable runnable) {
        if(!mBackgroundThread.isAlive()) {
            return;
        }
        mBackgroundHandler.post(runnable);
    }

    @Override
    public boolean stop() {
        if(!mBackgroundThread.isAlive()) {
            return true;
        }
        mBackgroundHandler.removeCallbacksAndMessages(null);
        return true;
    }
}
