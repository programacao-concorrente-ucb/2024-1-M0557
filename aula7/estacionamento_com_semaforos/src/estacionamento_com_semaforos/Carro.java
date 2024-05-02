package estacionamento_com_semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Carro extends Thread {
	private Semaphore estacionamento;
	
	public Carro(String nome, Semaphore estacionamento) {
		// chamando construtor da classe pai (Thread)
		// passando um novo nome
		super(nome);
		this.estacionamento = estacionamento;
	}
	
	public void espera() {
		// esperar entre 10 e 15 s
		try {
			Thread.sleep(10000 + new Random().nextInt(15000));
		} catch (InterruptedException e) {
			System.out.println("Thread interrompida!");
		}
	}
	
	public void run() {

		try {
			// realizar uma verificação para ver se
			// o estacionamento já está lotado (com 10 carros)
			
			// acquire() é similar ao lock()
			this.estacionamento.acquire();
			
			// se não tiver cheio, entro e ocupo uma vaga
			System.out.println(this.getName() + " entrou no estacionamento!");
			// fico um tempo aleatório
			this.espera();
			
			System.out.println(this.getName() + " saindo do estacionamento!");
			// saio do estacionamento, liberando a vaga (a permissão do meu semáforo)
			this.estacionamento.release();
			
		} catch (InterruptedException e) {
			System.out.println("Thread interrompida!");
		}
	}
}
