package kr.hs.emirim.ohm;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
    public class Room {

    public String roomname;
    public String roomtopic;
    public String roomtime;

    public Room() {

    }

    public Room(String roomname, String roomtopic, String roomtime) {
        this.roomname = roomname;
        this.roomtopic = roomtopic;
        this.roomtime = roomtime;
    }

}


