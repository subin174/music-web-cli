package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.*;
import btb.mp3.bestofthebet.repository.SongRepository;

import btb.mp3.bestofthebet.service.playlist_song.Playlist_songService;
import btb.mp3.bestofthebet.service.singer.ISingerService;
import btb.mp3.bestofthebet.service.singerAndSongService.ISingerAndSongService;
import btb.mp3.bestofthebet.service.songservice.ISongService;
import btb.mp3.bestofthebet.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    ISongService songService;

    @Autowired
    ISingerAndSongService singerAndSongService;

    @Autowired
    ISingerService singerService;

    @Autowired
    UserService userService;


    @Autowired
    private SongRepository songRepository;

    @GetMapping("/singerandsong/{id}")
    public ResponseEntity<Singer> findSingerBySong(@PathVariable("id") Long id) {
        Song song = songService.findById(id).get();
        if (song != null) {
            Singer singer = singerAndSongService.findBySong(song).getSinger();
            return new ResponseEntity<Singer>(singer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/topview")
    public ResponseEntity<List<Song>> Top10() {
        List<Song> songList = songService.findTop10View();
        if (songList != null) {
            return new ResponseEntity<List<Song>>(songList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/all")
    public List<Song> findall() {

        return songRepository.findAll();

    }


}
