
public class ContaBancaria {

	private int saldo;
	private Cliente cliente;
	private int id;
	private static int contador;
	
	public ContaBancaria(Cliente cliente) {
		this.cliente = cliente;
		contador++;
		id = contador;
	}

	public int getSaldo() {

		return saldo;
	}

	public int depositar(int valorDeposito) {
		if (valorDeposito > 0) {
			saldo = saldo + valorDeposito;
		} else {
			
		}
		return saldo;
	}

	public int sacar(int valorSaque) {
		if (valorSaque > 0 && valorSaque <= saldo) {
			saldo = saldo - valorSaque;
		} else {
			
		}
		return saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getId() {
		return id;
	}

}
