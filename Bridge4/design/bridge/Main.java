package design.bridge;

public class Main {

  public static void main(String[] args) {
    Vehicle bike = new Bike();
    bike.joinWorkshop(new ProduceWorkShop());
    bike.joinWorkshop(new AssembleWorkShop());
    bike.joinWorkshop(new TestWorkShop());
    bike.manufacture();
  
    Vehicle car = new Car();
    car.joinWorkshop(new ProduceWorkShop());
    car.joinWorkshop(new AssembleWorkShop());
    car.joinWorkshop(new PaintWorkShop());
    car.joinWorkshop(new TestWorkShop());
    car.manufacture();
    
    Vehicle bus = new Bus();
    bus.joinWorkshop(new RepairWorkShop());
    bus.joinWorkshop(new AssembleWorkShop());
    bus.joinWorkshop(new TestWorkShop());
    bus.manufacture();
  }
}
