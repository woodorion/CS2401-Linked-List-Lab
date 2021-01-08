class DeBlasioTests{
    public static void main(String[] args){

        System.out.println("\n***********Test 1***********");
        /* Test 1: Tests the add city method for negative distances. */
        LL list1 = new LL();
        list1.addCity("A", -1);
        list1.addCity("B", -1);
        list1.addCity("C", -1);
        list1.addCity("D", -1);
        list1.addCity("E", -1);
        list1.addCity("F", -1);
        list1.printMap();
        /* testing distance calculations with negatives */
        list1.findDistance("B","D");
        list1.findDistance("D","B"); // causes an error
        list1.findDistance("B","B");


        System.out.println("\n***********Test 2***********");
        /* Test 2: Testing insert */
        LL list2 = new LL();
        list2.addCity("A", -1);
        list2.insertCity("A","B", -1);
        list2.insertCity("A","C", 1);
        list2.insertCity("C","D", 1);
        list2.insertCity("D","E", 1);
        list2.insertCity("A","F", 1);
        list2.printMap();
        list2.addCity("E", 3);
        list2.insertCity("A","C", 1);
        list2.insertCity("C","D", 1);
        list2.printMap();
        list2.findDistance("A", "E");
        list2.findDistance("E", "A"); // error

        System.out.println("\n***********Test 3***********");
        /* Test 3: Testing remove */
        LL list3 = new LL();
        list3.addCity("A", -1);
        list3.addCity("B", 1);
        list3.addCity("C", 1);
        list3.addCity("D", 1);
        list3.addCity("E", 1);
        list3.addCity("F", 1);
        list3.printMap();
        list3.removeNode("A");
        list3.printMap();
        list3.removeNode("F");
        list3.printMap();
        list3.removeNode("C");
        list3.printMap();
        list3.removeNode("D");
        list3.printMap();
        list3.findDistance("B", "E");


    }
}