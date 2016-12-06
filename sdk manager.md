Changing the sdk location in Project Settings will solve the problem partially. When Android Studio is used to download a new SDK, it will place the new SDK in the internal SDK folder (inside Android Studio).

Existing android developers will already have a large sdks folder (hereinafter referred to as external SDK folder) containing all the SDKs downloaded before Android Studio came around.

For Mac/Linux users though there is a good way out. Soft links!

Exit Android Studio and perform the following steps:

```shell
cp -r <Android Studio>/sdk/ <external SDK folder>/
cd <Android Studio>/
mv <Android Studio>/sdk/ mv <Android Studio>/sdk.orig
ln -s <external SDK folder>/ sdk
```
