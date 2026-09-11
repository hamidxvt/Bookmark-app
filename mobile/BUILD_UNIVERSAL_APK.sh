#!/bin/bash

# ════════════════════════════════════════════════════════════════════════════════
# BUILD UNIVERSAL APK FOR BOOKMARK FIELD FORCE APP
# ════════════════════════════════════════════════════════════════════════════════
# This script builds a universal APK compatible with ALL Android devices:
# - CPU architectures: arm64-v8a, armeabi-v7a, x86_64, x86
# - Android versions: API 24+ (Android 7.0+)
# - Devices: Samsung, Xiaomi, OnePlus, low-end phones, everything
# ════════════════════════════════════════════════════════════════════════════════

set -e  # Exit on error

echo "════════════════════════════════════════════════════════════════"
echo "🚀 BUILDING UNIVERSAL APK"
echo "════════════════════════════════════════════════════════════════"

# Set up environment
export PATH="/Users/apple/develop/flutter/bin:/Users/apple/Library/Android/sdk/emulator:/Users/apple/Library/Android/sdk/platform-tools:$PATH"

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

# Step 1: Clean previous builds
echo ""
echo "📦 Step 1: Cleaning previous builds..."
rm -rf build/
flutter clean

# Step 2: Get dependencies
echo ""
echo "📦 Step 2: Fetching dependencies..."
flutter pub get

# Step 3: Build universal APK
echo ""
echo "📦 Step 3: Building universal APK..."
echo "   CPU architectures: arm64-v8a (ARM64), armeabi-v7a (ARM32), x86_64, x86"
echo "   Optimizations: Disabled minification for max compatibility"
echo "   Target SDK: 34 (Android 14)"
echo "   Min SDK: 24 (Android 7.0)"
echo ""
# --no-tree-shake-icons: keeps every Material Icons glyph in the font.
# The tree-shaker occasionally strips glyphs it can't statically prove
# are used, which shows up as tofu boxes on device. Disabling costs a
# few hundred KB but guarantees icons render on every screen.
flutter build apk --release --no-tree-shake-icons

# Step 4: Show build result
echo ""
echo "════════════════════════════════════════════════════════════════"
echo "✅ BUILD SUCCESSFUL!"
echo "════════════════════════════════════════════════════════════════"

APK_PATH="build/app/outputs/apk/release/app-release.apk"

if [ -f "$APK_PATH" ]; then
    SIZE=$(ls -lh "$APK_PATH" | awk '{print $5}')
    echo ""
    echo "📱 Universal APK Created:"
    echo "   Path: $APK_PATH"
    echo "   Size: $SIZE"
    echo "   Compatible with: ALL Android devices (API 24+)"
    echo ""
    echo "🔧 To install on device/emulator:"
    echo "   adb install -r $APK_PATH"
    echo ""
else
    echo "❌ APK not found at $APK_PATH"
    exit 1
fi

echo "════════════════════════════════════════════════════════════════"
