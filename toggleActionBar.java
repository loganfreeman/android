@Override
public boolean onTouchEvent(MotionEvent event) {
  if(event.getAction() == MotionEvent.ACTION_DOWN) {
    toggleActionBar();
  }
  return true;
}

private void toggleActionBar() {
  ActionBar actionBar = getActionBar();

  if(actionBar != null) {
    if(actionBar.isShowing()) {
      actionBar.hide();
    }
    else {
      actionBar.show();
    }
  }
}
