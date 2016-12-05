    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (isClikced) {
//                    AppManager.getAppManager().AppExit(this);
                    finish();
//                    System.exit(0);
                    return super.onKeyUp(keyCode, event);
                }
                isClikced = true;
                showToast("再按一次退出");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isClikced = false;
                    }
                }, 3000);
                break;

        }

        return false;

    }
