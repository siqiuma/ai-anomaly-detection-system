import { useEffect, useState } from "react";
import { fetchAnomalyResults } from "../api/client";
import TableView from "../components/TableView";

export default function AnomalyResultsPage() {
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchAnomalyResults()
      .then(setResults)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  const columns = [
    { key: "transactionId", label: "Transaction ID" },
    { key: "anomalyScore", label: "Score" },
    { key: "riskLevel", label: "Risk Level" },
    {
      key: "flagged",
      label: "Flagged",
      render: (value) => (value ? "Yes" : "No"),
    },
    { key: "matchedRules", label: "Matched Rules" },
  ];

  return (
    <div className="page">
      <h1>Anomaly Results</h1>
      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && <TableView columns={columns} data={results} />}
    </div>
  );
}