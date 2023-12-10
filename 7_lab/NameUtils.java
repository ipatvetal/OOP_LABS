    import javax.swing.DefaultListModel;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.List;

    public class NameUtils {

        public static void shuffleNames(DefaultListModel<String> listModel, String[] maleNames, String[] femaleNames) {
            String[] allNames = Arrays.copyOf(maleNames, maleNames.length + femaleNames.length);
            System.arraycopy(femaleNames, 0, allNames, maleNames.length, femaleNames.length);
            List<String> shuffledNames = Arrays.asList(allNames);
            Collections.shuffle(shuffledNames);
            shuffledNames.forEach(listModel::addElement);
        }
    }
