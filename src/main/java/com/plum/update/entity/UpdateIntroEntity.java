package com.plum.update.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

/**
 * Created by G_dragon on 2015/12/31.
 */
@Entity
@Table(name="update_intro")
public class UpdateIntroEntity extends AbstractEntity {
    private String id = UUID.randomUUID().toString();
    private String sysType;             //名称，预留字段
    private String sysVersion;          //所属人ID
    private Long updateFileSize;          //试卷ID
    private String updateFileUrl;        //试卷内容
    private Long checkSum;        //试卷附加属性,预留字段
    private Integer isLatestStable;      //试卷内容的校验和
    private Integer isDisable;                //书签类型 1-课堂  2-家庭
    private String description;
    private Date createTime;
    private String fileName;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public Long getUpdateFileSize() {
        return updateFileSize;
    }

    public void setUpdateFileSize(Long updateFileSize) {
        this.updateFileSize = updateFileSize;
    }

    public String getUpdateFileUrl() {
        return updateFileUrl;
    }

    public void setUpdateFileUrl(String updateFileUrl) {
        this.updateFileUrl = updateFileUrl;
    }

    public Long getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(Long checkSum) {
        this.checkSum = checkSum;
    }

    public Integer getIsLatestStable() {
        return isLatestStable;
    }

    public void setIsLatestStable(Integer isLatestStable) {
        this.isLatestStable = isLatestStable;
    }

    public Integer getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(Integer isDisable) {
        this.isDisable = isDisable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UpdateIntroEntity that = (UpdateIntroEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sysType != null ? !sysType.equals(that.sysType) : that.sysType != null) return false;
        if (sysVersion != null ? !sysVersion.equals(that.sysVersion) : that.sysVersion != null) return false;
        if (updateFileSize != null ? !updateFileSize.equals(that.updateFileSize) : that.updateFileSize != null)
            return false;
        if (updateFileUrl != null ? !updateFileUrl.equals(that.updateFileUrl) : that.updateFileUrl != null)
            return false;
        if (checkSum != null ? !checkSum.equals(that.checkSum) : that.checkSum != null) return false;
        if (isLatestStable != null ? !isLatestStable.equals(that.isLatestStable) : that.isLatestStable != null)
            return false;
        if (isDisable != null ? !isDisable.equals(that.isDisable) : that.isDisable != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return !(createTime != null ? !createTime.equals(that.createTime) : that.createTime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sysType != null ? sysType.hashCode() : 0);
        result = 31 * result + (sysVersion != null ? sysVersion.hashCode() : 0);
        result = 31 * result + (updateFileSize != null ? updateFileSize.hashCode() : 0);
        result = 31 * result + (updateFileUrl != null ? updateFileUrl.hashCode() : 0);
        result = 31 * result + (checkSum != null ? checkSum.hashCode() : 0);
        result = 31 * result + (isLatestStable != null ? isLatestStable.hashCode() : 0);
        result = 31 * result + (isDisable != null ? isDisable.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}