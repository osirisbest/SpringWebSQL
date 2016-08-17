package websql.my.com;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		// ClassPathXmlApplicationContext ac = new
		// ClassPathXmlApplicationContext("context.xml");
		// SQLiteDAO sqLiteDAO = (SQLiteDAO) ac.getBean("dataSource");

		// SQLiteDAO dao = new SQLiteDAO();
		// record rec = sqLiteDAO.getRecordbyID(3209);
		model.addAttribute("serverTime", 252);

		return "home";
	}

}
