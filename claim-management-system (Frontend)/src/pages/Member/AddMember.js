import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import memberService from "../../service/memberService";
import "./Member.css";

const AddMember = () => {
  let navigate = useNavigate();

  const [memberName, setMemberName] = useState("");
  const [dob, setDob] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [emailId, setEmailId] = useState("");
  const [contactNo, setContactNo] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [planId, setPlanId] = useState("");
  const [insuredAmount, setInsuredAmount] = useState("");

  const saveMember = async (e) => {
    e.preventDefault();

    const obj = {
      memberName,
      dob,
      city,
      state,
      emailId,
      contactNo,
      username,
      password,
      plan: {
        planId,
        insuredAmount,
      },
    };

    memberService.saveMember(obj).then((res) => {
      document.getElementById("Status").innerHTML = "Member Added Successfully";
    });

    navigate("/members");
  };

  return (
    <div className="container">
      <h4>New Member Registration</h4>
      <hr className="bg-success border-2 border-top border-success" />

      <form className="row g-3 p-4 mt-4" onSubmit={(e) => saveMember(e)}>
        <div className="row d-flex justify-content-center">
          <div className="col-md-3 mb-3">
            <label for="name" className="form-label">
              Name
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              name="memberName"
              value={memberName}
              onChange={(e) => setMemberName(e.target.value)}
            />
          </div>

          <div className="col-md-3 ">
            <label for="email" className="form-label">
              Email
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              name="emailId"
              value={emailId}
              onChange={(e) => setEmailId(e.target.value)}
            />
          </div>

          <div className="col-md-3">
            <label for="contact" className="form-label">
              Contact
            </label>
            <input
              type="number"
              className="form-control"
              id="contact"
              name="contactNo"
              value={contactNo}
              onChange={(e) => setContactNo(e.target.value)}
            />
          </div>
        </div>

        <div className="row d-flex justify-content-center mb-3">
          <div className="col-md-3">
            <label for="dob" className="form-label">
              DOB
            </label>
            <input
              type="text"
              className="form-control"
              id="dob"
              name="dob"
              value={dob}
              onChange={(e) => setDob(e.target.value)}
            />
          </div>

          <div className="col-md-3">
            <label for="username" className="form-label">
              Username
            </label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className="col-md-3">
            <label for="password" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
        </div>

        <div className="row d-flex justify-content-center mb-3">
          <div className="col-md-5">
            <label for="city" className="form-label">
              City
            </label>
            <input
              type="text"
              className="form-control"
              id="city"
              name="city"
              value={city}
              onChange={(e) => setCity(e.target.value)}
            />
          </div>

          <div className="col-md-4">
            <label for="state" className="form-label">
              State
            </label>
            <input
              type="text"
              className="form-control"
              id="state"
              name="state"
              value={state}
              onChange={(e) => setState(e.target.value)}
            />
          </div>
        </div>

        <div className="row d-flex justify-content-center mb-3">
          <div className="col-md-3">
            <label for="plan" className="form-label">
              Plan Type
            </label>
            <select
              id="plan"
              className="form-select"
              onChange={(e) => setPlanId(e.target.value)}
            >
              <option selected>select</option>
              <option name="planId" value={21}>
                Health Insurance
              </option>
              <option name="planId" value={22}>
                Home Insurance
              </option>
              <option name="planId" value={23}>
                Vehicle Insurance
              </option>
            </select>
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
                name={insuredAmount}
                value={insuredAmount}
                onChange={(e) => setInsuredAmount(e.target.value)}
              />
            </div>
          </div>

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
                value="1000000"
                disabled
              />
            </div>
          </div>
        </div>

        <div className="col-12 text-center mt-5">
          <button type="submit" className="btn btn-success btn-lg shadow px-4">
            Add Member
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddMember;
