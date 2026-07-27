#!/bin/bash
# Bookmark SFA — Post-install setup script
# Run this after all Homebrew installs are complete

set -e
BOLD='\033[1m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${BOLD}Bookmark SFA — Full Setup${NC}"
echo ""

# ── Backend (Laravel) ─────────────────────────────────────────────────────────
echo -e "${YELLOW}Setting up backend...${NC}"
cd "$(dirname "$0")/backend"

# Link PHP 8.3 to PATH if not already
export PATH="/opt/homebrew/opt/php@8.3/bin:/usr/local/opt/php@8.3/bin:$PATH"

# Create proper Laravel project first (gets boilerplate), then overwrite with our files
composer create-project laravel/laravel /tmp/bookmark_laravel_base --no-interaction

# Copy our custom files over the base project
cp -r /tmp/bookmark_laravel_base/. .
# Our files take precedence (already written)
echo -e "${GREEN}✓ Laravel base installed${NC}"

# Configure .env
cp .env.example .env
php artisan key:generate

# Create MySQL database
mysql -u root -pbookmark_dev -e "CREATE DATABASE IF NOT EXISTS bookmark_sfa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;" 2>/dev/null || \
mysql -u root -e "CREATE DATABASE IF NOT EXISTS bookmark_sfa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# Run migrations
php artisan migrate --force
echo -e "${GREEN}✓ Database migrated${NC}"

# Seed initial data
php artisan db:seed --class=DatabaseSeeder --force 2>/dev/null || echo "(No seeder yet — add one later)"

# Install Sanctum
php artisan vendor:publish --provider="Laravel\Sanctum\SanctumServiceProvider" --force

# Storage link
php artisan storage:link
echo -e "${GREEN}✓ Backend ready${NC}"

# ── Admin Panel ───────────────────────────────────────────────────────────────
echo ""
echo -e "${YELLOW}Setting up admin panel...${NC}"
cd "$(dirname "$0")/admin"
# Already installed — just verify
npm install
echo -e "${GREEN}✓ Admin panel ready${NC}"

# ── Instructions ─────────────────────────────────────────────────────────────
echo ""
echo -e "${BOLD}== Everything is ready! ==\n${NC}"
echo -e "To start development:\n"
echo -e "  Terminal 1 (Backend API):  ${BOLD}cd backend && php artisan serve${NC}"
echo -e "  Terminal 2 (Admin Panel):  ${BOLD}cd admin && npm run dev${NC}"
echo -e "  Terminal 3 (Scheduler):    ${BOLD}cd backend && php artisan schedule:work${NC}"
echo -e "  Android Studio:            ${BOLD}Open mobile/ folder → Run on Pixel 7 API 34 emulator${NC}"
echo ""
echo -e "  Mobile app API URL:        ${BOLD}http://10.0.2.2:8000/api${NC}"
echo -e "  Admin panel:               ${BOLD}http://localhost:3000${NC}"
echo ""
