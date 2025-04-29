import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Entities {
    static class Account {
        public UUID AccountId;
        public String FirstName;
        public String LastName;
        public AccountManager AccountManager;
    }

    static class Address {
        public String Country;
        public String City;
        public String Street;
        public int BuildingNumber;
        public int ApartmentNumber;
    }

    static class AccountManager {
        public String FirstName;
        public String LastName;
        public String Department;;
    }

    static class OrderItem {
        int Value = 0;
        String Description = null;
    }

    static class DeliveryContainer {
        public HashMap<UUID, OrderItem> Orders;

        public void PackContainer(OrderItem[] orderItems){
            HashMap<UUID, OrderItem> orders = new HashMap<>();
            for (OrderItem item: orderItems) {
                orders.put(UUID.randomUUID(), item);
            }
            Orders = orders;
        }
    }

    static class DeliveryOrder {
        public HashMap<UUID, DeliveryContainer> Containers;
        public Account Account;
        public AccountManager AccountManager;
        public Address DeliveryAddress;

        public DeliveryOrder(DeliveryContainer[] containers, Account account, AccountManager manager, Address address){
            Containers = new HashMap<>();
            for (DeliveryContainer container: containers) {
                Containers.put(UUID.randomUUID(), container);
            }
            Account = account;
            AccountManager = manager;
            DeliveryAddress = address;
        }

        public String toString() {
            String itemString = "";
            for (Entities.DeliveryContainer container: Containers.values()) {
                for (Map.Entry<UUID, OrderItem> item: container.Orders.entrySet()) {
                    itemString += String.format("- %s: %sx %s %n", item.getValue().Description, container.Orders.size(), item.getValue().Value);
                }
            }
            String result = String.format("Address: %s Items:%n%s %n", DeliveryAddress, itemString);
            return result;
        }
    }

    static class Transport {
        public UUID TransportId;
        public LinkedHashMap<UUID, DeliveryOrder> Orders;
        public Address CurrentDestination = null;
        public int DestinationTime = 0;
        public int ProgressTime = 0;

        public Transport(){
            TransportId = UUID.randomUUID();
            Orders = new LinkedHashMap<>();
        }

        public void Load(DeliveryOrder[] orders){
            for (DeliveryOrder order: orders) {
                Orders.put(UUID.randomUUID(), order);
            }
        }

        public String GetProgress() {
            return String.format("%.2f",(float) ProgressTime / DestinationTime * 100) + "%";
        }
    }

    static class TransportJob extends Thread {
        Transport Transport;
        public boolean isActive;
        public String CurrentDeliveryId;

        public TransportJob(Transport transport){
            Transport = transport;
            isActive = false;
        }

        public void run() {
            Execute();
        }

        void Execute(){
            if (Transport.Orders.size() > 0){
                System.out.printf("Transport %s has departed.%n", Transport.TransportId);
                isActive = true;
                for (Map.Entry<UUID, DeliveryOrder> order: Transport.Orders.entrySet()) {
                    Transport.CurrentDestination = order.getValue().DeliveryAddress;
                    Transport.DestinationTime = ThreadLocalRandom.current().nextInt(10, 100);
                    Transport.ProgressTime = 0;
                    CurrentDeliveryId = order.getKey().toString().split("-")[0];
                    while (Transport.DestinationTime - Transport.ProgressTime != 0) {
                        try {
                            Thread.sleep(200);
                            Transport.ProgressTime++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                isActive = false;
                System.out.printf("Transport %s has arrived.%n", Transport.TransportId, Transport.CurrentDestination);
            }
            else {
                System.out.printf("Transport %s is empty.%n", Transport.TransportId);
            }
        }
    }
}
