# DevxyzShield 1.0-devxyz — Android DNS Ad & Tracker Protection

DevxyzShield is JepongDevxyz's own native Android app for on-device DNS ad & tracker protection. Non-root, built with Kotlin, Jetpack Compose (Material 3), Room, DataStore, WorkManager, Hilt, and Android `VpnService`.

**Credit:** The UI and DNS/VPN engine in this version are based on the public AdShieldPro project by hillman3948-cyber (https://github.com/hillman3948-cyber/AdShieldPro), rebranded for JepongDevxyz with a new package name, app name, and update-check repository. The layout, theme, and feature set are kept pixel-identical to the reference: deep navy `#07101E`, emerald `#10B981`, animated shield on Home.

## What it does

- **Device-wide DNS filtering:** A local Android VPN service routes DNS requests to the on-device filter. Other app traffic continues over the device's normal network; DevxyzShield does not send it through a remote VPN server.
- **Protection modes:** Standard, Strict, Privacy, and Custom modes control which categories and lists are active.
- **Blocklists and rules:** Built-in ad, tracker, and threat lists; custom HTTPS list sources; custom block and allow rules; rule-conflict review. Filter lists sync in the background.
- **Privacy Browser:** Built-in WebView browser with subresource blocking, tracking-parameter stripping (`fbclid`, `gclid`, `utm_*`, etc.), pop-up blocking, third-party cookie controls, and cosmetic hiding rules.
- **Activity and statistics:** DNS totals, allowed/blocked queries, recent activity, trends, and live top apps when Android provides app attribution.
- **AI Smart Boost (rule-based, local):** Memory, storage, battery, and temperature signals; safe cleanup of DevxyzShield-owned temporary files; DNS cache and memory optimization. No cloud AI call.
- **Game Booster and battery modes:** Workload profiles that adjust DevxyzShield's own cache, background sync, and logging behavior.
- **Settings on-device:** Settings, custom rules, and activity history are stored locally. Allowed DNS lookups go to the upstream DNS provider selected in the app.

## Screens

Bottom navigation: Home, Ad Blocker, Optimizer, Statistics, Settings — plus Filters, Rules, Privacy Browser, and About as sub-screens. Reference screenshots of the original UI are in `docs/images/` (branding in those images is the original's; the built app shows DevxyzShield).

## Project details

- Package: `com.jepongdevxyz.devxyzshield`
- App name: DevxyzShield
- Version: `1.0-devxyz` (versionCode 1)
- minSdk 29, targetSdk 36, compileSdk 36
- Update check repository: `JepongDevxyz/DevxyzShield`
- Honest limits (same as the reference): first-party multiplexed streaming ads (YouTube, Facebook, TikTok, etc.) cannot be blocked by DNS alone without breaking playback; apps with hardcoded encrypted DNS can bypass a local DNS VPN; cosmetic hiding applies inside the built-in browser only.

## Build

Local Gradle in the assistant VM is known-broken, so builds are verified in GitHub Actions (see `.github/workflows/build.yml` — this file must be added via the GitHub web UI, because the GitHub App has no `workflows` permission to push it via API).

Debug build (no keystore needed):

```
./gradlew assembleDebug
```

Release build requires a keystore via `DEVXYZSHIELD_KEYSTORE_PATH`, `DEVXYZSHIELD_KEYSTORE_PASSWORD`, `DEVXYZSHIELD_KEY_ALIAS`, and `DEVXYZSHIELD_KEY_PASSWORD`.

## Project structure

```
app/src/main/java/com/jepongdevxyz/devxyzshield/
├── DevxyzShieldApplication.kt
├── MainActivity.kt
├── data/ (Room DB, DataStore preferences, repository)
├── di/AppModule.kt
├── domain/model/
├── engine/
│   ├── browser/BrowserContentBlocker.kt
│   ├── dns/ (DnsPacketCodec.kt, IpPacketFraming.kt, UpstreamDnsResolver.kt)
│   ├── filter/FilterRuleEngine.kt
│   └── optimizer/SystemOptimizerEngine.kt
├── service/ (DevxyzShieldVpnService.kt, BootCompletedReceiver.kt)
├── ui/screens/ (Home, Activity, AdBlockerHub, Browser, Filters, Optimizer, Rules, Settings, About)
├── ui/theme/Theme.kt
├── ui/viewmodel/DevxyzShieldViewModel.kt
├── update/GitHubUpdate.kt
└── worker/ (FilterUpdateWorker.kt, SmartOptimizationWorker.kt)
```
