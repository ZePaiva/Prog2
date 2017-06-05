import p2utils.HashTable;
import p2utils.Queue;

public class SupermarketOrdering{
	// fila de ordens/pedidos
	Queue<Order> ordens = new Queue();
	// taela de quantidades dos produtos
	HashTable<Integer> quantidades = new HashTable(10);

	// função em que pede (((?????)))
	public void enterOrder(Order pedido){
		// coloca o pedido na fila
		ordens.in(pedido);

		// se o produto já se encontra na tabela de quantidades, aumenta na quantidade pedida
		if (quantidades.contains(pedido.prodName)) {
			 quantidades.set(pedido.prodName, quantidades.get(pedido.prodName) + pedido.quantity);
		// caso o produto não se encotre na tabela de quantidades
		} else {
			quantidades.set(pedido.prodName, pedido.quantity);
		}
	}

	// retira a encomenda mais antiga
	public Order serveOrder(){
		// guarda e retira a encomenda mais antiga
		Order old = ordens.peek();
		ordens.out();

		// porque pode ser a última enomenda de um dado produto
		if (old.quantity == quantidades.get(old.prodName)){
			quantidades.remove(old.prodName);
		} else {
			quantidades.set(old.prodName, quantidades.get(old.prodName) - old.quantity);
		}

		return old;
	}

	// quantos produtos x estão a ser pedidos
	public int query(String product){
		// podem ser 0
		if (!quantidades.contains(product)){
			return 0;
		} else {
			return quantidades.get(product);
		}
	}

	// quantas unidaded de cada prduto estão encomendadas
	public void displayOrderedProducts(){
		String[] produtos = quantidades.keys();
		System.out.println("Produtos ordenados: ");

		for (int i = 0; i < produtos.length; i++) {
			System.out.printf("%s: %d\n", produtos[i], quantidades.get(produtos[i]));
		}
	}

	// função necessária (((??????????)))
	public int numOrders(){
		return ordens.size();
	}
}