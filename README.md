# Field Force Manager — Next.js Admin Dashboard

Production-ready B2B sales force automation platform for Bookmark Publishing. Real-time GPS tracking, customer management, visit scheduling, and product catalog.

## Tech Stack

- **Frontend:** Next.js 16 (App Router), TypeScript, Tailwind CSS, shadcn/ui, Recharts
- **Backend:** Next.js API Routes (serverless), Axios for staging proxy
- **Database:** PostgreSQL + Prisma ORM (ready to connect)
- **Auth:** NextAuth.js v4 (session-based)
- **Real-time:** Pusher/Soketi (ready for GPS tracking & live chat)
- **Deployment:** Railway (Nixpacks)

## Features

✅ **16 Admin Portal Screens**
- Dashboard with KPI cards & charts
- Sales Team (Bookers) management
- Customer CRM with geo-tagging
- Visit scheduling & tracking
- Product catalog with brands/subjects/series
- Live location map with GPS pins
- Real-time inbox & support tickets
- Master data management (Cities, Zones, Areas)

✅ **Real Data Integration**
- Server-side proxy to staging.bookmark.services
- Live DataTable endpoints (bookers, customers, visits, products)
- Session persistence & auto-reauth
- 2,600+ customers, 3 cities, 10 zones

✅ **Beautiful UI/UX**
- Responsive mobile-first design
- Collapsible desktop sidebar with tooltips
- Mobile drawer navigation
- Auto-generated breadcrumbs
- Modal forms for CRUD operations
- Smooth animations & transitions

✅ **Production Ready**
- Environment-based configuration
- Database schema (16 models)
- API authentication ready
- Error handling & loading states

## Getting Started

### Prerequisites
- Node.js 18+
- PostgreSQL 12+
- Git

### Local Development

```bash
git clone https://github.com/hamidxvt/bookmark.git
cd fieldforce
npm install

# Copy and configure environment
cp .env.local.example .env.local

# Run dev server
npm run dev
```

Visit http://localhost:3000 → Login with:
- Email: `admin@gmail.com`
- Password: `admin#123`

### Database Setup

```bash
# Generate Prisma client
npx prisma generate

# Run migrations (when PostgreSQL is connected)
npx prisma migrate deploy

# Seed data (optional)
npx prisma db seed
```

## Deployment to Railway

### 1. Connect Repository
```bash
railway login
railway init
```

### 2. Set Environment Variables in Railway Dashboard

```
DATABASE_URL=postgresql://[user]:[password]@[host]/fieldforce
NEXTAUTH_SECRET=[generate-32-char-random-string]
NEXTAUTH_URL=https://your-app.up.railway.app
```

(See `.env.railway` for all required variables)

### 3. Deploy

```bash
railway up
```

Or push to GitHub and Railway auto-deploys via GitHub Actions.

## Project Structure

```
fieldforce/
├── src/
│   ├── app/
│   │   ├── (auth)/            # Login page
│   │   ├── (dashboard)/        # Protected dashboard routes
│   │   ├── api/v1/            # REST API endpoints
│   │   ├── globals.css         # Global styles
│   │   └── layout.tsx          # Root layout
│   ├── components/
│   │   ├── layout/             # Sidebar, Header, MainContent
│   │   ├── dashboard/          # Dashboard charts & KPIs
│   │   ├── bookers/            # Sales team components
│   │   ├── customers/          # Customer components
│   │   ├── visits/             # Visit management
│   │   ├── products/           # Product catalog
│   │   ├── location/           # Live map
│   │   ├── requests/           # Support tickets
│   │   └── inbox/              # Messaging
│   ├── lib/
│   │   ├── auth.ts             # NextAuth config
│   │   ├── prisma.ts           # Database client
│   │   ├── staging.ts          # Staging.bookmark.services proxy
│   │   └── utils.ts            # Helper functions
│   ├── types/
│   │   └── index.ts            # TypeScript types
│   └── middleware.ts           # NextAuth middleware
├── prisma/
│   ├── schema.prisma           # Database schema
│   └── migrations/             # DB migrations
├── public/                      # Static assets
├── railway.json                 # Railway config
└── package.json
```

## API Endpoints

### Public (Unauthenticated)
- `POST /api/auth/callback/credentials` — Login
- `GET /api/auth/session` — Get session

### Protected (Authenticated)
- `GET /api/v1/dashboard` — Dashboard stats
- `GET /api/v1/bookers` — Sales team list
- `GET /api/v1/customers` — Customer list
- `GET /api/v1/visits` — Visits list
- `GET /api/v1/products` — Products list
- `GET /api/v1/requests` — Support tickets
- `GET /api/v1/location` — Live booker locations

## Authentication

- **Session Storage:** Database (NextAuth)
- **Strategy:** Credentials (email + password)
- **Protected Routes:** All `/dashboard/*` routes require session
- **Middleware:** `src/middleware.ts` enforces auth

## Database Schema

16 models for complete sales force automation:
- `User` (admin)
- `Booker` (sales team)
- `Customer`
- `Visit`
- `VisitReport`
- `Product`
- `Brand`
- `Subject`
- `Series`
- `City`
- `Zone`
- `Area`
- `CustomerAssignment`
- `Request`
- `Message`
- `GPSLog`

See `prisma/schema.prisma` for details.

## Next Steps

1. **Connect PostgreSQL** — Railway provides a PostgreSQL add-on
2. **Run Migrations** — `npx prisma migrate deploy`
3. **Replace Staging Proxy** — Implement real API endpoints once DB is live
4. **Add Real-time** — Wire up Pusher for GPS tracking & live chat
5. **Mobile App** — Same backend serves React Native booker app

## License

Proprietary — Bookmark Publishing

## Support

For deployment issues, see Railway docs: https://docs.railway.app
# Auto-build ready
