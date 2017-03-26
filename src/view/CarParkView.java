package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarParkView extends JFrame{

    private final String LABEL_BRAND = "label.brand";
    private final String LABEL_CAR_TYPE = "label.carType";
    private final String LABEL_FUEL_EFFICIENCY = "label.fuelEfficiency";
    private final String LABEL_FUEL_TYPE = "label.fuelType";
    private final String LABEL_MAX_SPEED = "label.maxSpeed";
    private final String LABEL_MODEL = "label.model";
    private final String LABEL_PRICE = "label.price";
    private final String LABEL_YEAR = "label.year";
    private final String LABEL_SORT_BY = "label.sortBy";
    private final String LABEL_SHOW_FROM_RANGE = "label.showRange";

    private final String BUTTON_ADD = "button.add";
    private final String BUTTON_SORT = "button.sort";
    private final String BUTTON_SHOW = "button.show";

    private final String SORT_YEAR = "sort.year";
    private final String SORT_PRICE = "sort.price";
    private final String SORT_MAX_SPEED = "sort.speed";
    private final String SORT_FUEL_EFF = "sort.fEff";

    private final String TYPE_ELECTRIC = "type.electric";
    private final String TYPE_PP = "type.patrol";

    private final String PATROL_BENZINE = "patrol.benzine";
    private final String PATROL_DIESEL = "patrol.diesel";
    private final String PATROL_GAS = "patrol.gas";


    private JPanel layoutPanel;
    private CardLayout cardLayout;
    private JComboBox<String> comboBoxLanguages;
    private JButton buttonSelectLanguage;
    private JTextArea output;

    private JLabel labelFuelType;
    private JLabel labelFuelEfficiency;

    private JTextField brand;
    private JTextField model;
    private JTextField year;
    private JTextField price;
    private JTextField maxSpeed;
    private JTextField fuelEfficiency;

    private JTextField rangeFrom;
    private JTextField rangeTo;

    private JComboBox<String> comboBoxCarType;
    private JComboBox<String> comboBoxFuelType;

    private JButton buttonAddCar;
    private JButton buttonSortCars;
    private JButton buttonCarsRange;

    private ResourceBundle mainBundle;

    public CarParkView() {


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setSize(750,400);
        this.setMinimumSize(new Dimension(750,400));
        this.setTitle("Car park");
        this.setLocationRelativeTo(null);  // to center the app

        JPanel languagePanel = new JPanel();
        comboBoxLanguages = new JComboBox<>(new String[]{"English", "Ukrainian", "Russian"});
        comboBoxLanguages.setPreferredSize(new Dimension(100, 26));
        languagePanel.add(new Label("Select language "), BorderLayout.CENTER);
        languagePanel.add(comboBoxLanguages, BorderLayout.CENTER);
        buttonSelectLanguage = new JButton("Next");
        languagePanel.add(buttonSelectLanguage, BorderLayout.SOUTH);
        setButtonNextListener();


        cardLayout = new CardLayout();
        layoutPanel = new JPanel(cardLayout);

        layoutPanel.add(languagePanel, "card1");

        add(layoutPanel);
    }

    private void setButtonNextListener() {

        buttonSelectLanguage.addActionListener(e -> {

            switch (getLanguageSelected()) {

                case "Ukrainian" :
                    mainBundle = ResourceBundle.getBundle("view.Interface", new Locale("uk_UA"));
                    break;
                case "Russian" :
                    mainBundle = ResourceBundle.getBundle("view.Interface", new Locale("ru_RU"));
                    break;
                default:
                    mainBundle = ResourceBundle.getBundle("view.Interface");
            }

            fillMainPanel();
            cardLayout.next(layoutPanel);
        });
    }

    private void fillMainPanel() {

        output = new JTextArea();
        output.setEditable(false);


        JPanel inputs = new JPanel();
        //inputs.setBackground(new Color(255,255,255));
        inputs.setLayout(new GridBagLayout());

        brand = new JTextField(12);
        model = new JTextField(12);
        year = new JTextField(3);
        price = new JTextField(5);
        maxSpeed = new JTextField(3);
        comboBoxCarType = new JComboBox<>(new String[]{
                mainBundle.getString(TYPE_PP),
                mainBundle.getString(TYPE_ELECTRIC)});
        comboBoxCarType.setPreferredSize(new Dimension(110, 20));
        comboBoxFuelType = new JComboBox<>(new String[]{
                mainBundle.getString(PATROL_BENZINE),
                mainBundle.getString(PATROL_DIESEL),
                mainBundle.getString(PATROL_GAS)});
        comboBoxFuelType.setPreferredSize(new Dimension(110, 20));
        fuelEfficiency = new JTextField(4);

        JComboBox<String> comboBoxSortType = new JComboBox<>(new String[]{
                mainBundle.getString(SORT_YEAR),
                mainBundle.getString(SORT_PRICE),
                mainBundle.getString(SORT_MAX_SPEED),
                mainBundle.getString(SORT_FUEL_EFF)});
        comboBoxSortType.setPreferredSize(new Dimension(110, 20));

        rangeFrom = new JTextField(4);
        rangeTo = new JTextField(4);


        buttonAddCar = new JButton(mainBundle.getString(BUTTON_ADD));
        buttonAddCar.setPreferredSize(new Dimension(80, 20));
        buttonSortCars = new JButton(mainBundle.getString(BUTTON_SORT));
        buttonSortCars.setPreferredSize(new Dimension(110, 20));
        buttonCarsRange = new JButton(mainBundle.getString(BUTTON_SHOW));
        buttonCarsRange.setPreferredSize(new Dimension(110, 20));

        output = new JTextArea();


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor = GridBagConstraints.BASELINE_LEADING;
        gbc.fill = GridBagConstraints.NONE;
        inputs.add(new Label(mainBundle.getString(LABEL_BRAND)), gbc);
        gbc.gridy++;
        inputs.add(new Label(mainBundle.getString(LABEL_MODEL)), gbc);
        gbc.gridy++;
        inputs.add(new Label(mainBundle.getString(LABEL_YEAR)), gbc);
        gbc.gridy++;
        inputs.add(new Label(mainBundle.getString(LABEL_PRICE)), gbc);
        gbc.gridy++;
        inputs.add(new Label(mainBundle.getString(LABEL_MAX_SPEED)), gbc);
        gbc.gridy++;
        inputs.add(new Label(mainBundle.getString(LABEL_CAR_TYPE)), gbc);
        gbc.gridy++;
        labelFuelType = new JLabel(mainBundle.getString(LABEL_FUEL_TYPE));
        inputs.add(labelFuelType, gbc);
        gbc.gridy++;
        labelFuelEfficiency = new JLabel(mainBundle.getString(LABEL_FUEL_EFFICIENCY));
        inputs.add(labelFuelEfficiency, gbc);
        gbc.gridy++;
        inputs.add(buttonAddCar, gbc);

        gbc.gridy++;
        inputs.add(new JLabel(mainBundle.getString(LABEL_SORT_BY)), gbc);
        gbc.gridy++;
        inputs.add(new JLabel(mainBundle.getString(LABEL_SHOW_FROM_RANGE)), gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        inputs.add(brand, gbc);
        gbc.gridy++;
        inputs.add(model, gbc);
        gbc.gridy++;
        inputs.add(year, gbc);
        gbc.gridy++;
        inputs.add(price, gbc);
        gbc.gridy++;
        inputs.add(maxSpeed, gbc);
        gbc.gridy++;
        inputs.add(comboBoxCarType, gbc);
        gbc.gridy++;
        inputs.add(comboBoxFuelType, gbc);
        gbc.gridy++;
        inputs.add(fuelEfficiency, gbc);

        gbc.gridy += 2;
        inputs.add(comboBoxSortType, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx=1;
        inputs.add(buttonSortCars, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.BASELINE_LEADING;

        JPanel panelRange = new JPanel(new GridBagLayout());
        GridBagConstraints gbcRange = new GridBagConstraints();
        gbcRange.gridx = gbcRange.gridy = 0;
        panelRange.add(rangeFrom, gbcRange);
        gbcRange.gridx++;
        panelRange.add(rangeTo, gbcRange);
        inputs.add(panelRange, gbc);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx=1;
        inputs.add(buttonCarsRange, gbc);


        JPanel mainPanel = new JPanel(new GridLayout(1,2));

        mainPanel.add(inputs);
        mainPanel.add(new JScrollPane(output));

        layoutPanel.add(mainPanel, "card2");

        setComboCarTypeItemListener();
    }

    private void setComboCarTypeItemListener() {

        comboBoxCarType.addItemListener(e -> {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                if (getComboCarTypeSelected().equals("Electric")) {

                    labelFuelType.setVisible(false);
                    labelFuelEfficiency.setVisible(false);
                    comboBoxFuelType.setVisible(false);
                    fuelEfficiency.setVisible(false);
                }
                else {

                    labelFuelType.setVisible(true);
                    labelFuelEfficiency.setVisible(true);
                    comboBoxFuelType.setVisible(true);
                    fuelEfficiency.setVisible(true);
                }
            }
        });
    }

    public void setButtonAddCarListener(ActionListener listener) {

        buttonAddCar.addActionListener(listener);
    }

    public void setButtonSortCars(ActionListener listener) {

        buttonSortCars.addActionListener(listener);
    }

    public void setButtonCarsRange(ActionListener listener) {

        buttonCarsRange.addActionListener(listener);
    }

    public void setOutputText(String text) {

        output.setText(text);
    }


    private String getLanguageSelected() {

        return comboBoxLanguages.getSelectedItem().toString();
    }

    public String getBrand() {

        return brand.getText();
    }

    public String getModel() {

        return model.getText();
    }

    public String getYear() {

        return year.getText();
    }

    public String getPrice() {

        return price.getText();
    }

    public String getMaxSpeed() {

        return maxSpeed.getText();
    }

    public String getFuelEfficiency() {

        return fuelEfficiency.getText();
    }

    public String getComboCarTypeSelected() {

        return comboBoxCarType.getSelectedItem().toString();
    }

    public String getComboFuelTypeSelected() {

        return comboBoxFuelType.getSelectedItem().toString();
    }

    public String getRangeFrom() {

        return rangeFrom.getText();
    }

    public String getRangeTo() {

        return rangeTo.getText();
    }
}
