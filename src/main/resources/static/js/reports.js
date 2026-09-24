requireAuth();

// Endpoint + which fields to show per report type
const REPORTS = {
  "students": { endpoint: "/api/students", title: "Student List",
    columns: ["registerNumber","name","email","phone","departmentId","courseId","year","semester","section"] },
  "attendance": { endpoint: "/api/attendance", title: "Attendance Report",
    columns: ["studentId","subjectId","date","semester","status"] },
  "fees": { endpoint: "/api/fees", title: "Fees Report",
    columns: ["studentId","academicYear","semester","totalFee","paidAmount","pendingAmount","paymentStatus"] },
  "results": { endpoint: "/api/results", title: "Results Report",
    columns: ["studentId","examId","subjectId","marks","maxMarks","grade","gradePoint"] },
  "library-books": { endpoint: "/api/library/books", title: "Library - Books Report",
    columns: ["isbn","title","author","category","quantity","availableQuantity"] },
  "hostel-allocations": { endpoint: "/api/hostel/allocations", title: "Hostel Allocations Report",
    columns: ["studentId","roomId","allocationDate","status"] },
  "placements": { endpoint: "/api/placements", title: "Placements Report",
    columns: ["companyName","jobRole","packageOffered","eligibility","driveDate","location"] },
};

let currentReportData = [];
let currentColumns = [];

async function loadReport() {
  const type = document.getElementById("reportType").value;
  const config = REPORTS[type];
  document.getElementById("reportTitle").textContent = config.title;
  currentColumns = config.columns;

  try {
    currentReportData = await apiGet(config.endpoint);
  } catch (err) {
    currentReportData = [];
  }

  const header = document.getElementById("reportHeader");
  header.innerHTML = currentColumns.map(c => `<th>${c}</th>`).join("") + "<th>#</th>";

  const body = document.getElementById("reportBody");
  if (currentReportData.length === 0) {
    body.innerHTML = `<tr><td colspan="${currentColumns.length + 1}" class="text-center text-muted">No data</td></tr>`;
    return;
  }
  body.innerHTML = currentReportData.map((row, i) =>
    "<tr>" + currentColumns.map(c => `<td>${row[c] ?? ""}</td>`).join("") + `<td>${i + 1}</td></tr>`
  ).join("");
}

function exportCsv() {
  if (currentReportData.length === 0) {
    alert("Load a report first.");
    return;
  }
  const header = currentColumns.join(",");
  const rows = currentReportData.map(row =>
    currentColumns.map(c => `"${(row[c] ?? "").toString().replace(/"/g, '""')}"`).join(",")
  );
  const csv = [header, ...rows].join("\n");
  const blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
  const url = URL.createObjectURL(blob);
  const a = document.createElement("a");
  a.href = url;
  a.download = document.getElementById("reportType").value + ".csv";
  a.click();
  URL.revokeObjectURL(url);
}

document.getElementById("reportType").addEventListener("change", loadReport);
document.addEventListener("DOMContentLoaded", loadReport);
