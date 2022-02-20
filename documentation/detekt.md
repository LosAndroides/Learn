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
