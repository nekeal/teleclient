package gui;

import APIClient.Client;
import studies.FieldOfStudy;
import studies.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudiesGUI {
    private JPanel mainPanel;
    private JComboBox<FieldOfStudy> fieldOfStudiesCBox;
    private JComboBox subjectCBox;
    private JComboBox comboBox3;
    private JTextField studiesName;
    private JTextField studiesSlug;
    private JButton deleteStudiesBtn;
    private JButton saveStudiesBtn;
    private JButton addNewFieldOfStudyBtn;
    private JButton addNewSubjectBtn;
    private JTextField subjectName;
    private JSpinner subjectSemesterSpin;
    private JButton deleteSubjectBtn;
    private JButton saveSubjectBtn;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Studies");
        frame.setContentPane(new StudiesGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public StudiesGUI() {
        ArrayList<FieldOfStudy> fieldOfStudiesList = Client.fetchFieldOfStudy();
        fieldOfStudiesCBox.setModel(new DefaultComboBoxModel(fieldOfStudiesList.toArray()));
        fieldOfStudiesCBox.setSelectedIndex(-1);
        updateFieldOfStudyForm();
        deleteStudiesBtn.setBackground(new Color(244, 67, 54));
        saveStudiesBtn.setBackground(new Color(76, 175, 80));
        saveStudiesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FieldOfStudy selected = getSelectedFieldOfStudy();
                if (selected == null) { //dodajemy nowy element
                    selected = new FieldOfStudy(studiesName.getText(), studiesSlug.getText());
                    fieldOfStudiesList.add(selected);
                    System.out.println(selected.toString());
                } else {
                    selected.save(studiesName.getText(), studiesSlug.getText());
                }
                fieldOfStudiesCBox.removeAllItems();
                fieldOfStudiesCBox.setModel(new DefaultComboBoxModel(fieldOfStudiesList.toArray()));
                fieldOfStudiesCBox.setSelectedItem(selected);
            }
        });
        fieldOfStudiesCBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FieldOfStudy selected = getSelectedFieldOfStudy();
                updateFieldOfStudyForm();
                updateSubjectCBox();
//                subjectCBox.setSelectedIndex(-1);
            }
        });
        addNewFieldOfStudyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fieldOfStudiesCBox.setSelectedIndex(-1);
                updateSubjectCBox();
            }
        });
        deleteStudiesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FieldOfStudy selected = getSelectedFieldOfStudy();
                fieldOfStudiesCBox.removeItem(selected);
                fieldOfStudiesList.remove(selected);
            }
        });
        saveSubjectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (getSelectedFieldOfStudy() == null)
                    return;
                Subject selected = getSelectedSubject();
                String subjectNameFieldValue = subjectName.getText();
                int subjectSemesterFieldValue = (Integer) subjectSemesterSpin.getValue();
                if (selected == null) {
                    selected = new Subject(subjectNameFieldValue, subjectSemesterFieldValue);
                    getSelectedFieldOfStudy().addSubject(selected);
                } else
                    selected.save(subjectNameFieldValue, subjectSemesterFieldValue, getSelectedFieldOfStudy());
                updateSubjectCBox();
            }
        });
        subjectCBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateSubjectForm();
            }
        });
        deleteSubjectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Subject selected = getSelectedSubject();
                subjectCBox.removeItem(selected);
                getSelectedFieldOfStudy().removeSubject(selected);
            }
        });
    }

    private void createUIComponents() {
        fieldOfStudiesCBox = new JComboBox(new DefaultComboBoxModel());
    }

    public void updateFieldOfStudyForm() {
        FieldOfStudy field = getSelectedFieldOfStudy();
        if (field == null) {
            studiesName.setText("");
            studiesSlug.setText("");
        } else {
            studiesName.setText(field.getName());
            studiesSlug.setText(field.getSlug());
        }
    }

    public FieldOfStudy getSelectedFieldOfStudy() {
        return (FieldOfStudy) fieldOfStudiesCBox.getSelectedItem();
    }

    public Subject getSelectedSubject() {
        return (Subject) subjectCBox.getSelectedItem();
    }

    public void updateSubjectCBox() {
        FieldOfStudy selected = getSelectedFieldOfStudy();
        if (selected == null)
            subjectCBox.setModel(new DefaultComboBoxModel());
        else{
            subjectCBox.setModel(new DefaultComboBoxModel(selected.getSubjects().toArray()));
        }
        updateSubjectForm();
    }

    public void updateSubjectForm() {
        FieldOfStudy field = getSelectedFieldOfStudy();
        Subject subject = getSelectedSubject();
        if (field == null) {
            subjectName.setText("");
            subjectSemesterSpin.setValue(0);
        } else {
            subjectName.setText(subject.getName());
            subjectSemesterSpin.setValue(subject.getSemester());
        }
    }
}
