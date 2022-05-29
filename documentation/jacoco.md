# JaCoCo

JaCoCo is an open-source toolkit for measuring and reporting Java code coverage.

Of course, it's compatible with Kotlin code too. That is very important on **`Los Androides`**.

## Resources used to implement this solution

- https://medium.com/nerd-for-tech/setup-jacoco-code-coverage-with-your-multimodule-android-app-kotlin-a0f82573a1
- https://thsaravana.github.io/blog/jacoco-single-coverage-for-multi-module/

# Relevant information in the JaCoCo Coverage Report
You can create a report in different formats:
- xml: usually used to feed other tools because it's easier to analyze by tools.
- csv: usually used to feed other tools because it's easier to analyze by tools.
- html: very useful to read the report.

In the implemented solution we're creating the xml and html reports.

### HTML report
You can see in the first screen the information grouped by package.
![Report, home screen](./images/jacoco_home.png)

If you enter in a group, you will see the information for all the contained classes inside the package.
![Report, package screen](./images/jacoco_package.png)

If you enter in a class, you will see the lines tested (green), the code that is tested, by you need to test specific alternatives of the 
code (yellow), the untested code (red), and the not testable code without any color. In this example, most of the code is covered, 
the `ViewState.Content` is tested only one branch of two, because we have tested this class sending a explicit items param, but we have not tested 
with the default value, and the `ViewState.Error` was not tested. Tested means that this code was used for some of the tests we have run in the project.
![Report, class screen](./images/jacoco_class.png)

So you could decide to create new tests depending on this useful information.

You can find more information here: 
- https://www.eclemma.org/userdoc/annotations.html

# JaCoCo in multi-module project

The default way to run JaCoCo to create the coverage report is doing a task for each module. But it is very useful to merge all the reports and have
only one report with the coverage of the full project.

The multi-module feature is implemented in **`Los Androides`**, even if we have only one module yet. This is why in the `jacoco.gradle` file you can
find a task for each module with the content empty: debugCoverage --> We only want to create the task to create the binary files with the result of
the tests that we will use later to elaborate the merged report. The `allDebugCoverage` task search for all the UnitTests and UI Tests reports and
takes all the source code to create a report with all the information.

# Using JaCoCo locally

As you can see in the `root/gradle-script/jacoco.gradle` file, this `allDebugCoverage` task depends on the `testDebugUnitTest` and
`connectedDebugAndroidTest` for all the modules in the project. The dependency with the `connectedDebugAndroidTest` is only applied in local builds.

So `testDebugUnitTest` and `connectedDebugAndroidTest` tasks for all the modules will be executed before creating the report.

You can run the JaCoCo task to create the testing coverage report very easily. You only need to run the task:

```shell
./gradlew allDebugCoverage
```

# Using JaCoCo in Github (Pending task)

We can run the JaCoCo task during the pipeline. And we can define a minimum coverage to accept the merge on the main branch, to avoid including
untested code.

We can add this feature in another task for **`Los Androides`**.

Reference plugin to use JaCoCo in Github:
https://github.com/marketplace/actions/jacoco-report
