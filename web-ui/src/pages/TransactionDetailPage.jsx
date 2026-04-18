import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import {
  fetchTransactionById,
  fetchAnomalyResultById,
  fetchExplanations,
} from "../api/client";

export default function TransactionDetailPage() {
  const { transactionId } = useParams();

  const [transaction, setTransaction] = useState(null);
  const [anomalyResults, setAnomalyResults] = useState([]);
  const [explanations, setExplanations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadData() {
      setLoading(true);
      setError("");

      try {
        const [transactionData, anomalyData, explanationData] = await Promise.all([
          fetchTransactionById(transactionId),
          fetchAnomalyResultById(transactionId),
          fetchExplanations(transactionId),
        ]);

        setTransaction(transactionData);
        setAnomalyResults(anomalyData);
        setExplanations(explanationData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    loadData();
  }, [transactionId]);

  return (
    <div className="page">
      <div className="detail-header">
        <Link to="/">← Back to Transactions</Link>
        <h1>Transaction Detail</h1>
      </div>

      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}

      {!loading && !error && transaction && (
        <>
          <section className="detail-card">
            <h2>Transaction</h2>
            <div className="detail-grid">
              <div><strong>Transaction ID:</strong> {transaction.transactionId}</div>
              <div><strong>Account ID:</strong> {transaction.accountId}</div>
              <div><strong>User ID:</strong> {transaction.userId}</div>
              <div><strong>Merchant ID:</strong> {transaction.merchantId}</div>
              <div><strong>Device ID:</strong> {transaction.deviceId}</div>
              <div><strong>Amount:</strong> {transaction.amount} {transaction.currency}</div>
              <div><strong>Country:</strong> {transaction.country}</div>
              <div><strong>Payment Method:</strong> {transaction.paymentMethod}</div>
              <div><strong>Transaction Type:</strong> {transaction.transactionType}</div>
              <div><strong>Event Time:</strong> {transaction.eventTime}</div>
            </div>
          </section>

          <section className="detail-card">
            <h2>Anomaly Result</h2>
            {anomalyResults.length === 0 ? (
              <p>No anomaly result found.</p>
            ) : (
              anomalyResults.map((result) => (
                <div key={result.id} className="detail-grid">
                  <div><strong>Score:</strong> {result.anomalyScore}</div>
                  <div><strong>Risk Level:</strong> {result.riskLevel}</div>
                  <div><strong>Flagged:</strong> {result.flagged ? "Yes" : "No"}</div>
                  <div><strong>Matched Rules:</strong> {result.matchedRules}</div>
                  <div><strong>Detected At:</strong> {result.detectedAt}</div>
                </div>
              ))
            )}
          </section>

          <section className="detail-card">
            <h2>Explanation</h2>
            {explanations.length === 0 ? (
              <p>No explanation found.</p>
            ) : (
              explanations.map((exp) => (
                <div key={exp.id} className="explanation-block">
                  <p><strong>Generation Method:</strong> {exp.generationMethod}</p>
                  <p><strong>Model Name:</strong> {exp.modelName}</p>
                  <p><strong>Created At:</strong> {exp.createdAt}</p>
                  <pre className="explanation-text">{exp.explanationText}</pre>
                </div>
              ))
            )}
          </section>
        </>
      )}
    </div>
  );
}