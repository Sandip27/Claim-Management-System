import axios from "axios";
const MEMBER_REST_API_URL = "http://localhost:8080/api/member/add";
const ALL_MEMBERS_URL = "http://localhost:8080/api/member/all";

class memberService {
  state = {
    store: JSON.parse(localStorage.getItem("login")),
  };

  getMember() {
    let token = JSON.parse(localStorage.getItem("login"));

    return axios.get(ALL_MEMBERS_URL, {
      headers: {
        authorization: token.accessToken,
        Accept: "Application/json",
        "Content-Type": "Application/json",
      },
    });
  }

  saveMember(member) {
    return axios.post(MEMBER_REST_API_URL, member);
  }
}

export default new memberService();
