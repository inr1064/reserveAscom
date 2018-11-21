package a_s.com.co.reserveascom;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * Created by Marlon on 26.03.2015.
 */

/*

* NEVER EVER
*
* DO NOT TOUCH THIS CLASS!!!!!!!!!!!!!!!!!!!!!!!
*
* */
public class MyNumberPicker extends NumberPicker {

    public MyNumberPicker(Context context) {
        super(context);
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        processAttributes(attrs);
    }

    //jhs, NumberPickerLine Colorの変形
    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void processAttributes(AttributeSet attrs) {
        this.setMinValue(attrs.getAttributeIntValue(null, "min", 0));
        this.setMaxValue(attrs.getAttributeIntValue(null, "max", 100));
        this.setValue(attrs.getAttributeIntValue(null, "initial", (this.getMaxValue() - this.getMinValue()) / 2));
        setDividerColor(MyNumberPicker.this, Color.rgb(78,205,196)); //jhs, NumberPickerLine Colorの変形

    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        processAttributes(attrs);
    }

}