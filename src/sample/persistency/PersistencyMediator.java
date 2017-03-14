package sample.persistency;

import sample.DomainClasses.Drawing;

import java.util.Properties;

/**
 * Created by jasve_29 on 13-Mar-17.
 */
public interface PersistencyMediator {
    public Drawing load(String nameDrawing);
    public Boolean save(Drawing drawing);
    public Boolean init(Properties props); //why isnt this just a constructor?
}
