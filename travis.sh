#!/usr/bin/env bash

if [ $TRAVIS_TAG ]; then
  echo "Assembling and publishing release"
  ./gradlew build publishApkRelease
else
  echo "Assembling and testing Debug"
  ./gradlew build test :app:assembleAndroidTest
fi
