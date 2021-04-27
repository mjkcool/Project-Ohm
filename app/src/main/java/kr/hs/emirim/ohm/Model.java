package kr.hs.emirim.ohm;

public class Model {

    private int image;
    private String title;
    private String desc;
    private String day;

    public Model(int image, String title, String desc, String day) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.day = day;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDay(){
        return day;
    }
    public void setDay(String day){
        this.day = day;
    }
}
