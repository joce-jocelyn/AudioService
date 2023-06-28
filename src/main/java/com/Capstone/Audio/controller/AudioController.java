package com.Capstone.Audio.controller;

import com.Capstone.Audio.AudioPlayer;
import com.Capstone.Audio.dto.MusicDto;
import com.Capstone.Audio.entity.Audio;
import com.Capstone.Audio.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @GetMapping("/{musicId}")
    public ResponseEntity<String> getSongById(@PathVariable Long musicId) {
        Optional<Audio> optionalMusic = audioService.getMusicById(musicId);
        if (optionalMusic.isPresent()) {
            Audio audio = optionalMusic.get();
            // Call audio player with music.getFileData() to play the song
            byte[] audioData = audio.getFileData();
            AudioPlayer.play(audioData);
            return ResponseEntity.ok("Song played successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{musicName}")
    public ResponseEntity<String> getSongByName(@PathVariable String musicName) {
        Optional<Audio> optionalMusic = audioService.getMusicByName(musicName);
        if (optionalMusic.isPresent()) {
            Audio audio = optionalMusic.get();
            // Call audio player with music.getFileData() to play the song
            byte[] audioData = audio.getFileData();
            AudioPlayer.play(audioData);
            return ResponseEntity.ok("Song played successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{musicArtist}")
    public ResponseEntity<String> getSongByArtist(@PathVariable String musicArtist) {
        Optional<Audio> optionalMusic = audioService.getMusicByArtist(musicArtist);
        if (optionalMusic.isPresent()) {
            Audio audio = optionalMusic.get();
            // Call audio player with music.getFileData() to play the song
            byte[] audioData = audio.getFileData();
            AudioPlayer.play(audioData);
            return ResponseEntity.ok("Song played successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{musicGenre}")
    public ResponseEntity<String> getSongByGenre(@PathVariable String musicGenre) {
        Optional<Audio> optionalMusic = audioService.getMusicByGenre(musicGenre);
        if (optionalMusic.isPresent()) {
            Audio audio = optionalMusic.get();
            // Call audio player with music.getFileData() to play the song
            byte[] audioData = audio.getFileData();
            AudioPlayer.play(audioData);
            return ResponseEntity.ok("Song played successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Audio> getAllSongs() {
        return audioService.getAllMusic();
    }

    @PostMapping
    public ResponseEntity<String> addMusic(@RequestParam("filePath") String filePath) {
        try {
            audioService.addMusic(filePath);
            return ResponseEntity.ok("Music added successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to add music.");
        }
    }

    @PutMapping("/{musicId}")
    public ResponseEntity<String> updateMusic(@PathVariable Long musicId, @RequestBody MusicDto musicDto) {
        boolean isUpdated = audioService.updateMusic(musicId, musicDto);
        if (isUpdated) {
            return ResponseEntity.ok("Music updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{musicId}")
    public ResponseEntity<String> deleteMusic(@PathVariable Long musicId) {
        boolean isDeleted = audioService.deleteMusic(musicId);
        if (isDeleted) {
            return ResponseEntity.ok("Music deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}