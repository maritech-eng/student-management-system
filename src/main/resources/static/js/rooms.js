// Auto-generated CRUD screen for Hostel Rooms
requireAuth();

const ENDPOINT = "/api/hostel/rooms";
const FIELDS = [{name:"hostelId", label:"Hostel Id", type:"text"}, {name:"roomNumber", label:"Room Number", type:"text"}, {name:"capacity", label:"Capacity", type:"number"}, {name:"occupiedBeds", label:"Occupied Beds", type:"number"}];

let allRecords = [];
let filteredRecords = [];
let currentPage = 1;
const pageSize = 8;
let editingId = null;
let sortField = null;
let sortAsc = true;

async function loadRecords() {
  try {
    allRecords = await apiGet(ENDPOINT);
    filteredRecords = allRecords;
    renderTable();
  } catch (err) {
    showAlert("Failed to load Hostel Rooms: " + err.message, "danger");
  }
}

function renderTable() {
  const tbody = document.getElementById("tableBody");
  if (filteredRecords.length === 0) {
    tbody.innerHTML = '<tr><td colspan="5" class="text-center text-muted py-4">No records found</td></tr>';
    document.getElementById("pagination").innerHTML = "";
    return;
  }
  const start = (currentPage - 1) * pageSize;
  const pageItems = filteredRecords.slice(start, start + pageSize);

  tbody.innerHTML = pageItems.map(item => {
    const cells = COLUMNS.map(c => `<td>${item[c] ?? ''}</td>`).join("");
    return `<tr>
      ${cells}
      <td class="text-nowrap row-actions">
        <button class="btn btn-sm btn-outline-secondary" onclick="viewRecord('${item.id}')" title="View"><i class="bi bi-eye"></i></button>
        <button class="btn btn-sm btn-outline-primary edit-btn" onclick="editRecord('${item.id}')" title="Edit"><i class="bi bi-pencil"></i></button>
        <button class="btn btn-sm btn-outline-danger delete-btn" onclick="deleteRecord('${item.id}')" title="Delete"><i class="bi bi-trash"></i></button>
      </td>
    </tr>`;
  }).join("");

  renderPagination();
}

const COLUMNS = ["hostelId", "roomNumber", "capacity", "occupiedBeds"];

function renderPagination() {
  const totalPages = Math.ceil(filteredRecords.length / pageSize);
  const pag = document.getElementById("pagination");
  let html = "";
  for (let i = 1; i <= totalPages; i++) {
    html += `<li class="page-item ${i === currentPage ? 'active' : ''}"><a class="page-link" href="#" onclick="goToPage(${i});return false;">${i}</a></li>`;
  }
  pag.innerHTML = html;
}

function goToPage(p) { currentPage = p; renderTable(); }

function sortBy(field) {
  if (sortField === field) { sortAsc = !sortAsc; } else { sortField = field; sortAsc = true; }
  filteredRecords.sort((a, b) => {
    const av = (a[field] ?? "").toString().toLowerCase();
    const bv = (b[field] ?? "").toString().toLowerCase();
    return sortAsc ? av.localeCompare(bv) : bv.localeCompare(av);
  });
  currentPage = 1;
  renderTable();
}

document.getElementById("searchInput").addEventListener("input", (e) => {
  const q = e.target.value.toLowerCase();
  filteredRecords = allRecords.filter(item =>
    FIELDS.some(f => (item[f.name] ?? "").toString().toLowerCase().includes(q))
  );
  currentPage = 1;
  renderTable();
});

function openAddModal() {
  editingId = null;
  document.getElementById("formModalTitle").textContent = "Add Room";
  FIELDS.forEach(f => {
    const el = document.getElementById("f_" + f.name);
    if (el) el.value = "";
  });
  hideFormError();
  new bootstrap.Modal(document.getElementById("formModal")).show();
}

async function editRecord(id) {
  editingId = id;
  const item = allRecords.find(r => r.id === id);
  if (!item) return;
  document.getElementById("formModalTitle").textContent = "Edit Room";
  FIELDS.forEach(f => {
    const el = document.getElementById("f_" + f.name);
    if (el) el.value = item[f.name] ?? "";
  });
  hideFormError();
  new bootstrap.Modal(document.getElementById("formModal")).show();
}

function viewRecord(id) {
  const item = allRecords.find(r => r.id === id);
  if (!item) return;
  const rows = FIELDS.map(f => `<tr><th class="text-muted small">${f.label}</th><td>${item[f.name] ?? '-'}</td></tr>`).join("");
  document.getElementById("viewBody").innerHTML = `<table class="table table-sm">${rows}</table>`;
  new bootstrap.Modal(document.getElementById("viewModal")).show();
}

async function saveEntity() {
  const payload = {};
  FIELDS.forEach(f => {
    const el = document.getElementById("f_" + f.name);
    if (!el) return;
    let val = el.value;
    if (f.type === "number") {
      payload[f.name] = val === "" ? null : Number(val);
    } else {
      payload[f.name] = val;
    }
  });

  try {
    if (editingId) {
      await apiPut(`${ENDPOINT}/${editingId}`, payload);
      showAlert("Room updated successfully", "success");
    } else {
      await apiPost(ENDPOINT, payload);
      showAlert("Room added successfully", "success");
    }
    bootstrap.Modal.getInstance(document.getElementById("formModal")).hide();
    loadRecords();
  } catch (err) {
    showFormError(err.message);
  }
}

async function deleteRecord(id) {
  if (!confirm("Are you sure you want to delete this record? This cannot be undone.")) return;
  try {
    await apiDelete(`${ENDPOINT}/${id}`);
    showAlert("Deleted successfully", "success");
    loadRecords();
  } catch (err) {
    showAlert("Delete failed: " + err.message, "danger");
  }
}

function showAlert(msg, type) {
  const box = document.getElementById("alertBox");
  box.innerHTML = `<div class="alert alert-${type} alert-dismissible fade show" role="alert">${msg}
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button></div>`;
  setTimeout(() => { box.innerHTML = ""; }, 4000);
}

function showFormError(msg) {
  const el = document.getElementById("formError");
  el.textContent = msg;
  el.classList.remove("d-none");
}
function hideFormError() {
  document.getElementById("formError").classList.add("d-none");
}



document.addEventListener("DOMContentLoaded", loadRecords);
