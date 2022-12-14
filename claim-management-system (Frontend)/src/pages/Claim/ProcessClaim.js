import axios from "axios";
import React, { useState, useEffect } from "react";
import "./Claim.css";
import { useNavigate } from "react-router-dom";

const ProcessClaim = () => {
  let navigate = useNavigate();

  const [memberName, setMemberName] = useState();
  const [planType, setPlanType] = useState();
  const [requestDate, setRequestDate] = useState();
  const [claimAmount, setClaimAmount] = useState();
  const [insuredAmount, setInsuredAmount] = useState();
  const [status, setStatus] = useState("");
  const [claimId, setClaimId] = useState();
  const [search, setSearch] = useState("");

  useEffect(() => {
    fetchData();
  }, [search]);

  const fetchData = async () => {
    const result = await axios.get(
      `http://localhost:8080/claim/fetchMember/${search}`
    );
    setMemberName(result.data.member.memberName);
    setPlanType(result.data.member.plan.planName);
    setRequestDate(result.data.requestDate);
    setClaimAmount(result.data.claimAmount);
    setInsuredAmount(result.data.member.plan.insuredAmount);
    setClaimId(result.data.claimId);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const obj = {
      claimStatus: status,
    };
    await axios.put(`http://localhost:8080/claim/process/${claimId}`, obj);
    navigate("/claims");
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className="container">
      <div className="process-claim">
        <h4>Process Claim</h4>
        <hr />

        <section className="search-bar">
          <form
            className=" d-flex justify-content-center col-md-3 mx-auto mt-4"
            onSubmit={handleSearchSubmit}
          >
            <input
              className="form-control py-2 rounded-pill shadow text-center"
              type="number"
              placeholder="Enter member id...   🔍"
              id="search"
              onChange={(e) => setSearch(e.target.value)}
            />
          </form>
        </section>

        <section className="row g-3 p-4 mt-2">
          <div className="row d-flex justify-content-center">
            <div className="col-md-6 mb-2">
              <label for="name" className="form-label">
                Member Name
              </label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={memberName}
                disabled
              />
            </div>

            <div className="row d-flex justify-content-center mb-2">
              <div className="col-md-6">
                <label for="planType" className="form-label">
                  Insurance Type
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="planType"
                  value={planType}
                  disabled
                />
              </div>
            </div>
          </div>

          <div className="row d-flex justify-content-center mb-2">
            <div className="col-md-6">
              <label for="date" className="form-label">
                Request Date
              </label>
              <input
                type="text"
                className="form-control"
                id="date"
                value={requestDate}
                disabled
              />
            </div>
          </div>

          <div className="row d-flex justify-content-center mb-2">
            <div className="col-md-3">
              <label for="amount" className="form-label">
                Claim Amount
              </label>
              <input
                type="number"
                className="form-control"
                id="amount"
                value={claimAmount}
                disabled
              />
            </div>

            <div className="col-md-3">
              <label for="insuredAmount" className="form-label">
                Insured Amount
              </label>
              <input
                type="number"
                className="form-control"
                id="insuredAmount"
                value={insuredAmount}
                disabled
              />
            </div>
          </div>

          <form onSubmit={handleSubmit}>
            <div className="row d-flex justify-content-center mb-2">
              <div className="col-md-4">
                <label for="status" className="form-label">
                  Process Response
                </label>
                <select
                  id="status"
                  className="form-select"
                  name={status}
                  onChange={(e) => setStatus(e.target.value)}
                  required
                >
                  <option selected>select</option>
                  <option value="Approved">Approve</option>
                  <option value="Rejected">Reject </option>
                </select>
                <div className="invalid-feedback">Please select a Response</div>
              </div>
            </div>

            <div className="col-12 text-center mt-4">
              <button type="submit" className="btn btn-success px-4 shadow">
                Submit
              </button>
            </div>
          </form>
        </section>
      </div>
    </div>
  );
};

export default ProcessClaim;
