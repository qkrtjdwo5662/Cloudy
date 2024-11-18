import axios from "axios";

export interface RegisterMemberRequest {
  loginId: string;
  password: string;
  departmentName: string;
  businessRegistrationNumber: string;
}

export const registerNormalMember = async (
  data: RegisterMemberRequest,
  accessToken: string,
): Promise<void> => {
  try {
    const response = await axios.post(
      `http://k11a606.p.ssafy.io:8081/auth/register/normal`,
      data,
    );

    if (response.status === 200) {
      // console.log("회원 등록 성공:", response.data.message);
    } else {
      // console.error("회원 등록 실패:", response.data.message);
    }
  } catch (error) {
    // console.error("회원 등록 중 오류 발생:", error);
    throw new Error("회원 등록에 실패했습니다.");
  }
};
