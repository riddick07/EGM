package com.gipermarket.dao.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> mail;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, Date> registrationDate;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, Credentials> credentials;

}

