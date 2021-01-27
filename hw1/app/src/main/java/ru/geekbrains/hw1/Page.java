package ru.geekbrains.hw1;

public enum Page {
    FIRST(0, R.id.menuPage1),
    SECOND(1, R.id.menuPage2),
    THIRD(2, R.id.menuPage3);

    static Page byPageNum(int pageNum) {
        return Page.values()[pageNum];
    }

    static Page byMenuId(int menuId) {
        for (Page task : Page.values()) {
            if (task.menuId == menuId) {
                return task;
            }
        }
        return null;
    }

    final int pageNum;

    final int menuId;

    Page(int pageNum, int menuId) {
        this.pageNum = pageNum;
        this.menuId = menuId;
    }

}
