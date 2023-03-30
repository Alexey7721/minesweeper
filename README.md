![# __Minesweeper__](icons/label.png)

## __Описание__
Приложение представляет собой возможность сыграть в сапёра.
<br>
### Представленно две версии отображения:
- В окне;
- Консольная внрсия(нужно указывать координаты);
  <br>

В [Main](https://github.com/Alexey7721/minesweeper/blob/master/src/com/company/Main.java) можно выбрать тип отображения, положив во [IView](https://github.com/Alexey7721/minesweeper/blob/master/src/com/company/views/interfaces/IView.java) [SwingView](https://github.com/Alexey7721/minesweeper/blob/master/src/com/company/views/swing/SwingView.java) или [ViewConsole](https://github.com/Alexey7721/minesweeper/blob/master/src/com/company/views/console/ViewConsole.java).
```
    public static void main(String[] args) {

        Model model = new Model();
        IView view = new SwingView();
//        IView view = new ViewConsole();

        Controller controller = new Controller(view, model);
        controller.start();

        int i = 6;
        short a = (short) i;

        Short a1 = a;
        int i1 = a;
    }
```

## __Языки и инструменты__

[![IntelliJ IDEA](icons/intellij-idea-48.png)](https://www.jetbrains.com/idea/)
[![Java](icons/java-coffee-cup-48.png)](https://www.java.com/ru/)
[![Visual studio code](icons/visual-studio-code-2019-48.png)](https://code.visualstudio.com/)
<br>

## __Установка и настойка__
Отройте проект в среде разработки.





