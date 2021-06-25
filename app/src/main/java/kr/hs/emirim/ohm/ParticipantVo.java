package kr.hs.emirim.ohm;

public class ParticipantVo{
    private String nickname;

    public ParticipantVo(String mynick) {
        this.nickname = mynick;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}