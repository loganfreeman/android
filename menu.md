menu example
---
```java
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/search"
        android:icon="@drawable/ic_search"
        android:title="@string/search"
        app:actionViewClass="android.support.v7.widget.SearchView"
        app:showAsAction="ifRoom|collapseActionView" />

    <item
        android:id="@+id/checkboxItem"
        android:checkable="true"
        android:checked="true"
        android:title="@string/checkbox" />

    <item
        android:id="@+id/radiobuttonItem"
        android:title="@string/radiobutton_clickme"
        app:showAsAction="never">
        <menu>
            <group android:checkableBehavior="single">
                <item
                    android:id="@+id/radioOne"
                    android:title="@string/radiobutton" />
                <item
                    android:id="@+id/radioTwo"
                    android:checked="true"
                    android:title="@string/radiobutton" />
                <item
                    android:id="@+id/radioThree"
                    android:title="@string/radiobutton" />
            </group>
        </menu>
    </item>

</menu>
```
searchView in menu
---
```java
public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getString(R.string.search_view_example));

        return super.onCreateOptionsMenu(menu);
    }
 ```