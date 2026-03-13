package tp.appliSpring.bank.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.bank.converter.MyBankGenericMapper;
import tp.appliSpring.bank.core.model.Task;
import tp.appliSpring.bank.core.service.ServiceTask;
import tp.appliSpring.bank.persistence.entity.TaskEntity;
import tp.appliSpring.bank.persistence.repository.TaskRepository;
import tp.appliSpring.generic.service.GenericServiceDirectImpl;

@Service
@Transactional
public class ServiceTaskImpl extends GenericServiceDirectImpl<Task, TaskEntity,Long> implements ServiceTask {

    private MyBankGenericMapper myBankGenericMapper;

    private TaskRepository daoTask;

    @Autowired
    public ServiceTaskImpl(TaskRepository daoTask, MyBankGenericMapper myBankGenericMapper){
        super(Task.class,TaskEntity.class,daoTask,myBankGenericMapper);
        this.daoTask=daoTask;
        this.myBankGenericMapper =myBankGenericMapper;
    }
}
