package restaurant.core;
import restaurant.common.ConstantMessages;
import restaurant.common.ExceptionMessages;
import restaurant.models.client.Client;
import restaurant.models.client.ClientImpl;
import restaurant.models.waiter.FullTimeWaiter;
import restaurant.models.waiter.HalfTimeWaiter;
import restaurant.models.waiter.Waiter;
import restaurant.models.working.Working;
import restaurant.models.working.WorkingImpl;
import restaurant.repositories.ClientRepository;
import restaurant.repositories.Repository;
import restaurant.repositories.WaiterRepository;

import java.util.Objects;

public class ControllerImpl implements Controller{
    private final Repository<Waiter> waiters;
    private final Repository<Client> clients;
    private int servedClient;

    public ControllerImpl() {
        this.waiters = new WaiterRepository();
        this.clients = new ClientRepository();

    }
    @Override
    public String addWaiter(String type, String waiterName) {
        if(!Objects.equals(type,"FullTimeWaiter") &&
                !Objects.equals(type,"HalfTimeWaiter")) {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_INVALID_TYPE);
        }

        Waiter waiter;

        if(Objects.equals(type,"FullTimeWaiter")) {

            waiter = new FullTimeWaiter(waiterName);
        } else {
            waiter = new HalfTimeWaiter(waiterName);
        }

        waiters.add(waiter);
        return ConstantMessages.WAITER_ADDED.formatted(type,waiterName);
    }

    @Override
    public String addClient(String clientName, String... orders) {
        Client client = new ClientImpl(clientName);

        for(String order : orders) {

            client.getClientOrders().add(order);
        }

        clients.add(client);
        return ConstantMessages.CLIENT_ADDED.formatted(clientName);
    }

    @Override
    public String removeWaiter(String waiterName) {
        if(waiters.byName(waiterName) == null) {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_DOES_NOT_EXIST.formatted(waiterName));
        }
        waiters.remove(waiters.byName(waiterName));

        return ConstantMessages.WAITER_REMOVE.formatted(waiterName);
    }

    @Override
    public String removeClient(String clientName) {
        if(clients.byName(clientName) == null) {
            throw new IllegalArgumentException(ExceptionMessages.CLIENT_DOES_NOT_EXIST.formatted(clientName));
        }
        waiters.remove(waiters.byName(clientName));

        return ConstantMessages.CLIENT_REMOVE.formatted(clientName);
    }

    @Override
    public String startWorking(String clientName) {
        if(waiters.getCollection().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.THERE_ARE_NO_WAITERS);
        }
        Working working = new WorkingImpl();
        Client client = clients.byName(clientName);
        working.takingOrders(client, waiters.getCollection());
        servedClient++;
        return "Client %s was served.".formatted(clientName);
    }

    @Override
    public String getStatistics() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("%d client/s was/were served.".formatted(servedClient)).append("\n");
        stringBuilder.append("Waiter's statistics:").append("\n");
        for(Waiter waiter : waiters.getCollection()) {

            stringBuilder.append("Name: %s".formatted(waiter.getName())).append("\n");
            stringBuilder.append("Efficiency: %s".formatted(waiter.getEfficiency())).append("\n");
            if(!waiter.takenOrders().getOrdersList().isEmpty()) {
                stringBuilder.append("Taken orders: %s".formatted(String.join(", ", waiter.takenOrders().getOrdersList()))).append("\n");
            } else {
                stringBuilder.append("Taken orders: None").append("\n");
            }
        }
        return stringBuilder.toString().trim();
    }
}
