/*
Um objecto da classe Train representa um comboio composto de vários vagões
de mercadorias a granel.
Quando se cria um comboio, é necessário especificar a capacidade de cada
vagão e a capacidade total que o comboio suporta, ambas em toneladas.
Pode acrescentar-se um vagão com certa carga à cauda de um comboio
(addWagon) ou pode retirar-se um vagão da cauda (removeWagon), segundo uma
política LIFO (último a entrar é o primeiro a sair).
Naturalmente, a carga de um vagão não pode superar a sua capacidade e só
se pode acrescentar um vagão que não faça ultrapassar a carga total máxima do
comboio.
Também é possível pedir para descarregar (unload) uma dada quantidade, o que
será feito pela descarga completa e retirada de zero ou mais vagões da cauda
e pela descarga parcial de outro vagão para completar a quantidade pedida.
Em qualquer altura é possível obter uma lista da carga nos vagões do
comboio (list); saber o número de vagões (size) ou a carga total
transportada (totalCargo).

Métodos principais:
addWagon     Acrescenta um vagão com uma certa carga ao fim do comboio.
removeWagon  Retira o vagão do fim do comboio e devolve a sua carga.
unload       Descarrega uma certa quantidade, retirando os vagões que ficarem
             vazios.  Devolve o número de vagões retirados.
acceptsCargo Verifica se o comboio pode aceitar uma carga adicional,
             sem ultrapassar o limite de carga total.
list         Devolve um array com as cargas de cada vagão, do primeiro ao último.
             Não altera nada no comboio.
size         Tamanho (número de vagões) do comboio.
totalCargo   Carga total transportada no comboio.
*/
public class Train {

	private double maxCargo;
	private double wagonCargo;
	private double occupiedCargos[];
	private int wagons;
	private int occupiedWagons;

	public Train(double wagonCap, double trainCap){
		maxCargo = trainCap;
		wagonCargo = wagonCap;
		if (maxCargo % wagonCargo == 0) {
			wagons = (int)maxCargo / (int)wagonCargo;
		} else {
			wagons = (int)(maxCargo / wagonCargo) + 1;
		}
		occupiedCargos = new double[wagons];
		for (int i = 0; i < wagons; i++) {
			occupiedCargos[i] = 0;
		}
		occupiedWagons = 0;
	}

	public boolean isEmpty(){
		return (occupiedCargos[0] == 0);
	}

	public void addWagon(double cargo){
		assert acceptsCargo(cargo) : "No More space";
		assert cargo <= wagonCargo : "cargas fdds";
		for (int i = 0; i < wagons; i++) {
			if (occupiedCargos[i] == 0) {
				occupiedCargos[i] = cargo;
				occupiedWagons++;
				break;
			}
		}
	}

	public double removeWagon(){
		assert !isEmpty() : "Comboio vazio";
		double toRet = 0;
		for (int i = wagons-1; i >= 0; i--) {
			if (occupiedCargos[i] != 0) {
				toRet = occupiedCargos[i];
				occupiedCargos[i] = 0;
				occupiedWagons--;
				break;
			}
		}
		return toRet;
	}

	public int unload(double amount){
		int removedWagons = 0;
		for (int i = wagons-1; i >= 0; i--) {
			if (occupiedCargos[i] != 0) {
				if (occupiedCargos[i] - amount > 0) {
					occupiedCargos[i] -= amount;
					break;
				} else {
					amount -= occupiedCargos[i];
					occupiedCargos[i] = 0;
					removedWagons++;
					occupiedWagons--;
				}
			}
		}
		return removedWagons;
	}

	public boolean acceptsCargo(double cargo){

		return ((maxCargo >= cargo + totalCargo()) && occupiedWagons < wagons);
	}

	public double[] list(){
		int size = 0;
		try {
			for (int i = 0; i <= wagons; i++) {
				if (occupiedCargos[i] == 0) {
					size = i;
					break;
				}
			}
		} catch (NullPointerException e) {
			size = wagons;
		} catch (ArrayIndexOutOfBoundsException e) {
			size = wagons;
		}
		double[] list = new double[size];
		System.arraycopy(occupiedCargos, 0, list, 0, size);
		return list;
	}

	public int size(){
		return occupiedWagons;
	}

	public double totalCargo(){
		double total = 0;
		for (int i = 0; i < wagons; i++) {
			if (occupiedCargos[i] == 0) {
				break;
			} else {
				total += occupiedCargos[i];
			}
		}
		return total;
	}
}
