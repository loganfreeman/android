public interface BaseAdapterData {
    int getItemViewType();
}

public interface UIInit {
    int getContentViewId();

    void initViews();

    void initBeforeView();
}



public abstract class BaseViewHolder<T extends BaseAdapterData> extends RecyclerView.ViewHolder implements UIInit {

    protected BaseRecyclerAdapter mBaseAdapter;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private void setUIContext(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    protected Context getContext() {
        return mContext;
    }


    public BaseViewHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter) {
        super(itemView);
        mBaseAdapter = baseRecyclerAdapter;
        ButterKnife.bind(this, itemView);
        setUIContext(itemView.getContext());
        initBeforeView();
        initViews();
    }

    @Override
    public void initBeforeView() {

    }

    @Override
    public void initViews() {

    }

    public abstract void updateItem(T data, int position);
}

public class NoDataViewHolder extends BaseViewHolder {
    public NoDataViewHolder(View itemView, BaseRecyclerAdapter baseRecyclerAdapter) {
        super(itemView, baseRecyclerAdapter);
    }

    @Override
    public void updateItem(BaseAdapterData Data, int position) {

    }

    @Override
    public int getContentViewId() {
        return 0;
    }

    @Override
    public void initViews() {

    }
}

public class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private SparseArray<Class<? extends BaseViewHolder>> typeHolders = new SparseArray();
    private List<BaseAdapterData> mData = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public BaseRecyclerAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void registerHolder(Class<? extends BaseViewHolder> viewHolder, int itemViewType) {
        typeHolders.put(itemViewType, viewHolder);
    }

    public <T extends BaseAdapterData> void registerHolder(Class<? extends BaseViewHolder> viewHolder, T data) {
        if (data == null) {
            return;
        }
        typeHolders.put(data.getItemViewType(), viewHolder);
        addData(data);
    }

    public void registerHolder(Class<? extends BaseViewHolder> viewHolder, List<? extends BaseAdapterData> data) {
        if (Check.isEmpty(data)) {
            return;
        }
        typeHolders.put(data.get(0).getItemViewType(), viewHolder);
        addData(data);
    }

    public void setData(List<? extends BaseAdapterData> data) {
        if (Check.isEmpty(data)) {
            return;
        }
        mData.clear();
        addData(data);
    }

    public void clear() {
        mData.clear();
    }

    public void addData(List<? extends BaseAdapterData> data) {
        if (data == null) {
            return;
        }
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public <T extends BaseAdapterData> void addData(T data) {
        if (data == null) {
            return;
        }
        mData.add(data);

        notifyDataSetChanged();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(viewType, parent, false);
        BaseViewHolder viewHolder = new NoDataViewHolder(itemView, this);
        try {
            Class<?> cls = typeHolders.get(viewType);
            Constructor holderConstructor = cls.getDeclaredConstructor(View.class, BaseRecyclerAdapter.class);
            holderConstructor.setAccessible(true);
            viewHolder = (BaseViewHolder) holderConstructor.newInstance(itemView, this);
        } catch (NoSuchMethodException e) {
            LogHelper.e(e, "Create %s error,is it a inner class? can't create no static inner ViewHolder ", typeHolders.get(viewType));
        } catch (Exception e) {
            LogHelper.e(e, e.getCause() + "");
        }
        return viewHolder;
    }

    public List<BaseAdapterData> getData() {
        return mData;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (Check.isEmpty(mData) || Check.isNull(mData.get(position))) {
            return;
        }

        if (getItemViewType(position) != holder.getContentViewId()) {
            return;
        }

        holder.updateItem(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItemViewType();
    }
}

