
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Entities.Transport[] transports = PrepareTransports();
        Entities.TransportJob[] jobs = new Entities.TransportJob[transports.length];
        for (int i = 0; i < transports.length; i++){
            jobs[i] = new Entities.TransportJob(transports[i]);
        }
        for (Entities.TransportJob job: jobs) {
            job.start();
        }
        Thread.sleep(1000);
        while (CheckForActiveJobs(jobs)){
            StringBuilder info = new StringBuilder("Transports in route | ");
            for (Entities.TransportJob j: jobs){
                if (j.isActive){
                    info.append(String.format(
                            "Transport ID: %s, delivery ID: %s, destination: %s, progress: %s | ",
                            j.Transport.TransportId.toString().split("-")[0],
                            j.CurrentDeliveryId,
                            j.Transport.CurrentDestination.City,
                            j.Transport.GetProgress()));
                }
            }
            System.out.println(info);
            Thread.sleep(1000);
        }
    }

    static Entities.Transport[] PrepareTransports() {
        DataProvider dataProvider = new DataProvider();
        // Pack containers
        Entities.DeliveryContainer c1 = new Entities.DeliveryContainer();
        c1.PackContainer(new Entities.OrderItem[]{
                dataProvider.OrderItems[0],
                dataProvider.OrderItems[1],
                dataProvider.OrderItems[2],
                dataProvider.OrderItems[3],
                dataProvider.OrderItems[4]});
        Entities.DeliveryContainer c2 = new Entities.DeliveryContainer();
        c2.PackContainer(new Entities.OrderItem[]{
                dataProvider.OrderItems[5],
                dataProvider.OrderItems[6],
                dataProvider.OrderItems[7]});
        Entities.DeliveryContainer c3 = new Entities.DeliveryContainer();
        c3.PackContainer(new Entities.OrderItem[]{
                dataProvider.OrderItems[8],
                dataProvider.OrderItems[9],
                dataProvider.OrderItems[10],
                dataProvider.OrderItems[11]});
        Entities.DeliveryContainer c4 = new Entities.DeliveryContainer();
        c4.PackContainer(new Entities.OrderItem[]{
                dataProvider.OrderItems[12],
                dataProvider.OrderItems[13]});
        Entities.DeliveryContainer c5 = new Entities.DeliveryContainer();
        c5.PackContainer(new Entities.OrderItem[]{
                dataProvider.OrderItems[14],
                dataProvider.OrderItems[15]});

        // Set up delivery orders
        Entities.DeliveryOrder o1 = new Entities.DeliveryOrder(
                new Entities.DeliveryContainer[]{c1, c2},
                dataProvider.Accounts[0],
                dataProvider.Accounts[0].AccountManager,
                dataProvider.Addresses[0]);
        Entities.DeliveryOrder o2 = new Entities.DeliveryOrder(
                new Entities.DeliveryContainer[]{c3, c4},
                dataProvider.Accounts[1],
                dataProvider.Accounts[1].AccountManager,
                dataProvider.Addresses[1]);
        Entities.DeliveryOrder o3 = new Entities.DeliveryOrder(
                new Entities.DeliveryContainer[]{c5},
                dataProvider.Accounts[2],
                dataProvider.Accounts[2].AccountManager,
                dataProvider.Addresses[2]);

        // Set up transports
        Entities.Transport t1 = new Entities.Transport();
        Entities.Transport t2 = new Entities.Transport();
        t1.Load(new Entities.DeliveryOrder[]{o1, o2});
        t2.Load(new Entities.DeliveryOrder[]{o3});

        return new Entities.Transport[]{t1, t2};
    }

    static boolean CheckForActiveJobs(Entities.TransportJob[] jobs ){
        for (Entities.TransportJob job : jobs){
            if (job.isActive){
                return true;
            }
        }
        return false;
    }
}
