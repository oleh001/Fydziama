package fydziama.in.ua.dao;

import fydziama.in.ua.entity.Faq;

import java.util.List;

public interface FaqDao extends GeneralDao<Faq>  {
    List<Faq> findBySteps();
    List<Faq> findByQuestions();

}
