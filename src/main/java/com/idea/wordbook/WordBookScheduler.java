package com.idea.wordbook;

import cn.yiiguxing.plugin.translate.wordbook.WordBookItem;
import cn.yiiguxing.plugin.translate.wordbook.WordBookService;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.IconLoader;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public final class WordBookScheduler {

    private Timer timer;
    private boolean isRunning;
    private int interval;
    private int unit = 60*100;
    private int quantity;
    private String algorithm;

    public WordBookScheduler() {
        this.timer = new Timer();
        this.isRunning = false;
    }

    public synchronized void start(int interval, int quantity, String algorithm) {
        if (!isRunning) {
            this.interval = interval;
            this.quantity = quantity;
            this.algorithm = algorithm;
            isRunning = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    WordBookService wordBookService = ServiceManager.getService(WordBookService.class);
                    List<WordBookItem> words = wordBookService.getWords();
                    List<WordBookReviewItem> wordBookReviewItems = words.parallelStream()
                            .map(word -> new WordBookReviewItem(word.getWord(), word.getExplanation()))  // 转换为 WordBookReviewItem
                            .collect(Collectors.toList());
                    List<WordBookReviewItem> selectedWords = selectWords(wordBookReviewItems);
                    showWordsNotification(selectedWords);
                }
            }, interval * unit, interval * unit); // 每 `interval` 分钟触发一次
        }
    }

    public void stop() {
        if (isRunning) {
            timer.cancel();
            isRunning = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    private List<WordBookReviewItem> selectWords(List<WordBookReviewItem> words) {
        if ("Ebbinghaus".equals(algorithm)) {
            //TODO id和本地单词表做映射
            // 使用艾宾浩斯算法选择单词（简化版）
            words.sort(Comparator.comparingInt(WordBookReviewItem::getReviewCount)
                    .thenComparing(WordBookReviewItem::getLastReviewedTime));
        } else {
            // 随机选择
            Collections.shuffle(words);
        }
        return words.subList(0, Math.min(quantity, words.size()));
    }

    private void showWordsNotification(List<WordBookReviewItem> selectedWords) {
        for (WordBookReviewItem item : selectedWords) {
            //Messages.showMessageDialog(ProjectManager.getInstance().getDefaultProject(), item.getWord() + ": " + item.getExplanation(), "Word Review", Messages.getInformationIcon());
            // 格式化时间
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String formattedDate = sdf.format(item.getLastReviewedTime());
//            // 使用 HTML 格式化通知内容
//            String htmlContent = "<html>" +
//                    "<b><span>" + item.getWord() + "</span></b><br>" +
//                    "Explanation: <span>" + item.getExplanation() + "</span><br>" +
////                    "Review Count: <span>" + item.getReviewCount() + "</span><br>" +
////                    "Last Reviewed: <span style='color: #8E44AD;'>" + formattedDate + "</span><br>" +
//                    "</html>";
//
//            // 创建通知
//            Notification notification = new Notification(
//                    "WordBookReview",  // 通知组 ID
//                    "Wordbook Review",  // 通知标题
//                    htmlContent,  // HTML 格式的通知内容
//                    NotificationType.INFORMATION  // 通知类型
//            );
            Notification notification = new Notification(
                    "WordBookReview",  // 通知组 ID
                    item.getWord(),  // 通知标题
                    item.getExplanation(),  // HTML 格式的通知内容
                    NotificationType.INFORMATION  // 通知类型
            );
            notification.setIcon(IconLoader.getIcon("/icons/wordbook.svg", WordBookItem.class));

            // 显示通知
            Notifications.Bus.notify(notification);
            // 更新复习次数
            item.incrementReviewCount();
            item.setLastReviewedTime(new Date());
        }
    }
}

