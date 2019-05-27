# LibraryPersianCalendar

it is a simple android persian calendarview and datepicker. you can simply install by add below code in project build.gradle:
```
repositories {
        ...
        mavenCentral()
        ...
    }
```
and add below code in module build.gradle:
```
apply plugin: 'com.android.application'

android {
    ...
    defaultConfig {
        ...
    }
    buildTypes {
        release {
            ...
        }
    }
}

dependencies {
    ...
    **compile 'android.hseify69.ir.persiancalendarlibrary:persiancalendarlibrary:1.2'**
    ...
}
```

# How to use it?

add xml code like below in your layout:
```
<android.hseify69.ir.persiancalendarlibrary.views.SimplePersianCalendarView
        android:id="@+id/AM_calendarView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```
and init like below code in your java class:
```
        //define calendar object
        final SimplePersianCalendarView calendarView=findViewById(R.id.AM_calendarView);

        //you can initialize with special date and selected days
        calendarView.init();

        //you can set multiple or singel select mode. by default it is in singel mode
        calendarView.setMultiSelectableDay(false);

        //you can set select or unselect listener
        calendarView.setOnSelectDay(new SimplePersianCalendarView.OnSelectOneDay() {
            @Override
            public void onSelectd(Date date) {

            }

            @Override
            public void onUnselectd(Date date) {

            }
        });

        //you can set some selected days
        ArrayList<String> dates=new ArrayList<>();
        dates.add("1394/3/1");
        dates.add("1395/4/2");
        dates.add("1396/5/3");
        dates.add("1397/6/4");
        dates.add("1398/7/5");
        dates.add("1399/8/6");
        dates.add("1400/9/7");
        ...
        calendarView.setSelectedDaysList(dates);

        //get selected days list
        calendarView.getSelectedDaysList();
```

I am very happy to know your comments and get the stars from you