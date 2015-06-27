package Core;

import Core.Exceptions.*;
import Util.Position;

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
        _view = new View();
        _view.init();
    }

    public void start() {
        _view.write(_model.getMessage(Model.Message.MESSAGE_START_GAME));
        game();
    }

    private void game() {
        boolean finish = false;
        boolean currentBlack = false;

        while (!finish) {
            if (currentBlack) {
                _view.write(_model.getMessage(Model.Message.MESSAGE_CURRENT_COLOR_BLACK));
            } else {
                _view.write(_model.getMessage(Model.Message.MESSAGE_CURRENT_COLOR_WHITE));
            }

            _view.write(_model.getMessage(Model.Message.MESSAGE_GET_POSITION));

            Position positionStart = null;

            try {
                positionStart = _model.parseStringWithPosition(_view.getMessage());

            } catch (IOException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_INPUT_POSITION));
            } catch (InputPositionDontHaveSplitterException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_INPUT_POSITION));
            } catch (InputPositionHaveIncorrectValuesException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_INPUT_POSITION));
            }

            _view.write(_model.getMessage(Model.Message.MESSAGE_GET_POSITION));

            Position positionEnd = null;

            try {
                positionEnd = _model.parseStringWithPosition(_view.getMessage());
            } catch (IOException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_INPUT_POSITION));
            } catch (InputPositionDontHaveSplitterException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_INPUT_POSITION));
            } catch (InputPositionHaveIncorrectValuesException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_INPUT_POSITION));
            }

            try {
                _model.move(positionStart, positionEnd);
            } catch (FigureCantMoveException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_FIGURE_CANT_MOVE));
            } catch (FigureIsNullException e) {
                _view.write(_model.getMessage(Model.Message.ERROR_NO_FIGURE));
            } catch (KingCantMoveException e) {
                finish = !finish;
                _view.write(_model.getMessage(Model.Message.MESSAGE_CURRENT_COLOR_LOSE));
            }

            currentBlack = !currentBlack;


        }
    }

}
