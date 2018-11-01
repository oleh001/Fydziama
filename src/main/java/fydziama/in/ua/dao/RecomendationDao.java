package fydziama.in.ua.dao;

import fydziama.in.ua.entity.Recomendation;

import java.util.List;

public interface RecomendationDao extends GeneralDao<Recomendation>  {

    List<Recomendation> findByVisibilityEquals();
}
