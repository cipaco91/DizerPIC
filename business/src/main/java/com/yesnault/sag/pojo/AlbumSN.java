package com.yesnault.sag.pojo;

import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.Photo;

import java.io.Serializable;

/**
 * Created by CParaschivescu on 5/5/2015.
 */
public class AlbumSN implements Serializable{

    private Photo photoCover;

    private Album album;

    public Photo getPhotoCover() {
        return photoCover;
    }

    public void setPhotoCover(Photo photoCover) {
        this.photoCover = photoCover;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
