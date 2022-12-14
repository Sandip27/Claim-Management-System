import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Claim.css";
import { useNavigate } from "react-router-dom";

const ClaimRequest = () => {
  let navigate = useNavigate();

  const [memberName, setMemberName] = useState();
  const [memberId, setMemberId] = useState();
  const [planType, setPlanType] = useState();
  const [requestDate, setRequestDate] = useState();
  const [claimAmount, setClaimAmount] = useState();
  const [insuredAmount, setInsuredAmount] = useState();
  const [search, setSearch] = useState("");
  const [maxClaimAmount, setMaxClaimAmount] = useState("");

  useEffect(() => {
    fetchData();

    clearData();
  }, [search]);

  const clearData = () => {
    if (search === "") {
      setMaxClaimAmount("");
      setMemberName("");
      setMemberId("");
      setPlanType("");
      setInsuredAmount("");
    }
  };

  const fetchData = async () => {
    const result = await axios.get(
      `http://localhost:8080/api/member/${search}`
    );

    setMemberId(result.data.memberId);
    setMemberName(result.data.memberName);
    setPlanType(result.data.plan.planName);
    setInsuredAmount(result.data.plan.insuredAmount);
    setMaxClaimAmount("Rs. 700000");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const obj = {
      claimAmount: claimAmount,
      requestDate: requestDate,
      member: {
        memberId: memberId,
      },
    };

    await axios.post("http://localhost:8080/claim/add", obj);
    navigate("/claims");
  };

  return (
    <div className="container">
      <div className="process-claim">
        <h4>New Claim Request</h4>
        <hr className="bg-success border-2 border-top border-success" />

        <section className="search-bar">
          <form
            className=" d-flex justify-content-center col-md-3 mx-auto mt-4"
            onSubmit={(e) => e.preventDefault()}
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
          <div className="row d-flex justify-content-center mb-2">
            <div className="col-md-2 ">
              <label for="name" className="form-label">
                Member Id
              </label>
              <input
                type="text"
                className="form-control"
                id="name"
                value={memberId}
                disabled
              />
            </div>
            <div className="col-md-5 mb-2">
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
              <div className="col-md-4">
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

              <div className="col-md-3">
                <label for="insuredAmount" className="form-label">
                  Insured Amount
                </label>
                <div class="input-group has-validation">
                  <span class="input-group-text" id="inputGroupPrepend">
                    ₹
                  </span>
                  <input
                    type="number"
                    className="form-control"
                    id="insuredAmount"
                    value={insuredAmount}
                    disabled
                  />
                </div>
              </div>
            </div>
          </div>

          <div className="row d-flex justify-content-center mb-2">
            <div className="col-md-3">
              <label for="date" className="form-label">
                Request Date
              </label>
              <input
                type="text"
                className="form-control"
                id="date"
                value={requestDate}
                onChange={(e) => setRequestDate(e.target.value)}
              />
            </div>

            <div className="col-md-4 mb-2">
              <label for="amount" className="form-label">
                Claim Amount
              </label>
              <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend">
                  ₹
                </span>
                <input
                  type="number"
                  className="form-control"
                  id="amount"
                  value={claimAmount}
                  onChange={(e) => setClaimAmount(e.target.value)}
                />
              </div>
            </div>
          </div>

          <div className="row d-flex justify-content-center mb-2">
            <div className="col-md-3">
              <label for="maxAmount" className="form-label">
                Max Claimable Amount
              </label>
              <div class="input-group has-validation">
                <span class="input-group-text" id="inputGroupPrepend">
                  ₹
                </span>
                <input
                  type="text"
                  className="form-control"
                  id="maxAmount"
                  value={maxClaimAmount}
                  disabled
                />
              </div>
            </div>
          </div>

          <div className="col-12 text-center mt-5">
            <button
              type="submit"
              className="btn btn-success btn-lg px-4 shadow"
              onClick={handleSubmit}
            >
              Submit
            </button>
          </div>
        </section>
      </div>
    </div>
  );
};

export default ClaimRequest;
