package View.labels;

import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LabelDate {
    static LocalDateTime ldt = LocalDateTime.now();
    public static String currentDate = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.ENGLISH).format(ldt);
    public static Label date = new Label("Choosen date " + currentDate);
}
