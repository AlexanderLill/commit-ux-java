import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataProvider {

    public Entities.AccountManager[] AccountManagers;
    public Entities.Account[] Accounts;
    public Entities.Address[] Addresses;
    public Entities.OrderItem[] OrderItems;

    public DataProvider(){
        AccountManagers = new Entities.AccountManager[]{
                new Entities.AccountManager(){{FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); Department = "WestCorp";}},
                new Entities.AccountManager(){{FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); Department = "EastCorp";}},
                new Entities.AccountManager(){{FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); Department = "NorthCorp";}}
        };

        Accounts = new Entities.Account[]{
                new Entities.Account(){{ AccountId = UUID.randomUUID(); FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); AccountManager = AccountManagers[0];}},
                new Entities.Account(){{ AccountId = UUID.randomUUID(); FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); AccountManager = AccountManagers[0];}},
                new Entities.Account(){{ AccountId = UUID.randomUUID(); FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); AccountManager = AccountManagers[1];}},
                new Entities.Account(){{ AccountId = UUID.randomUUID(); FirstName = GetRandomItem(ItemType.FirstName); LastName = GetRandomItem(ItemType.SecondName); AccountManager = AccountManagers[2];}}
        };

        Addresses = new Entities.Address[]{
                new Entities.Address(){{Country = "United States"; City = GetRandomItem(ItemType.City); Street = GetRandomItem(ItemType.Street); BuildingNumber = ThreadLocalRandom.current().nextInt(0, 100); ApartmentNumber = ThreadLocalRandom.current().nextInt(0, 10);}},
                new Entities.Address(){{Country = "United States"; City = GetRandomItem(ItemType.City); Street = GetRandomItem(ItemType.Street); BuildingNumber = ThreadLocalRandom.current().nextInt(0, 100); ApartmentNumber = ThreadLocalRandom.current().nextInt(0, 10);}},
                new Entities.Address(){{Country = "United States"; City = GetRandomItem(ItemType.City); Street = GetRandomItem(ItemType.Street); BuildingNumber = ThreadLocalRandom.current().nextInt(0, 100); ApartmentNumber = ThreadLocalRandom.current().nextInt(0, 10);}},
                new Entities.Address(){{Country = "United States"; City = GetRandomItem(ItemType.City); Street = GetRandomItem(ItemType.Street); BuildingNumber = ThreadLocalRandom.current().nextInt(0, 100); ApartmentNumber = ThreadLocalRandom.current().nextInt(0, 10);}},
                new Entities.Address(){{Country = "United States"; City = GetRandomItem(ItemType.City); Street = GetRandomItem(ItemType.Street); BuildingNumber = ThreadLocalRandom.current().nextInt(0, 100); ApartmentNumber = ThreadLocalRandom.current().nextInt(0, 10);}},
                new Entities.Address(){{Country = "United States"; City = GetRandomItem(ItemType.City); Street = GetRandomItem(ItemType.Street); BuildingNumber = ThreadLocalRandom.current().nextInt(0, 100); ApartmentNumber = ThreadLocalRandom.current().nextInt(0, 10);}}
        };

        OrderItems = new Entities.OrderItem[] {
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}},
                new Entities.OrderItem(){{Value = ThreadLocalRandom.current().nextInt(10, 100); Description = GetRandomItem(ItemType.OrderDescription);}}
        };
    }

    public String GetRandomItem(ItemType type){
        String[] firstNames = new String[] {"James", "John", "Robert", "Michael", "William", "Mary", "Patricia", "Jennifer", "Linda", "Elizabeth"};
        String[] secondNames = new String[] {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis"};
        String[] cities = new String[] {"New York", "Los Angeles", "Washington", "Denver", "Chicago", "Boston", "San Francisco", "Philadelphia", "Seattle", "Portland"};
        String[] streets = new String[] {"Bradford Road", "Church Road", "Hill Street", "Wharf Road", "Brown Street", "Pound Lane"};
        String[] orderDescriptions = new String[] {"Books", "Computers", "Electronics", "Fashion", "Health", "Household", "Music", "Sport", "Tools", "Toys"};

        int i = 0;
        switch (type){
            case FirstName:
                i = ThreadLocalRandom.current().nextInt(0, firstNames.length);
                return firstNames[i];
            case SecondName:
                i = ThreadLocalRandom.current().nextInt(0, secondNames.length);
                return secondNames[i];
            case City:
                i = ThreadLocalRandom.current().nextInt(0, cities.length);
                return cities[i];
            case Street:
                i = ThreadLocalRandom.current().nextInt(0, streets.length);
                return streets[i];
            case OrderDescription:
                i = ThreadLocalRandom.current().nextInt(0, orderDescriptions.length);
                return orderDescriptions[i];
        }
        return "NA";
    }

    public enum ItemType{
        FirstName,
        SecondName,
        City,
        Street,
        OrderDescription
    }
}
