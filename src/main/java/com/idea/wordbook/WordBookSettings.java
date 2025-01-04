package com.idea.wordbook;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "WordBookSettings", storages = @Storage("WordBookSettings.xml"))
public class WordBookSettings implements PersistentStateComponent<WordBookSettings> {

    private int interval = 5;  // 默认间隔 5 分钟
    private int quantity = 3;  // 默认显示 3 个单词
    private String algorithm = "Random";  // 默认算法为随机

    // Getter 和 Setter
    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    @Nullable
    @Override
    public WordBookSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull WordBookSettings state) {
        this.interval = state.interval;
        this.quantity = state.quantity;
        this.algorithm = state.algorithm;
    }

    // 获取实例
    public static WordBookSettings getInstance() {
        return ServiceManager.getService(WordBookSettings.class);
    }
}

