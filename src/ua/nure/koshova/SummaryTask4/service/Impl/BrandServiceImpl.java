package ua.nure.koshova.SummaryTask4.service.Impl;

import ua.nure.koshova.SummaryTask4.db.dao.BrandDao;
import ua.nure.koshova.SummaryTask4.db.dao.impl.BrandDaoImpl;
import ua.nure.koshova.SummaryTask4.db.entity.Brand;
import ua.nure.koshova.SummaryTask4.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService{

    private BrandDao brandDao = BrandDaoImpl.getInstance();

    public List<Brand> getBrandList() {
        return brandDao.findAllBrands();
    }

    public Brand getBrandById(Long id){
        return brandDao.getBrandById(id);
    }
    public Long getBrandByName(String name){
        return brandDao.getBrandByName(name);
    }
}
