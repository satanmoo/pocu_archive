package academy.pocu.comp2500.lab11;

import academy.pocu.comp2500.lab11.pocu.User;
import academy.pocu.comp2500.lab11.pocu.Wallet;

public class SafeWallet extends Wallet {
    public SafeWallet(User user) throws IllegalAccessException {
        super(user);
    }

    @Override
    public boolean deposit(final int amount) {
        int balance = super.getAmount();
        if (amount < 0) {
            return false;
        }
        if (balance > Integer.MAX_VALUE - amount) {
            throw new OverflowException("Wallet amount overflow!");
        }

        return super.deposit(amount);
    }
}
