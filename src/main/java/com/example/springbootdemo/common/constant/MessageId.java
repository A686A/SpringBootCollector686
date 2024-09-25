package com.example.springbootdemo.common.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

@Getter
public enum MessageId {
    MESSAGE_ID_4000001("4000001", "info.001", "0");

    private final String responseMessageId;
    private final String logMessageId;
    private final String ignoreObjectNameFlg;

    MessageId(String responseMessageId, String logMessageId, String ignoreObjectNameFlg) {
        this.responseMessageId = responseMessageId;
        this.logMessageId = logMessageId;
        this.ignoreObjectNameFlg = ignoreObjectNameFlg;
    }

    public static MessageId getMessageIdByResponseMessageId(final String responseMessageId) {
        MessageId[] messageIds = MessageId.values();
        for (MessageId localMessageId : messageIds) {
            if (localMessageId.getLogMessageId().equals(responseMessageId)) {
                return localMessageId;
            }
        }
        throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
    }
}
