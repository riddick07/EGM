package com.gipermarket.dao.bean;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Credentials.class)
public abstract class Credentials_ {

	public static volatile SingularAttribute<Credentials, Long> id;
	public static volatile SingularAttribute<Credentials, Long> userId;
	public static volatile SingularAttribute<Credentials, String> login;
	public static volatile SingularAttribute<Credentials, String> password;

}

