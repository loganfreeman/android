public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {

    private static final String LOG_TAG = "cube-views-page-indicator";
    private static final boolean DEBUG = CubeDebug.DEBUG_PAGE_INDICATOR;

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    public abstract static class ViewHolderBase {

        private int mIndex;

        private void setIndex(int index) {
            mIndex = index;
        }

        private int getIndex() {
            return mIndex;
        }

        public abstract View createView(LayoutInflater layoutInflater, int position);

        public abstract void updateView(int position, boolean isCurrent);
    }

    public abstract static class ViewHolderCreator {
        public abstract ViewHolderBase createViewHolder();
    }

    private ViewHolderCreator mViewHolderCreator;

    public void setViewHolderCreator(ViewHolderCreator viewHolderCreator) {
        mViewHolderCreator = viewHolderCreator;
    }

    private Runnable mTabSelector;

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            ViewHolderBase viewHolderBase = (ViewHolderBase) view.getTag();
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = viewHolderBase.getIndex();
            mViewPager.setCurrentItem(newSelected);
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

    private final LinearLayout mTabLayout;

    private ViewPager mViewPager;
    private OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex = -1;

    private OnTabReselectedListener mTabReselectedListener;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        mTabLayout = new LinearLayout(context);
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int) (MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Re-center the tab display if we're at a new (scrollable) size.
            updateTab(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(scrollPos, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(i);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        if (mListener != null) {
            mListener.onPageScrolled(i, v, i1);
        }
    }

    @Override
    public void onPageSelected(int i) {
        if (DEBUG) {
            CLog.d(LOG_TAG, "onPageSelected: %s", i);
        }
        moveToItem(i);
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();

        final int count = adapter.getCount();
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        for (int i = 0; i < count; i++) {
            ViewHolderBase viewHolder = mViewHolderCreator.createViewHolder();
            viewHolder.setIndex(i);
            View view = viewHolder.createView(layoutInflater, i);
            view.setFocusable(true);
            view.setOnClickListener(mTabClickListener);
            view.setTag(viewHolder);
            if (view.getLayoutParams() == null) {
                mTabLayout.addView(view, new LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
            } else {
                mTabLayout.addView(view);
            }
        }
        mSelectedTabIndex = mViewPager.getCurrentItem();
        updateTab(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        moveToItem(initialPosition);
    }

    public void moveToItem(int item) {
        if (DEBUG) {
            CLog.d(LOG_TAG, "moveToItem: %s", item);
        }
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        if (mSelectedTabIndex == item) {
            // return;
        }
        mSelectedTabIndex = item;

        // will lead to call moveToItem again, but will not hit here :)
        mViewPager.setCurrentItem(item);
        if (DEBUG) {
            CLog.d(LOG_TAG, "mViewPager.setCurrentItem: %s", item);
        }
        if (mListener != null) {
            mListener.onPageSelected(item);
        }
        updateTab(item);
    }

    private void updateTab(int item) {

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final ViewHolderBase viewHolderBase = (ViewHolderBase) child.getTag();
            final boolean isSelected = (i == item);
            viewHolderBase.updateView(i, isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }
}
