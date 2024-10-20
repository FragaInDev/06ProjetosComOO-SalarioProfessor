package com.fraga.salarioprofessor;

public class ProfessorTitular extends Professor {
    private int anosInstituicao;
    private double salario;

    public int getAnosInstituicao() {
        return anosInstituicao;
    }

    public void setAnosInstituicao(int anosInstituicao) {
        this.anosInstituicao = anosInstituicao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public double calcSalario() {
        int increments = anosInstituicao / 5;
        return salario * Math.pow(1.05, increments);
    }
}