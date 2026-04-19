export default function RiskBadge({ level }) {
  const normalized = (level || "").toUpperCase();

  let className = "risk-badge risk-unknown";

  if (normalized === "HIGH") {
    className = "risk-badge risk-high";
  } else if (normalized === "MEDIUM") {
    className = "risk-badge risk-medium";
  } else if (normalized === "LOW") {
    className = "risk-badge risk-low";
  }

  return <span className={className}>{normalized || "UNKNOWN"}</span>;
}