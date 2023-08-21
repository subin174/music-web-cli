package btb.mp3.bestofthebet.controller;

import btb.mp3.bestofthebet.model.PlayList;
import btb.mp3.bestofthebet.model.Singer;
import btb.mp3.bestofthebet.model.Singer_And_song;
import btb.mp3.bestofthebet.model.Song;
import btb.mp3.bestofthebet.service.singer.ISingerService;
import btb.mp3.bestofthebet.service.singerAndSongService.ISingerAndSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/singer")
public class SingerController {
    @Autowired
    ISingerService singerService;

    @Autowired
    ISingerAndSongService singerAndSongService;

    @GetMapping("/all")
    public ResponseEntity<List<Singer>> findAll() {
        return new ResponseEntity<List<Singer>>(singerService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/listSong/{id}")
    @Transactional
    public ResponseEntity<List<Song>> findSongByIdSinger(@PathVariable("id") Long id) {
        Singer singer = singerService.findById(id).get();

        System.out.println(id);

        if(singer!= null){
            List<Singer_And_song> singer_and_songList = singerAndSongService.findSingerAndSongBySinger(singer);
            List<Song> songList = new ArrayList();
            for (Singer_And_song s : singer_and_songList) {
                songList.add(s.getSong());
            }

            System.out.println(songList);
            return new ResponseEntity<List<Song>>(songList,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
