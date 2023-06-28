package com.Capstone.Audio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicDto {
    private Long musicId;
    private String musicName;
    private String musicArtist;
    private String musicGenre;
}
