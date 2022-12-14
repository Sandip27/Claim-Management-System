import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Member.css";

const Members = () => {
  const [members, setMembers] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    loadMembers();
  }, [members]);

  const loadMembers = async () => {
    const result = await axios.get("http://localhost:8080/api/member/all");
    setMembers(result.data);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className="container">
      <div className="all-members">
        <h4>All Members List</h4>
        <hr />

        <section className="search-bar">
          <form
            className=" d-flex justify-content-center col-md-4 mx-auto my-4"
            onSubmit={handleSubmit}
          >
            <input
              className="form-control py-2 rounded-pill shadow text-center"
              type="search"
              placeholder="Search member...  🔍"
              id="search"
              onChange={(e) => setSearch(e.target.value)}
            />
          </form>
        </section>

        <section className="py-3 px-2">
          <table className="table table-striped shadow text-center">
            <thead className="table-dark">
              <tr>
                <td scope="col">Member Id</td>
                <td scope="col">Member Name</td>
                <td scope="col">Address</td>
                <td scope="col">Email</td>
                <td scope="col">Phone</td>
                <td scope="col">Insurance Type</td>
                <td scope="col">Insured Amount</td>
                <td scopr="col">Action</td>
              </tr>
            </thead>
            <tbody>
              {members
                .filter((item) => {
                  return search.toLowerCase() === ""
                    ? item
                    : item.memberName.toLowerCase().includes(search);
                })
                .map((member, index) => {
                  return (
                    <tr key={index}>
                      <th scope="row">{member.memberId}</th>
                      <td>{member.memberName}</td>
                      <td>{member.state}</td>
                      <td>{member.emailId}</td>
                      <td>{member.contactNo}</td>
                      <td>{member.plan.planName}</td>
                      <td>{member.plan.insuredAmount}</td>
                      <td>
                        <Link
                          to={`/updateMember/${member.memberId}`}
                          className="btn btn-warning btn-sm"
                        >
                          Update
                        </Link>
                      </td>
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

export default Members;
