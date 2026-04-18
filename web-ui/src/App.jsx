import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import TransactionsPage from "./pages/TransactionsPage";
import AnomalyResultsPage from "./pages/AnomalyResultsPage";
import ExplanationsPage from "./pages/ExplanationsPage";
import TransactionDetailPage from "./pages/TransactionDetailPage";
import "./App.css";


export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<TransactionsPage />} />
        <Route path="/transactions/:transactionId" element={<TransactionDetailPage />} />
        <Route path="/anomaly-results" element={<AnomalyResultsPage />} />
        <Route path="/explanations" element={<ExplanationsPage />} />
      </Routes>
    </BrowserRouter>
  );
}