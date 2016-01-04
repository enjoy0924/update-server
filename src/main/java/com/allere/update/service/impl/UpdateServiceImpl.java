package com.allere.update.service.impl;

import com.allere.update.dao.IUpdateIntroDao;
import com.allere.update.dto.UpdateIntroDTO;
import com.allere.update.entity.UpdateIntroEntity;
import com.allere.update.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by G_dragon on 2015/12/29.
 */
@Service("updateJPAService")
public class UpdateServiceImpl implements IUpdateService {

    @Autowired
    private IUpdateIntroDao updateIntroDao;

    /**
     * 保存更新文件到服务器后台
     *
     * @param sysType
     * @param sysVersion
     * @param fileName
     * @param url
     * @param size
     * @param checkSum
     * @param description
     */
    @Override
    public void saveToSpace(String sysType, String sysVersion, String fileName, String url, Long size, long checkSum, String description) {

        UpdateIntroEntity updateIntroEntity = new UpdateIntroEntity();
        updateIntroEntity.setSysType(sysType);
        updateIntroEntity.setDescription(description);
        updateIntroEntity.setSysVersion(sysVersion);
        updateIntroEntity.setCheckSum(checkSum);
        updateIntroEntity.setFileName(fileName);
        updateIntroEntity.setUpdateFileUrl(url);
        updateIntroEntity.setUpdateFileSize(size);
        updateIntroEntity.setCreateTime(new Date());

        updateIntroDao.create(updateIntroEntity);
    }

    @Override
    public List<UpdateIntroDTO> listUpdateFile() {

        List<UpdateIntroDTO> updateIntroDTOList = new ArrayList<>();
        List<UpdateIntroEntity> updateIntroEntities =  updateIntroDao.findAll();

        if(null == updateIntroEntities || updateIntroEntities.isEmpty()){
            return updateIntroDTOList;
        }

        for(UpdateIntroEntity updateIntroEntity : updateIntroEntities){

            UpdateIntroDTO updateIntroDTO = new UpdateIntroDTO();
            updateIntroDTO.setCheckSum(updateIntroEntity.getCheckSum());
            updateIntroDTO.setDescription(updateIntroEntity.getDescription());
            updateIntroDTO.setFilename(updateIntroEntity.getFileName());
            updateIntroDTO.setCreateTime(updateIntroEntity.getCreateTime());

            updateIntroDTOList.add(updateIntroDTO);
        }

        return updateIntroDTOList;
    }

}
