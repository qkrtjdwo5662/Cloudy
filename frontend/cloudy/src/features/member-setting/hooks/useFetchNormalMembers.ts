import axios from "axios";

export interface MemberResponse {
  memberId: number;
  departmentName: string;
  loginId: string;
}

export const fetchNormalMembers = async (
  accessToken: string,
): Promise<MemberResponse[]> => {
  try {
    // console.log("api", accessToken);
    const response = await axios.get(
      `http://k11a606.p.ssafy.io:8081/members/normal`,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
          Accept: "*/*",
        },
      },
    );

    return response.data.data.normalMemberGetResponses;
  } catch (error) {
    // console.error("Failed to fetch normal members:", error);
    throw new Error("일반 회원 정보를 가져오는 데 실패했습니다.");
  }
};
