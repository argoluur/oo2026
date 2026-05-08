import React, { useState, useEffect } from "react";

function ExternalBooks() {
  const [bibles, setBibles] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function fetchBibles() {
      try {
        const response = await fetch(`${process.env.REACT_APP_API_URL}/books/external`);
        if (!response.ok) throw new Error("Backend andis vea: " + response.status);
        
        const data = await response.json();
        
        // Vastus on tõenäoliselt massiiv [{bible_id: 13, language: "albanian", ...}, ...]
        setBibles(data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    }
    fetchBibles();
  }, []);

  if (loading) return <div className="container mt-5">Laadin Piibli versioone...</div>;
  if (error) return <div className="alert alert-danger mt-5">Viga: {error}</div>;

  return (
    <div className="container mt-5">
      <h2 className="mb-4">Välised Piibli andmed (Holy Bible API)</h2>
      <div className="table-responsive shadow-sm">
        <table className="table table-hover table-striped">
          <thead className="table-dark">
            <tr>
              <th>ID</th>
              <th>Keel</th>
              <th>Versioon</th>
            </tr>
          </thead>
          <tbody>
            {bibles.map((bible) => (
              <tr key={bible.bible_id}>
                <td>{bible.bible_id}</td>
                <td className="text-capitalize">{bible.language}</td>
                <td>{bible.version}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      {bibles.length === 0 && <p>Andmeid ei leitud.</p>}
    </div>
  );
}

export default ExternalBooks;