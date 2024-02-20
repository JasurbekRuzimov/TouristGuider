package uz.example.touristguider.models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class City {
    private String name;
    private String region;
    private Drawable cityImage;
    private String history;
    private String location;
    private String attraction;
    private String hours;
    private String phone;
    private String website;

    public City(String name, String region, Drawable cityImage, String history, String location, String attraction, String hours, String phone, String website) {
        this.name = name;
        this.region = region;
        this.cityImage = cityImage;
        this.history = history;
        this.location = location;
        this.attraction = attraction;
        this.hours = hours;
        this.phone = phone;
        this.website = website;
    }


    public City(String name, String region, Drawable cityImage) {
        this.name = name;
        this.region = region;
        this.cityImage = cityImage;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Drawable getCityImage() {
        return cityImage;
    }

    public void setCityImage(Drawable cityImage) {
        this.cityImage = cityImage;
    }

    public boolean getDataImage() {
        return false;
    }

    public boolean getDataTitle() {
        return false;
    }

    public boolean getDataDesc() {
        return false;
    }

}
