package restaurant.models.client;
import restaurant.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ClientImpl implements Client{

    private String name;
    private final Collection<String> clientOrders;

    public ClientImpl(String name){
        setName(name);
        clientOrders = new ArrayList<>();
    }

    @Override
    public Collection<String> getClientOrders() {
        return clientOrders;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (Objects.equals(name, null) || name.isBlank()) {
            throw new NullPointerException(ExceptionMessages.CLIENT_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
}
