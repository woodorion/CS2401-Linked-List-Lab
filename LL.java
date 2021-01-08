public class LL {
    class City{
        String name;    //name of city
        int westDist;   //distance left/west of current city
        int eastDist;   //distance right/east of current city
        City west;      //previous/left
        City east;      //next/right

        //Creates list with 1 city.
        /***** I don't think this creates a list (since its in the sub-class).
         * while its true that a single node is a list in general,
         * since this class is not accessable outside, and there is a LL class it is not in itself externally a linked list. 
         *****/
        public City(String name, int westDist, int eastDist){
            /* Since these are distances, really they are unsigned, but being stored as an int means you need to check if its positive. */
            this.name = name;
            this.westDist = westDist;
            this.eastDist = eastDist;
        }
    }
    City head = null;
    City tail = null;

    /* It would be good if there were more comments so I know the details. 
    * I am assuming this method appends at the end of the list, sort of for initialization purposes. 
    * this is okay, but I think it is not needed in general.
    */
    public void addCity(String name, int westDist){
        City newCity = new City(name,westDist,0);

        if(head == null){
            head = tail = newCity;
            head.west = null;
            tail.east = null;
            //if this is the only city, distances are set to 0. No other cities to have distances to
            newCity.eastDist = 0;
            newCity.westDist = 0;
        }
        else{
            tail.east = newCity;
            newCity.west = tail;
            tail = newCity;
            tail.east = null;
            newCity.west.eastDist = newCity.westDist;   //overrides the previous/west City's east distance to be updated. e.g. Distance A to B equals B to A.
            newCity.eastDist = 0;   //distance east is 0, as the tail is the furthest east
        }
    }
    /**
     * Why only east? 
     */
    public void insertCity(String target, String name, int eastFromTar){
        //method to insert city in the middle of the map, after a given City (aka target)
        //inputs are target City's name, and distance of new city from Target city (east)
        //searches the list for the target City's name, returns if not found
        //assumes that actual distances don't change. e.g. adding city B doesn't make cities A and C further apart
        City current = head;
        while(current != null) {
            if(current.name.equals(target)) {
                if (eastFromTar > current.eastDist) {
                    /* This should call the append method. */
                    System.out.println("This town couldn't fit into the middle of this city and the next");
                    //put in code to calculate where this node would fit into the map, add accordingly
                    return;
                }
                if (eastFromTar < 0) { /* Good! */
                    System.out.println("You cannot have negative distances");
                    return;
                }
                if (eastFromTar == 0) {
                    System.out.println("This city is still within " + current.name);
                    return;
                }

                City newCity = new City(name, eastFromTar, current.east.westDist - eastFromTar);
                current.eastDist = eastFromTar;
                current.east.westDist = newCity.eastDist;
                //*** input code to make new city the head and tail if the target doesn't exist
                //Insert newCity after 'target'
                current.east.west = newCity;
                newCity.east = current.east;
                current.east = newCity;
                newCity.west = current;
                return;
            }
            current = current.east; //if current city is not the target city, move on to next city
        }
        System.out.println("A city of this name cannot be found on the map");

    }
    public void removeNode(String name){
        //removes the city under the given name
        //recalculates distances, doesn't shrink the map (e.g. cities A and C are the same distance apart, regardless if city B exists)
        City current = head;
        while(current != null){
            if(current.name.equals(name)){
                //checks if City to be removed is the head
                if(current == head){
                    head = current.east;
                    current.east.westDist = 0;
                    current.east.west = null;
                    current.east = null;
                    return;
                }
                //checks if City to be removed is the tail
                if(current == tail){
                    tail = current.west;
                    current.west.eastDist = 0;
                    current.west.east = null;
                    current.west = null;
                    return;
                }
                //changes the previous and next City's distances to each other to be the same distance apart
                int newDist = current.eastDist + current.westDist;
                current.west.eastDist = newDist;
                current.east.westDist = newDist;

                //adjusts City's next to current to point to each other, removing their connection to current city
                current.east.west = current.west;
                current.west.east = current.east;

                //removes current City's connections to anything else, finishing it's removal from the map
                current.east = null;
                current.east = null;
                return;
            }
            current = current.east; //if current city is not the target city, move on to next city
        }
        System.out.println(name + " cannot be removed, as " + name + " does not exist in this map");
    }
    public void findNode(String name){
        //finds the desired City in the map, reports how far away it is from it's neighbours. If not found, informs user
        City current = head;

        //linear search to find desired city
        while(current != null){
            //checks if desired city is the head node

            if(current.name.equals(name)){  //reports information about city, ends method
                if(current == head){
                    System.out.println(name + " is the first City on the map, and is " + current.eastDist + " miles West from " + current.east.name);
                    return;
                }
                //checks if desired city is the tail node
                if(current == tail){
                    System.out.println(name + " is the last city on the map, and is " + current.westDist + " miles East from " + current.west.name);
                    return;
                }
                //if city is somewhere in the middle, informs user of distance of both neighbours
                System.out.println(name + " is located " + current.westDist + " miles East from " + current.west.name + " and " + current.eastDist + " miles West from " + current.east.name);
                return;
            }
            current = current.east;     //moves to next city if the current city is not desired
        }
        System.out.println(name + " cannot be found in this map");
    }
    public void findDistance(String start, String end){
        //given the names of two cities, finds the distance between the two.
        //order of city names must be East to West

        /* how can you overcome this limitation? 
            if the distances are reflective, (i.e. A to B is the same as B to A), 
            can you determine if nodes are out of order and re-call the method? 
        */
        City current = head;
        int totalMiles = 0;
        while(current != null){
            if(current.name.equals(start)){
                while(current != null){
                    if(current.name.equals(end)){
                        System.out.println(end + " is " + totalMiles + " miles East of " + start);
                        return;
                    }
                    totalMiles += current.eastDist;
                    current = current.east;
                    if(current == null){
                        System.out.println(end + " does not exist in this map");
                        return;
                    }
                }
            }
            current = current.east;
        }
        System.out.println(start + " cannot be found on this map");
        System.out.println("Please ensure cities are entered West to East");
    }
    public void printCities(){
        City current = head;
        if(head == null){       //if the map is empty, inform the user of this
            System.out.println("This map is empty");
            return;
        }
        while(current != null){
            System.out.println(current.name + " West: " + current.westDist + " East: " + current.eastDist + " ");
            current = current.east;
        }
    }
    public void printMap(){
        //graphical representation of printCities
        City current = head;
        if(head == null){
            System.out.println("This map is empty");
            return;
        }
        while(current != null){
            if(current == tail){
                System.out.println(current.name);
                return;
            }
            System.out.print(current.name + " <--- " + current.eastDist + " ---> ");
            current = current.east;
        }
    }
}

