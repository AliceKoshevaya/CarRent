package ua.nure.koshova.finalProject.view.util.myJSTL;

import ua.nure.koshova.finalProject.db.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiscountCalculation extends TagSupport{

//        private User discont;
//
//        public void setDiscont(User discont) {
//            this.discont = discont;
//        }
//
//        @Override
//        public int doStartTag() throws JspException {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(abonentBill.getActivationDate());
//            calendar.add(Calendar.MONTH, 1);
//            java.util.Date date = calendar.getTime();
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//
//            } catch (IOException e) {
//                throw new JspException("Can't execute custom tag", e);
//            }
//            return SKIP_BODY;
//        }
//
//        @Override
//        public int doEndTag() throws JspException {
//            return SKIP_PAGE;
//        }
//    }
}
