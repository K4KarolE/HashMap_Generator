package src;

public class Data {
    
    public int WIDGET_WIDTH = 180;
    public int WIDGET_HEIGHT = 28;
    
    public int BASE_X = 40;
    public int BASE_Y = 30;
    public int X_DIFF = 160;
    public int Y_DIFF = WIDGET_HEIGHT + 5;
    
    public int getPosY(double x) {
        double i = BASE_Y + Y_DIFF * x;
        return (int)i;
    }
    
    
    public int keyAndValueWidgetsWidth = WIDGET_WIDTH + 40;
    public int keysAndValuesWidgetPosXdiff = 40;
    public int _xPosCompensate = 16;
    public int FRAME_WIDTH = BASE_X * 2 + keyAndValueWidgetsWidth * 2 + keysAndValuesWidgetPosXdiff + _xPosCompensate;
    public int FRAME_HEIGHT = 700;
    public int FRAME_POS_X = 800;
    public int FRAME_POS_Y = 600;
}

