package com.smart.canteen.entity;

import java.time.LocalDateTime;

/**
 * @author lc
 * @date 2020/3/3下午 10:18
 */
public interface BaseEntity {


    LocalDateTime getCreateTime();


    void setCreateTime(LocalDateTime createTime);


    Long getCreatorId();


    void setCreatorId(Long creatorId);


    String getCreatorAccount();


    void setCreatorAccount(String creatorAccount);


    String getCreatorName();


    void setCreatorName(String creatorName);


    LocalDateTime getLastUpdateTime();


    void setLastUpdateTime(LocalDateTime lastUpdateTime);


    Long getLastUpdateId();


    void setLastUpdateId(Long lastUpdateId);


    String getLastUpdateAccount();


    void setLastUpdateAccount(String lastUpdateAccount);


    String getLastUpdateName();


    void setLastUpdateName(String lastUpdateName);


    Long getVersion();


    void setVersion(Long version);


    Boolean getDeleted();


    void setDeleted(Boolean deleted);


}
