package a_s.com.co.reserveascom;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * Created by Marlon on 26.03.2015.
 */
public class MyNumberPicker extends NumberPicker {

    public MyNumberPicker(Context context) {
        super(context);
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributes(attrs);
    }

    private void processAttributes(AttributeSet attrs) {
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 100));
        this.setValue(attrs.getAttributeIntValue(null, "initial", (this.getMaxValue() - this.getMinValue()) / 2));
    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        processAttributes(attrs);
    }
}