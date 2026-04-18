import { useEffect, useState } from "react";
import { fetchTransactions } from "../api/client";
import TableView from "../components/TableView";
import { Link } from "react-router-dom";

export default function TransactionsPage() {
  const [transactions, setTransactions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchTransactions()
      .then(setTransactions)
      .catch((err) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

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
    { key: "paymentMethod", label: "Payment Method" },
    { key: "transactionType", label: "Type" },
  ];

  return (
    <div className="page">
      <h1>Transactions</h1>
      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && <TableView columns={columns} data={transactions} />}
    </div>
  );
}