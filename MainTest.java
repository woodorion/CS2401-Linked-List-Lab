public class MainTest {
    public static void main(String[] args){
        LL testList = new LL();
        testList.addCity("Town City",323);
        testList.addCity("Shady Sands",12);
        testList.addCity("City 42", 20);
        testList.addCity("New York", 10);

        System.out.println("\nTesting insert function:");
        testList.insertCity("City 42", "Rockport", 9);
        testList.printMap();

        System.out.println("\nTesting remove function:");
        testList.removeNode("Shady Sands");
        testList.printMap();

        System.out.println("\nTesting search function");
        testList.findNode("City 42");

        System.out.println("\nTesting distance function");
        testList.findDistance("City 42", "New York");

        System.out.println("Printing all Cities");
        testList.printCities();

        System.out.println("TESTING OVER");
    }
}
