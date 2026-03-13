package tp.appliSpring.bank.core.service;

import tp.appliSpring.bank.core.model.Operation;

import java.util.List;

public interface ServiceOperation {
    public Operation create(Operation op, String numCpt);
    public List<Operation> searchByCompte(String numCpt);
}
