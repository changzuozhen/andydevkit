# andydevkit
develop kit

# release
implementation 'com.didi.soda:andydevkit:0.0.2'
implementation(group: 'com.didi.soda', name: 'andydevkit', version: '0.0.2')

# ref
https://github.com/novoda/bintray-release

# dependencies
./gradlew demo:dependencies --configuration releaseRuntimeClasspath > ./docs/dependencies.txt

./gradlew demo:assembleDebug