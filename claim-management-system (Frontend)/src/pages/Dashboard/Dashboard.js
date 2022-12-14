import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Dashboard.css";

const Dashboard = () => {
  const [totalMembers, setTotalMembers] = useState([]);
  const [totalClaims, setTotalClaims] = useState([]);
  const [totalPendingClaims, setTotalPendingClaims] = useState([]);
  const [totalApprovedClaims, setTotalApprovedClaims] = useState([]);

  useEffect(() => {
    loadTotalMembers();
  }, []);

  const loadTotalMembers = async () => {
    const countMembers = await axios.get(
      "http://localhost:8080/api/member/total"
    );
    setTotalMembers(countMembers.data);

    const countClaims = await axios.get("http://localhost:8080/claim/total");
    setTotalClaims(countClaims.data);

    const countPendingClaims = await axios.get(
      "http://localhost:8080/claim/total/pending"
    );
    setTotalPendingClaims(countPendingClaims.data);

    const countApprovedClaims = await axios.get(
      "http://localhost:8080/claim/total/approved"
    );
    setTotalApprovedClaims(countApprovedClaims.data);
  };

  return (
    <div className="container">
      <div className="heading">
        <h4>Welcome to Admin Dashboard</h4>
        <hr />
      </div>

      <div className="info d-flex justify-content-evenly">
        <div className="box box-1">
          <h5>Total Members</h5>
          <h5 className="data">{totalMembers}</h5>
        </div>

        <div className="box box-2">
          <h5>Total Claims</h5>
          <h5 className="data">{totalClaims}</h5>
        </div>

        <div className="box box-3">
          <h5>Pending Claims</h5>
          <h5 className="data">{totalPendingClaims}</h5>
        </div>

        <div className="box box-4">
          <h5>Approved Claims</h5>
          <h5 className="data">{totalApprovedClaims}</h5>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
