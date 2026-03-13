package tp.appliSpring.bank.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.bank.converter.MyBankGenericMapper;
import tp.appliSpring.bank.core.model.Client;
import tp.appliSpring.bank.core.service.ServiceClient;
import tp.appliSpring.bank.persistence.entity.ClientEntity;
import tp.appliSpring.bank.persistence.repository.ClientRepository;
import tp.appliSpring.generic.service.GenericServiceDirectImpl;


@Service //@Component de type Service
@Transactional
public class ServiceClientImpl extends GenericServiceDirectImpl<Client,ClientEntity,Long> implements ServiceClient {

	private ClientRepository daoClient;

	private PasswordEncoder passwordEncoder;

	private MyBankGenericMapper myBankGenericMapper;

	@Override
	public Client create(Client obj) {
		obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		return super.create(obj);
	}

	@Autowired
	public ServiceClientImpl(ClientRepository daoClient, PasswordEncoder passwordEncoder, MyBankGenericMapper myBankGenericMapper){
		super(Client.class, ClientEntity.class,daoClient,myBankGenericMapper);
		this.myBankGenericMapper=myBankGenericMapper;
		this.daoClient=daoClient;
		this.passwordEncoder=passwordEncoder;
	}

	@Override
	public  Client searchByIdWithAccounts(long numeroCli) {
		ClientEntity clientEntity = daoClient.findWithAccountById(numeroCli);
		return myBankGenericMapper.map(clientEntity,Client.class);
	}

}
