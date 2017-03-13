menu
---
```xml
<item android:id="@+id/action_search"
		android:queryHint="Search"
		android:title="Search"
		android:icon="@drawable/ic_action_search"
		android:showAsAction="always"
		android:actionViewClass="android.widget.SearchView" /> 
```

code
---
```java
@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
		mSearchView.setIconifiedByDefault(true);
		mSearchView.setOnQueryTextListener(this);
		mSearchView.setOnSearchClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onQueryTextChange(mSearchView.getQuery().toString());
				}
		});
		return super.onCreateOptionsMenu(menu);
	}
 ```
