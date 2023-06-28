package com.Capstone.Audio.service;

import com.Capstone.Audio.dto.MusicDto;
import com.Capstone.Audio.entity.Audio;
import com.Capstone.Audio.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Service
public class AudioService {

    @Autowired
    private AudioRepository audioRepository;

    public Optional<Audio> getMusicById(Long musicId) {
        return audioRepository.findById(musicId);
    }

    public Optional<Audio> getMusicByName(String musicName) {
        return audioRepository.findByMusicName(musicName);
    }

    public Optional<Audio> getMusicByArtist(String musicArtist) {
        return audioRepository.findByMusicArtist(musicArtist);
    }

    public Optional<Audio> getMusicByGenre(String musicGenre) {
        return audioRepository.findByMusicGenre(musicGenre);
    }

    public List<Audio> getAllMusic() {
        return audioRepository.findAll();
    }

    public void addMusic(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] fileData = Files.readAllBytes(file.toPath());
        String fileName = file.getName();
        String fileType = getFileExtension(fileName);
        long fileSize = file.length();

        Audio audio = new Audio();
        audio.setFileName(fileName);
        audio.setFileData(fileData);
        audio.setFileType(fileType);
        audio.setFileSize(fileSize);

        audioRepository.save(audio);
    }

    public boolean updateMusic(Long musicId, MusicDto musicDto) {
        Optional<Audio> optionalAudio = audioRepository.findById(musicId);
        if (optionalAudio.isPresent()) {
            Audio audio = optionalAudio.get();
            audio.setMusicName(musicDto.getMusicName());
            audio.setMusicArtist(musicDto.getMusicArtist());
            audio.setMusicGenre(musicDto.getMusicGenre());
            audioRepository.save(audio);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteMusic(Long musicId) {
        Optional<Audio> optionalAudio = audioRepository.findById(musicId);
        if (optionalAudio.isPresent()) {
            Audio audio = optionalAudio.get();
            audioRepository.delete(audio);
            return true;
        } else {
            return false;
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return ""; // Return empty string if no file extension found
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }
}
