package me.huang.jpa.entity.streamable;

import lombok.RequiredArgsConstructor;
import me.huang.jpa.entity.po.User;
import org.springframework.data.util.Streamable;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(staticName = "of")
public class Usernames implements Streamable<User> {

    private final Streamable<User> streamable;

    public List<String> usernames() {
        return streamable.stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public Iterator<User> iterator() {
        return streamable.iterator();
    }
}
