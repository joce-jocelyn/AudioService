package com.Capstone.Audio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "audio")
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Lob
    @Column(name = "file_data")
    private byte[] fileData;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private long fileSize;

    @Column(name = "music_id")
    private String musicId;

    @Column(name = "music_name")
    private String musicName;

    @Column(name = "music_artist")
    private String musicArtist;

    @Column(name = "music_genre")
    private String musicGenre;
}