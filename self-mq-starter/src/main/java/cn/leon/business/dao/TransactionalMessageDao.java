package cn.leon.business.dao;

import cn.leon.business.model.TransactionalMessage;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionalMessageDao {

    void insertSelective(TransactionalMessage record);

    void updateStatusSelective(TransactionalMessage record);

    List<TransactionalMessage> queryPendingCompensationRecords(LocalDateTime minScheduleTime,
                                                               LocalDateTime maxScheduleTime,
                                                               int limit);
}
