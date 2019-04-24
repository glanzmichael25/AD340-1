# How-to: Fragments, app bars, and navigation drawers

**Before you begin:** Be sure to add a reference to the design library to the
app's build config.

## Fragments

### Creating a simple fragment

 1. Create a new activity called `HostActivity`. Inherits
    from `AppCompatActivity`.
 1. Create a new fragment called `MainFragment`. Inherits from `Fragment`
    (from the support library).
 1. Create a new layout called 'fragment_main'. Use vertical linear layout.
    The layout should contain a single `TextView` that displays a string
    resource called 'fragment_main'.
 1. Create a new layout called 'activity_host'. Use vertical linear layout.
 1. Add linear layout inside of 'activity_host' with `@+id/fragment_host`.
 1. Add the following code to the `HostActivity.onCreate()`:

        setContentView(R.layout.activity_host);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
          fragmentManager.beginTransaction();
        MainFragment fragment = new MainFragment();
        fragmentTransaction.add(R.id.fragment_host, fragment);
        fragmentTransaction.commit();

 1. Add the following code to `MainFragment.onCreateView()`:

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          return inflater.inflate(R.layout.fragment_main, container, false);
        }

 1. Run the code.

### Swapping fragments

 1. Create a new fragment called `SecondFragment`.
 1. Create a new layout called 'fragment_second'. Use linear layout.
 1. Add the following code to `SecondFragment.java`:

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_second, container, false);
        }

 1. Add a string resource, and layout w/ `TextView` for `SecondFragment.java`.
 1. Add the following code to `HostActivity.java`.

        ... implements implements MainFragment.OnNextClick

        public void swapFragments(Fragment fragment) {
          Fragment newFragment = null;

          if (fragment instanceof MainFragment) {
            newFragment = new SecondFragment();
          } else {
            newFragment = new MainFragment();
          }

          FragmentTransaction transaction =
          getSupportFragmentManager().beginTransaction();

          transaction.replace(R.id.fragment_host, newFragment);
          transaction.addToBackStack(null);

          transaction.commit();
        }

        @Override
        public void OnMainFragmentNextClick(MainFragment fragment) {
          swapFragments(fragment);
        }

 1. In the `fragment_main.xml` layout, add a button
    with a string resource that says "Next" and an
    id of `@+id/fragment_main_next`.
 1. Add the following code to `MainFragment.java`:

        ... implements View.OnClickListener

        public interface OnNextClickListener {
          void OnMainFragmentNextClick(MainFragment fragment);
        }

        OnNextClickListener listener;

        @Override
        public void onAttach(Context context) {
          super.onAttach(context);
          try {
            listener = (OnNextClickListener) context;
          } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
              " must implement OnMainFragmentNextClick");
          }
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
          super.onActivityCreated(savedInstanceState);

          Button nextBtn =
            (Button)this.getView().findViewById(R.id.frag_main_next);
          nextBtn.setOnClickListener(this);
        }

        public void onClick(View view) {
          if (listener != null) {
            listener.OnMainFragmentNextClick(this);
          }
        }

 1. Run the code.

### Passing data using bundles

```
newFragment = new SecondFragment();

Bundle args = new Bundle();
args.putString("MY_KEY", "my data");
newFragment.setArguments(args);

```

## App bar

### Creating the app bar

 1. Create a resource file `res/menu/app_bar_items.xml`.

    * You might need to create a new folder first. Right-click
      your app and select **New > Android Resource Directory**.
    * In the **New Resource Directory** dialog, select 'menu'
      in the **Resource type** box.

 1. Define the menu items in the app bar:

        <?xml version="1.0" encoding="utf-8"?>
        <menu xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto">
            <item android:id="@+id/action_share"
                android:title="@string/app_bar_share"
                android:icon="@android:drawable/ic_menu_share"
                app:showAsAction="ifRoom" />

            <item android:id="@+id/action_settings"
                android:title="@string/app_bar_settings"
                app:showAsAction="never" />
        </menu>

 1. Create the XML for the toolbar `res/layout/app_bar.xml`.
    Select support `Toolbar` as root element.
 1. Define the XML for the toolbar:

        <android.support.v7.widget.Toolbar
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            android:elevation="4dp"
            android:theme="@style/ThemeOverlay.AppCompat.ActionBar"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"/>

 1. Change the default style in `res/values/styles.xml` to
    suppress the action bar:

        <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">

 1. Add the toolbar to the `activity_host.xml` layout:

        <include
            layout="@layout/app_bar"
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

 1. Connect the app bar items to the app bar in
    `HostActivity.onCreate()`:

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

 1. Override the `HostActivity.onCreateOptionsMenu()` method:

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.app_bar_items, menu);
            return super.onCreateOptionsMenu(menu);
        }

### Responding to user clicks

Add the following code to `HostActivity`:

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {

                case R.id.action_settings:
                    Log.i(TAG, "Settings clicked");
                    Toast toast = Toast.makeText(this,
                            "Settings clicked", Toast.LENGTH_SHORT);
                    toast.show();
                    return true;

                case R.id.action_share:
                    Log.i(TAG, "Share clicked");
                    return true;

                default:
                    return super.onOptionsItemSelected(item);

            }
        }

## Navigation bar

### Adding a new vector image

 1. Download [Material Design](https://material.io/tools/icons/?style=baseline)
    icons as SVG.
 1. Right-click app module, click **New > Vector Asset**.
 1. In the **Configure Vector Asset** dialog, select the file that you download.
    Accept all the default prompts that it presents to you.

### Adding drawer navigation

 1. Change the layout in `HostActivity`:

    * Change the root to `DrawerLayout`.
    * Put a `LinearLayout` inside the `DrawerLayout`.
    * Add a `NavView` element to the `DrawerLayout`
      (outside of the `LinearLayout`).

          <?xml version="1.0" encoding="utf-8"?>
          <android.support.v4.widget.DrawerLayout
              xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:app="http://schemas.android.com/apk/res-auto"
              android:id="@+id/drawer_layout"
              android:orientation="vertical"
              android:layout_width="match_parent"
              android:layout_height="match_parent">
              <LinearLayout
                  android:orientation="vertical"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent">

                  <include
                      layout="@layout/app_bar"
                      android:layout_width="match_parent"
                      android:layout_height="wrap_content" />

                  <FrameLayout
                      android:id="@+id/fragment_host"
                      android:layout_width="match_parent"
                      android:layout_height="match_parent" />
              </LinearLayout>
              <android.support.design.widget.NavigationView
                  android:id="@+id/nav_view"
                  android:layout_width="wrap_content"
                  android:layout_height="match_parent"
                  android:layout_gravity="start"
                  android:background="@android:color/white"
                  app:menu="@menu/nav_drawer" />
          </android.support.v4.widget.DrawerLayout>

 1. Create a new XML resource called 'nav_drawer.xml' for items in the menu/ folder.
 1. Define the items in `nav_drawer.xml`:

        <?xml version="1.0" encoding="utf-8"?>
        <menu xmlns:android="http://schemas.android.com/apk/res/android">
            <group android:checkableBehavior="single">
                <item
                    android:id="@+id/nav_code"
                    android:icon="@drawable/ic_baseline_code_24px"
                    android:title="@string/nav_code" />

                <item
                    android:id="@+id/nav_today"
                    android:icon="@android:drawable/ic_menu_today"
                    android:title="@string/nav_today" />

                <item
                    android:id="@+id/nav_help"
                    android:icon="@android:drawable/ic_menu_help"
                    android:title="@string/nav_help" />
            </group>
        </menu>

 1. Run the code. Swipe in from the left to see the drawer.


### Adding a toggle button (hamburger)

Add the following code to the `HostActivity.onCreate()`:

```
drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
ActionBarDrawerToggle toggle =
  new ActionBarDrawerToggle(this, drawer, toolbar,
    R.string.nav_open_drawer, R.string.nav_close_drawer);

drawer.addDrawerListener(toggle);
toggle.syncState();
```

### Responding to user clicks

Add the following code to the `HostActivity`:

        ... implements NavigationView.OnNavigationItemSelectedListener

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            ...

            navigationView = (NavigationView)findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.nav_code:
                    Log.i(TAG, "code clicked");
                    break;

                case R.id.nav_today:
                    Log.i(TAG, "today clicked");
                    break;

                case R.id.nav_help:
                    Log.i(TAG, "help clicked");
                    break;

                default:
                    break;
            }

            drawer.closeDrawer(GravityCompat.START);

            return false;
        }

### Navigating from nav drawer clicks

Update `onNavigationItemSelected()` so that it uses
the `FragmentManager` to swap fragments.

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment newFragment;

            switch (menuItem.getItemId()) {
                case R.id.nav_code:
                    Log.i(TAG, "code clicked");
                    newFragment = new MainFragment();
                    break;

                case R.id.nav_today:
                    Log.i(TAG, "today clicked");
                    newFragment = new SecondFragment();
                    break;

                case R.id.nav_help:
                    Log.i(TAG, "help clicked");
                    newFragment = new MainFragment();
                    break;

                default:
                    newFragment = new MainFragment();
                    break;
            }

            drawer.closeDrawer(GravityCompat.START);

            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_host, newFragment);
            transaction.addToBackStack(null);

            transaction.commit();

            return false;
        }