package fydziama.in.ua.dao;

import fydziama.in.ua.entity.Banner;
import fydziama.in.ua.entity.BannerType;

import java.util.List;


public interface BannerDao extends  GeneralDao<Banner> {

    List<Banner> getBannerWithType(BannerType type);
}
