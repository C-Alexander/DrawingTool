package sample.DomainClasses;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by jasve_29 on 21-Feb-17.
 */
@SuppressWarnings("DefaultFileTemplate")
public class AnchorComparator implements Comparator<DrawingItem>, Serializable {

    @Override
    public int compare(DrawingItem o1, DrawingItem o2) {
        double distance1 = Math.sqrt(Math.pow((o1.getAnchor().getX()-0), 2)
                + Math.pow((o1.getAnchor().getY()-0), 2));
        double distance2 = Math.sqrt(Math.pow((o2.getAnchor().getX()-0), 2)
                + Math.pow((o2.getAnchor().getY()-0), 2));
        if (distance1 > distance2) {
            return 1;
        } else if (distance2 > distance1) {
            return -1;
        }
        return 0;
    }
}
