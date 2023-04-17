package project.service.oderService;

import project.config.Config;
import project.model.oderList.Oder;

import java.util.List;

public class OderServiceIMPL implements IOderService{
    public List<Oder> oderListsAll = new Config<Oder>().readFromFile(Config.PATH_LIST_ODER);
    @Override
    public List<Oder> findAll() {
        return oderListsAll;
    }

    @Override
    public void save(Oder oder) {
        if (findById(oder.getId()) != null) {
            int index = oderListsAll.indexOf(findById(oder.getId()));
            oderListsAll.set(index, oder);
        } else {
            oderListsAll.add(oder);
        }
        new Config<Oder>().writeToFile(Config.PATH_LIST_ODER,oderListsAll);
    }

    @Override
    public Oder findById(int id) {
        for (int i = 0; i < oderListsAll.size(); i++) {
            if (oderListsAll.get(i).getId() == id) {
                return oderListsAll.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
