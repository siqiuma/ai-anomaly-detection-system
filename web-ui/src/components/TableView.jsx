export default function TableView({ columns, data }) {
  if (!data || data.length === 0) {
    return <p>No data available.</p>;
  }

  return (
    <table className="data-table">
      <thead>
        <tr>
          {columns.map((col) => (
            <th key={col.key}>{col.label}</th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row, idx) => (
          <tr key={row.id ?? idx}>
            {columns.map((col) => (
              <td key={col.key}>
                {typeof col.render === "function"
                  ? col.render(row[col.key], row)
                  : row[col.key]}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
}