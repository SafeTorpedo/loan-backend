async function fetchLoans() {
    const response = await fetch("/loans");
    const loans = await response.json();
    let loansHtml = "<ul>";
    loans.forEach((loan) => {
      loansHtml += `<li>${loan.loanType}: $${loan.loanAmount} - ${loan.loanStatus} (ID: ${loan.loanId})</li>`;
    });
    loansHtml += "</ul>";
    document.getElementById("loans").innerHTML = loansHtml;
  }

  async function fetchUsers() {
    const response = await fetch("/users");
    const users = await response.json();
    let usersHtml = "<ul>";
    users.forEach((user) => {
      usersHtml += `<li>${user.name} (ID: ${user.userId}) - Credit Score: ${user.creditScore}</li>`;
    });
    usersHtml += "</ul>";
    document.getElementById("users").innerHTML = usersHtml;
  }

  async function fetchUserById() {
    const userId = document.getElementById("userId").value;
    const response = await fetch(`/users/${userId}`);
    const user = await response.json();
    if (user) {
      document.getElementById(
        "userDetails"
      ).innerText = `${user.name} - Email: ${user.email} - Credit Score: ${user.creditScore}`;
    } else {
      document.getElementById("userDetails").innerText = "User not found";
    }
  }

  async function fetchLoanById() {
    const loanId = document.getElementById("loanId").value;
    const response = await fetch(`/loans/${loanId}`);
    const loan = await response.json();
    if (loan) {
      document.getElementById("loanDetails").innerText = `${
        loan.loanType
      }: $${loan.loanAmount} - ${loan.loanStatus} (Due Date: ${new Date(
        loan.calculateDueDate
      ).toLocaleDateString()})`;
    } else {
      document.getElementById("loanDetails").innerText = "Loan not found";
    }
  }

  async function fetchLoanDueDate() {
    const loanId = document.getElementById("loanIdDueDate").value;
    const response = await fetch(`/loans/${loanId}/due-date`);
    const dueDate = await response.json();
    if (dueDate) {
      document.getElementById(
        "loanDueDate"
      ).innerText = `Due Date: ${new Date(dueDate).toLocaleDateString()}`;
    } else {
      document.getElementById("loanDueDate").innerText = "Loan not found";
    }
  }

  async function fetchPaymentStatus() {
    const loanId = document.getElementById("loanIdPayment").value;
    const paymentDate = document.getElementById("paymentDate").value;
    const response = await fetch(`/loans/${loanId}/payment/${paymentDate}`);
    const paymentStatus = await response.json();
    if (paymentStatus) {
      document.getElementById(
        "paymentStatus"
      ).innerText = `Payment Status: ${paymentStatus}`;
    } else {
      document.getElementById("paymentStatus").innerText =
        "Loan or payment status not found";
    }
  }