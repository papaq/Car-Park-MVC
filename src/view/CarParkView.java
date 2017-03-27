package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class CarParkView extends JFrame{

    public  final String LABEL_BRAND            = "label.brand";
    private final String LABEL_CAR_TYPE         = "label.carType";
    public  final String LABEL_FUEL_EFFICIENCY  = "label.fuelEfficiency";
    private final String LABEL_FUEL_TYPE        = "label.fuelType";
    public  final String LABEL_MAX_SPEED        = "label.maxSpeed";
    public  final String LABEL_MODEL            = "label.model";
    public  final String LABEL_PRICE            = "label.price";
    public  final String LABEL_YEAR             = "label.year";
    private final String LABEL_SORT_BY          = "label.sortBy";
    private final String LABEL_SHOW_FROM_RANGE  = "label.showRange";
    public  final String LABEL_SUM_PRICE        = "label.sumPrice";

    private final String BUTTON_ADD     = "button.add";
    private final String BUTTON_SORT    = "button.sort";
    private final String BUTTON_SHOW    = "button.show";

    public  final String SORT_YEAR      = "sort.year";
    public  final String SORT_PRICE     = "sort.price";
    public  final String SORT_MAX_SPEED = "sort.speed";
    public  final String SORT_FUEL_EFF  = "sort.fEff";

    public  final String TYPE_ELECTRIC  = "type.electric";
    public  final String TYPE_PP        = "type.patrol";

    public  final String PETROL_BENZINE = "petrol.benzine";
    public  final String PETROL_DIESEL  = "petrol.diesel";
    public  final String PETROL_GAS     = "petrol.gas";

    public  final String CAR             = "car";
    private final String MISTAKE_MESSAGE = "mistake";
    private final String ERROR           = "error";
    public  final String RANGE_FROM      = "range.from";
    public  final String RANGE_TO        = "range.to";

    private final String COUNT_PRICE = "sum.price";


    private JPanel              layoutPanel;
    private CardLayout          cardLayout;
    private JComboBox<String>   comboBoxLanguages;
    private JButton             buttonSelectLanguage;
    private JTextArea           output;

    private JLabel              labelFuelType;
    private JLabel              labelFuelEfficiency;

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
    private JComboBox<String> comboBoxSortType;

    private JButton buttonAddCar;
    private JButton buttonSortCars;
    private JButton buttonCarsRange;
    private JButton buttonCountParkPrice;

    private ResourceBundle mainBundle;

    public CarParkView() {

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setSize(800,400);
        this.setMinimumSize(new Dimension(800,400));
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

        buttonAddCar = new JButton();
        buttonSortCars = new JButton();
        buttonCarsRange = new JButton();
        buttonCountParkPrice = new JButton();
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
        inputs.setLayout(new GridBagLayout());

        brand   = new JTextField(12);
        model   = new JTextField(12);
        year    = new JTextField(3);
        price   = new JTextField(5);
        maxSpeed = new JTextField(3);
        comboBoxCarType = new JComboBox<>(new String[]{
                mainBundle.getString(TYPE_PP),
                mainBundle.getString(TYPE_ELECTRIC)});
        comboBoxCarType.setPreferredSize(new Dimension(110, 20));
        comboBoxFuelType = new JComboBox<>(new String[]{
                mainBundle.getString(PETROL_BENZINE),
                mainBundle.getString(PETROL_DIESEL),
                mainBundle.getString(PETROL_GAS)});
        comboBoxFuelType.setPreferredSize(new Dimension(110, 20));
        fuelEfficiency = new JTextField(4);

        comboBoxSortType = new JComboBox<>(new String[]{
                mainBundle.getString(SORT_YEAR),
                mainBundle.getString(SORT_PRICE),
                mainBundle.getString(SORT_MAX_SPEED),
                mainBundle.getString(SORT_FUEL_EFF)});
        comboBoxSortType.setPreferredSize(new Dimension(110, 20));

        rangeFrom   = new JTextField(4);
        rangeTo     = new JTextField(4);


        buttonAddCar.setText(mainBundle.getString(BUTTON_ADD));
        buttonAddCar.setPreferredSize(new Dimension(80, 20));
        buttonSortCars.setText(mainBundle.getString(BUTTON_SORT));
        buttonSortCars.setPreferredSize(new Dimension(110, 20));
        buttonCarsRange.setText(mainBundle.getString(BUTTON_SHOW));
        buttonCarsRange.setPreferredSize(new Dimension(110, 20));
        buttonCountParkPrice.setText(mainBundle.getString(COUNT_PRICE));
        buttonCountParkPrice.setPreferredSize(new Dimension(110, 20));

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
        gbc.gridy++;
        inputs.add(new JLabel(mainBundle.getString(LABEL_SUM_PRICE)), gbc);


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
        gbc.weightx = 1;
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

        gbc.gridy++;
        inputs.add(buttonCountParkPrice, gbc);


        JPanel mainPanel = new JPanel(new GridLayout(1,2));

        mainPanel.add(inputs);
        mainPanel.add(new JScrollPane(output));

        layoutPanel.add(mainPanel, "card2");

        setComboCarTypeItemListener();
    }

    private void setComboCarTypeItemListener() {

        comboBoxCarType.addItemListener(e -> {

            if (e.getStateChange() == ItemEvent.SELECTED) {

                if (getComboCarTypeSelected().equals(mainBundle.getString(TYPE_ELECTRIC))) {

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

    public void setButtonParkPrice(ActionListener listener) {

        buttonCountParkPrice.addActionListener(listener);
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

    public String getComboSortTypeSelected() {

        return comboBoxSortType.getSelectedItem().toString();
    }

    public String getRangeFrom() {

        return rangeFrom.getText();
    }

    public String getRangeTo() {

        return rangeTo.getText();
    }


    public void showErrorMessage(String message) {

        JOptionPane.showMessageDialog(
                this,
                mainBundle.getString(MISTAKE_MESSAGE).concat(mainBundle.getString(message)).trim().concat("!"),
                mainBundle.getString(ERROR).concat("!"), JOptionPane.ERROR_MESSAGE);
    }

    public boolean constStringEqualsBundle(String constString, String bundleString) {

        return constString.equals(mainBundle.getString(bundleString));
    }

    public String getLocale(String name) {

        try {

            return mainBundle.getString(name);
        }
        catch (Exception e) {

            return "";
        }
    }
}
