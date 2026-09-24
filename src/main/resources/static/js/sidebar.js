// Renders the sidebar + topbar user badge on every page (except login.html).
// Nav items are filtered by role. Reports/Settings are included for
// completeness; access to the underlying data still goes through the
// backend's real authorization rules.

const NAV_SECTIONS = [
  {
    items: [
      { href: "dashboard.html", icon: "bi-speedometer2", label: "Dashboard", roles: "all" },
    ]
  },
  {
    title: "Academics",
    items: [
      { href: "departments.html", icon: "bi-diagram-3", label: "Departments", roles: ["SUPER_ADMIN","ADMIN"] },
      { href: "courses.html", icon: "bi-book", label: "Courses", roles: ["SUPER_ADMIN","ADMIN"] },
      { href: "subjects.html", icon: "bi-journal-bookmark", label: "Subjects", roles: ["SUPER_ADMIN","ADMIN","FACULTY"] },
      { href: "timetable.html", icon: "bi-calendar3", label: "Timetable", roles: "all" },
    ]
  },
  {
    title: "People",
    items: [
      { href: "students.html", icon: "bi-people", label: "Students", roles: ["SUPER_ADMIN","ADMIN","STAFF","FACULTY"] },
      { href: "staff.html", icon: "bi-person-badge", label: "Staff", roles: ["SUPER_ADMIN","ADMIN"] },
      { href: "faculty.html", icon: "bi-mortarboard", label: "Faculty", roles: ["SUPER_ADMIN","ADMIN"] },
    ]
  },
  {
    title: "Academic Records",
    items: [
      { href: "attendance.html", icon: "bi-calendar-check", label: "Attendance", roles: "all" },
      { href: "exams.html", icon: "bi-pencil-square", label: "Exams", roles: ["SUPER_ADMIN","ADMIN","FACULTY"] },
      { href: "results.html", icon: "bi-award", label: "Results", roles: "all" },
    ]
  },
  {
    title: "Finance",
    items: [
      { href: "fees.html", icon: "bi-cash-coin", label: "Fees", roles: "all" },
    ]
  },
  {
    title: "Campus",
    items: [
      { href: "library-books.html", icon: "bi-book-half", label: "Library - Books", roles: "all" },
      { href: "library-transactions.html", icon: "bi-arrow-left-right", label: "Library - Issue/Return", roles: ["SUPER_ADMIN","ADMIN","STAFF"] },
      { href: "hostels.html", icon: "bi-building", label: "Hostels", roles: ["SUPER_ADMIN","ADMIN","STAFF"] },
      { href: "rooms.html", icon: "bi-door-open", label: "Hostel Rooms", roles: ["SUPER_ADMIN","ADMIN","STAFF"] },
      { href: "hostel-allocations.html", icon: "bi-house-check", label: "Hostel Allocations", roles: "all" },
      { href: "buses.html", icon: "bi-bus-front", label: "Transport - Buses", roles: ["SUPER_ADMIN","ADMIN","STAFF"] },
      { href: "transport-allocations.html", icon: "bi-signpost-split", label: "Transport Allocations", roles: "all" },
    ]
  },
  {
    title: "Engagement",
    items: [
      { href: "leave.html", icon: "bi-envelope-paper", label: "Leave Requests", roles: "all" },
      { href: "notices.html", icon: "bi-megaphone", label: "Notices", roles: "all" },
      { href: "events.html", icon: "bi-calendar-event", label: "Events", roles: "all" },
      { href: "placements.html", icon: "bi-briefcase", label: "Placements", roles: "all" },
    ]
  },
  {
    title: "Reports",
    items: [
      { href: "reports.html", icon: "bi-graph-up", label: "Reports", roles: ["SUPER_ADMIN","ADMIN"] },
    ]
  },
  {
    title: "Account",
    items: [
      { href: "profile.html", icon: "bi-person-circle", label: "My Profile", roles: "all" },
      { href: "settings.html", icon: "bi-gear", label: "Settings", roles: "all" },
    ]
  },
];

function canSee(item, role) {
  return item.roles === "all" || item.roles.includes(role);
}

function renderSidebar() {
  const user = getCurrentUser();
  const role = user ? user.role : null;
  const currentPage = window.location.pathname.split("/").pop() || "dashboard.html";

  let sectionsHtml = "";
  NAV_SECTIONS.forEach(section => {
    const visibleItems = section.items.filter(it => canSee(it, role));
    if (visibleItems.length === 0) return;
    if (section.title) {
      sectionsHtml += `<div class="nav-section-title">${section.title}</div>`;
    }
    visibleItems.forEach(it => {
      const active = it.href === currentPage ? "active" : "";
      sectionsHtml += `<a class="nav-link ${active}" href="${it.href}"><i class="bi ${it.icon}"></i> ${it.label}</a>`;
    });
  });

  const sidebarHtml = `
    <div class="sidebar" id="sidebarEl">
      <div class="brand"><i class="bi bi-mortarboard-fill"></i> SMS Portal</div>
      <nav class="nav flex-column py-2">
        ${sectionsHtml}
      </nav>
      <div class="mt-auto py-3">
        <a class="nav-link logout-link" href="#" onclick="doLogout();return false;"><i class="bi bi-box-arrow-right"></i> Logout</a>
      </div>
    </div>`;

  document.getElementById("sidebarContainer").innerHTML = sidebarHtml;

  const badge = document.getElementById("userBadge");
  if (badge && user) {
    badge.innerHTML = `<i class="bi bi-person-circle me-1"></i>${user.name} <span class="badge bg-light text-dark border ms-1">${user.role.replace('_',' ')}</span>`;
  }

  const toggleBtn = document.getElementById("sidebarToggle");
  if (toggleBtn) {
    toggleBtn.addEventListener("click", () => {
      document.getElementById("sidebarEl").classList.toggle("show");
    });
  }
}

async function doLogout() {
  try { await apiPost("/api/auth/logout", {}); } catch (e) { /* ignore */ }
  sessionStorage.removeItem("currentUser");
  window.location.href = "login.html";
}

// Render as soon as requireAuth() (called by the page itself) has populated
// sessionStorage. We piggyback on DOMContentLoaded + a short check because
// requireAuth() is async and runs at the top of each page's own script.
document.addEventListener("DOMContentLoaded", () => {
  const wait = setInterval(() => {
    if (getCurrentUser()) {
      clearInterval(wait);
      renderSidebar();
    }
  }, 50);
  // Fallback: stop waiting after 5s (requireAuth will have redirected to login by then anyway)
  setTimeout(() => clearInterval(wait), 5000);
});
