documentation
---
[Create a Drawer Layout](https://developer.android.com/training/implementing-navigation/nav-drawer.html)

example
---
```java
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout android:id="@+id/drawer_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:ignore="UnusedAttribute">

    <LinearLayout
        android:id="@+id/content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true"
        android:orientation="vertical">

        <android.support.v7.widget.Toolbar
            android:id="@+id/appbar_toolbar"
            android:layout_width="match_parent"
            android:layout_height="?actionBarSize"
            android:elevation="@dimen/toolbar_elevation"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            app:contentInsetStart="@dimen/content_inset"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light" />

        <FrameLayout
            android:id="@+id/appbar_frame"
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ScrollView
                android:id="@+id/main_scroll"
                android:layout_width="match_parent"
                android:layout_height="wrap_content">

                <LinearLayout
                    android:id="@+id/scroll_content"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:clipToPadding="false"
                    android:orientation="vertical"
                    android:padding="@dimen/content_inset"
                    tools:ignore="UnusedAttribute">

                    <com.kabouzeid.appthemehelper.common.views.ATEPrimaryTextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:fontFamily="sans-serif-medium"
                        android:tag="window_background_dependent,textsize_headline,font_RobotoSlab-Bold.ttf"
                        android:text="@string/header" />

                    <com.kabouzeid.appthemehelper.common.views.ATESecondaryTextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginBottom="@dimen/content_inset_half"
                        android:layout_marginTop="@dimen/content_inset"
                        android:fontFamily="sans-serif-light"
                        android:lineSpacingMultiplier="1.5"
                        android:tag="text_secondary,textsize_body"
                        android:text="@string/lorem_ipsum" />

                    <com.kabouzeid.appthemehelper.common.views.ATECheckBox
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="@dimen/content_inset"
                        android:checked="true"
                        android:tag="tint_accent_color,text_primary,textsize_body"
                        android:text="@string/checkbox" />

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="@dimen/content_inset"
                        android:orientation="horizontal">

                        <com.kabouzeid.appthemehelper.common.views.ATESwitch
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:checked="true"
                            android:tag="tint_accent_color,text_primary,textsize_body"
                            android:text="@string/switchViewCompat" />

                        <Switch
                            android:id="@+id/switchView"
                            android:layout_width="wrap_content"
                            android:layout_height="wrap_content"
                            android:layout_marginLeft="@dimen/content_inset"
                            android:layout_marginStart="@dimen/content_inset"
                            android:checked="true"
                            android:text="@string/switchView" />

                    </LinearLayout>

                    <com.kabouzeid.appthemehelper.common.views.ATERadioButton
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="@dimen/content_inset"
                        android:checked="true"
                        android:enabled="true"
                        android:tag="tint_accent_color,text_primary,textsize_body"
                        android:text="@string/radiobutton" />

                    <com.kabouzeid.appthemehelper.common.views.ATEEditText
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="@dimen/content_inset"
                        android:hint="@string/edit_text"
                        android:tag="tint_accent_color,text_primary,textsize_body" />

                    <com.kabouzeid.appthemehelper.common.views.ATEProgressBar
                        android:id="@+id/progress"
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:layout_gravity="center_horizontal"
                        android:layout_marginTop="@dimen/content_inset"
                        android:tag="tint_accent_color" />

                    <com.kabouzeid.appthemehelper.common.views.ATESeekBar
                        android:id="@+id/seek"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:layout_marginTop="@dimen/content_inset"
                        android:max="100"
                        android:progress="60"
                        android:tag="tint_accent_color" />

                    <Button
                        android:id="@+id/button"
                        android:layout_width="match_parent"
                        android:layout_height="48dp"
                        android:layout_marginTop="@dimen/content_inset"
                        android:clickable="true"
                        android:text="@string/button" />

                </LinearLayout>

            </ScrollView>

            <android.support.design.widget.FloatingActionButton
                android:id="@+id/fab"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="bottom|end"
                android:layout_margin="@dimen/content_inset"
                android:clickable="true"
                android:src="@drawable/ic_add"
                android:tag="bg_tint_accent_color_selector_lighter"
                app:elevation="6dp"
                app:pressedTranslationZ="12dp" />

        </FrameLayout>

    </LinearLayout>

    <android.support.design.widget.NavigationView
        android:id="@+id/navigation_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/drawer_header"
        app:menu="@menu/drawer" />

</android.support.v4.widget.DrawerLayout>
```

toolbar
---
```java
final Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setBackgroundColor(ThemeStore.primaryColor(this));
```
