package restaurant.models.waiter;
import restaurant.common.ExceptionMessages;
import restaurant.models.orders.TakenOrders;
import restaurant.models.orders.TakenOrdersImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class BaseWaiter implements Waiter{

    protected String name;
    protected int efficiency;
    protected TakenOrders takenOrders;

    protected BaseWaiter(String name,int efficiency) {

        setName(name);
        setEfficiency(efficiency);
        takenOrders = new TakenOrdersImpl();
    }

    public void setName(String name) {
        if(Objects.equals(name,null) || name.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.WAITER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEfficiency(int efficiency) {
        if(efficiency < 0) {
            throw new IllegalArgumentException(ExceptionMessages.WAITER_EFFICIENCY_LESS_THAN_ZERO);
        }
        this.efficiency = efficiency;
    }

}
