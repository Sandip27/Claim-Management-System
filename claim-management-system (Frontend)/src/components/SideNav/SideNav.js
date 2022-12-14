import React from "react";
import { Outlet, Link } from "react-router-dom";
import "./SideNav.css";

const SideNav = () => {
  return (
    <div className="app-side-nav bg-primary">
      <nav className="side-nav-content">
        <ul className="nav-list">
          <li className="nav-list-item ">
            <i class="fa-solid fa-chart-line"></i>
            <span>
              <Link to="dashboard">Dashboard</Link>
            </span>
          </li>

          <li className="nav-list-item">
            <i class="fa-solid fa-users"></i>
            <span>
              <Link to="members">All Members</Link>
            </span>
          </li>

          <li className="nav-list-item">
            <i class="fa-solid fa-user-plus"></i>
            <span>
              <Link to="addMember">Add Member</Link>
            </span>
          </li>

          <li className="nav-list-item">
            <i class="fa-solid fa-magnifying-glass"></i>
            <span>
              <Link to="claims">All Claims</Link>
            </span>
          </li>

          <li className="nav-list-item">
            <i class="fa-solid fa-file-circle-plus"></i>
            <span>
              <Link to="newRequest">Claim Request</Link>
            </span>
          </li>

          <li className="nav-list-item">
            <i class="fa-solid fa-list-check"></i>
            <span>
              <Link to="process">Process Claim</Link>
            </span>
          </li>

          <li className="nav-list-item log-out px-4">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>
              <Link to="login">Log Out</Link>
            </span>
          </li>
        </ul>
      </nav>

      <Outlet />
    </div>
  );
};

export default SideNav;
