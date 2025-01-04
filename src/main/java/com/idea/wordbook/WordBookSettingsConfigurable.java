package com.idea.wordbook;

import com.intellij.openapi.options.Configurable;
import javax.swing.*;

public class WordBookSettingsConfigurable implements Configurable {

    private WordBookConfigForm configForm;

    public WordBookSettingsConfigurable() {
        configForm = new WordBookConfigForm();
    }

    @Override
    public String getDisplayName() {
        return "WordBook Review Settings";  // 显示在设置页面的名称
    }

    @Override
    public JComponent createComponent() {
        return configForm.getMainPanel();
    }

    @Override
    public boolean isModified() {
        // 如果配置已被修改，返回 true
        WordBookSettings settings = WordBookSettings.getInstance();
        return settings.getInterval() != configForm.getInterval() ||
                settings.getQuantity() != configForm.getQuantity() ||
                !settings.getAlgorithm().equals(configForm.getAlgorithm());
    }

    @Override
    public void apply() {
        // 保存配置
        WordBookSettings settings = WordBookSettings.getInstance();
        settings.setInterval(configForm.getInterval());
        settings.setQuantity(configForm.getQuantity());
        settings.setAlgorithm(configForm.getAlgorithm());
    }

    @Override
    public void reset() {
        // 重置配置为当前的设置
        WordBookSettings settings = WordBookSettings.getInstance();
        configForm.setInterval(settings.getInterval());
        configForm.setQuantity(settings.getQuantity());
        configForm.setAlgorithm(settings.getAlgorithm());
    }
}

