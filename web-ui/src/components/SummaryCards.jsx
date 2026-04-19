export default function SummaryCards({ stats }) {
  const cards = [
    { label: "Total Transactions", value: stats.total, className: "summary-card total" },
    { label: "High Risk", value: stats.high, className: "summary-card high" },
    { label: "Medium Risk", value: stats.medium, className: "summary-card medium" },
    { label: "Low Risk", value: stats.low, className: "summary-card low" },
  ];

  return (
    <div className="summary-grid">
      {cards.map((card) => (
        <div key={card.label} className={card.className}>
          <div className="summary-label">{card.label}</div>
          <div className="summary-value">{card.value}</div>
        </div>
      ))}
    </div>
  );
}