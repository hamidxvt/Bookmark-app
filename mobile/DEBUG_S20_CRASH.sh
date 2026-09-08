#!/bin/bash

export PATH="/Users/apple/develop/flutter/bin:/Users/apple/Library/Android/sdk/platform-tools:$PATH"

echo "════════════════════════════════════════════════════════════════"
echo "📱 DEBUGGING SAMSUNG S20 ULTRA APP CRASH"
echo "════════════════════════════════════════════════════════════════"

echo ""
echo "1️⃣ Checking device and CPU architecture..."
adb devices
adb shell getprop ro.product.cpu.abi

echo ""
echo "2️⃣ Clearing logcat..."
adb logcat -c

echo ""
echo "3️⃣ Installing ARM64 APK..."
adb install -r /Users/apple/Documents/bookmark_field_force_manager/bookmark-arm64.apk

echo ""
echo "4️⃣ Launching app..."
adb shell am start -n com.bookmark.sfa/.MainActivity

echo ""
echo "5️⃣ Waiting 15 seconds for crash..."
sleep 15

echo ""
echo "6️⃣ Getting full crash logs..."
echo "════════════════════════════════════════════════════════════════"
adb logcat -d | grep -i "flutter\|crash\|exception\|error\|fatal\|native" | tail -100

echo ""
echo "7️⃣ Also checking native crash logs..."
adb logcat -d | grep -i "signal\|segfault\|abort" | tail -20

echo ""
echo "════════════════════════════════════════════════════════════════"
echo "✅ Full diagnostic complete!"
echo "════════════════════════════════════════════════════════════════"
