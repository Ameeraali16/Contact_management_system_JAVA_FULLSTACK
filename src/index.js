// Old (React 17 and below):
// import ReactDOM from 'react-dom';
// ReactDOM.render(<App />, document.getElementById('root'));


// ✅ Correct (React 18+):
import React from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';

const container = document.getElementById('root');
const root = createRoot(container);
root.render(<App />);
