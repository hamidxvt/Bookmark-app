# BOOKMARK
### Your ally in academic excellence

# BUSINESS REQUIREMENTS DOCUMENT
## Sales Force Automation System
Field sales visit planning, live tracking, sample control and performance-linked payroll for Bookmark's nationwide sales network.

---

| Metadata | Details |
| :--- | :--- |
| **Document Title** | Sales Force Automation System - Business Requirements Document |
| **Client** | Bookmark Publishing Pvt. Ltd. |
| **Prepared For** | Xvantech (Development Vendor) |
| **Version** | 1.0 - Draft for Review |
| **Date** | 23 July 2026 |
| **Status** | Issued for vendor feasibility assessment and estimation |
| **Classification** | Confidential |

**Address:** Bookmark Publishing Pvt. Ltd. B-424, Street No. 3, Block-13, Gulberg, Federal B Area, Karachi  
**CONFIDENTIAL**

---

## Contents
1. Project Background
   - 1.1 About Bookmark
   - 1.2 The Business Problem
   - 1.3 Legacy Vendor Transition and Handover Risk
   - 1.4 Immediate Action Required from Xvantech
2. Project Objectives
3. Scope of Work
   - 3.1 In Scope (Phase 1)
   - 3.2 Out of Scope (Phase 1)
4. User Roles and Permissions
5. Master Data Requirements
6. Functional Requirements
   - 6.1 Authentication and Onboarding
   - 6.2 Automated Visit Planning Engine
   - 6.3 Day Start and Day End
   - 6.4 Visit Execution and Live Tracking
   - 6.5 Visit Outcome Capture
   - 6.6 Ad-hoc and Pre-Scheduled Visits
   - 6.7 Missed Visits, Reasons and Approvals
   - 6.8 Sample Management
   - 6.9 Leave Management
   - 6.10 Salary, Deductions and Security Deposit
   - 6.11 Motivation and Engagement
   - 6.12 Admin Panel and Reporting
7. Business Rules Summary
8. Non-Functional Requirements
9. Assumptions, Dependencies and Open Items (TBD)
   - 9.1 Assumptions
   - 9.2 Dependencies
   - 9.3 Consolidated Open Items (TBD)
10. Future Roadmap (Phase 2 and Beyond)
11. Acceptance Criteria
12. Sign-Off

---

## SECTION 1: Project Background

### 1.1 About Bookmark
Bookmark Publishing Pvt. Ltd. is one of Pakistan's leading educational publishers, producing textbooks and learning resources for Early Years, Primary, Lower Secondary, O-Level and A-Level. The company sells through a nationwide field sales network that calls directly on schools and book shops. Its customer base includes major school systems across the country.

Because revenue depends almost entirely on field activity — school visits, sample placement and relationship building with decision makers — the productivity of the sales team is the single largest controllable driver of the business.

### 1.2 The Business Problem
Bookmark currently has no reliable way to verify, measure or manage what its sales officers do during the working day. This is the core problem this project exists to solve. The specific pain points are:

- **No visibility of field activity:** Management cannot confirm whether a sales officer actually visited a school, when they arrived, or how long they stayed.
- **No structured visit planning:** Route and target selection is left to the individual, so high-value schools are visited inconsistently while others are missed entirely.
- **Unverifiable reasons for non-performance:** When a visit does not happen, there is no evidence trail and no approval mechanism, so poor performance cannot be distinguished from genuine obstacles.
- **Uncontrolled sample distribution:** Samples are issued without a hard annual limit per officer, without approval workflow, and without any follow-up to confirm whether the sample was returned or converted — representing direct and recurring financial leakage.
- **No link between performance and pay:** Salary is disbursed irrespective of whether daily visit targets were met.
- **Cities without a physical office cannot be supervised at all:** Bookmark is expanding into locations where no manager is physically present to observe the team.

The proposed Sales Force Automation (SFA) system addresses all of the above by making every field activity planned, tracked, evidenced, reviewed and financially accountable.

### 1.3 Legacy Vendor Transition and Handover Risk
A previous vendor developed an earlier version of the mobile application and admin panel. Bookmark must proceed on the assumption that the source code for the existing application and admin panel may never be handed over. This is a live commercial risk, not a hypothetical one, and it directly affects how this project must be started.

Bookmark currently retains working access to the deployed application and the admin panel. That access window may close at any time. Everything of value must therefore be extracted and documented while it still exists.

### 1.4 Immediate Action Required from Xvantech
Before development planning begins, and as a matter of urgency, Xvantech is requested to complete the following discovery and preservation exercise:

- Capture and archive every screen and interface of the existing mobile application (APK), including all states, error screens and edge-case flows.
- Capture and archive every screen of the existing admin panel, including all reports, filters and configuration pages.
- Document the current end-to-end operational flow — how the application behaves today, what data it captures, and how the admin panel consumes it.
- Export or record any accessible master data (users, areas, schools, shops, historical visits) before access is lost.
- Study the captured material and issue a written feasibility assessment answering the central question: can the existing application be built upon, or must the system be rebuilt from the ground up?

> **TBD:** The feasibility outcome (rebuild vs. extend) is not yet determined. Project timeline, commercial estimate and technology stack all remain open until Xvantech submits this assessment. This BRD defines the required business behaviour and is deliberately independent of that decision.

---

## SECTION 2: Project Objectives

The system must deliver against the following measurable objectives:

| ID | Objective | Success Definition |
| :--- | :--- | :--- |
| **O-1** | Complete activity visibility | Every action of every sales officer during working hours is logged and retrievable from the admin panel. |
| **O-2** | Structured, automated visit planning | Seven visits per officer per working day, generated automatically, prioritised by business value and sequenced into an efficient route. |
| **O-3** | Real-time field tracking | Management can observe an officer's live position and journey during an active visit, comparable to a ride-hailing tracking view. |
| **O-4** | Evidence-based exception handling | Any missed visit requires a photographic and written justification that is formally reviewed and either approved or penalised. |
| **O-5** | Controlled sample distribution | Samples are capped by an annual per-officer limit, require prior approval, and are tracked to return or recovery. |
| **O-6** | Performance-linked compensation | A defined portion of salary is earned daily against completed visits and is automatically deducted when targets are unmet without approved cause. |
| **O-7** | Office-independent operation | The system functions fully in cities where Bookmark has no physical office and no on-ground supervisor. |
| **O-8** | Extensibility | The architecture accommodates future modules without redevelopment of the core. |

---

## SECTION 3: Scope of Work

### 3.1 In Scope (Phase 1)
Phase 1 comprises two connected products:
- A **mobile application for field sales officers** published on Google Play Store, used to start the working day, execute and record visits, request samples, apply for leave and view personal performance.
- A **web-based admin panel** used by management to assign visits, monitor officers live, review and approve exceptions, control samples, and analyse performance through dashboards and reports.

The functional areas delivered in Phase 1 are:

| Functional Area | Coverage |
| :--- | :--- |
| **Authentication and onboarding** | Admin-controlled onboarding, credential issue, password management. No public sign-up. |
| **Master data management** | Cities, areas, schools, shops, priority classification, product catalogue, officer-area mapping. |
| **Automated visit planning** | Nightly job generating the next day's seven prioritised, routed visits per officer. |
| **Visit execution** | Day start, sequential visit start, GPS route guidance, time and distance capture. |
| **Live tracking** | Real-time officer position visible in the admin panel during an active visit. |
| **Visit outcome capture** | Contact person, designation, contact number, feedback, samples handed over, visit type. |
| **Exception management** | Photographic proof and written reason for missed visits; review, approval or penalty. |
| **Sample management** | Annual limit, request and approval workflow, reminders, recovery and payroll deduction. |
| **Leave management** | 28 annual leaves (10 sick, 18 casual), in-app application and approval. |
| **Compensation engine** | Three-part salary structure, daily earned component, automatic deductions, security deposit ledger. |
| **Engagement features** | Motivational prompts at day milestones and a daily sales tip/quote of the day. |
| **Admin reporting** | Dashboards, multi-dimensional filters, per-visit drill-down, per-institution visit history. |

### 3.2 Out of Scope (Phase 1)
The following are explicitly excluded from Phase 1 and are addressed in Section 10 (Future Roadmap):
- Desktop/PC employee productivity monitoring
- End-to-end payroll processing
- Paperless accounting, book-keeping and invoicing
- Email marketing and campaign scheduling
- AI chatbot across company data

---

## SECTION 4: User Roles and Permissions

Four distinct roles operate the system. All roles are created and controlled from the admin panel; none can self-register.

| Role | Interface | Core Permissions |
| :--- | :--- | :--- |
| **Sales Officer** | Mobile app | Start day; execute assigned visits in sequence; create ad-hoc visits; record visit outcomes; edit own same-day visits; upload missed-visit evidence; request samples; mark samples received; apply for leave; view own calendar, performance and penalties; change own password. |
| **Coordinator / Office Agent** | Admin panel | Receive inbound customer queries; create and assign priority visits to the relevant area officer, including purpose, products to carry and instructions. |
| **City Head** | Admin panel | Supervise all officers in their city; review and approve or reject missed-visit reasons; approve sample requests; view city-level dashboards and reports. |
| **Administrator** | Admin panel | Full system access; onboard employees and define salary structure and sample limit; manage master data; view all officers across all cities including live tracking; view and reset credentials; override any approval; access all reports and financial ledgers. |

> **TBD:** The document refers to both a "city head" and a "checker" as the approver for sample requests. Bookmark to confirm whether these are the same role or two separate roles with different approval authority.  
> **TBD:** Administrator visibility of user passwords is stated as a requirement. Storing passwords in a retrievable form is a security weakness. Recommended alternative: the administrator can force a password reset and issue new credentials, but cannot view existing passwords. Bookmark to confirm the preferred approach.

---

## SECTION 5: Master Data Requirements

The automated planning engine depends entirely on the quality of master data. The system must hold and allow maintenance of the following:

| Entity | Description and Attributes |
| :--- | :--- |
| **Cities** | All cities where Bookmark operates, including those without a physical office. |
| **Areas** | Each city divided into defined areas. Every area is mapped to exactly one sales officer. |
| **Schools** | Full school database per area, each classified as High, Medium or Low priority. Example: Gulshan-e-Iqbal, Karachi contains approximately 500 schools. |
| **Book shops** | Full shop database per area. Example: Gulshan-e-Iqbal, Karachi contains approximately 300 shops. |
| **Institution profile** | Name, address, geo-coordinates, contact persons and designations, priority class, and complete visit history. |
| **Products** | Product catalogue with pricing, used for sample requests and value calculation. |
| **Officer-area mapping** | Which officer owns which area. Example: Hassan Ahmed owns Gulshan-e-Iqbal, Karachi. |
| **Visit types** | Complimentary visit, sample visit, fresh visit, and others as defined. |

> **TBD:** The complete list of visit types is not finalised. Confirmed so far: complimentary visit, sample visit, fresh visit. Bookmark to provide the full list with definitions.  
> **TBD:** Source, format and current completeness of the school and shop master data are not confirmed, nor is the ownership of ongoing data maintenance. This is a critical dependency — the planning engine cannot function without it.

---

## SECTION 6: Functional Requirements

### 6.1 Authentication and Onboarding

| Ref | Requirement |
| :--- | :--- |
| **FR-1.1** | The mobile application must not provide any public sign-up option. |
| **FR-1.2** | All employees are onboarded from the admin panel. Credentials are generated at onboarding and shared with the employee. |
| **FR-1.3** | The sales officer can change their own password from within the application. |
| **FR-1.4** | A forgot-password recovery flow must be available. |
| **FR-1.5** | During onboarding the administrator must define: personal and employment details, assigned city and area, reporting City Head, three-part salary structure (Section 6.10) and annual sample limit (Section 6.8). |
| **FR-1.6** | The administrator can view an employee's complete profile and system details. |

### 6.2 Automated Visit Planning Engine
This is the operational heart of the system. Each sales officer is required to complete seven visits per working day. The plan for the following day is generated automatically by a scheduled job that runs at 12:00 midnight.

#### Generation Logic
For each active sales officer, the nightly job executes in the following order:
1. **Step 1 — Coordinator-assigned visits:** Check whether the Coordinator has assigned any visit to this officer. Any such visit is placed in the next day's list on priority. These carry the coordinator's instructions: location, purpose, products to carry and actions required.
2. **Step 2 — Carry-forward:** Include any previously scheduled visit that did not take place.
3. **Step 3 — Pre-scheduled visits:** Include any visit already booked for that date from a prior visit outcome (Section 6.6).
4. **Step 4 — Master data fill:** Fill the remainder from area master data to reach a total of seven, using the standard mix below.
5. **Step 5 — Route optimisation:** Arrange all seven visits into a geographically efficient sequence and assign that ordered plan to the officer for the next day.

#### Standard Daily Mix

| # | Visit Slot | Source |
| :--- | :--- | :--- |
| **1** | Coordinator-assigned visit | Priority placed first |
| **2** | School (High priority) | From area master data |
| **3** | School (High priority) | From area master data |
| **4** | School (Medium priority) | From area master data |
| **5** | School (Medium priority) | From area master data |
| **6** | Book shop | From area master data |
| **7** | Book shop | From area master data |

> **Worked Example:** Hassan Ahmed is the sales officer for Gulshan-e-Iqbal, Karachi (holding ~500 schools and ~300 book shops). At midnight, the job finds 1 coordinator-assigned visit for him. It adds that visit first, then selects 2 high-priority schools, 2 medium-priority schools and 2 book shops from the Gulshan-e-Iqbal master data, routes all 7 into sequence, and publishes the plan to his application for the next working day.

> **TBD:** The selection rule for choosing which specific schools and shops are picked from the area pool is not defined (e.g. longest time since last visit, rotation cycle, or sales potential).  
> **TBD:** Whether the daily mix is fixed, configurable by administrator, or varies by city or area.  
> **TBD:** Behaviour when carried-forward and pre-scheduled visits together exceed seven is not defined.

### 6.3 Day Start and Day End

| Ref | Requirement |
| :--- | :--- |
| **FR-3.1** | The sales officer must explicitly start the day in the application before any other activity is permitted. |
| **FR-3.2** | No visit can be started until the day has been started. This is a hard system rule. |
| **FR-3.3** | The first assigned visit is intended to be started from home, before the officer departs, so that travel time to the first call is captured. |
| **FR-3.4** | If the officer does not start the day at all on a working day, that day is deducted from their leave balance (Section 6.9). |
| **FR-3.5** | The officer may declare that they cannot work on a given day, with a stated reason (for example heavy rain) without starting the day. |
| **FR-3.6** | The system records day start time, day end time and total active duration. |

> **TBD:** Working days per week, standard working hours, and treatment of public holidays/weekends are not specified.

### 6.4 Visit Execution and Live Tracking

| Ref | Requirement |
| :--- | :--- |
| **FR-4.1** | Visits are presented in the planned route sequence. The officer starts each visit from the application. |
| **FR-4.2** | On starting a visit, the application captures the officer's current location and opens map-based route navigation to the destination. |
| **FR-4.3** | From visit start, the system continuously records the officer's movement until the visit is closed. |
| **FR-4.4** | The system captures and locks, for each visit: travel time taken to reach the location and time spent at the location. |
| **FR-4.5** | The admin panel provides a live tracking view showing the officer's real-time position and journey during an active visit (equivalent to the live ride view in ride-hailing applications). |
| **FR-4.6** | Recorded time and location data for a completed visit are locked and cannot be edited by the sales officer. |

> **TBD:** Continuous tracking vs. active visit tracking only.  
> **TBD:** Offline data capture and sync behavior.

### 6.5 Visit Outcome Capture
On completing a visit, the sales officer records:
- Name of the person met
- Designation of the person met
- Contact number of the person met
- Feedback and discussion notes
- Whether samples were handed over, and if so which products and quantities
- Visit type (complimentary, sample, fresh, or other)
- Any follow-up date committed by the customer

**Same-day editing:** Officers can close a visit quickly and edit text/feedback details later that same day (until midnight or day end). System-captured timing and location data remain locked.

### 6.6 Ad-hoc and Pre-Scheduled Visits
- **Ad-hoc visits:** Officers can create and start unplanned visits on the spot. Tracked and recorded identically to planned visits.
- **Pre-scheduled visits:** When a customer requests a return call on a specific future date, the system pre-schedules it and fills only the remaining visits for that date during the nightly run.

### 6.7 Missed Visits, Reasons and Approvals

| Ref | Requirement |
| :--- | :--- |
| **FR-7.1** | The officer must upload a photograph of the situation preventing the visit (e.g. heavy rain or a police cordon). |
| **FR-7.2** | The officer must also submit a written reason alongside the photograph. |
| **FR-7.3** | The reason is routed to the officer's City Head for review. |
| **FR-7.4** | If the City Head approves the reason, no penalty applies. |
| **FR-7.5** | If the City Head rejects the reason, a penalty is applied and becomes visible in the officer's profile. |
| **FR-7.6** | The Administrator can view and override any approval decision. |
| **FR-7.7** | A missed visit is marked, carried forward, and displays an attempt counter (e.g., 1/5). |

### 6.8 Sample Management

| Ref | Requirement |
| :--- | :--- |
| **FR-8.1** | Each sales officer is assigned an annual sample limit in monetary value (e.g., PKR 300,000/year). |
| **FR-8.2** | Officer requests samples through the app; system auto-calculates PKR value from the product catalogue. |
| **FR-8.3** | Triggers approval notification to City Head / Checker. |
| **FR-8.4** | On approval, sample value is deducted from annual sample limit. |
| **FR-8.5** | 10-day automated reminder to update sample status. |
| **FR-8.6** | Officer can mark sample as received/recovered in-app. |
| **FR-8.7** | 20-day automated second reminder. |
| **FR-8.8** | Un-updated sample after 2nd reminder is automatically deducted from the officer's next payroll. |
| **FR-8.9** | Administrator has visibility of full sample ledger. |

### 6.9 Leave Management
- **Entitlement:** 28 days total (10 Sick, 18 Casual).

| Ref | Requirement |
| :--- | :--- |
| **FR-9.1** | Apply for leave directly from mobile app. |
| **FR-9.2** | Working day without "Day Start" automatically deducts 1 day from leave balance. |
| **FR-9.3** | Leave balance visible to officer in-app and management on admin panel. |

### 6.10 Salary, Deductions and Security Deposit
Compensation structure example (Total: PKR 125,000/month based on 20 working days):

| Component | Amount | Treatment |
| :--- | :--- | :--- |
| **A. Basic Salary** | PKR 40,000 | Paid unconditionally every month. |
| **B. Security Deposit** | PKR 25,000 | Withheld monthly and accumulated; released at year end. |
| **C. Performance Component** | PKR 60,000 | PKR 3,000 earned per working day (20 days/month), subject to visit completion. |
| **Total** | **PKR 125,000** | |

- **Deduction Rule:** Daily performance allowance (PKR 3,000) is deducted if visits are incomplete AND the submitted reason is rejected by City Head.

### 6.11 Motivation and Engagement

| Trigger | Content |
| :--- | :--- |
| **On day start** | Encouraging message (e.g., "Best of luck, you can do it.") |
| **At half day** | Progress message (e.g., "You are doing great, half way to go.") |
| **On day completion** | Congratulatory message on completing all assigned visits. |
| **Daily** | Sales-related tip or Quote of the Day. |

### 6.12 Admin Panel and Reporting

| Ref | Requirement |
| :--- | :--- |
| **FR-12.1** | Dashboard view of overall field activity and performance. |
| **FR-12.2** | Filter officers by city and area. |
| **FR-12.3** | Filter officer activity and customer records by date range. |
| **FR-12.4** | Visit drilldown: click any visit to see full timings, route, location, contacts, notes, samples, and photos. |
| **FR-12.5** | Institution history: view full visit history for any school or shop. |
| **FR-12.6** | Live tracking view during active visits. |
| **FR-12.7** | Visibility of leave balances, penalties, sample ledgers, and salary calculations. |
| **FR-12.8** | Calendar view showing daily visit completion status. |

---

## SECTION 7: Business Rules Summary

- **BR-01:** Complete 7 visits per working day.
- **BR-02:** No visit can start before Day Start.
- **BR-03:** Each area mapped to exactly 1 officer.
- **BR-04:** Automated visit plan generated at 12:00 midnight.
- **BR-05:** Coordinator visits always take top priority.
- **BR-06:** Uncompleted visits carry forward with attempt counter.
- **BR-07:** Missed visit requires photo + written reason.
- **BR-08:** Missed visits approved/rejected by City Head; Super Admin can override.
- **BR-09:** System timing and location data are locked.
- **BR-10:** Visit detail text editing allowed same day only.
- **BR-11:** Annual leave entitlement = 28 days (10 sick, 18 casual).
- **BR-12:** Working day with no Day Start deducts 1 day from leave balance.
- **BR-13:** Performance salary component = PKR 3,000/day (20 days/month).
- **BR-14:** Rejected missed visit reason results in PKR 3,000 salary deduction.
- **BR-15:** Security deposit accumulates monthly and releases at year end.
- **BR-16:** Samples capped by annual monetary limit & require prior approval.
- **BR-17:** Sample status reminders at 10 and 20 days.
- **BR-18:** Unrecovered/un-updated sample deducted from next payroll.
- **BR-19:** No public sign-up; all accounts onboarded by Admin.

---

## SECTION 8: Non-Functional Requirements

- **NFR-1 (Availability):** Published on Google Play Store.
- **NFR-2 (Security):** Encrypted data in transit/at rest, RBAC, full audit trail.
- **NFR-3 (Reliability):** Foolproof operation in un-supervised cities without physical offices.
- **NFR-4 (Location Integrity):** Guard against mock location / GPS spoofing apps.
- **NFR-5 (Extensibility):** Architecture must accommodate future Phase 2 modules without core refactoring.
- **NFR-6 (Performance):** Responsive on mid-range Android devices over mobile data.
- **NFR-7 (Battery Efficiency):** Optimized location tracking to preserve battery life over full day.
- **NFR-8 (Data Retention):** Historical retention of all visits, samples, leaves, and payroll logs.

---

## SECTION 9: Assumptions, Dependencies and Open Items

### 9.1 Assumptions
- All officers carry Android smartphones with GPS and mobile data.
- Complete master data supplied by Bookmark.
- Phase 1 calculates and reports salary figures (no direct bank API disbursement).
- Standard month treated as 20 working days.
- Currency is PKR.

### 9.2 Dependencies
- Xvantech feasibility assessment (rebuild vs extend).
- Master data availability.
- Mapping service licensing (Google Maps).
- Bookmark Play Store developer account.

### 9.3 Consolidated Open Items (TBD Summary)
- **T-01:** Rebuild vs. extend legacy application.
- **T-02:** City Head vs. Checker approval roles.
- **T-03:** Admin password viewing vs. force reset.
- **T-04:** Full visit types list.
- **T-05:** School/Shop master data format & ownership.
- **T-06:** Master pool selection algorithm for nightly planner.
- **T-07:** Daily visit mix configurability.
- **T-08:** Handling overflow when pre-scheduled + carry-forwards > 7.
- **T-09:** Working calendar and public holiday policy.
- **T-10:** Continuous vs active-visit GPS tracking.
- **T-11:** Offline data sync limits.
- **T-12:** Daily edit cut-off exact time.
- **T-13:** Ad-hoc visits counting toward daily 7 target.
- **T-14:** Maximum attempt count for carry-forward visits.
- **T-15:** SLA for City Head reason approvals.
- **T-16 to T-27:** Additional business policy decisions regarding samples, leaves, salary deductions, and technical specs.

---

## SECTION 10: Future Roadmap (Phase 2 and Beyond)

- **R-1:** Desktop Employee Productivity Monitoring (Office Staff).
- **R-2:** End-to-End Automated Payroll Execution.
- **R-3:** Paperless Accounting & Invoicing Integration.
- **R-4:** Targeted Email Marketing & Campaign Scheduler.
- **R-5:** AI Chatbot across enterprise company data.

---

## SECTION 11: Acceptance Criteria

- **AC-01:** Admin can onboard sales officer with full salary, sample limit, and area mapping.
- **AC-02:** Coordinator can assign priority visits.
- **AC-03:** Nightly midnight job generates 7-visit routed plan applying standard mix.
- **AC-04:** Hard gate blocking visit execution before Day Start.
- **AC-05:** Starting visit opens navigation, logs travel time, on-site time, and GPS path.
- **AC-06:** Live map position visible on Admin Panel.
- **AC-07:** Outcomes recordable with same-day text editing.
- **AC-08:** Creation and tracking of ad-hoc visits.
- **AC-09:** Pre-scheduling future visits from visit outcomes.
- **AC-10:** Photo + text justification for missed visits with City Head approval routing and penalty application.
- **AC-11:** Attempt counter increment on carry-forward visits.
- **AC-12:** Sample requests auto-calc value, enforce limits, and issue 10/20 day reminders.
- **AC-13:** Unresolved samples auto-deduct from payroll.
- **AC-14:** In-app leave application and auto-deduction on missing Day Start.
- **AC-15:** Salary ledger accurately reflects basic, deposit, earned daily component, and deductions.
- **AC-16:** Motivational prompts at specified triggers.
- **AC-17:** Multi-level dashboard filtering and full drill-down history.
- **AC-18:** Production deployment on Google Play Store.

---

## SECTION 12: Sign-Off

| Field | Stakeholder / Representative |
| :--- | :--- |
| **Name** | __________________________________________ |
| **Designation** | __________________________________________ |
| **Signature** | __________________________________________ |
| **Date** | __________________________________________ |

*Document Control: Version 1.0, issued 23 July 2026.*
