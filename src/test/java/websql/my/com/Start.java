package websql.my.com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqLiteDAO = (SQLiteDAO) ac.getBean("sqliteDAO");
		record rec = new record();
		rec.setAuthor("author");
		rec.setName("name");

		sqLiteDAO.insert(rec);

	}

}
