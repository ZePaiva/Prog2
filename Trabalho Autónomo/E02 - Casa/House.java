import static java.lang.System.*;
import static java.lang.Math.*;

public class House {

   private Room [] rooms;
   private Door [] doors;
   private int extensionSize;
   private int numDoors;
   private int size;
   private String houseType;

   public House(String houseType,int maxSize,int extensionSize) {
      this.houseType = houseType;
      this.extensionSize = extensionSize;
      rooms = new Room[maxSize];
      size = 0;
      doors = new Door[maxSize];
      numDoors = 0;
   }

   public House(String houseType) {
      this(houseType,8,4);
   } 

   public int size() {
      return size;
   }

   public int maxSize() {
      return rooms.length;
   }

   public Room room(int i) {
      return rooms[i];
   }

   public int addRoom(Room r) {
      if (size == rooms.length) extendHouse();
      rooms[size] = r;
      size++;
      return (size-1);
   }

   private void extendHouse() {
      Room [] newRooms = new Room[rooms.length+extensionSize];
      arraycopy(rooms, 0, newRooms, 0, rooms.length);
      rooms = newRooms;
   } 

   public void addDoor(Door d) {
      if (numDoors == doors.length) {
         Door[] dow = new Door[doors.length + extensionSize];
         arraycopy(doors, 0, dow, 0, doors.length);
         doors = dow;
      }

      doors[numDoors] = d;
      numDoors++;
   }

   public int numDoors() {
      return numDoors;
   }

   public int maxNumDoors() {
      return doors.length;
   }

   public int roomClosestToRoomType(String roomType){
      double dist = 0;
      double dist2 = Integer.MAX_VALUE;
      int closerDivType = 0;

      //percorre o array
      for (int i = 0; i < size; i++) {
         //quando encontra para na posição
         if (rooms[i].roomType() == roomType){
            //percorre o array mas salta a posição da divisão pedida
            for (int j = 0; j < size; j++) {
               //para saltar
               if (j != i){
                  dist = sqrt(pow((rooms[i].geomCenter()).x() - (rooms[j].geomCenter()).x(), 2) + pow((rooms[i].geomCenter()).y() - (rooms[j].geomCenter()).y(), 2));
                  if (dist < dist2) {
                     dist2 = dist;
                     closerDivType = j;
                  }
               }
            }
            //no caso de a divisão pedida não ser a ultima que foi adicionada
            break;
         }
      }

      return closerDivType;
   }

   public int maxDoorsInAnyRoom(){
      int maxDoors = 0;
      int temps = 0;

      //percorre o array das salas
      for (int i = 0; i < size; i++) {
         //por cada sala verifica se a quantidade de portas
         for (int j = 0; j < numDoors; j++) {
            // se forem iguais incrementa
            if (doors[j].roomId1() == i || doors[j].roomId2() == i) {
               temps++;
            }
         }
         //depois de ter percorrido o array das portas compara com o valor maximo anterior
         if (temps > maxDoors) {
            maxDoors = temps;
         }
         //reinicia a vairavel temporaria
         temps = 0;
      }

      return maxDoors;
   }
}

