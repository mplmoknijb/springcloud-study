package cn.leon.business.config;

import cn.leon.business.enums.ExchangeType;
import cn.leon.business.model.TransactionalMessage;
import cn.leon.business.service.ITransactionMessageManagementService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitBindingProperties;
import org.springframework.cloud.stream.binder.rabbit.properties.RabbitExtendedBindingProperties;
import org.springframework.cloud.stream.config.BindingProperties;
import org.springframework.cloud.stream.config.BindingServiceProperties;
import org.springframework.cloud.stream.messaging.DirectWithAttributesChannel;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class SelfTxChannelImpl extends DirectWithAttributesChannel implements SelfTxChannel {

    private final ITransactionMessageManagementService managementService;

    private BindingServiceProperties bindingServiceProperties;

    private RabbitExtendedBindingProperties rabbitExtendedBindingProperties;

    @Override
    public <T> void sendTransasctionMessage(T content) {
        Map<String, BindingProperties> bindings = bindingServiceProperties.getBindings();
        String beanName = getBeanName();
        BindingProperties bindingProperties = bindings.get(beanName);
        String destination = bindingProperties.getDestination();
        String queueName = destination.concat(bindingProperties.getGroup());
        RabbitBindingProperties rabbitBindingProperties = rabbitExtendedBindingProperties.getBindings().get(beanName);
        // save to db
        TransactionalMessage record = new TransactionalMessage();
        record.setQueueName(queueName);
        record.setExchangeName(destination);
        record.setExchangeType(ExchangeType.TOPIC.getType());
        record.setRoutingKey(rabbitBindingProperties.getConsumer().getBindingRoutingKey());
        record.setBusinessModule("SAVE_ORDER");
        record.setBusinessKey(UUID.randomUUID().toString());
        managementService.saveTransactionalMessageRecord(record, content.toString());
        // transaction
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                managementService.sendMessageSync(record, content.toString());
            }
        });
        System.out.println("send message!");
    }
}
