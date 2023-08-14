package academy.pocu.comp2500.lab11;

import academy.pocu.comp2500.lab11.pocu.Product;
import academy.pocu.comp2500.lab11.pocu.ProductNotFoundException;
import academy.pocu.comp2500.lab11.pocu.User;
import academy.pocu.comp2500.lab11.pocu.Wallet;
import academy.pocu.comp2500.lab11.pocu.Warehouse;
import academy.pocu.comp2500.lab11.pocu.WarehouseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class App {
    private void printWarehouseList(PrintStream out, WarehouseType[] types) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("WAREHOUSE: Choose your warehouse!%s", System.lineSeparator()));
        for (int i = 0; i < types.length; ++i) {
            sb.append(String.format("%d. %s%s", i + 1, types[i].toString(), System.lineSeparator()));
        }
        out.print(sb);
    }

    public void run(BufferedReader in, PrintStream out, PrintStream err) {
        WarehouseType[] types = WarehouseType.values();
        WarehouseType type;
        while (true) {
            printWarehouseList(out, types);
            try {
                String s = in.readLine();

                if (s.equals("exit")) {
                    return;
                }

                int num = Integer.parseInt(s);
                if (num <= 0 || num > types.length) {
                    continue;
                }

                type = types[num - 1];
                break;

            } catch (IOException | NumberFormatException exception) {
                continue;
            }
        }

        assert (type != null);

        User user = new User();
        Wallet wallet;
        try {
            wallet = new SafeWallet(user);
        } catch (IllegalAccessException exception) {
            err.println("AUTH_ERROR");
            return;
        }

        assert (wallet != null);

        Warehouse warehouse = null;
        while (true) {
            out.printf("BALANCE: %d%s", wallet.getAmount(), System.lineSeparator());

            if (warehouse == null) {
                warehouse = new Warehouse(type);
            }
            ArrayList<Product> products = warehouse.getProducts();

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("PRODUCT_LIST: Choose the product you want to buy!%s", System.lineSeparator()));
            for (int i = 0; i < products.size(); ++i) {
                Product product = products.get(i);
                sb.append(String.format("%d. %-20s %d%s", i + 1, product.getName(), product.getPrice(), System.lineSeparator()));
            }
            out.print(sb);

            int index;
            try {
                String s = in.readLine();

                if (s.equals("exit")) {
                    return;
                }

                int num = Integer.parseInt(s);
                if (num <= 0 || num > products.size()) {
                    continue;
                }

                index = num - 1;

            } catch (IOException | NumberFormatException exception) {
                continue;
            }

            Product selectedProduct = products.get(index);
            // product 존재 + amount 존재
            boolean isWithdrawn = wallet.withdraw(selectedProduct.getPrice());
            if (selectedProduct.getPrice() != 0 && !isWithdrawn) {
                continue;
            }
            try {
                warehouse.removeProduct(selectedProduct.getId());
            } catch (ProductNotFoundException exception) {
                System.out.println(exception.getMessage());
                wallet.deposit(selectedProduct.getPrice());
            }
        }
    }
}
