package me.huang.jpa.entity.projection;

public interface UsernameAndCity {

    String getUsername();
    Address getAddress();

    interface Address {
        String getCity();
    }
}
