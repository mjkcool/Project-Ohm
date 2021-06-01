package kr.hs.emirim.ohm;

public class ChatingData { //채팅 데이터 값

    private String nickname; //닉네임
    private String msg; //채팅 내용

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

}
