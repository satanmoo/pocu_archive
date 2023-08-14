package academy.pocu.comp2500.lab9;

import java.util.Collection;

public interface IPricingModel {
    public int getTotalPrice(Collection<Book> books);
}
