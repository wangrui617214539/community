package life.majiang.community.dto;

import lombok.Data;

@Data//多了评论者和被评论者，类似于commentDTO
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
