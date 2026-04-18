import { useState } from "react";
import { fetchExplanations } from "../api/client";
import TableView from "../components/TableView";

export default function ExplanationsPage() {
  const [transactionId, setTransactionId] = useState("");
  const [results, setResults] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSearch = async () => {
    if (!transactionId.trim()) return;

    setLoading(true);
    setError("");

    try {
      const data = await fetchExplanations(transactionId.trim());
      setResults(data);
    } catch (err) {
      setError(err.message);
      setResults([]);
    } finally {
      setLoading(false);
    }
  };

  const columns = [
    { key: "transactionId", label: "Transaction ID" },
    { key: "generationMethod", label: "Generation Method" },
    { key: "modelName", label: "Model Name" },
    { key: "explanationText", label: "Explanation" },
  ];

  return (
    <div className="page">
      <h1>Explanations</h1>

      <div className="search-box">
        <input
          type="text"
          placeholder="Enter transaction ID"
          value={transactionId}
          onChange={(e) => setTransactionId(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && results.length > 0 && (
        <TableView columns={columns} data={results} />
      )}
    </div>
  );
}