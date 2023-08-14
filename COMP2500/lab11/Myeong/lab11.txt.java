
//---------------------- App.java ----------------------
package academy.pocu.comp2500.lab11;

import academy.pocu.comp2500.lab11.pocu.Product;
import academy.pocu.comp2500.lab11.pocu.ProductNotFoundException;
import academy.pocu.comp2500.lab11.pocu.User;
import academy.pocu.comp2500.lab11.pocu.Wallet;
import academy.pocu.comp2500.lab11.pocu.Warehouse;
import academy.pocu.comp2500.lab11.pocu.WarehouseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public final class App {
    private User user = new User();

    public App() {
    }

    public void run(BufferedReader in, PrintStream out, PrintStream err) throws OverflowException {
        StringBuilder builder = new StringBuilder("WAREHOUSE:");
        builder.append(" Choose your warehouse!");
        for (WarehouseType warehouseType : WarehouseType.values()) {
            builder.append(System.lineSeparator())
                    .append(warehouseType.ordinal() + 1)
                    .append(". ")
                    .append(warehouseType.name());
        }
        builder.append(System.lineSeparator());

        String readString;
        int readInteger = 0;
        WarehouseType selectType = null;

        while (true) {
            out.printf("%s", builder);

            try {
                readString = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (readString.equals("exit")) {
                return;
            }

            try {
                readInteger = Integer.parseInt(readString);
            } catch (NumberFormatException e) {
                out.println("숫자가 아닌 값을 입력하였습니다. 다시 입력해주세요.");
                continue;
            }

            if (readInteger <= 0 || readInteger > WarehouseType.values().length) {
                out.println("해당하는 숫자가 없습니다. 다시 입력해주세요.");
                continue;
            }

            for (WarehouseType warehouseType : WarehouseType.values()) {
                if (readInteger == warehouseType.ordinal() + 1) {
                    selectType = warehouseType;
                    break;
                }
            }

            if (selectType != null) {
                break;
            } else {
                System.err.println("Logic Error");
                return;
            }
        }

        Warehouse departmentWarehouse = new Warehouse(selectType);

        SafeWallet userDepartmentWallet = null;
        Product selectProduct = null;

        try {
            if (userDepartmentWallet == null) {
                userDepartmentWallet = new SafeWallet(this.user);
            }
        } catch (IllegalAccessException e) {
            err.println("AUTH_ERROR");
            return;
        }

        while (true) {
            StringBuilder builderWarehouse = new StringBuilder("PRODUCT_LIST: ");
            builderWarehouse.append(" Choose the product you want to buy!");

            int index = 0;
            for (Product product : departmentWarehouse.getProducts()) {
                builderWarehouse.append(System.lineSeparator())
                        .append(++index)
                        .append(". ")
                        .append(String.format("%-11s", product.getName()))
                        .append(String.format("%9s", product.getPrice()));
            }
            builderWarehouse.append(System.lineSeparator());

            int amount = 0;
            if ((amount = userDepartmentWallet.getAmount()) < 0) {
                throw new OverflowException("Deposit Overflow exception");
            }

            out.printf("BALANCE: %s%s", amount, System.lineSeparator());

            out.printf("%s", builderWarehouse);

            try {
                readString = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (readString.equals("exit")) {
                return;
            }

            try {
                readInteger = Integer.parseInt(readString);
            } catch (NumberFormatException e) {
                out.println("숫자가 아닌 값을 입력하였습니다. 다시 입력해주세요.");
                continue;
            }

            if (readInteger <= 0 || readInteger > departmentWarehouse.getProducts().size()) {
                out.println("해당하는 숫자가 없습니다. 다시 입력해주세요.");
                continue;
            }

            index = 0;
            for (Product product : departmentWarehouse.getProducts()) {
                if (readInteger == ++index) {
                    selectProduct = product;
                    break;
                }
            }

            boolean isError = false;

            try {
                if (selectProduct != null) {
                    if (userDepartmentWallet.withdraw(selectProduct.getPrice())) {
                        departmentWarehouse.removeProduct(selectProduct.getId());
                        out.println("Puchase to " + selectProduct.getName());
                    }
                }
            } catch (ProductNotFoundException e) {
                System.err.println(e);
                isError = true;
            }

            if (isError) {
                userDepartmentWallet.deposit(selectProduct.getPrice());
            }
        }
    }
}


//----------------------OverflowException.java----------------------

package academy.pocu.comp2500.lab11;

public class OverflowException extends RuntimeException {
    private static final long serialVersionUID = 11L;

    public OverflowException(final String message) {
        super(message);
    }
}


//----------------------SafeWallet.java----------------------

package academy.pocu.comp2500.lab11;

import academy.pocu.comp2500.lab11.pocu.User;
import academy.pocu.comp2500.lab11.pocu.Wallet;

import java.util.HashSet;

public class SafeWallet extends Wallet {
    public SafeWallet(final User user) throws IllegalAccessException {
        super(user);
    }

    @Override
    public boolean deposit(final int amount) {
        if (amount > 0 && super.getAmount() + amount < 0) {
            throw new OverflowException("Deposit Overflow exception");
        }
        boolean isChecked = super.deposit(amount);

        return isChecked;
    }
}
