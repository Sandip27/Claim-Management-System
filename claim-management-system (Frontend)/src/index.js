import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Header from "./components/Header/Header";
import App from "./App";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <div>
    <div className="main">
      <Header />
      <App />
    </div>
  </div>
);
