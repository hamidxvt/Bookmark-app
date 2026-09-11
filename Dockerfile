FROM ghcr.io/cirruslabs/flutter:latest

WORKDIR /app

# Copy mobile app
COPY mobile/pubspec.yaml mobile/pubspec.lock* ./mobile/

# Get dependencies early (cache layer)
WORKDIR /app/mobile
RUN flutter pub get

# Copy entire mobile app
COPY mobile/ .

# Build release APK
RUN flutter build apk --release --no-tree-shake-icons

# Copy to accessible location
RUN cp build/app/outputs/flutter-apk/app-arm64-v8a-release.apk /app/bookmark_sfa.apk

# Expose for download
FROM node:20-alpine
COPY --from=0 /app/bookmark_sfa.apk /app/public/
WORKDIR /app
RUN echo "APK built successfully" > public/index.html
EXPOSE 3000
CMD ["npx", "http-server", "public", "-p", "3000"]
