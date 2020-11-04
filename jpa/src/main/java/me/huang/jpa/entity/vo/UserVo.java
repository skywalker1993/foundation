package me.huang.jpa.entity.vo;

import lombok.Value;
import me.huang.jpa.entity.embedded.Address;

@Value
public class UserVo {

    String username;
    String phone;
    Address address;
}
