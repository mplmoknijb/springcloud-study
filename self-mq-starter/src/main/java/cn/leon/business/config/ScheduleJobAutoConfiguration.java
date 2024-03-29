package cn.leon.business.config;

import cn.leon.business.service.ITransactionMessageManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
public class ScheduleJobAutoConfiguration {

    private final ITransactionMessageManagementService managementService;

    /**
     * todo compensation
     */
    private final RedissonClient redisson = Redisson.create();

    @Scheduled(fixedDelay = 10000)
    public void transactionalMessageCompensationTask() throws Exception {
        RLock lock = redisson.getLock("transactionalMessageCompensationTask");
        boolean tryLock = lock.tryLock(5, 300, TimeUnit.SECONDS);
        if (tryLock) {
            try {
                long start = System.currentTimeMillis();
                log.info("开始执行事务消息推送补偿定时任务...");
                managementService.processPendingCompensationRecords();
                long end = System.currentTimeMillis();
                long delta = end - start;
                // 以防锁过早释放
                if (delta < 5000) {
                    Thread.sleep(5000 - delta);
                }
                log.info("执行事务消息推送补偿定时任务完毕,耗时:{} ms...", end - start);
            } finally {
                lock.unlock();
            }
        }
    }
}
