package tp.appliSpring.generic.model;

import java.io.Serializable;

public interface WithId<ID extends Serializable> {
	
	public ID extractId(); 

}