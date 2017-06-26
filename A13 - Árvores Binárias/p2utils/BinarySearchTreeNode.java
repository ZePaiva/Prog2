package p2utils;

public class BinarySearchTreeNode<E>{
	String key;
	E elem;
	BinarySearchTreeNode<E> left = null;
	BinarySearchTreeNode<E> right = null;

	public BinarySearchTreeNode(String chave, E elemento){
		key = chave;
		elem = elemento;
	}

	// procura uma chave
	public BinarySearchTreeNode<E> search(String chave){
		if (chave.equals(key)) {
			return this;	
		} else if (chave.compareTo(key) < 0 && left != null) {
			return left.search(chave);
		} else if (chave.compareTo(key) > 0 && right != null) {
			return right.search(chave);	
		} else {
			return null;
		}
	}

	// garante que a chave existe
	public boolean contains(String chave){
		return this.search(chave) != null;
	}

	// mete uma folha (wow)
	public BinarySearchTreeNode<E> insertLeaf(BinarySearchTreeNode<E> node){
		BinarySearchTreeNode toRet = node;
		if (node == null) {
			toRet = this;
		} else if (this.key.compareTo(node.key) < 0) {
			node.left = insertLeaf(node.left);
		} else if (this.key.compareTo(node.key) > 0) {
			node.right = insertLeaf(node.right);
		}
		return toRet;
	}


	// também dá pra remover usando inserção mas isso é muito fácil
	public BinarySearchTreeNode<E> removeRecursive(String chave){
		BinarySearchTreeNode tmp = this;

		if (chave.compareTo(key) < 0) {
			left = left.removeRecursive(chave);
		} else if (chave.compareTo(key) > 0) {
			right = right.removeRecursive(chave);
		} else if (left == null) {
			tmp = right;
		} else if (right == null) {
			tmp = left;
		} else {
			tmp = right.leftMost();
			right = right.removeRecursive(tmp.key);
			tmp.left = left;
			tmp.right = right;
		}
		return tmp;
	}

	// vê qual é que está mais à esquerda
	public BinarySearchTreeNode<E> leftMost(){
		if (left != null) {
			return left.leftMost();
		} else {
			return this;
		}
	}

	// same que em cima, mas pra direita
	public BinarySearchTreeNode<E> rightMost(){
		if (right != null) {
			return right.rightMost();
		} else {
			return this;
		}
	}

	// o nome diz tudo
	public String toString(){
		String toRet = key;
		if (left != null) {
			toRet += " " + left.toString();
		}
		if (right != null) {
			toRet += " " + right.toString();
		}
		
		return toRet;
	}

	// o comentário da de cima
	public int arrayKeys(String[] keysArray, int rounds){
		int jogs = rounds;
		if (left != null) {
			jogs = left.arrayKeys(keysArray, rounds);
		}

		keysArray[jogs++] = key;
		if (right != null) {
			jogs = right.arrayKeys(keysArray, jogs);
		}

		return jogs;
	}
}