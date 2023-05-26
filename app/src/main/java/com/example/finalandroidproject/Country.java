package com.example.finalandroidproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Country implements Parcelable {

    private String Country;
    private String Image;
    private String Link;
    private String Points;
    private String Position;
    private String Singer;
    private String Song;

    public Country(){}
    public Country(String country, String image, String link, String points, String position, String singer, String song) {
        Country = country;
        Image = image;
        Link = link;
        Points = points;
        Position = position;
        Singer = singer;
        Song = song;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getSong() {
        return Song;
    }

    public void setSong(String song) {
        Song = song;
    }

    public Country(Parcel source) {
        Country = source.readString();
        Image = source.readString();
        Link = source.readString();
        Singer = source.readString();
        Song = source.readString();
        Points = source.readString();
        Position = source.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Country);
        dest.writeString(Image);
        dest.writeString(Link);
        dest.writeString(Singer);
        dest.writeString(Song);
        dest.writeString(Points);
        dest.writeString(Position);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size){return new Country[size];
        }
    };
}
