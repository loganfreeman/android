private static boolean isMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
