package com.example.musicfragment.model;

public class Music {
    String nameofSong, nameofSinger, link, nameOfLyric;

    public String getNameofSong() {
        return nameofSong;
    }

    public void setNameofSong(String nameofSong) {
        this.nameofSong = nameofSong;
    }

    public String getNameofSinger() {
        return nameofSinger;
    }

    public void setNameofSinger(String nameofSinger) {
        this.nameofSinger = nameofSinger;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNameOfLyric() {
        return nameOfLyric;
    }

    public void setNameOfLyric(String nameOfLyric) {
        this.nameOfLyric = nameOfLyric;
    }

    public Music(String nameofSong) {
    }

    public Music() {
    }

    public Music(String nameofSong, String nameofSinger, String link, String nameOfLyric) {
        this.nameofSong = nameofSong;
        this.nameofSinger = nameofSinger;
        this.link = link;
        this.nameOfLyric = nameOfLyric;
    }
}
