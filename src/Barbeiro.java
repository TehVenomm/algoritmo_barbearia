//Trabalho problema do barbeiro - Sistemas Operacionais
//Gabriel Braz e Santos - 260569

import java.util.Random;

public class Barbeiro
{
    // status 1 - Dormindo
    // status 2 - Cortando Cabelo
    private int status = 1;

    //Logica inicial para o barbeiro decidir o que fazer.
    public void decisionLogic(Cadeira[] listaCadeiras, Cliente[] listaClientes){
        int id_cliente_esperou_mais;

        switch (status){
            //Barbeiro dormindo verifica se existe clientes esperando
            case 1:
                System.out.println("Barbeiro esta dormindo, acorda para verificar se existem clientes aguardando.");

                //Verifica as cadeiras de espera, encontra o cliente que aguardou por mais tempo.
                id_cliente_esperou_mais = verificar_cadeiras(listaCadeiras, listaClientes);

                //Retorno -1 significa que nao achou nenhum cliente, caso achar inicia a logica para cortar o cabelo.
                if (id_cliente_esperou_mais == -1){
                    tentar_dormir(listaCadeiras);
                } else {
                    pegar_cliente_sentado(listaCadeiras, listaClientes, id_cliente_esperou_mais);
                }
                break;

            //Barbeiro cortando cabelo verifica se terminou de cortar o cabelo e pega o proximo cliente
            case 2:
                //Caso a cadeira do barbeiro estiver vazia, procura um cliente.
                if (listaCadeiras[0].getStatus() == 1){
                    System.out.println("Barbeiro termina de cortar cabelo do cliente, e procura o proximo.");
                    //Verifica as cadeiras de espera, encontra o cliente que aguardou por mais tempo.
                    id_cliente_esperou_mais = verificar_cadeiras(listaCadeiras, listaClientes);

                    //Retorno -1 significa que nao achou nenhum cliente, barbeiro dorme.
                    //Outros retornos iniciam a logica para cortar cabelo do cliente.
                    if (id_cliente_esperou_mais == -1){
                        tentar_dormir(listaCadeiras);
                    } else {
                        pegar_cliente_sentado(listaCadeiras, listaClientes, id_cliente_esperou_mais);
                    }
                } else {
                    System.out.println("Barbeiro ainda esta ocupado cortando o cabelo do cliente #" + listaCadeiras[0].getId_cliente());
                }

                //Se a cadeira do barbeiro estiver ocupada, e ele estiver cortando cabelo, ele continua cortando.
                //Quem decide a hora de sair e o cliente.

                break;
        }

        System.out.println("----------");
    }

    //O barbeiro acorda, sai da cadeira de barbeiro
    //Pega o cliente, tira da cadeira de espera, coloca ele na cadeira do barbeiro, e corta o seu cabelo.
    //Quem decide quando terminar de cortar o cabelo e o cliente, pois ele sempre tem razao.
    private void pegar_cliente_sentado(Cadeira[] listaCadeiras, Cliente[] listaClientes, int id_cliente){
        //Barbeiro tira cliente da cadeira de espera.
        int old_cadeira = listaClientes[id_cliente].getId_cadeira_usada();

        System.out.println("\nBarbeiro encontrou um cliente!");
        System.out.println("Cliente #" + id_cliente + " - Esperou por: " + listaClientes[id_cliente].getTempo_esperando() + " horas - Na cadeira #" + old_cadeira);

        listaCadeiras[old_cadeira].setId_cliente(-1);
        listaCadeiras[old_cadeira].setStatus(1);

        System.out.println("\nBarbeiro tira o cliente da cadeira de espera.");

        //Barbeiro move cliente para a cadeira do barbeiro, e corta seu cabelo.
        listaCadeiras[0].setStatus(2);
        listaCadeiras[0].setId_cliente(id_cliente);
        listaClientes[id_cliente].startCortarCabelo();

        System.out.println("Barbeiro guia o cliente para sua cadeira, e comeca a cortar seu cabelo.");

        //Muda status do barbeiro para cortando cabelo.
        this.status = 2;

        System.out.println("\nCadeira #" + old_cadeira + " agora esta: " + listaCadeiras[old_cadeira].getTypeStatus());
        System.out.println("Cliente #" + id_cliente + " agora esta: " + listaClientes[id_cliente].getTypeStatus() + " na cadeira #" + listaClientes[id_cliente].getId_cadeira_usada());
        System.out.println("Barbeiro agora esta " + getTypeStatus() + " do cliente #" + id_cliente + " na cadeira #" + listaClientes[id_cliente].getId_cadeira_usada());
    }

    private int verificar_cadeiras(Cadeira[] listaCadeiras, Cliente[] listaClientes){
        boolean cliente_esperando = false;
        int id_cliente_esperou_mais = -1;
        int tempo_esperando = 0;
        int temp_id_cliente;

        System.out.println("Barbeiro verifica as cadeiras de espera:\n");
        //A cadeira do barbero esta sendo ocupada pelo barbeiro, logo ignora cadeira id 0 para olhar somente as cadeiras de espera.
        for (int i = 1; i < listaCadeiras.length; i++)
        {
            if (listaCadeiras[i].getStatus() == 2){
                //Cadeira status 2 == ocupado, tem clientes esperando.

                cliente_esperando = true;
                temp_id_cliente = listaCadeiras[i].getId_cliente();

                System.out.println("Cadeira #" + i + " - " + listaCadeiras[i].getTypeStatus() + " - Cliente #" + temp_id_cliente + " aguardou " + listaClientes[temp_id_cliente].getTempo_esperando() + " horas.");

                //Se tiver clientes esperando, acha qual deles esperou por mais tempo.
                if (listaClientes[temp_id_cliente].getTempo_esperando() > tempo_esperando || id_cliente_esperou_mais == -1 )
                {
                    tempo_esperando = listaClientes[temp_id_cliente].getTempo_esperando();
                    id_cliente_esperou_mais = temp_id_cliente;
                }
            } else {
                System.out.println("Cadeira #" + i + " - " + listaCadeiras[i].getTypeStatus());
            }
        }

        if (cliente_esperando) {
            return id_cliente_esperou_mais;
        } else {
            return -1;
        }
    }

    //Barbeiro senta na cadeira de barbeiro, e comeca a dormir.
    private void tentar_dormir(Cadeira[] listaCadeiras){
        listaCadeiras[0].setStatus(2);
        listaCadeiras[0].setId_cliente(-1);
        this.status = 1;

        System.out.println("\nBarbeiro esta desocupado, e todas as cadeiras estao vazias.");
        System.out.println("Barbeiro muda status para: " + getTypeStatus());
    }

    public String getTypeStatus(){
        switch(this.status) {
            case 1:
                return "Dormindo";
            case 2:
                return "Cortando Cabelo";
            default:
                return "Desconhecido";
        }
    }
}
