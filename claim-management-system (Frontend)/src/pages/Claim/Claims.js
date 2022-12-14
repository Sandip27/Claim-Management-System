import React, { useState, useEffect } from "react";
import "./Claim.css";
import axios from "axios";

const Claims = () => {
  const [claims, setClaims] = useState([]);

  useEffect(() => {
    loadClaims();
  }, []);

  const loadClaims = async () => {
    const result = await axios.get("http://localhost:8080/claim/all");
    setClaims(result.data);
  };

  return (
    <div className="container">
      <div className="all-claims">
        <h4>All Claims</h4>
        <hr />

        <section className="py-4 px-3">
          <table className="table table-striped shadow text-center">
            <thead className="table-dark">
              <tr>
                <td scope="col">Claim ID</td>
                <td scope="col">Member ID</td>
                <td scope="col">Member Name</td>
                <td scope="col">Plan Type</td>
                <td scope="col">Request Date</td>
                <td scope="col">Claim Amount</td>
                <td scope="col">Status</td>
              </tr>
            </thead>
            <tbody>
              {claims.map((claim, index) => {
                return (
                  <tr>
                    <th scope="row" key={index}>
                      {claim.claimId}
                    </th>
                    <td>{claim.member.memberId}</td>
                    <td>{claim.member.memberName}</td>
                    <td>{claim.member.plan.planName}</td>
                    <td>{claim.requestDate}</td>
                    <td>{claim.claimAmount}</td>
                    <td className="fst-italic">{claim.claimStatus}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </section>
      </div>
    </div>
  );
};

export default Claims;
