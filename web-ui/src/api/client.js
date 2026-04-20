const API_BASE = "http://localhost:8083";

export async function fetchTransactions() {
  const res = await fetch(`${API_BASE}/transactions`);
  if (!res.ok) throw new Error("Failed to fetch transactions");
  return res.json();
}

export async function fetchTransactionById(transactionId) {
  const res = await fetch(`${API_BASE}/transactions/${transactionId}`);
  if (!res.ok) throw new Error("Failed to fetch transaction");
  return res.json();
}

export async function fetchAnomalyResults() {
  const res = await fetch(`${API_BASE}/anomaly-results`);
  if (!res.ok) throw new Error("Failed to fetch anomaly results");
  return res.json();
}

export async function fetchAnomalyResultById(transactionId) {
  const res = await fetch(`${API_BASE}/anomaly-results/${transactionId}`);
  if (!res.ok) throw new Error("Failed to fetch anomaly result");
  return res.json();
}

export async function fetchExplanations(transactionId = "") {
  const url = transactionId
    ? `${API_BASE}/explanations/${transactionId}`
    : `${API_BASE}/explanations`;

  const res = await fetch(url);
  if (!res.ok) throw new Error("Failed to fetch explanations");
  return res.json();
}

export async function fetchTransactionFullView(transactionId) {
  const res = await fetch(`${API_BASE}/transactions/${transactionId}/full-view`);
  if (!res.ok) throw new Error("Failed to fetch full transaction view");
  return res.json();
}

export async function publishTransaction(payload) {
  const res = await fetch(`${API_BASE}/transactions/publish`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(payload),
  });

  if (!res.ok) throw new Error("Failed to publish transaction");
  return res.text();
}