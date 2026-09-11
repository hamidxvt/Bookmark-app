#!/bin/bash
set -e

echo "🚀 Building APK with Railway..."

cd mobile
flutter clean
flutter pub get
flutter build apk --release --no-tree-shake-icons

APK_PATH="build/app/outputs/flutter-apk/app-arm64-v8a-release.apk"
VERSION=$(grep "version:" pubspec.yaml | head -1 | awk '{print $2}' | cut -d'+' -f1)

echo "✅ APK built: $APK_PATH"
echo "📦 Version: $VERSION"

# Upload to GitHub releases if GITHUB_TOKEN set
if [ -n "$GITHUB_TOKEN" ]; then
  echo "📤 Uploading to GitHub releases..."
  cd ..
  
  # Create release
  gh release create "v${VERSION}" "${APK_PATH}" \
    --title "Build ${VERSION}" \
    --notes "Automated build from Railway" \
    --draft=false || echo "Release already exists, uploading asset..."
  
  echo "✅ Uploaded to GitHub!"
fi

echo "🎉 Build complete!"
