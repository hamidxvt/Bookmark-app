# Bookmark SFA - Admin Panel

React 19 dashboard built with Vite, Tailwind CSS, React Router, and React Query.

## Setup

```bash
cd admin

# Install dependencies
npm install

# Start dev server
npm run dev
```

Panel runs on http://localhost:3000

## Environment

Create .env if needed:

```
VITE_API_URL=http://localhost:8000/api
```

## Build

```bash
# Development
npm run dev

# Production build
npm run build

# Preview production build
npm run preview
```

## Project Structure

**src/api/** - Axios client and endpoint definitions
- client.ts - All REST calls with auth interceptors

**src/components/** - Reusable UI components
- Layout.tsx - Sidebar navigation and main layout

**src/pages/** - Page components organized by feature
- auth/LoginPage.tsx - Login form
- DashboardPage.tsx - Stats and overview
- visits/VisitsPage.tsx - Visit list with filters
- visits/VisitDetailModal.tsx - Full visit drill-down
- visits/MissedVisitsPage.tsx - Missed visit approval queue
- officers/OfficersPage.tsx - Officer management
- officers/OfficerFormModal.tsx - Add/edit officer form
- samples/SamplesPage.tsx - Sample requests and ledger
- leaves/LeavesPage.tsx - Leave request approval
- payroll/PayrollPage.tsx - Salary ledger
- institutions/InstitutionsPage.tsx - School/shop database
- LiveMapPage.tsx - Real-time GPS tracking map

**src/hooks/** - Custom React hooks
- useAuth.ts - Zustand auth state management

**src/types/** - TypeScript interfaces
- index.ts - All domain models and API types

**src/utils/** - Utility functions

**src/App.tsx** - Route definitions and auth guards

**src/main.tsx** - React entry point

**src/index.css** - Global styles with Tailwind

## Dependencies

Main packages:

- react 19, react-dom 19
- react-router-dom - Client-side routing
- @tanstack/react-query - Data fetching and caching
- axios - HTTP client
- zustand - State management (auth)
- lucide-react - Icons
- date-fns - Date utilities
- @tailwindcss/vite - Tailwind CSS
- typescript - Type safety

See package.json for full list.

## Features

**Authentication**
- Login with phone and password
- Token stored in localStorage
- Auto-redirect to login if unauthorized
- Logout clears token

**Dashboard**
- Real-time stats (officers online, visits today, pending approvals)
- Auto-refresh every 30 seconds

**Officers**
- List all officers with filters
- Add new officer with salary structure
- Edit officer details
- Force password reset
- View personal info and assignments

**Visits**
- View all visits with filters (date, status, officer, institution)
- Click visit for full drill-down (GPS route, timings, contact, notes, photos)
- Filter by status (pending, ongoing, completed, missed)

**Missed Visits**
- Review queue of missed visits awaiting approval
- See photo evidence and written reason
- Approve (no penalty) or reject (apply salary deduction)
- Admin can override any decision

**Samples**
- Pending sample request approvals
- Full sample ledger with history
- View reminder dates sent (10, 20 day)
- Track payroll deduction date

**Leaves**
- Leave request approval queue
- View leave type and reason
- Approve or reject with note
- Track leave balance

**Payroll**
- Monthly salary ledger
- View breakdown: basic + deposit + earned daily + deductions
- Filter by month and officer
- See deduction reasons

**Institutions**
- Browse school and bookshop database
- View visit history for any institution
- See priority, address, contact info
- Map to visits for targeting

**Live Map**
- Google Maps showing all active officers
- Click officer to see current visit and last location
- Map updates every 5 seconds
- Shows exact coordinates

## UI Components

Built with Tailwind CSS and lucide-react icons:

- MaterialCardView-like cards
- Form inputs with validation feedback
- Tables with hover states
- Modals for detail views
- Sidebars with collapsible navigation
- Status badges with color coding
- Loading states and empty states

## Authentication Flow

1. POST /api/auth/login with credentials
2. Store token in localStorage via Zustand
3. Axios interceptor adds token to all requests
4. If 401 response, clear token and redirect to login
5. Protected routes check auth state before rendering

## API Integration

All API calls in src/api/client.ts:

- authApi - Login/logout
- officersApi - Officer CRUD and live positions
- visitsApi - Visit list and drill-down
- missedVisitsApi - Missed visit approval
- samplesApi - Sample requests and ledger
- leavesApi - Leave request approval
- payrollApi - Salary ledger
- institutionsApi - Institution data and history
- dashboardApi - Stats
- masterDataApi - Cities, areas, products

React Query automatically caches responses and enables offline/stale data.

## Styling

Global styles in index.css with Tailwind imports.

Component styles inline with Tailwind classes:

```jsx
<div className="bg-white rounded-xl border border-gray-100 p-5">
  <p className="text-gray-900 font-bold">Title</p>
</div>
```

Color scheme:
- Primary: #1B4F9B
- Accent: #F5A623
- Success: #388E3C
- Error: #D32F2F
- Warning: #F57C00

## Performance

- React Query caches all data automatically
- Refetch intervals configured per query (5-30s)
- Pagination (50 records/page) on large lists
- Lazy loading for modals and details
- Memoization on components

## Deployment

```bash
# Build
npm run build

# Output in dist/
# Deploy dist/ folder to your server
```

Can be hosted on:
- Vercel (recommended for React)
- Netlify
- AWS S3 + CloudFront
- Any static host + API on separate backend server

## Development Tips

- Use React Query DevTools to inspect cache
- Check Network tab in browser DevTools for API calls
- Use Redux/Zustand DevTools for state inspection
- Hot reload works on file save during `npm run dev`

## Notes

- All numbers formatted with locale-specific thousands separator
- Dates formatted as YYYY-MM-DD for consistency
- Timestamps in UTC
- Modals dismiss on backdrop click or close button
- Forms validate on submit
- API errors show toast notifications
