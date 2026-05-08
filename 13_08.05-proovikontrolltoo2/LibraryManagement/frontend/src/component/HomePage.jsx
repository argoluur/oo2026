import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./HomePage.css";

function HomePage() {
  const [books, setBooks] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const pageSize = 4;

  const API_URL = process.env.REACT_APP_API_URL || "http://localhost:8081/api";
  const BASE_URL = API_URL.replace("/api", "");

  useEffect(() => {
    async function fetchBooks() {
      try {
        const response = await fetch(`${API_URL}/books/page?page=${page}&size=${pageSize}`);
        const data = await response.json();
        setBooks(data.content || []);
        setTotalPages(data.totalPages || 0);
      } catch (e) {
        console.error("Unable to fetch", e);
      }
    }
    fetchBooks();
  }, [page, API_URL]);

  return (
    <div
      className="container mt-4"
      style={{
        minHeight: "100vh",
        padding: "20px",
      }}
    >
      <h2
        className="text-center mb-4"
        style={{ color: "#000", textShadow: "1px 1px 2px #f5f5f5ff" }}
      >
        Latest Books
      </h2>

      {books.length === 0 ? (
        <p className="text-center" style={{ color: "#555" }}>
          No books available.
        </p>
      ) : (
        <>
          <div
            className="d-flex gap-3 overflow-auto"
            style={{
              width: "100%",
              paddingBottom: "10px",
              scrollSnapType: "x mandatory",
            }}
          >
            {books.map((book) => (
              <Link
                to={`/books/${book.id}`}
                key={book.id}
                style={{
                  textDecoration: "none",
                  flex: "0 0 20%",
                  scrollSnapAlign: "start",
                }}
              >
                <div className="book-card p-2 shadow-sm" style={{ minWidth: "200px", height: "400px" }}>
                  {book.image ? (
                    <img
                      src={`${BASE_URL}${book.image}`}
                      alt={book.title}
                      style={{ width: "100%", height: "250px", objectFit: "cover" }}
                    />
                  ) : (
                    <div
                      style={{
                        height: "250px",
                        backgroundColor: "#eee",
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        marginBottom: "8px",
                        fontSize: "0.9rem",
                        color: "#888",
                      }}
                    >
                      No Image
                    </div>
                  )}

                  <h5 style={{ color: "#333", minHeight: "2.5rem" }}>{book.title}</h5>

                  <p style={{ color: "#555", fontSize: "0.85rem", minHeight: "3rem" }}>
                    {book.description || "No description available"}
                  </p>

                  <p style={{ color: "#000000ff", fontSize: "0.8rem", fontWeight: "bold"}}>
                    Shelf: {book.shelf || "N/A"}
                  </p>
                </div>
              </Link>
            ))}
          </div>

          <div className="d-flex justify-content-center align-items-center mt-4 gap-3">
            <button 
              className="btn btn-primary" 
              disabled={page === 0} 
              onClick={() => setPage(page - 1)}
            >
              Previous
            </button>
            
            <span className="fw-bold">Page {page + 1} of {totalPages}</span>

            <button 
              className="btn btn-primary" 
              disabled={page >= totalPages - 1} 
              onClick={() => setPage(page + 1)}
            >
              Next
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default HomePage;