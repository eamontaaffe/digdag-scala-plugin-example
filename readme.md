## Getting it running

```
# Build and publish it into the local repo
./gradlew publish

# If you are running a digdag server you need to do this
digdag selfupdate

# Run the example
digdag run --project example example.dig -p repos=`pwd`/build/repo
```
