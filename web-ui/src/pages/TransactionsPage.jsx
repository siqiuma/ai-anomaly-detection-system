import { useEffect, useMemo, useState } from "react";
import { Link } from "react-router-dom";
import { fetchTransactions, fetchAnomalyResults } from "../api/client";
import TableView from "../components/TableView";
import RiskBadge from "../components/RiskBadge";
import SummaryCards from "../components/SummaryCards";

export default function TransactionsPage() {
  const [transactions, setTransactions] = useState([]);
  const [anomalyResults, setAnomalyResults] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadData() {
      try {
        const [transactionsData, anomalyResultsData] = await Promise.all([
          fetchTransactions(),
          fetchAnomalyResults(),
        ]);

        setTransactions(transactionsData);
        setAnomalyResults(anomalyResultsData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    loadData();
  }, []);

  const anomalyMap = useMemo(() => {
    const map = new Map();

    for (const result of anomalyResults) {
      map.set(result.transactionId, result);
    }

    return map;
  }, [anomalyResults]);

  const mergedTransactions = useMemo(() => {
    return transactions.map((txn) => {
      const anomaly = anomalyMap.get(txn.transactionId);

      return {
        ...txn,
        riskLevel: anomaly?.riskLevel ?? "UNKNOWN",
        anomalyScore: anomaly?.anomalyScore ?? "-",
      };
    });
  }, [transactions, anomalyMap]);

const stats = useMemo(() => {
  let high = 0;
  let medium = 0;
  let low = 0;

  for (const txn of mergedTransactions) {
    const level = (txn.riskLevel || "").trim().toUpperCase();

    if (level === "HIGH") high++;
    else if (level === "MEDIUM") medium++;
    else if (level === "LOW") low++;
  }

  return {
    total: mergedTransactions.length,
    high,
    medium,
    low,
  };
}, [mergedTransactions]);

  const columns = [
    {
      key: "transactionId",
      label: "Transaction ID",
      render: (value) => <Link to={`/transactions/${value}`}>{value}</Link>,
    },
    { key: "accountId", label: "Account ID" },
    { key: "amount", label: "Amount" },
    { key: "currency", label: "Currency" },
    { key: "country", label: "Country" },
    {
      key: "riskLevel",
      label: "Risk",
      render: (value) => <RiskBadge level={value} />,
    },
    { key: "anomalyScore", label: "Score" },
    { key: "paymentMethod", label: "Payment Method" },
    { key: "transactionType", label: "Type" },
  ];

  return (
    <div className="page">
      <h1>Transactions</h1>
      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && (
        <>
          <SummaryCards stats={stats} />
          <TableView columns={columns} data={mergedTransactions} />
        </>
    )}
    </div>
  );
}