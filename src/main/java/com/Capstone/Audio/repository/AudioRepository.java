package com.Capstone.Audio.repository;

import com.Capstone.Audio.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
    Optional<Audio> findByMusicName(String musicName);

    Optional<Audio> findByMusicArtist(String musicArtist);

    Optional<Audio> findByMusicGenre(String musicGenre);
}
