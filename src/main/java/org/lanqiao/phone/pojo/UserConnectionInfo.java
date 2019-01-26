package org.lanqiao.phone.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class UserConnectionInfo {
    private UserIdAndType userIdAndType;
    private String socketId;
    private String usernameFind;
}
