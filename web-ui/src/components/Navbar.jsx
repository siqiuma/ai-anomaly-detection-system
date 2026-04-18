import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="navbar">
      <h2 className="logo">AI Anomaly Detection Dashboard</h2>
      <div className="nav-links">
        <Link to="/">Transactions</Link>
        <Link to="/anomaly-results">Anomaly Results</Link>
        <Link to="/explanations">Explanations</Link>
      </div>
    </nav>
  );
}