requireAuth();

async function loadDashboard() {
  try {
    const data = await apiGet("/api/dashboard/summary");

    document.getElementById("statStudents").textContent = data.totalStudents ?? 0;
    document.getElementById("statStaff").textContent = data.totalStaff ?? 0;
    document.getElementById("statFaculty").textContent = data.totalFaculty ?? 0;
    document.getElementById("statDepartments").textContent = data.totalDepartments ?? 0;
    document.getElementById("statCourses").textContent = data.totalCourses ?? 0;
    document.getElementById("statResults").textContent = data.resultsSummary?.recordCount ?? 0;

    const att = data.attendanceSummary || {};
    document.getElementById("attendanceText").textContent =
      `${att.present ?? 0} present / ${att.absent ?? 0} absent - overall ${att.overallPercentage ?? 0}%`;

    new Chart(document.getElementById("attendanceChart"), {
      type: "doughnut",
      data: {
        labels: ["Present", "Absent"],
        datasets: [{
          data: [att.present ?? 0, att.absent ?? 0],
          backgroundColor: ["#22c55e", "#ef4444"]
        }]
      },
      options: { plugins: { legend: { position: "bottom" } } }
    });

    const fees = data.feesSummary || {};
    document.getElementById("feesText").textContent =
      `Collected: ${(fees.totalCollected ?? 0).toLocaleString()} | Pending: ${(fees.totalPending ?? 0).toLocaleString()}`;

    new Chart(document.getElementById("feesChart"), {
      type: "bar",
      data: {
        labels: ["Collected", "Pending"],
        datasets: [{
          data: [fees.totalCollected ?? 0, fees.totalPending ?? 0],
          backgroundColor: ["#4f46e5", "#f59e0b"]
        }]
      },
      options: { plugins: { legend: { display: false } } }
    });

  } catch (err) {
    console.error("Failed to load dashboard summary", err);
  }
}

document.addEventListener("DOMContentLoaded", loadDashboard);
