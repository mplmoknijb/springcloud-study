package cn.leon.bussiness.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class StorageClient {

    @Autowired
    private RestTemplate restTemplate;

    public void deduct(String commodityCode, int orderCount) {
        String url = "http://localhost:8081/api/storage/deduct?commodityCode=" + commodityCode + "&count=" + orderCount;
        try {
            restTemplate.getForEntity(url, Void.class);
        } catch (Exception e) {
            log.error("deduct url {} ,error:", url, e);
            throw new RuntimeException();
        }
    }
}
