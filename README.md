# Appzuku
![Logo]()
<p align="center">
  <img src="https://img.shields.io/github/downloads/northmendo/Appzuku/total?label=Downloads"/>
  <img src="https://img.shields.io/github/v/release/northmendo/Appzuku?include_prereleases&label=Release"/>
  <img src=""/>
</p>

## What is Appzuku?
Appzuku is an Android application that stops background applications using either Shizuku or Root permissions. It helps improve device performance, reduce memory usage, and lower device heat in a lightweight and safe way.

## What's new in my version?
I'm someone who:
- Loves automation. One of my favourite automation apps on Android is MacroDroid / Tasker.
- Need to have full control of my device. I don't like apps running in the background without my permission.
- Frequently kill apps running on my phone that I did not approve or allow.

Due to the above, the solution I'm looking for is very niche and unique to me. Superfreezz was a solution I used in the past. It supports intents, which means you can use MacroDroid / Tasker to call it and kill apps. You can configure what apps are whitelisted and what apps should be killed. The problem is that on a non-rooted device, the apps don't get killed automatically. It prompts users to tap on the "Force Stop" button for every app it's trying to kill. Also, Superfreezz doesn't support Shizuku. The author has long abandoned the project.

Then, I came across Hail, which is an awesome app (highly recommended) and it works well with Shizuku. The problem is that it disables apps, not kill them. While you can suspend apps (which doesn't disable them), it's not quite what I want. I still use Hail though, but I needed something else separate.

Finally, I came across shappky and AppZuku. The latter is an fork and an enhancement of the former. However, they're still missing features. So, I basically added 2 things. One is a kill all button that kills all running apps except for the ones that are whitelisted or hidden. The 2nd is the use of intents, which you can call from Macrodroid / Tasker. This means that under certain conditions, your automation macro will run and will kill the apps when the condition(s) are right.

For the intent, create it like so:

- Broadcast
- Action: com.northmendo.Appzuku.ACTION_KILL_ALL
- Package: com.northmendo.Appzuku
- Class: com.northmendo.Appzuku.KillTriggerReceiver

## Download
https://github.com/chaoscreater/Appzuku/blob/main/app-debug.apk

## Features
- **Flexible Permissions**: Works with either Shizuku or Root access.
- **Search & Filter**: Quickly find apps in your running list by name or package ID.
- **Autostart Prevention**: Prevent specific apps from running in the background automatically.
- **Theme Customization**: Support for Light, Dark, and System Default themes.
- **Background Service**: Automatically kills unused apps periodically when activated.
- **Quick Settings Kill App Tile**: Instantly force-stop the current foreground application directly from your notification shade.
- **Protected Apps**: System-critical apps and user-whitelisted apps are protected from being killed.
- **RAM Monitoring**: Real-time display of system RAM usage.

## Screenshots
<img width="412" height="861" alt="image" src="https://github.com/user-attachments/assets/dab95638-aefa-412e-a2c9-3caa55c16d8b" />
<img width="418" height="870" alt="image" src="https://github.com/user-attachments/assets/ebcbc6db-74ba-4d23-af1c-f3b9a68eebda" />
<img width="411" height="869" alt="image" src="https://github.com/user-attachments/assets/854e4811-b766-4a5e-a94f-1613f0c9d881" />

## Requirements
- **Android Version**: 6.0 (SDK 23) or higher.
- **Shizuku or Root**: Appzuku requires Root access or the Shizuku app to be running.

## Installation
You can download and install Appzuku via:
**GitHub Releases**: Download the latest APK from the [Releases page](https://github.com/northmendo/Appzuku/releases).

## License
Appzuku is licensed under the [GNU General Public License v3.0](LICENSE).

## Donate
If you want to support me, I would be very grateful. 

[**Buy me a coffee**](https://ko-fi.com/ricky76324)

## Credits
This project was forked from [northmendo/Appzuku](https://github.com/northmendo/Appzuku)
