package sp.senai.br.android_todo.model;

public enum Situacao {

    ANDAMENTO {
        @Override
        public String toString() {
            return "Em Andamento";
        }
    },
    FINALIZADA {
        @Override
        public String toString() {
            return "Finalizada";
        }
    }

}
