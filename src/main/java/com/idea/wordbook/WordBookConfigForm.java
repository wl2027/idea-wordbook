package com.idea.wordbook;


import javax.swing.*;

public class WordBookConfigForm {
    private JPanel mainPanel;
    private JTextField intervalField;
    private JTextField quantityField;
    private JComboBox<String> algorithmComboBox;

    public WordBookConfigForm() {
        // 创建 mainPanel，并添加控件
        mainPanel = new JPanel();

        // 初始化控件
        intervalField = new JTextField(10);
        quantityField = new JTextField(10);
        algorithmComboBox = new JComboBox<>(new String[]{"Random", "Ebbinghaus"});

        // 添加控件到 mainPanel
        mainPanel.add(new JLabel("Interval (minutes):"));
        mainPanel.add(intervalField);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(quantityField);
        mainPanel.add(new JLabel("Algorithm:"));
        mainPanel.add(algorithmComboBox);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    // 获取和设置配置项的方法
    public int getInterval() {
        return Integer.parseInt(intervalField.getText());
    }

    public void setInterval(int interval) {
        intervalField.setText(String.valueOf(interval));
    }

    public int getQuantity() {
        return Integer.parseInt(quantityField.getText());
    }

    public void setQuantity(int quantity) {
        quantityField.setText(String.valueOf(quantity));
    }

    public String getAlgorithm() {
        return (String) algorithmComboBox.getSelectedItem();
    }

    public void setAlgorithm(String algorithm) {
        algorithmComboBox.setSelectedItem(algorithm);
    }
}

