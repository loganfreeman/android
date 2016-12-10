class AsyncThreadDispatcher implements Dispatcher {
    private ExecutorService mAsyncExecutor ;

    AsyncThreadDispatcher() {
        mAsyncExecutor = Executors.newCachedThreadPool();
    }

    @Override
    public void dispatch(Runnable runnable) {
        if(mAsyncExecutor.isShutdown()) {
            return;
        }
        mAsyncExecutor.execute(runnable);
    }

    @Override
    public boolean stop() {
        return true;
    }
}
