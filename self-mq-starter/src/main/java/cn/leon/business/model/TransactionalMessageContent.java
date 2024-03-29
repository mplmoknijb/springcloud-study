package cn.leon.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionalMessageContent extends BaseEntity{
    private Long id;
    private Long messageId;
    private String content;
}
