package restaurant.models.waiter;
import restaurant.models.orders.TakenOrders;

import java.util.List;

public class FullTimeWaiter extends BaseWaiter {
    private static final int INITIAL_EFFICIENCY = 8;

    public FullTimeWaiter(String name) {
        super(name, INITIAL_EFFICIENCY);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEfficiency() {
        return this.efficiency;
    }

    @Override
    public boolean canWork() {
        return this.efficiency > 0;
    }

    @Override
    public TakenOrders takenOrders() {
        return this.takenOrders;
    }

    @Override
    public void work() {
        this.efficiency -= 1;
        if(this.efficiency < 0) {
            this.efficiency = 0;
        }
    }
}