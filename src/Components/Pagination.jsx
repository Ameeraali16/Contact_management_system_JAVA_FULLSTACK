// Pagination.js
import React from 'react';

function Pagination({ totalPages, currentPage, setCurrentPage }) {
    return (
        <div className="pagination">
            {[...Array(totalPages)].map((_, i) => (
                <button
                    key={i}
                    className={`btn page-btn ${i + 1 === currentPage ? 'active' : ''}`}
                    onClick={() => setCurrentPage(i + 1)}
                >
                    {i + 1}
                </button>
            ))}
        </div>
    );
}

export default Pagination;
