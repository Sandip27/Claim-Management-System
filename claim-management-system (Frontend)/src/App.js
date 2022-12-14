import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ClaimRequest from "./pages/Claim/ClaimRequest";
import Members from "./pages/Member/Members";
import AddMember from "./pages/Member/AddMember";
import ProcessClaim from "./pages/Claim/ProcessClaim";
import Claims from "./pages/Claim/Claims";
import SideNav from "./components/SideNav/SideNav";
import Dashboard from "./pages/Dashboard/Dashboard";
import Login from "./pages/Login/Login";
import UpdateMember from "./pages/Member/UpdateMember";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<SideNav />}>
          <Route path="login" element={<Login />} />
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="members" element={<Members />} />
          <Route path="addMember" element={<AddMember />} />
          <Route path="updateMember/:id" element={<UpdateMember />} />
          <Route path="newRequest" element={<ClaimRequest />} />
          <Route path="claims" element={<Claims />} />
          <Route path="process" element={<ProcessClaim />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
