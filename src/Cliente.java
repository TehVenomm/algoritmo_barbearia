//Trabalho problema do barbeiro - Sistemas Operacionais
//Gabriel Braz e Santos - 260569

import java.util.Random;

public class Cliente
{
    // status 1 - Fora da loja
    // status 2 - Sentado
    // status 3 - Cortando cabelo
    private int status = 1;
    private int tempo_esperando;
    private int id_cadeira_usada;
    private int id;

    Cliente(int id){
        this.id = id;
    }

    public int getTempo_esperando (){
        return this.tempo_esperando;
    }

    public int getId_cadeira_usada(){
        return this.id_cadeira_usada;
    }

    public void startCortarCabelo(){
        this.status = 3;
        this.tempo_esperando = 0;

        //Cadeira do barbeiro sempre e id = 0.
        this.id_cadeira_usada = 0;
    }

    public String getTypeStatus(){
        switch(this.status) {
            case 1:
                return "Fora da loja";
            case 2:
                return "Sentado";
            case 3:
                return "Cortando cabelo";
            default:
                return "Desconhecido";
        }
    }

    private boolean decisao_entrar_na_loja(){
        Random rand = new Random();
        double chance = rand.nextDouble();

        if (chance <= 0.4){
            System.out.println("Cliente #" + this.id + " decidiu ir no barbeiro agora.");
            return true;
        } else {
            System.out.println("Cliente #" + this.id + " decidiu nao ir no barbeiro agora.");
            return false;
        }
    }

    private int verificar_cadeiras(Cadeira[] listaCadeiras){
        boolean cadeira_vaga = false;
        int id_cadeira_vaga = -1;

        System.out.println("Cliente #" + id + " procura uma cadeira vazia.\n");
        for (int i = 1; i < listaCadeiras.length; i++)
        {
            if (listaCadeiras[i].getStatus() == 1){
                cadeira_vaga = true;
                id_cadeira_vaga = i;

                System.out.println("Cadeira #" + i + " - Esta " + listaCadeiras[i].getTypeStatus());

                break;
            }
        }

        if (cadeira_vaga) {
            return id_cadeira_vaga;
        } else {
            return -1;
        }
    }

    private void sentar_em_cadeira_vaga(Cadeira[] listaCadeiras, int id_cadeira_livre){
        System.out.println("Cliente #" + this.id + " decide sentar na cadeira #" + id_cadeira_livre);

        this.id_cadeira_usada = id_cadeira_livre;
        this.status = 2;
        listaCadeiras[id_cadeira_livre].setStatus(2);
        listaCadeiras[id_cadeira_livre].setId_cliente(this.id);

        System.out.println("\nCadeira #" + this.id_cadeira_usada + " agora esta: " + listaCadeiras[this.id_cadeira_usada].getTypeStatus());
        System.out.println("Cliente #" + this.id + " agora esta: " + getTypeStatus() + " na cadeira #" + this.id_cadeira_usada);

    }

    private void sair_da_loja_barbeiro_done(Cadeira[] listaCadeiras){
        System.out.println("Cliente #" + this.id + " esta satisfeito com o corte e decide sair.");

        listaCadeiras[this.id_cadeira_usada].setStatus(1);
        listaCadeiras[this.id_cadeira_usada].setId_cliente(-1);
        this.id_cadeira_usada = -1;
        this.status = 1;

        System.out.println("\nCliente #" + this.id + " - Status: " + getTypeStatus());
    }

    private void continuar_esperando(){
        this.tempo_esperando++;
        System.out.println("Cliente #" + this.id + " continua " + getTypeStatus() + " - Esperou por " + this.tempo_esperando + " horas.");
    }

    public void decisionLogic(Cadeira[] listaCadeiras){
        switch (status){
            case 1:
                if (decisao_entrar_na_loja()){
                    int id_cadeira = verificar_cadeiras(listaCadeiras);

                    if (id_cadeira == -1){
                        System.out.println("\nCliente #" + this.id + " viu que nao tinha espaco e saiu.");

                        System.out.println("Cliente #" + this.id + " - Status: " + getTypeStatus());
                    } else {
                        sentar_em_cadeira_vaga(listaCadeiras, id_cadeira);
                    }

                } else {
                    System.out.println("\nCliente #" + this.id + " - Status: " + getTypeStatus());
                }
                break;
            case 2:
                continuar_esperando();
                break;
            case 3:
                sair_da_loja_barbeiro_done(listaCadeiras);
                break;

        }

        System.out.println("----------");
    }
}
