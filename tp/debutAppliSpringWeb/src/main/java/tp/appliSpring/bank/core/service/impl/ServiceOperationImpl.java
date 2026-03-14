package tp.appliSpring.bank.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.bank.converter.MyBankGenericMapper;
import tp.appliSpring.bank.core.model.Operation;
import tp.appliSpring.bank.core.service.ServiceOperation;
import tp.appliSpring.bank.persistence.entity.CompteEntity;
import tp.appliSpring.bank.persistence.entity.OperationEntity;
import tp.appliSpring.bank.persistence.repository.CompteRepository;
import tp.appliSpring.bank.persistence.repository.OperationRepository;

import java.util.List;

@Service
@Transactional
public class ServiceOperationImpl implements ServiceOperation {

    @Autowired
    private MyBankGenericMapper myBankGenericMapper;

    @Autowired
    private CompteRepository daoCompte;

    @Autowired
    private OperationRepository daoOperation;

    @Override
    public Operation create(Operation op, String numCpt) {
        Long numCptLong = Long.parseLong(numCpt);
        OperationEntity opEntity = myBankGenericMapper.map(op,OperationEntity.class);
        CompteEntity compteEntity = daoCompte.findById(numCptLong).get();
        opEntity.setCompte(compteEntity);
        OperationEntity savedOpEntity = daoOperation.save(opEntity);
        op.setNumero(String.valueOf(savedOpEntity.getNumero()));
        return op;
    }

    @Transactional()
    public List<Operation> searchByCompte(String numCpt) {
        Long numCptLong = Long.parseLong(numCpt);
        //CompteEntity compteEntity = daoCompte.findWithOperations(numCptLong);
        CompteEntity compteEntity = daoCompte.findById(numCptLong).get();
        compteEntity.getOperations().size();// boucle interne automatique (lazy loading différé)
        return myBankGenericMapper.map(compteEntity.getOperations(),Operation.class);
    }
}
