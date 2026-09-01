#!/usr/bin/env node

/**
 * Release APK Script
 * 
 * Usage:
 *   node scripts/release-apk.js --version-code 2 --version-name "1.1.0" --apk-path "./build/app/outputs/flutter-apk/app-release.apk" --notes "Bug fixes and performance improvements"
 *   
 * Options:
 *   --version-code    Required: Integer version code (e.g., 2, 3)
 *   --version-name    Required: Semantic version (e.g., "1.1.0")
 *   --apk-path        Required: Path to the APK file to upload
 *   --notes           Required: Release notes/changelog
 *   --mandatory       Optional: Make this a mandatory update (flag, default: false)
 *   --min-version     Optional: Minimum version code required before this update (e.g., 1)
 */

const fs = require('fs');
const path = require('path');
const { PrismaClient } = require('@prisma/client');

const prisma = new PrismaClient();

// Parse command line arguments
const args = process.argv.slice(2);
const options = {};

for (let i = 0; i < args.length; i++) {
  if (args[i].startsWith('--')) {
    const key = args[i].substring(2);
    if (args[i + 1] && !args[i + 1].startsWith('--')) {
      options[key] = args[i + 1];
      i++;
    } else {
      options[key] = true; // Flag
    }
  }
}

async function main() {
  try {
    // Validate inputs
    if (!options['version-code'] || !options['version-name'] || !options['apk-path'] || !options['notes']) {
      console.error('❌ Missing required arguments');
      console.error('Usage: node scripts/release-apk.js --version-code 2 --version-name "1.1.0" --apk-path "./app.apk" --notes "Changelog"');
      process.exit(1);
    }

    const versionCode = parseInt(options['version-code']);
    const versionName = options['version-name'];
    const apkPath = options['apk-path'];
    const releaseNotes = options['notes'];
    const isMandatory = options['mandatory'] === true || options['mandatory'] === 'true';
    const minVersionCode = options['min-version'] ? parseInt(options['min-version']) : null;

    // Validate version code is an integer
    if (isNaN(versionCode) || versionCode <= 0) {
      console.error('❌ version-code must be a positive integer');
      process.exit(1);
    }

    // Check if APK file exists
    if (!fs.existsSync(apkPath)) {
      console.error(`❌ APK file not found: ${apkPath}`);
      process.exit(1);
    }

    // Check if version already exists
    const existing = await prisma.appRelease.findUnique({
      where: { versionCode },
    });

    if (existing) {
      console.error(`❌ Version code ${versionCode} already exists`);
      process.exit(1);
    }

    // For demo purposes, we'll use a simple file path as download URL
    // In production, you'd upload to S3/Railway Volumes and get a public URL
    const downloadUrl = `/releases/apk/app-${versionName}-release.apk`;

    console.log('📦 Creating app release record...');
    const release = await prisma.appRelease.create({
      data: {
        versionCode,
        versionName,
        downloadUrl,
        releaseNotes,
        isMandatory,
        minVersionCode,
        changelogText: releaseNotes,
      },
    });

    console.log('✅ Release created successfully!');
    console.log('');
    console.log('📋 Release Details:');
    console.log(`   Version Code: ${release.versionCode}`);
    console.log(`   Version Name: ${release.versionName}`);
    console.log(`   Release Date: ${release.releaseDate}`);
    console.log(`   Mandatory: ${release.isMandatory ? 'Yes' : 'No'}`);
    console.log(`   Min Version: ${release.minVersionCode || 'None (all versions)'}`);
    console.log(`   Download URL: ${release.downloadUrl}`);
    console.log('');
    console.log('📝 Next Steps:');
    console.log('   1. Upload the APK to a public location (S3, Railway Volumes, etc.)');
    console.log('   2. Update the downloadUrl in the database if using a different location');
    console.log('   3. Test the update on a device with the previous version');

    process.exit(0);
  } catch (error) {
    console.error('❌ Error:', error.message);
    process.exit(1);
  } finally {
    await prisma.$disconnect();
  }
}

main();
