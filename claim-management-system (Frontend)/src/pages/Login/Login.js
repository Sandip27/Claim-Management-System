import React from "react";
import "./Login.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const loginCheck = (e) => {
    e.preventDefault();
    const obj = { username, password };
    console.log("Hey!" + obj);

    fetch("http://localhost:8080/api/member/loginCheck", {
      method: "POST",
      headers: { "content-type": "application/json" },
      body: JSON.stringify(obj),
    }).then((Response) => {
      Response.json().then((result) => {
        localStorage.setItem(
          "login",
          JSON.stringify({
            accessToken: result.data.accessToken,
          })
        );
      });

      let token = JSON.parse(localStorage.getItem("login"));

      if (token.accessToken == null) {
        alert("Wrong Username or Password!");
      } else {
        navigate("/dashboard");
      }
    });
  };

  return (
    <div className="container login">
      <section className="d-flex flex-column min-vh-100 mt-5 pt-5 align-items-center">
        <div className="row">
          <div className="col-md-10 mx-auto rounded shadow bg-white">
            <span className="row">
              <div className="col-mid-6">
                <div className="m-5">
                  <h3 className="text-center">Login Page</h3>
                </div>

                <form className="m-5">
                  <div className="mb-3">
                    <label className="form-label" htmlFor="username">
                      Username
                    </label>
                    <input
                      className="form-control"
                      type="text"
                      id="username"
                      onChange={(e) => setUsername(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label className="form-label" htmlFor="password">
                      Password
                    </label>
                    <input
                      className="form-control"
                      type="password"
                      id="password"
                      shadow
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>
                  <div>
                    <button
                      type="submit"
                      className="form-control btn btn-success mt-3 shadow"
                      onClick={loginCheck}
                    >
                      Submit
                    </button>
                  </div>
                </form>
              </div>

              <span className="px-3 pb-5" />
              <h6 className="text-center">
                Don't have an account?
                <Link to="/addMember" className="text-primary signUp px-2">
                  Sign Up
                </Link>
              </h6>
            </span>
          </div>
        </div>
      </section>
    </div>
  );
};

export default Login;
