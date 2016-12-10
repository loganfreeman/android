class MainThreadDispatcher implements Dispatcher {
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    @Override
    public void dispatch(Runnable runnable) {
        mMainHandler.post(runnable);
    }

    @Override
    public boolean stop() {
        mMainHandler.removeCallbacksAndMessages(null);
        return true;
    }
}
