package br.com.tts.gincananoacampamento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int qtdAlunosCirculo;

        qtdAlunosCirculo = Integer.parseInt(st.nextToken());

        while(qtdAlunosCirculo != 0) {

            if ((qtdAlunosCirculo < 1) || (qtdAlunosCirculo > 100)) {
                return;
            }

            LinkedList<Aluno> alunos = new LinkedList<>();

            for (int i = 0; i < qtdAlunosCirculo; i++) {
                String dado = br.readLine();
                String[] dados = dado.split(" ");

                Aluno aluno = new Aluno();

                if (dados[0].length() > 30){
                    return;
                }

                aluno.setNome(dados[0]);
                aluno.setValor(Integer.parseInt(dados[1]));

                alunos.add(aluno);
            }

            gincana(alunos,null);

            Aluno vencedor = new Aluno();

            for(Aluno aluno : alunos){
                if(aluno.getSaiu() == false){
                    vencedor = aluno;
                    break;
                }
            }

            System.out.println("Vencedor(a): " + vencedor.getNome());

            st = new StringTokenizer(br.readLine());
            qtdAlunosCirculo = Integer.parseInt(st.nextToken());

        }

    }

    private static void gincana(LinkedList<Aluno> alunos, Aluno alunoSaiuPorUltimo) {
        int value_original;
        int value;
        Aluno iniciaAluno;
        Aluno proximoAluno;

        if(restantes(alunos) <= 1){
            return;
        }

        if (alunoSaiuPorUltimo == null) {
            iniciaAluno = alunos.get(0);
        }else{
            iniciaAluno = alunoSaiuPorUltimo;
        }

        value = iniciaAluno.getValor();

        int index = alunos.indexOf(iniciaAluno);

        if (alunoSaiuPorUltimo != null) {
            int saiu = alunos.indexOf(alunoSaiuPorUltimo);
            alunos.get(saiu).setSaiu(true);
        }

        value_original = value;

        for (int i = 0; i < value; i++) {
            if( value_original % 2 == 0)
                index--;
            else
                index++;

            if (index > ( alunos.size() - 1)){
               index = 0;
            }
            if (index < 0){
                index = alunos.size() - 1;
            }

            while (alunos.get(index).getSaiu()){
                if( value_original % 2 == 0)
                    index--;
                else
                    index++;

                if (index > ( alunos.size() - 1)){
                    index = 0;
                }
                if (index < 0){
                    index = alunos.size() - 1;
                }
            }

        }

        alunoSaiuPorUltimo = alunos.get(index);
        gincana(alunos,alunoSaiuPorUltimo);

    }

    private static int restantes(LinkedList<Aluno> alunos) {
        int count = 0;
        for (Aluno aluno : alunos){
            if (aluno.getSaiu() == false){
                count++;
            }
        }
        return count;
    }

    private static class Aluno {
        private String nome;
        private int valor;
        private boolean saiu;

        public void Aluno(){
            this.saiu = false;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public boolean getSaiu() {
            return saiu;
        }

        public void setSaiu(boolean saiu) {
            this.saiu = saiu;
        }

    }

}
