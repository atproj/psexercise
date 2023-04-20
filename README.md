# psexercise

## DESCRIPTION
psexercise is an android application that calculates the ideal address for a given driver based on sustainability score.  To display this information a 
list of drivers is presented, and selecting a driver takes you to the next screen detailing the best shipment.  This shipment is then deducted from 
the initial list of shipments listed in assets/data.json.  Rotation of the app is supported.

## TECHNICAL DETAILS
This app was built using android jetpack, dagger-hilt, junit, mockk, and kotlin.  It follows the MVVM pattern with clean architecture:
![image](https://github.com/atproj/psexercise/blob/master/Screen%20Shot%202023-04-20%20at%206.36.03%20PM.png)

## RUN APP
Build the app by entering ```./gradlew assembleDebug``` on the command line

Install the app by using adb (part of the android sdk)
```
./adb install app/build/intermediates/apk/debug/app-debug.apk
```

## TEST APP
Unit tests are included in app/src/tests in a package structure mirroring the feature it tests.  Run tests by entering 
```./gradlew test``` on the command line.

