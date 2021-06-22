package kr.hs.emirim.ohm;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
    public class Room {

    public String roomname;
    public String roomtopic;
    public String ownerID;
    public String open;

    public Room() {

    }

    public Room(String roomname, String roomtopic, String ownerID, String open) {
        this.roomname = roomname;
        this.roomtopic = roomtopic;
        this.ownerID = ownerID;
        this.open = open;
    }

}


