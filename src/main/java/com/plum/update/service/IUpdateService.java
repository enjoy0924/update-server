package com.plum.update.service;

import com.plum.update.dto.UpdateIntroDTO;

import java.util.List;

/**
 * Created by G_dragon on 2015/12/29.
 */
public interface IUpdateService {

    void saveToSpace(String sysType, String sysVersion, String fileName, String url, Long size, long checkSum, String description);

    List<UpdateIntroDTO> listUpdateFile();
}
