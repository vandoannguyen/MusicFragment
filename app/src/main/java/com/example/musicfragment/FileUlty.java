package com.example.musicfragment;

import android.nfc.FormatException;
import android.os.Environment;
import android.util.Log;

import com.example.musicfragment.model.Lyric;
import com.example.musicfragment.model.Music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileUlty {
    static File file = Environment.getExternalStorageDirectory();

    public static ArrayList<Music> getListMusic() {
        ArrayList<Music> list = new ArrayList<>();
        String filePath = file.getPath() + "/Zing MP3";
//        Log.e("FileUlty", file.list()+"");
        File fi = new File(filePath);
        File[] files = fi.listFiles();
        for (File fil : files) {
            if (!fil.isDirectory() && fil.getName().contains(".mp3")) {
                String link = fil.getAbsolutePath();
                String name = fil.getName();
                Log.e("FileUlty", name);
                String nameOfSong = name.substring(0, name.indexOf("_"));
                String nameOfSinger = name.substring(name.indexOf("_") + 1, name.lastIndexOf("_"));
                String nameOfLyric = name.substring(name.lastIndexOf("-") + 1, name.indexOf(".mp3"));
                Log.e("FileUlty", nameOfLyric);
                list.add(new Music(nameOfSong, nameOfSinger, link, nameOfLyric));
            }
        }
        return list;
    }

    public static ArrayList<Lyric> getLyric(String nameOfLyric) {

        String filePath = file.getAbsolutePath() + "/Zing MP3" + "/Lyrics";
        File f = new File(filePath);
        File[] fs = f.listFiles();
        Log.e("getLyric","AAAAAAAAAAAAAAAAAa"+ fs.length);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        for (File fi : fs) {
            Log.e("getLyric", fi.getAbsolutePath());
            if (fi.getName().equals(nameOfLyric)) {

                try {
                    ArrayList<Lyric> list= new ArrayList<>();
//                    FileInputStream fis = new FileInputStream(fi.getAbsoluteFile());
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fi.getAbsolutePath()));
                    String lyric;
                    while ((lyric = bufferedReader.readLine()) != null) {

                        Log.e("getLyric", lyric);
                        String time = lyric.substring(1, 9);
                        String ly = lyric.substring(10);
                        try{
                            String mm= time.substring(0, time.indexOf(":"));
                            int m= Integer.parseInt(mm);
                            String ss=time.substring(time.indexOf(":")+1, time.lastIndexOf("."));
                            int s= Integer.parseInt(ss);
                            String SS=time.substring(time.lastIndexOf(".")+1);
                            int S= Integer.parseInt(SS);
                            int minisecon= m*60*1000+s*1000+S*10;
                            Lyric lyric1= new Lyric(minisecon, ly);

                            list.add(lyric1);
                        }
                        catch (NumberFormatException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    bufferedReader.close();
                    Log.e("getLyric",list.size()+"");
                    return list;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
        return null;
    }
}
