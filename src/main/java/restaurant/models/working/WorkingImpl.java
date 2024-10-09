package restaurant.models.working;
import restaurant.models.client.Client;
import restaurant.models.waiter.Waiter;
import java.util.Collection;

public class WorkingImpl implements Working{
    @Override
    public void takingOrders(Client client, Collection<Waiter> waiters) {

        for (Waiter waiter : waiters) {
            while (waiter.canWork() && client.getClientOrders().iterator().hasNext()) {
                waiter.work();

                String currentOrder = client.getClientOrders().iterator().next();
                waiter.takenOrders().getOrdersList().add(currentOrder);
                client.getClientOrders().remove(currentOrder);
            }
        }
    }
}
