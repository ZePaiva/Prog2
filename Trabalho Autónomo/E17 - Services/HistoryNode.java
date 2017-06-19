
public class HistoryNode {
   int time;
   String client;
   int serviceIndex; // índice do serviço no vector de serviços
   HistoryNode next;

   public int maxTime(){
   	if (next == null){
   		return time;
   	} else {
   		int ntime = next.maxTime();
   		if (time > ntime) {
   			return time;
   		} else {
   			return ntime;
   		}
   	}
   }
}

