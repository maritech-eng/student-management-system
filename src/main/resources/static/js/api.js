// Shared API helper. All requests use credentials:"include" so the
// Spring Security session cookie (JSESSIONID) is sent along.

async function apiRequest(url, method, body) {
  const options = {
    method,
    credentials: "include",
    headers: { "Content-Type": "application/json" },
  };
  if (body !== undefined) {
    options.body = JSON.stringify(body);
  }

  const res = await fetch(url, options);

  if (res.status === 401) {
    // Session expired or not logged in - send back to login
    window.location.href = "login.html";
    throw new Error("Not authenticated");
  }

  if (res.status === 204) {
    return null; // No Content (DELETE)
  }

  let data = null;
  const text = await res.text();
  if (text) {
    try { data = JSON.parse(text); } catch (e) { data = text; }
  }

  if (!res.ok) {
    const message = (data && (data.message || data.error)) || `Request failed (${res.status})`;
    throw new Error(message);
  }

  return data;
}

function apiGet(url) { return apiRequest(url, "GET"); }
function apiPost(url, body) { return apiRequest(url, "POST", body); }
function apiPut(url, body) { return apiRequest(url, "PUT", body); }
function apiDelete(url) { return apiRequest(url, "DELETE"); }

/**
 * Guards a page: redirects to login.html if there's no active session.
 * Also stores the current user in sessionStorage (per-tab, not persisted
 * across browser restarts) so the sidebar can show name/role without an
 * extra request on every page.
 */
async function requireAuth() {
  try {
    const user = await apiGet("/api/auth/me");
    sessionStorage.setItem("currentUser", JSON.stringify(user));
    if (user.role) {
      document.body.classList.add("role-" + user.role.toLowerCase());
    }
    return user;
  } catch (err) {
    window.location.href = "login.html";
    throw err;
  }
}

function getCurrentUser() {
  const raw = sessionStorage.getItem("currentUser");
  return raw ? JSON.parse(raw) : null;
}
