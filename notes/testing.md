# How-to: Testing in Android

This page describes how to create unit tests in Android
with JUnit and how to create UI tests using Espresso.

**Before you begin:** Make sure that your application
has the testing frameworks added as dependencies.

Needed dependencies:
```
dependencies {
    // local tests
    testImplementation 'junit:junit:4.12'
    testImplementation 'androidx.test:core:1.1.0'
    testImplementation 'org.mockito:mockito-core:2.27.0'
    testImplementation 'com.google.truth:truth:0.44'
    testImplementation 'org.robolectric:robolectric:4.0-beta-1'

    // instrumented tests
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.1.0'
    androidTestImplementation 'androidx.test:runner:1.1.0'
    androidTestImplementation 'androidx.test:rules:1.1.0'
}
```

## Unit tests

The most numerous and fundamental of all tests that
you write for Android apps are unit tests.

### Testing standalone classes with JUnit

This example creates a fictional class, `TipProfile`,
that is used to store multiple tipping numbers in
a tipping app. Although specific to the
`TipProfile` class, you can refer to the following
examples to define your own tests.

<img src="https://github.com/telpirion/AD340/blob/master/images/TipApp.png" width="300px" />

These examples use a standard Java testing framework,
[JUnit](https://junit.org/junit4/).

Start by creating a simple Java class.

```
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TipProfile {

    public final static double SERVICE_GOOD = 1.0;
    public final static double SERVICE_MEH = 0.75;
    public final static double SERVICE_BAD = 0.50;

    private String name;
    private double baseRate;

    public TipProfile(String name, double baseRate) {
        this.name = name;

        if (baseRate > 1.0) {
            this.baseRate = 0.2;
        } else {
            this.baseRate = baseRate;
        }
    }


    public double getTipAmount(double cost,
            double experience) {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        double rawResult = cost * (baseRate * experience);

        return Double.parseDouble(df.format(rawResult));

    }
}
```

Next, create a test that tests the methods that
you're building.

 1. In the '[project]/app/test/java/[package-name]/'
    folder, add a new Java file for your test.
    Typically, test classes have names that end in
    'Test'.

 1. Add the following imports:

        import com.google.common.truth.Truth;
        import org.junit.Test;

 1. Create your setup & teardown code. Note that you
    can also use other annotations like
    `@BeforeEach` for steps that occur before every
    test.

        @Before
        public void setUp() {
            this.foodServiceProfile =
              new TipProfile("Food server", 0.20);
        }

        ...

        @After
        public void tearDown() {
            // do something here as needed ...
        }

 1. Create your test method. These often have really
    long names--that's to clarify what they do:

            @Test
            public void tipProfile_FoodServerProfile_ReturnsCorrectValues() {
                double tipAmountGood =  foodServiceProfile.getTipAmount(10.00,
                        TipProfile.SERVICE_GOOD);
                assertThat(tipAmountGood).isEqualTo(2.00);

                double tipMeh = foodServiceProfile.getTipAmount(10.00,
                        TipProfile.SERVICE_MEH);
                assertThat(tipMeh).isEqualTo(1.50);

                double tipBad = foodServiceProfile.getTipAmount(10.00,
                        TipProfile.SERVICE_BAD);
                assertThat(tipBad).isEqualTo(1.00);
            }

### Testing with Mockito mocks

Rarely are classes neatly decoupled from the
Android framework. Often times, the classes that
need to be test require access to the app's
context or to services on the device.

One solution for addressing these missing
external components in your tests is using a
'mock'. The primary solution for creating
mocks in Android is
[Mockito](https://site.mockito.org/).

The following code demonstrates how to add a simple
mock to a test. For this example, the `TipProfile`
class has been updated to take a context object
in its constructor and use it to retrieve values.

```
public TipProfile(Context ctx) {
    this.name = ctx.getString(R.string.default_tip_profile);
    String defaultTipAmt = ctx.getString(R.string.default_tip_amt_str);
    this.baseRate = Double.parseDouble(defaultTipAmt);
}
```

 1. Create the mocks for the classes that your code
    needs.

        @Mock
        Context mockContext;

        @Mock
        Resources mockResources;

 1. Initialize your mocks. You also have to define
    the behavior of the methods in your mocks before
    they are called.

        @Before
        public void setUp() {
            this.foodServiceProfile =
                    new TipProfile("Food server", 0.20);

            // MUST do this to tell Mockito to create mocks
            MockitoAnnotations.initMocks(this);

            when(mockContext.getString(R.string.default_tip_profile))
                    .thenReturn("Default profile");
            when(mockContext.getResources()).thenReturn(mockResources);
            when(mockResources.getDimension(R.dimen.default_tip_amt))
                    .thenReturn((float) 0.25);

            this.defaultProfile = new TipProfile(mockContext);
        }

 1. Create your tests (like other tests). Note that
    Mockito has its own test functions like
    `verify()`.

        @Test
        public void tipProfile_DefaultProfile_ReturnsCorrectValues() {

            double tipGood = defaultProfile.getTipAmount(10.00,
                    TipProfile.SERVICE_GOOD);
            assertThat(tipGood).isEqualTo(2.50);
        }

### Testing with Robolectric mocks

If your code needs a lot of integration with the
Android framework, you'll want to use
[Robolectric](http://robolectric.org/)
to create the mocks ("fakes") used in your unit tests.

The following sample demonstrates how to use
Robolectric in unit tests.

 1. First, you need to tell Gradle that you're using
    Android resources when you run your tests:

        android {
            ...
            testOptions {
                unitTests.includeAndroidResources = true
            }
        }

 1. Also, because Robolectric is still under
    development, you need to add the following to your
    `gradle.properties`:

        android.enableUnitTestBinaryResources=true

 1. Finally, you can create your test:

        @RunWith(RobolectricTestRunner.class)
        public class TipProfileRoboTest {

            @Test
            public void tipProfile_DefaultProfile_ReturnsCorrectValues_Robolectric() {

                Context roboContext = ApplicationProvider.getApplicationContext();

                TipProfile defaultProfileRobo = new TipProfile(roboContext);

                double tipGood = defaultProfileRobo.getTipAmount(10.00,
                        TipProfile.SERVICE_GOOD);
                assertThat(tipGood).isEqualTo(2.50);

            }
        }

**Note:** The Android docs are very vague (and
possibly incorrect) with regards to creating a
Robolectric test. The Robolectric docs have a
[much better example](http://robolectric.org/writing-a-test/).

### Creating unit tests for activities

The following example shows how to create a simple
unit test for an activity using Robolectric.

```
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testNotEnoughDataProvided() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.button).performClick();

        assertEquals("Check your inputs", ShadowToast.getTextOfLatestToast());
    }
}
```

## Integration tests

Integration tests are best used when your app needs
to connect to an
[external service or content provider](https://developer.android.com/training/testing/integration-testing). We will
cover this type of test when we discuss services.

## UI tests

### Creating UI tests with Espresso

The best toolset for creating UI tests in Android is
[Espresso](https://developer.android.com/training/testing/espresso).

**Note:** There are two versions of Espresso that you
can use for your apps currently: the older
`android.support.test.espresso` package version and the
newer `androidx.test.espresso` package version. Be sure
that you're consistently using one version or the
other. These examples all use the
[AndroidX version](https://developer.android.com/training/testing/set-up-project).

 1. In the
    '[project]/app/androidTest/java/[package-name]/'
    folder, add a new Java file for your test.
    Typically, test classes have names that end in
    'Test'.
 1. Add the following imports. You may need to add
    more as you develop your tests.

        import androidx.test.runner.AndroidJUnit4;

        import org.junit.Rule;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import androidx.test.filters.LargeTest;
        import androidx.test.rule.ActivityTestRule;
        import static androidx.test.espresso.Espresso.onData;
        import static androidx.test.espresso.Espresso.onView;
        import static androidx.test.espresso.action.ViewActions.click;
        import static androidx.test.espresso.action.ViewActions.typeText;
        import static androidx.test.espresso.assertion.ViewAssertions.matches;
        import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static androidx.test.espresso.matcher.ViewMatchers.withId;
        import static androidx.test.espresso.matcher.ViewMatchers.withText;
        import static org.hamcrest.CoreMatchers.allOf;
        import static org.hamcrest.CoreMatchers.instanceOf;
        import static org.hamcrest.CoreMatchers.is;

 1. Add the code for your your test like the following:

        @RunWith(AndroidJUnit4.class)
        @LargeTest
        public class TipAppUITest {

            @Rule
            public ActivityTestRule<MainActivity> activityRule =
                    new ActivityTestRule<>(MainActivity.class);

            @Test
            public void testFoodServiceGoodTipGeneration() {

                onView(withId(R.id.txtbx_svc_amt))
                        .perform(typeText("10.00"));

                // Set the value of the service type
                // spinner
                onView(withId(R.id.spn_svc_type))
                        .perform(click());
                onData(allOf(is(instanceOf(String.class)), is("Food server")))
                        .perform(click());

                // Set the value of the quality
                // spinner
                onView(withId(R.id.spn_quality))
                        .perform(click());
                onData(allOf(is(instanceOf(String.class)), is("Good")))
                        .perform(click());

                onView(withId(R.id.button)).perform(click());

                onView(withId(R.id.tip_output)).check(matches(withText("$2.00")));
            }
        }

