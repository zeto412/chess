package Core;

import java.io.IOException;

public class Controller {

    private Model _model;
    private View _view;

    private static class Holder {

        static final Controller instance = new Controller();
    }

    public static Controller getInstance() {
        return Holder.instance;
    }

    private Controller() {

    }

    public void init(final short width, final short height) {
        _model = Model.getInstance();
        _model.init(width, height);
        _view.init();
    }

    public void start() {
        _view.write(_model.getMessage(Model.Message.GAMESTART_MESSAGE));
        game();
    }

    private void game() {
        boolean finish = false;

        while (!finish) {
            _view.write("Write your move: {char, num}");

            try {
                _model.parseStringWithPosition(_view.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}
