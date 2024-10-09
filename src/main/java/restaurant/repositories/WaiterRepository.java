package restaurant.repositories;
import restaurant.models.waiter.Waiter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class WaiterRepository implements Repository<Waiter>{
    private final Collection<Waiter> waiters;

    public WaiterRepository(){
        waiters = new ArrayList<>();
    }

    @Override
    public Collection<Waiter> getCollection() {
        return Collections.unmodifiableCollection(this.waiters);
    }

    @Override
    public void add(Waiter entity) {
        this.waiters.add(entity);
    }

    @Override
    public boolean remove(Waiter entity) {
        return this.waiters.remove(entity);
    }

    @Override
    public Waiter byName(String name) {
        return this.waiters
                .stream()
                .filter(a -> Objects.equals(a.getName(), name))
                .findFirst()
                .orElse(null);
    }
}
