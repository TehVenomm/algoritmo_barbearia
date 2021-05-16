//Trabalho problema do barbeiro - Sistemas Operacionais
//Gabriel Braz e Santos - 260569

public class Cadeira {

    // status 1 - Disponivel
    // status 2 - Ocupado
    private int status = 1;

    // tipo 1 - Cadeira do barbeiro
    // tipo 2 - Cadeira de espera
    private int tipo;
    private int id_cliente = -1;

    Cadeira(int tipo){
        this.tipo = tipo;
    }

    public int getId_cliente(){
        return this.id_cliente;
    }

    public void setId_cliente(int id_cliente){
        this.id_cliente = id_cliente;
    }

    public int getStatus()
    {
        return status;
    }

    public String getTypeStatus(){
        switch(this.status) {
            case 1:
                return "Disponivel";
            case 2:
                return "Ocupada";
            default:
                return "Desconhecido";
        }
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getTipo()
    {
        return tipo;
    }

    public String getTypeTipo(){
        switch(this.tipo) {
            case 1:
                return "Cadeira do barbeiro";
            case 2:
                return "Cadeira de espera";
            default:
                return "Desconhecido";
        }
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }
}