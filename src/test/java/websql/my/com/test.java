package websql.my.com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

	public static void main(String[] args) {

		final Logger logger = LoggerFactory.getLogger(HomeController.class);

		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("context.xml");
		SQLiteDAO sqLiteDAO = (SQLiteDAO) ac.getBean("sqliteDAO");
		// System.out.println(sqLiteDAO.getRecordbyID(10).getName());
		logger.debug("clean start");
		sqLiteDAO.clean();
		logger.info("clean OK");

		record rec = new record();

		for (int i = 0; i < 100; i++) {
			rec.setAuthor("author" + i);
			rec.setName("name" + i);

			sqLiteDAO.insert(rec);
		}

		logger.info("insert OK");

	}

}
