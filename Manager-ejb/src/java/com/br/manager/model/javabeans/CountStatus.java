package com.br.manager.model.javabeans;

/**
 *
 * @author TPicelli
 */
public class CountStatus{
        private Tarefa.Status Status; private int Count; 

        public CountStatus(Tarefa.Status Status, int Count) {
            this.Status = Status;
            this.Count = Count;
        }

        public Tarefa.Status getStatus() {
            return Status;
        }

        public void setStatus(Tarefa.Status Status) {
            this.Status = Status;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

    }