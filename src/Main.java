//Trabalho problema do barbeiro - Sistemas Operacionais
//Gabriel Braz e Santos - 260569

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int posicao_loop = 0;
        int qtd_cadeiras = 0;
        int qtd_clientes = 0;
        Scanner scan = new Scanner(System.in);

        //Precisamos decidir a quantia de cadeiras e clientes.
        //Para facilitar, tera sempre um cliente a mais do que a quantia de cadeiras (cadeira barbeiro + espera)
        //Isso deixa todos os estados serem utilizados pelo script, tanto zero quanto clientes demais na loja
        do
        {
            System.out.println("Insira a quantidade de cadeiras de espera para usar neste problema: (Ex. 3)");
            System.out.println("-> Pelo menos 1! <-");
            qtd_cadeiras = scan.nextInt();
        } while(qtd_cadeiras < 1);

        qtd_clientes = qtd_cadeiras + 2; //Clientes = Cadeiras + Cadeira Barbeiro + 1.
        qtd_cadeiras = qtd_cadeiras + 1; // Cadeiras de espera + 1 Cadeira barbeiro
        Barbeiro barbeiro = new Barbeiro();
        Cliente[] cliente_array = new Cliente[qtd_clientes];
        Cadeira[] cadeira_array = new Cadeira[qtd_cadeiras];

        //Criando as cadeiras.
        // 0 = Cadeira barbeiro
        // 1 -> n = Cadeiras de espera
        for (int i = 0; i < qtd_cadeiras; i++){
            if (i == 0) {
                //Cadeira barbeiro - tipo 1
                cadeira_array[i] = new Cadeira(1);
            } else {
                //Cadeiras de espera - tipo 2
                cadeira_array[i] = new Cadeira(2);
            }
        }

        //Criando as cadeiras.
        // 0 = Cadeira barbeiro
        // 1 -> n = Cadeiras de espera
        for (int i = 0; i < qtd_clientes; i++){
            cliente_array[i] = new Cliente(i);
        }

        //Executa o problema do barbeiro infinitamente,
        //Mate o programa para terminar.
        do{
            //Executa a logica dos clientes.
            cliente_array[posicao_loop].decisionLogic(cadeira_array);

            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //Executa a logica do barbeiro.
            barbeiro.decisionLogic(cadeira_array, cliente_array);

            posicao_loop++;

            //Se chegou no ultimo cliente, volta ao primeiro para decidir o que fazer,
            //Os clientes sao um educados, esperam na vez para tomar decisoes.
            if (posicao_loop == qtd_clientes){
                posicao_loop = 0;
            }

            //Espera 5 segundos para o usuario poder acompanhar o que esta acontecendo.
            try
            {
                Thread.sleep(5000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } while (true);
    }
}
