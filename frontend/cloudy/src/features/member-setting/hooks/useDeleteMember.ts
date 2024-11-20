import axios from "axios";

export const deleteNormalMember = async (
  memberId: number,
  accessToken: string,
): Promise<void> => {
  try {
    const response = await axios.delete(
      `http://k11a606.p.ssafy.io:8081/members/delete?nomalMemberId=${memberId}`,
      {
        headers: {
          Authorization: `Bearer ${accessToken}`,
          Accept: "*/*",
        },
      },
    );

    if (response.status === 200) {
      // console.log("회원 삭제 성공");
    } else {
      // console.error("회원 삭제 실패: ", response.data.message);
    }
  } catch (error) {
    // console.error("회원 삭제 중 오류 발생:", error);
    throw new Error("회원 삭제에 실패했습니다.");
  }
};
