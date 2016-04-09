package testeditor.gui.question_view.actions;

import testeditor.Test;
import testeditor.question.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Класс-слушатель для события открытия файла
 */
public class CreateQuestionAction extends AbstractAction {

    private JList list;

    public CreateQuestionAction(JList qList){
        list = qList;
        this.putValue (Action.NAME, "<html>" +
                                        "<font color='green' size=+1>" +
                                            "<b>&#10010;&nbsp;&nbsp;&nbsp;</b>" +
                                        "</font>" +
                                        "Создать" +
                                    "</html>"
                       );
        this.putValue(Action.SHORT_DESCRIPTION,"Создать новый тест");
        this.putValue(Action.SMALL_ICON, UIManager.getIcon("FileView.fileIcon"));
    }

    public void actionPerformed(ActionEvent event){
        Test test = Test.getTest();
        Object[] types = {
                              "Выбор",
                              "Короткий ответ",
                              "Верно/Неверно",
                              "Соответствие",
                              "Числовой"
                         };
        String s = (String)JOptionPane.showInputDialog(
                null,
                "",
                "Выберите тип вопроса:",
                JOptionPane.PLAIN_MESSAGE,
                null,
                types,
                "Выбор");

        if ((s != null) && (s.length() > 0)) {
            Question q;
            switch (s) {
                case "Выбор":
                    q = new MultiChoice();
                    break;
                case "Короткий ответ":
                    q = new ShortAnswer();
                    break;
                case "Верно/Неверно":
                    q = new TrueFalse();
                    break;
                case "Соответствие":
                    q = new Matching();
                    break;
                case "Числовой":
                    q = new Numerical();
                    break;
            }
            test.add(q);
            JFrame frame = q.getFrame();
            frame.setVisible(true);
        }
    }
}
