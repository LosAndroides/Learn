# Detekt

You should follow the good practices to avoid bugs and complexity.

Detekt is a static code analysis tool for Kotlin to detect code smells.
You can find all the information here: https://github.com/detekt/detekt

You can run `detekt` task locally from the gradle tab or from command line.

```
./gradle detekt
```

You can find the report created by detekt in the `$project/build/reports/detekt/detekt.html` file.
Once you have the report, you can solve the issues fixing your code, or suppressing the false positives. 
Sometimes you could suppress a warning and assume the risk to don't fix this warning. You can find useful information about how to suppress warning here:
- https://detekt.dev/suppressing-rules.html
- https://detekt.dev/baseline.html
- https://detekt.dev/suppressors.html

It's very common to include `detekt` task in the checking flow, or create a specific step in the CI tools to fail the PR if you have any warning in your code.
In the case of `Los Androides` app, the `maxIssues` is configured to `0`, so the task fails if you don't have all the warnings fixed or suppressed. 

# Using Detekt in pre-push stage

It's very useful to have an automated script to run detekt task locally before pushing your code to the remote repository. This feature will avoid to 
consume CI time running the detekt task during the pipeline, detecting the detekt warning before push the changes in the remote repository. 

We can use the Git `pre-push` hook for this purpose. This feature was developed taking this guide as reference: 
https://detekt.dev/docs/gettingstarted/git-pre-commit-hook/

This is the script to run detekt:

```shell
#!/usr/bin/env bash
echo "Running detekt check..."
OUTPUT="/tmp/detekt-$(date +%s)"
./gradlew detekt > $OUTPUT
EXIT_CODE=$?
if [ $EXIT_CODE -ne 0 ]; then
  cat $OUTPUT
  rm $OUTPUT
  echo "***********************************************"
  echo "                 Detekt failed                 "
  echo " Please fix the above issues before committing "
  echo "***********************************************"
  exit $EXIT_CODE
fi
rm $OUTPUT
```
---
### **Disclaimer:** 
This script is only tested on MAC computer, so I'm not sure if you can have problems using it on any other OS. If you think we can do this compatible 
with other OS, please create a PR to help the community.
---

# Manual way to create `pre-push` script

You can do it manually by yourself creating a file called `pre-push` into the `projectRoot/.git/hooks/` folder containing the script above. This 
script will be executed every time you are trying to push something into the remote repository. If the detekt task fails, the push is cancelled.

**This pre-push hook needs to be executable, so you may need to change the permission (`chmod +x pre-push`).**

# Gradle task to copy `pre-push` script

You can use this Gradle task to easily copy the script from the repository to your local `.git/hooks/` path.

* ### From commandline:
```shell
./gradlew copyDetektPrePushScript
```

* ### From gradle menu:

You can find the `copyDetektPrePushScript` task in the `tools` group in your Gradle menu of the Android Studio.

**This pre-push hook needs to be executable, so you may need to change the permission (`chmod +x pre-push`).**
