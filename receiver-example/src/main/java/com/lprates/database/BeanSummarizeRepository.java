package com.lprates.database;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lprates.bean.DummyBean;

public class BeanSummarizeRepository {

	private static SessionFactory sessionFactory;

	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public void save(Collection<DummyBean> dummyBeans) {
		Session session = sessionFactory.openSession();

		Map<Integer, Long> aggregatedDummies = new HashMap<>();

		for (DummyBean dummyBean : dummyBeans) {
			aggregatedDummies.put(dummyBean.getId(),
					aggregatedDummies.getOrDefault(dummyBean.getId(), 0L) + dummyBean.getValue());
		}

		session.beginTransaction();

		try {
			aggregatedDummies.keySet().stream().forEach(i -> {
				BeanSummarize beanSummary = (BeanSummarize) session.get(BeanSummarize.class, i);

				if (beanSummary == null) {
					beanSummary = new BeanSummarize(i, aggregatedDummies.get(i));
//					System.out.println("Saving a bean summary: " + beanSummary);
//					session.save(beanSummary);
				} else {
					beanSummary.setSum(beanSummary.getSum() + aggregatedDummies.get(i));
//					System.out.println("Updating a bean summary: " + beanSummary);
//					session.update(beanSummary);
				}

				session.saveOrUpdate(beanSummary);
			});

			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
