package btb.mp3.bestofthebet.controller;


import btb.mp3.bestofthebet.model.*;

import btb.mp3.bestofthebet.service.playlist.PlaylistService;
import btb.mp3.bestofthebet.service.playlist_song.Playlist_songService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/playlist")
public class PlaylistControllerAPI {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private Playlist_songService playlist_songService;



    @GetMapping("/topView")
    public ResponseEntity<List<PlayList>> topView() {
        List<PlayList> playListView = playlistService.sortView();

        if (playListView != null) {
            return new ResponseEntity<List<PlayList>>(playListView, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/playlistsong/{id}")
    private ResponseEntity<List<Song>> songPlaylisy(@PathVariable Long id) {
        PlayList playList = playlistService.findById(id).get();
        if (playList != null) {
            List<Playlist_Song> playlist_songs = playlist_songService.playlistSong(playList);
            List<Song> songList = new ArrayList<>();
            for (Playlist_Song playSong : playlist_songs) {
                songList.add(playSong.getSong());
            }
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
