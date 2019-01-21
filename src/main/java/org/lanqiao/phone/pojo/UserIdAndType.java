package org.lanqiao.phone.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserIdAndType {
    private int userIdFind;
    private String userType;
}
