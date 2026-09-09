package estudantes.entidades;

import professor.entidades.*;

public class Superprocesso {
    public boolean ativo;

    public int total_paginas;
    public boolean apenas_atas;
    public boolean tem_diploma;
    public String[] tipo_atestado;
    public String[] destinatario;
    
    public Processo processo;
    
    public Superprocesso(Processo processo) {
        this.ativo = true;
        this.total_paginas = 0;
        this.apenas_atas = false;
        this.tem_diploma = false;
        this.tipo_atestado =  null;
        this.destinatario = null;
        this.processo = processo;
    }
}
