import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { publishTransaction } from "../api/client";

export default function NewTransactionPage() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    transactionId: `txn_ui_${Date.now()}`,
    accountId: "acc_demo_001",
    userId: "user_demo_001",
    merchantId: "m_demo_001",
    deviceId: "dev_demo_001",
    amount: 2500,
    currency: "USD",
    country: "RU",
    paymentMethod: "CREDIT_CARD",
    transactionType: "PURCHASE",
    eventTime: new Date().toISOString(),
  });

  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState("");

  function handleChange(e) {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: name === "amount" ? Number(value) : value,
    }));
  }

  async function handleSubmit(e) {
    e.preventDefault();
    setSubmitting(true);
    setError("");

    try {
      await publishTransaction(form);
      navigate(`/transactions/${form.transactionId}`, {
        state: { justSubmitted: true }
      });
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div className="page">
      <h1>New Transaction</h1>

      <form className="transaction-form" onSubmit={handleSubmit}>
        <div className="form-grid">
          <label>
            Transaction ID
            <input name="transactionId" value={form.transactionId} onChange={handleChange} />
          </label>

          <label>
            Account ID
            <input name="accountId" value={form.accountId} onChange={handleChange} />
          </label>

          <label>
            User ID
            <input name="userId" value={form.userId} onChange={handleChange} />
          </label>

          <label>
            Merchant ID
            <input name="merchantId" value={form.merchantId} onChange={handleChange} />
          </label>

          <label>
            Device ID
            <input name="deviceId" value={form.deviceId} onChange={handleChange} />
          </label>

          <label>
            Amount
            <input name="amount" type="number" value={form.amount} onChange={handleChange} />
          </label>

          <label>
            Currency
            <input name="currency" value={form.currency} onChange={handleChange} />
          </label>

          <label>
            Country
            <input name="country" value={form.country} onChange={handleChange} />
          </label>

          <label>
            Payment Method
            <input name="paymentMethod" value={form.paymentMethod} onChange={handleChange} />
          </label>

          <label>
            Transaction Type
            <input name="transactionType" value={form.transactionType} onChange={handleChange} />
          </label>

          <label className="full-width">
            Event Time
            <input name="eventTime" value={form.eventTime} onChange={handleChange} />
          </label>
        </div>

        {error && <p className="error">{error}</p>}

        <button type="submit" disabled={submitting}>
          {submitting ? "Submitting..." : "Publish Transaction"}
        </button>
      </form>
    </div>
  );
}