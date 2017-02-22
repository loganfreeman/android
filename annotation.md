IntDef
---
Denotes that the annotated element of integer type, represents a logical type and that its value should be one of the explicitly named constants. If the IntDef#flag() attribute is set to true, multiple constants can be combined.
```java
 @Retention(SOURCE)
  @IntDef({NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS})
  public @interface NavigationMode {}
  public static final int NAVIGATION_MODE_STANDARD = 0;
  public static final int NAVIGATION_MODE_LIST = 1;
  public static final int NAVIGATION_MODE_TABS = 2;
  ...
  public abstract void setNavigationMode(@NavigationMode int mode);
  @NavigationMode
  public abstract int getNavigationMode();
```
