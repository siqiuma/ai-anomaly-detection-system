export default function ProcessingTimeline({
  transactionReady,
  anomalyReady,
  explanationReady,
}) {
  const steps = [
    {
      label: "Transaction Ingested",
      status: transactionReady ? "done" : "pending",
    },
    {
      label: "Anomaly Evaluated",
      status: anomalyReady
        ? "done"
        : transactionReady
        ? "in-progress"
        : "pending",
    },
    {
      label: "Explanation Generated",
      status: explanationReady
        ? "done"
        : anomalyReady
        ? "in-progress"
        : "pending",
    },
  ];

  return (
    <div className="timeline">
      {steps.map((step, index) => (
        <div key={step.label} className="timeline-step-wrapper">
          <div className={`timeline-step ${step.status}`}>
            <div className="timeline-circle" />
            <div className="timeline-label">{step.label}</div>
          </div>
          {index < steps.length - 1 && <div className="timeline-line" />}
        </div>
      ))}
    </div>
  );
}