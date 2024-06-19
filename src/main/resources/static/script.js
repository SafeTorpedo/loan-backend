async function fetchLoans() {
  const loansDiv = document.getElementById('loans');

  // Check if the loans are already displayed
  if (loansDiv.style.display === 'block') {
    // Hide the loans
    loansDiv.style.display = 'none';
    loansDiv.innerHTML = ''; // Clear the content
  } else {
    // Fetch and display the loans
    const response = await fetch('/loans');
    const loans = await response.json();
    let loansHtml = `
      <table>
        <thead>
          <tr>
            <th>Loan ID</th>
            <th>Loan Type</th>
            <th>Loan Amount</th>
            <th>Loan Status</th>
          </tr>
        </thead>
        <tbody>
    `;
    loans.forEach((loan) => {
      // Determine the background color based on loan status
      const statusColor = loan.loanStatus === 'APPROVED' ? '#e6ffe6' : '#ffbfbf';

      loansHtml += `
        <tr>
          <td>${loan.loanId}</td>
          <td>${loan.loanType}</td>
          <td>$${loan.loanAmount}</td>
          <td style="background-color: ${statusColor};">${loan.loanStatus}</td>
        </tr>
      `;
    });
    loansHtml += `
        </tbody>
      </table>
    `;
    loansDiv.innerHTML = loansHtml;
    loansDiv.style.display = 'block'; // Ensure the loans are displayed
  }
}




async function fetchUsers() {
  const usersDiv = document.getElementById('users');

  // Check if the users are already displayed
  if (usersDiv.style.display === 'block') {
    // Hide the users
    usersDiv.style.display = 'none';
    usersDiv.innerHTML = ''; // Clear the content
  } else {
    // Fetch and display the users
    const response = await fetch('/users');
    const users = await response.json();
    let usersHtml = `
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>User ID</th>
            <th>Credit Score</th>
          </tr>
        </thead>
        <tbody>
    `;
    users.forEach((user) => {
      const creditScoreColor = user.creditScore < 650 ? '#ffbfbf' : '#e6ffe6';
      usersHtml += `
        <tr>
          <td>${user.name}</td>
          <td>${user.userId}</td>
          <td style="background-color: ${creditScoreColor};">${user.creditScore}</td>
        </tr>
      `;
    });
    usersHtml += `
        </tbody>
      </table>
    `;
    usersDiv.innerHTML = usersHtml;
    usersDiv.style.display = 'block'; // Ensure the users are displayed
  }
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